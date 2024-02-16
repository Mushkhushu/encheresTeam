package fr.eni.projet.encheres.bll.service;

import java.time.LocalDate;
import java.util.List;

import fr.eni.projet.encheres.bll.exceptions.BusinessException;
import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Utilisateur;

public interface ArticleService {

	void createArticle(Article article) throws BusinessException;

	void updatePrixVente(Article article, Integer prixVente);

	Article getArticleById(Integer no_article);

	List<Article> getAllArticles();

	List<Article> getAllArticlesByUser(Utilisateur utilisateur);

	void deleteArticle(Integer noArticle);

	List<Article> getArticlesEnCours();

	String determineEtatVente(LocalDate dateDebutEncheres);
	
	List<Article> notConnectedResearch(String rechercheNom, Integer theId);
}
