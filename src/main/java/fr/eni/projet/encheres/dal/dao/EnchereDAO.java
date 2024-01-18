package fr.eni.projet.encheres.dal.dao;

import java.util.List;

import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Enchere;


public interface EnchereDAO {
	
	

	List <Enchere> getEncherebyArticleId(Integer noArticle);

	List <Enchere> getEncherebyUserId(Integer noUtilisateur);

	void addEncherir(Article article);

}
