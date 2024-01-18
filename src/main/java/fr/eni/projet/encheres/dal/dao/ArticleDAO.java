package fr.eni.projet.encheres.dal.dao;

import java.util.List;

import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Utilisateur;


public interface ArticleDAO {
	
	void createArticle(Article article);
	
	void updatePrixVente(Article article, Integer prixVente);

	Article getArticleById(Integer no_article);
	
	List<Article> getAllArticles();

	List<Article> getAllArticlesByUser(Utilisateur utilisateur);
	
	void deleteArticle();
	
}
