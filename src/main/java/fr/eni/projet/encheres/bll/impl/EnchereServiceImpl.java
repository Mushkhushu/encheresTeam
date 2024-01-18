package fr.eni.projet.encheres.bll.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet.encheres.bll.service.EnchereService;
import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Enchere;
import fr.eni.projet.encheres.dal.dao.EnchereDAO;
@Service
public class EnchereServiceImpl implements EnchereService{
//les attributs
	private EnchereDAO enchereDAO;
//les contructeurs
public EnchereServiceImpl(EnchereDAO enchereDAO) {
	super();
	this.enchereDAO = enchereDAO;
}
@Override
public void addEncherir(Article article) {
enchereDAO.addEncherir(article);
	
}
@Override
public List<Enchere> getEncherebyUserId(Integer noUtilisateur) {

	return enchereDAO.getEncherebyUserId(noUtilisateur);
}
@Override
public List<Enchere> getEncherebyArticleId(Integer noArticle) {

	return enchereDAO.getEncherebyArticleId(noArticle);
}
	


}
