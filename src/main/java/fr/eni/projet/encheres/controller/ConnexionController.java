package fr.eni.projet.encheres.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fr.eni.projet.encheres.bll.exceptions.BusinessException;
import fr.eni.projet.encheres.bll.service.UtilisateurService;
import fr.eni.projet.encheres.bo.Utilisateur;
import jakarta.validation.Valid;

@Controller
public class ConnexionController {
//les attributs
	UtilisateurService utilisateurService;

//le constructeur
	public ConnexionController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

	// la méthode GET pour accéder à la page de logIn
	@GetMapping("/login")
	public String AfficherConnexion() {
		return "login";
	}

	// la méthode GET pour accéder à la page de signIn
	@GetMapping("/signin")
	public String AfficherInscription(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "signin";
	}

	// la méthode POST pour s'inscrire
	@PostMapping("/signin")
	public String sinscrire(@Valid  @ModelAttribute("utilisateur") Utilisateur utilisateur,
			BindingResult bindingResult,
			@RequestParam("confMotDePasse") String confMotDePasse,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "signin";
		}
		if (!utilisateur.getMotDePasse().equals(confMotDePasse)) {
			bindingResult.rejectValue("motDePasse", "confirmation.incorrecte",
					"Les deux saisies doivent être identiques");
				return "signin";
		}
		//RECUPERER EXCEPTION
		try {
			this.utilisateurService.add(utilisateur);
		} catch (BusinessException e) {
			model.addAttribute("exceptions", e.getListeException());
			e.printStackTrace();
			return "signin";
		}
		System.out.println(utilisateur);
		return "redirect:/";
	}
}


//@RequestParam("pseudo") String pseudo,
//@RequestParam("prenom") String prenom,
//@RequestParam("nom") String nom,
//@RequestParam("email") String email,
//@RequestParam("phone") Integer telephone,
//@RequestParam("rue") String rue,
//@RequestParam("code") Integer codePostal,
//@RequestParam("ville") String ville,
//@RequestParam("mdp") String motDePasse,

//utilisateur = new Utilisateur();
//utilisateur.setPseudo(pseudo);
//utilisateur.setPrenom(prenom);
//utilisateur.setNom(nom);
//utilisateur.setEmail(email);
//utilisateur.setTelephone(telephone);
//utilisateur.setRue(rue);
//utilisateur.setCodePostal(codePostal);
//utilisateur.setVille(ville);
//utilisateur.setMotDePasse(motDePasse);