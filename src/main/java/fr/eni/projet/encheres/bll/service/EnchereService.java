package fr.eni.projet.encheres.bll.service;

import java.util.List;

import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Enchere;

public interface EnchereService {

	void addEncherir(Article article);

	List<Enchere> getEncherebyUserId(Integer noUtilisateur);

	List<Enchere> getEncherebyArticleId(Integer noArticle);

}
