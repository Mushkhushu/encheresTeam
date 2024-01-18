package fr.eni.projet.encheres.bll.service;

import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Retrait;

public interface RetraitService {
	//les méthodes passe-plats:
void insertRetraitPoint(Article article);
Retrait ReadById(int noArticle);
void updateRetrait(Retrait retrait);
void deleteRetrait(int noArticle);
}
