package fr.eni.projet.encheres.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import fr.eni.projet.encheres.bll.exceptions.BusinessException;
import fr.eni.projet.encheres.bll.service.ArticleService;
import fr.eni.projet.encheres.bll.service.CategorieService;
import fr.eni.projet.encheres.bll.service.RetraitService;
import fr.eni.projet.encheres.bll.service.UtilisateurService;
import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Categorie;
import fr.eni.projet.encheres.bo.Utilisateur;

@Controller
public class EncheresController {

	CategorieService categorieService;
	ArticleService articleService;
	UtilisateurService utilisateurService;
	RetraitService retraitService;

	public EncheresController(CategorieService categorieService, ArticleService articleService,
			UtilisateurService utilisateurService, RetraitService retraitService) {
		super();
		this.categorieService = categorieService;
		this.articleService = articleService;
		this.utilisateurService = utilisateurService;
		this.retraitService = retraitService;
	}

	@GetMapping("/enchere")
	public String afficherCategories(Model model) {
		List<Categorie> listeCategories = categorieService.getAllCategories();
		model.addAttribute("listeCategories", listeCategories);
		List<Article> articlesEnCours = articleService.getArticlesEnCours();
		model.addAttribute("articlesEnCours", articlesEnCours);

		for (Article article : articlesEnCours) {
			Utilisateur utilisateur = utilisateurService.getUserById(article.getUtilisateur().getNoUtilisateur());
			model.addAttribute("utilisateur", utilisateur);
		}
		return "index";
	}

	// Charge automatiquement /enchere "si aucune ressource n’est indiquée dans
	// l’url"
	@GetMapping("/")
	public String redirigerVersEncheres() {
		return "redirect:/enchere";
	}

	// Voir le profil d'un vendeur quand on est pas connecté
	@GetMapping("/enchere/vendeur/{id}")
	public String afficherDetailVendeur(@PathVariable("id") Integer vendeurId, 
		Utilisateur utilisateur,Principal principal, Model model) {
		
		String pseudo = principal.getName();
		Utilisateur utilisateurConnecte = utilisateurService.getUserByPseudo(pseudo);
		
		Utilisateur vendeur = utilisateurService.getUserById(vendeurId);
		
		model.addAttribute("utilisateurConnecte",utilisateurConnecte);
		model.addAttribute("utilisateur", vendeur);
		return "view-detail-utilisateur";
	}

	// Voir mon profil une fois connecté
	@GetMapping("/enchere/monProfil")
	public String afficherMonProfil(Principal principal, Model model) {
		String pseudo = principal.getName();
		Utilisateur utilisateurConnecte = utilisateurService.getUserByPseudo(pseudo);
		
		model.addAttribute("utilisateurConnecte",utilisateurConnecte);
		model.addAttribute("utilisateur", utilisateurConnecte);
		return "view-detail-utilisateur";
	}
	
	//Se rediriger vers la page modification du profil
	@GetMapping("/enchere/modifier")
	public String modifierProfil(Principal principal, Model model) {
		String pseudo = principal.getName();
		Utilisateur utilisateurConnecte = utilisateurService.getUserByPseudo(pseudo);
		System.out.println("Utilisateur by pseudo :" + utilisateurConnecte);
		model.addAttribute("utilisateur", utilisateurConnecte);
		return "view-modification-profil";
	}

	// Enregistrer les modifications de mon profil
	@PostMapping("/enchere/monProfil")
	public String afficherProfilModifie(@ModelAttribute("utilisateurConnecte") Utilisateur utilisateurConnecte,
			@RequestParam(name = "mdpNouveau", required =false) String mdpNouveau,
			Model model) {
		utilisateurService.updateUser(utilisateurConnecte, mdpNouveau);
		Integer id = utilisateurConnecte.getNoUtilisateur();
	System.out.println("post modif" +utilisateurConnecte);
		model.addAttribute("utilisateurConnecte", utilisateurService.getUserById(id));
		return "redirect:/enchere/monProfil";
	}


	// requete de type get pour afficher la page de création d'un article
	@GetMapping("/enchere/vendre")
	public String afficherVendreArticle(Principal principal, Model model) {
		String pseudo = principal.getName();
		Utilisateur utilisateur = this.utilisateurService.getUserByPseudo(pseudo);
		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("article", new Article());
		List<Categorie> listeCategories = categorieService.getAllCategories();
		model.addAttribute("listeCategories", listeCategories);
		return "vendre-article";
	}

	// requete de type post pour créer un nouvel article
	@PostMapping("/enchere/vendre")
	public String creerNouveauArticle(Principal principal,
			@RequestParam(name = "noCategorie", required = true) String noCategorie,
			@ModelAttribute("article") Article article, Model model) {
		String pseudo = principal.getName();
		Utilisateur utilisateur = this.utilisateurService.getUserByPseudo(pseudo);
		article.setUtilisateur(utilisateur);
		Integer theId = Integer.parseInt(noCategorie);
		article.setCategorie(categorieService.getCategorie(theId));
		try {
			this.articleService.createArticle(article);
			this.retraitService.insertRetraitPoint(article);
		} catch (BusinessException e) {
			// gestion des erreurs
			model.addAttribute("exceptions", e.getListeException());
			return "vendre-article";
		}
		return "redirect:/enchere";
	}

	@PostMapping("/enchere/supprimer")
	public String supprimerProfil(@ModelAttribute("utilisateurConnecte") Utilisateur utilisateurConnecte,
			Model model) {
		Integer id = utilisateurConnecte.getNoUtilisateur();
		utilisateurService.deleteUser(id);
		
		return "redirect:/enchere";	
	}
	
	
	@PostMapping("/enchere/search")
	public String searchArticles(
			@RequestParam(name = "rechercheNom", required = false) String rechercheNom,
			@RequestParam(name = "noCategorie", required = false, defaultValue = "22") String noCategorie,
			Model model) {
		List<Categorie> listeCategories = categorieService.getAllCategories();
		model.addAttribute("listeCategories", listeCategories);
		
			Integer theId = Integer.parseInt(noCategorie);
			List<Article> articlesResults = articleService.notConnectedResearch(rechercheNom, theId);
			
			model.addAttribute("articlesResults",articlesResults); 
			for (Article article : articlesResults) {
				Utilisateur utilisateur = utilisateurService.getUserById(article.getUtilisateur().getNoUtilisateur());
				model.addAttribute("utilisateur", utilisateur);
				return "index";
			}
			return "index";
	}
}
