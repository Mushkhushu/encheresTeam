package fr.eni.projet.encheres.bll.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet.encheres.bll.service.CategorieService;
import fr.eni.projet.encheres.bo.Categorie;
import fr.eni.projet.encheres.dal.dao.CategorieDAO;
@Service
public class CategorieServiceImpl implements CategorieService{
	
//les attributs
private CategorieDAO categorieDAO;

// le constructeur
public CategorieServiceImpl(CategorieDAO categorieDAO) {
	super();
	this.categorieDAO = categorieDAO;
}

@Override
public Categorie getCategorie(int noCategorie) {
	return categorieDAO.getCategorie(noCategorie);
}
@Override
public List<Categorie> getAllCategories() {
	return categorieDAO.getAllCategories();
}

}
