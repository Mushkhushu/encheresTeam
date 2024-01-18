package fr.eni.projet.encheres.bll.impl;

import org.springframework.stereotype.Service;

import fr.eni.projet.encheres.bll.service.RetraitService;
import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Retrait;
import fr.eni.projet.encheres.dal.dao.RetraitDAO;
@Service
public class RetraitServiceImpl implements RetraitService{
//les attributs
	private RetraitDAO retraitDAO;
//les constructeurs
	public RetraitServiceImpl(RetraitDAO retraitDAO) {
		super();
		this.retraitDAO = retraitDAO;
	}
//les méthodes passe-plats:
//CREATE créer un point de retrait à partir d'un article (par défaut: adresse du vendeur)	
	@Override
	public void insertRetraitPoint(Article article) {
		retraitDAO.insertRetraitPoint(article);	
	}
//READ récupérer une adresse de retrait à partir du noArticle
	@Override
	public Retrait ReadById(int noArticle) {
Retrait retrait = retraitDAO.ReadById(noArticle);
		return retrait;
	}
//UPDATE  un retrait (selon le noArticle)
	@Override
	public void updateRetrait(Retrait retrait) {
		retraitDAO.updateRetrait(retrait);		
	}
//DELETE un retrait	 à partir du noArticle
	@Override
	public void deleteRetrait(int noArticle) {
		retraitDAO.deleteRetrait(noArticle);	
	}

	
}
