package fr.eni.projet.encheres.bll.service;

import java.util.List;

import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Utilisateur;

public interface ArticleService {
	
	void createArticle(Article article);
	
	void updatePrixVente(Article article, Integer prixVente);

	Article getArticleById(int no_article);
	
	List<Article> getAllArticles();

	List<Article> getAllArticlesByUser(Utilisateur utilisateur);
	
	void deleteArticle();

}
