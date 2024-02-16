package fr.eni.projet.encheres.bll.impl;

import java.time.LocalDate;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet.encheres.bll.exceptions.BusinessException;
import fr.eni.projet.encheres.bll.service.ArticleService;
import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Categorie;
import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.dal.dao.ArticleDAO;
import fr.eni.projet.encheres.dal.dao.CategorieDAO;
import fr.eni.projet.encheres.dal.dao.UtilisateurDAO;

@Service
public class ArticleServiceImpl implements ArticleService {
//les attributs
	private ArticleDAO articleDAO;
	private UtilisateurDAO utilisateurDAO;
	private CategorieDAO categorieDAO;

//le constructeur
	public ArticleServiceImpl(ArticleDAO articleDAO, UtilisateurDAO utilisateurDAO, CategorieDAO categorieDAO) {
		super();
		this.articleDAO = articleDAO;
		this.utilisateurDAO = utilisateurDAO;
		this.categorieDAO = categorieDAO;
	}

	@Override
	public void createArticle(Article article) throws BusinessException {
		article.setPrixVente(article.getMiseAPrix());
		String etatVente = determineEtatVente(article.getDateDebutEncheres());
	    article.setEtatVente(etatVente);
		articleDAO.createArticle(article);
	}

	@Override
	public void updatePrixVente(Article article, Integer prixVente) {
		articleDAO.updatePrixVente(article, prixVente);
	}

	@Override
	public Article getArticleById(Integer no_article) {
		Article article = articleDAO.getArticleById(no_article);
		Utilisateur utilisateur = utilisateurDAO.getUserById(article.getUtilisateur().getNoUtilisateur());
		article.setUtilisateur(utilisateur);
		return article;
	}

	@Override
	public List<Article> getAllArticles() {
		return articleDAO.getAllArticles();
	}

	@Override
	public List<Article> getAllArticlesByUser(Utilisateur utilisateur) {
		return articleDAO.getAllArticlesByUser(utilisateur);
	}

	@Override
	public void deleteArticle(Integer noArticle) {
		articleDAO.deleteArticle(noArticle);	
	}

	@Override
	public List<Article> getArticlesEnCours() {
		return articleDAO.getArticlesEnCours();
	}
	
	public String determineEtatVente(LocalDate dateDebutEncheres) {
		LocalDate dateDuJour = LocalDate.now();
		if (dateDuJour.isEqual(dateDebutEncheres)) {
			return "en cours";
		} else {
			return "créé";
		}
	}

	@Override
	public List<Article> notConnectedResearch(String rechercheNom, Integer theId) {
		//si pas de choix de catégorie
		if (theId==22) {
			if (rechercheNom!=null) {
				return articleDAO.searchByNameContain(rechercheNom);
			}
			return articleDAO.getArticlesEnCours();
		//si choix de catégorie fait
		} else {
			Categorie categorie =categorieDAO.getCategorie(theId);
			if (rechercheNom!=null) {
				return articleDAO.searchByNameContainAndCategorie(rechercheNom, categorie);
			}
			return articleDAO.searchByCategorie(categorie);
		}
	}

	
}
