package fr.eni.projet.encheres.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.eni.projet.encheres.bll.service.CategorieService;
import fr.eni.projet.encheres.bo.Categorie;

@Controller
public class EncheresController {
	
	CategorieService categorieService;
	
	
	public EncheresController(CategorieService categorieService) {
		super();
		this.categorieService = categorieService;
	}


	@GetMapping("/enchere")
	public String afficherCategories(Model model) {
		List<Categorie> listeCategories = categorieService.getAllCategories();
		model.addAttribute("listeCategories",listeCategories);
		return "index";
	}
	
	//Charge automatiquement /enchere "si aucune ressource n’est indiquée dans l’url" 
	@GetMapping("/")
	public String redirigerVersEncheres() {
		return "redirect:/enchere";
	}

}
