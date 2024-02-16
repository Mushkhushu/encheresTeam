package fr.eni.projet.encheres.bll.service;

import java.util.List;

import fr.eni.projet.encheres.bo.Categorie;

public interface CategorieService {

	Categorie getCategorie(int noCategorie);

	List<Categorie> getAllCategories();
}
