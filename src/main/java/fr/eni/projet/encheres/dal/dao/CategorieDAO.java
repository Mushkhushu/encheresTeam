package fr.eni.projet.encheres.dal.dao;

import java.util.List;

import fr.eni.projet.encheres.bo.Categorie;

public interface CategorieDAO {

	Categorie getCategorie(Integer noCategorie);

	List<Categorie> getAllCategories();
}
