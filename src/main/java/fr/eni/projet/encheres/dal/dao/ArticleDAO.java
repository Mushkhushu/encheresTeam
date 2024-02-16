package fr.eni.projet.encheres.dal.dao;

import java.util.List;

import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Categorie;
import fr.eni.projet.encheres.bo.Utilisateur;

public interface ArticleDAO {

	void createArticle(Article article);

	void updatePrixVente(Article article, Integer prixVente);

	Article getArticleById(Integer no_article);

	List<Article> getAllArticles();

	List<Article> getAllArticlesByUser(Utilisateur utilisateur);
	
	void deleteArticle(Integer noArticle);

	List<Article> getArticlesEnCours();
	
	List<Article> searchByNameContain(String rechercheNom);

	List<Article> searchByCategorie(Categorie categorie);

	List<Article> searchByNameContainAndCategorie(String rechercheNom, Categorie categorie);	

}
