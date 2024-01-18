package fr.eni.projet.encheres.dal.dao;

import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Retrait;

public interface RetraitDAO {

	void insertRetraitPoint(Article article);
	Retrait ReadById(Integer noArticle);
	void updateRetrait(Retrait retrait);
	void deleteRetrait(Integer noArticle);
}
