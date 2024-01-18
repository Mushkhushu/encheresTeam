package fr.eni.projet.encheres.bll.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet.encheres.bll.service.ArticleService;
import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.dal.dao.ArticleDAO;
@Service
public class ArticleServiceImpl implements ArticleService{
//les attributs
	private ArticleDAO articleDAO;
//le constructeur
public ArticleServiceImpl(ArticleDAO articleDAO) {
	super();
	this.articleDAO = articleDAO;
}


@Override
public void createArticle(Article article) {
	articleDAO.createArticle(article);
}
@Override
public void updatePrixVente(Article article, Integer prixVente) {
	articleDAO.updatePrixVente(article, prixVente);
}

@Override
public Article getArticleById(int no_article) {
	return articleDAO.getArticleById(no_article);
}


@Override
public List<Article> getAllArticles() {
	return articleDAO.getAllArticles();
}
@Override
public List<Article> getAllArticlesByUser(Utilisateur utilisateur) {
	return articleDAO.getAllArticlesByUser(utilisateur);
}
@Override
public void deleteArticle() {
	articleDAO.deleteArticle();
}
}
