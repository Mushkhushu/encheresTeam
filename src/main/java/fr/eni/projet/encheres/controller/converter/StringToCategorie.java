package fr.eni.projet.encheres.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.projet.encheres.bll.service.CategorieService;
import fr.eni.projet.encheres.bo.Categorie;

@Component
public class StringToCategorie implements Converter<String, Categorie> {

	private CategorieService categorieService;
	
	
	public StringToCategorie(CategorieService categorieService) {
		super();
		this.categorieService = categorieService;
	}

	@Override
	public Categorie convert(String noCategorie) {
		Integer theId = Integer.parseInt(noCategorie);
		Categorie categorie = categorieService.getCategorie(theId);
		return categorie;
	}
}
