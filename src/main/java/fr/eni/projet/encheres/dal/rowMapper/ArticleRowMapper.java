package fr.eni.projet.encheres.dal.rowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Categorie;
import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.dal.dao.ArticleDAO;
import fr.eni.projet.encheres.dal.dao.CategorieDAO;
import fr.eni.projet.encheres.dal.dao.UtilisateurDAO;

public class ArticleRowMapper implements RowMapper<Article> {
	@Autowired
	private UtilisateurDAO utilisateurDAO;
	@Autowired
	private ArticleDAO articleDAO;
	@Autowired
	private CategorieDAO categorieDAO;

	@Override
	public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
		Article article = new Article();
		Utilisateur utilisateur = new Utilisateur();
		Categorie categorie = new Categorie();
		article.setNoArticle(rs.getInt(1));
		article.setNomArticle(rs.getString(2));
		article.setDescription(rs.getString(3));
		Date datedebut = rs.getDate(4);
		article.setDateDebutEncheres(datedebut.toLocalDate());
		Date datefin = rs.getDate(5);
		article.setDateFinEncheres(datefin.toLocalDate());
		article.setMiseAPrix(rs.getInt(6));
		article.setPrixVente(rs.getInt(7));
		utilisateur.setNoUtilisateur(rs.getInt(8));
		utilisateur.setPseudo(rs.getString(11));
		article.setUtilisateur(utilisateur);
		categorie.setNoCategorie(rs.getInt(9));
		article.setCategorie(categorie);
		article.setEtatVente(rs.getString(10));
		return article;
	}

}