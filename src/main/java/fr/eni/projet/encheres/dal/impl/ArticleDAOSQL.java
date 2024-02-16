package fr.eni.projet.encheres.dal.impl;


import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Categorie;
import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.dal.dao.ArticleDAO;
import fr.eni.projet.encheres.dal.rowMapper.ArticleRowMapper;

@Repository
public class ArticleDAOSQL implements ArticleDAO {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;


	public ArticleDAOSQL(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.jdbcTemplate = jdbcTemplate;
	}

	// Créer un Article
	@Override
	public void createArticle(Article article) {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		String sql = "INSERT INTO Articles (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, etat) "
				+ "VALUES (:nomArticle, :description, :dateDebutEncheres,:dateFinEncheres, :prix_initial, :prix_vente, :noUtilisateur, :noCategorie, :etat);";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("nomArticle", article.getNomArticle());
		namedParameters.addValue("description", article.getDescription());
		namedParameters.addValue("dateDebutEncheres", article.getDateDebutEncheres());
		namedParameters.addValue("dateFinEncheres", article.getDateFinEncheres());
		namedParameters.addValue("prix_initial", article.getMiseAPrix());
		namedParameters.addValue("prix_vente", article.getMiseAPrix());
		namedParameters.addValue("noUtilisateur", article.getUtilisateur().getNoUtilisateur());
		namedParameters.addValue("noCategorie", article.getCategorie().getNoCategorie());
		namedParameters.addValue("etat", article.getEtatVente());

		namedParameterJdbcTemplate.update(sql, namedParameters, keyHolder);

		if (keyHolder.getKey() != null) {
			article.setNoArticle(keyHolder.getKey().intValue());
		}

	}

	// Modifier le prix de vente actuel de l'Article
	@Override
	public void updatePrixVente(Article article, Integer prixVente) {
		String sql = "INSERT INTO Articles (prix_vente) VALUE (:prixVente);";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("prixVente", article.getPrixVente());
		// Où fait-on le setPrixVente/ou récupère-t-on le nouveau prix de vente ? Dans
		// les enchères ?

		namedParameterJdbcTemplate.update(sql, namedParameters);

	}

	// Selectionner un Article par ID
	@Override
	public Article getArticleById(Integer noArticle) {
		String sql = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM Articles WHERE no_article =:noArticle; ";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("noArticle", noArticle);

		Article article = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
				new BeanPropertyRowMapper<>(Article.class));
		return article;
	}

	// Selectionner tous les Articles vendus
	@Override
	public List<Article> getAllArticles() {
		String sql = "SELECT * FROM Articles";
		List<Article> listeArticles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class));
		return listeArticles;
	}

	// Selectionner liste d'Article par Utilisateur
	@Override
	public List<Article> getAllArticlesByUser(Utilisateur utilisateur) {
		String sql = "SELECT * FROM Articles WHERE no_utilisateur = :no_utilisateur";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("no_utilisateur", utilisateur.getNoUtilisateur());

		List<Article> listeArticles = namedParameterJdbcTemplate.query(sql, namedParameters,
				new BeanPropertyRowMapper<>(Article.class));
		return listeArticles;
	}

	@Override
	public void deleteArticle(Integer noArticle) {
		String sql = "DELETE FROM articles WHERE no_article = :no_article";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("no_article", noArticle);
		namedParameterJdbcTemplate.update(sql, namedParameters);
 
	}

	@Override
	public List<Article> getArticlesEnCours() {
		String sql = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, articles.no_utilisateur, articles.no_categorie, etat, utilisateurs.pseudo FROM Articles inner join utilisateurs on utilisateurs.no_utilisateur = articles.no_utilisateur WHERE articles.etat= 'en cours'";
		List<Article> listeArticlesEnCours = jdbcTemplate.query(sql, new ArticleRowMapper());
		return listeArticlesEnCours;
	}


	@Override
	public List<Article> searchByNameContain(String rechercheNom) {
		List<Article> articlesResults;
		
		String sql ="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, articles.no_utilisateur, articles.no_categorie, etat, utilisateurs.pseudo from ARTICLES inner join utilisateurs on utilisateurs.no_utilisateur = articles.no_utilisateur WHERE articles.nom_article LIKE :rechercheNom";
		 String rechercheParam = "%" + rechercheNom + "%";
		 
		 MapSqlParameterSource namedParameters = new MapSqlParameterSource().addValue("rechercheNom", rechercheParam);
		 articlesResults = namedParameterJdbcTemplate.query(sql, namedParameters, new ArticleRowMapper());
		return articlesResults;
	}

	@Override
	public List<Article> searchByCategorie(Categorie categorie) {
		List<Article> articlesResults;
		String sql ="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, articles.no_utilisateur, articles.no_categorie, etat, utilisateurs.pseudo from ARTICLES inner join utilisateurs on utilisateurs.no_utilisateur = articles.no_utilisateur WHERE articles.no_categorie=:no_categorie";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		 namedParameters.addValue("no_categorie", categorie.getNoCategorie());
		 articlesResults = namedParameterJdbcTemplate.query(sql, namedParameters, new ArticleRowMapper());
		return articlesResults;
	}

	@Override
	public List<Article> searchByNameContainAndCategorie(String rechercheNom, Categorie categorie) {
		List<Article> articlesResults;
		String sql ="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, articles.no_utilisateur, articles.no_categorie, etat, utilisateurs.pseudo from ARTICLES inner join utilisateurs on utilisateurs.no_utilisateur = articles.no_utilisateur WHERE articles.no_categorie=:no_categorie AND articles.nom_article LIKE :rechercheNom";
		 String rechercheParam = "%" + rechercheNom + "%";
		 
		 MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		 namedParameters.addValue("rechercheNom", rechercheParam);
		 namedParameters.addValue("no_categorie", categorie.getNoCategorie());
		 articlesResults = namedParameterJdbcTemplate.query(sql, namedParameters, new ArticleRowMapper());
		return articlesResults;
	}
}
