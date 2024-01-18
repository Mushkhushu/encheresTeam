package fr.eni.projet.encheres.dal.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Retrait;
import fr.eni.projet.encheres.dal.dao.RetraitDAO;

@Repository
public class RetraitDAOSQL implements RetraitDAO {
	
//les attributs
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
//le constructeur	
	public RetraitDAOSQL(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.jdbcTemplate = jdbcTemplate;
	}
//les méthodes CRUD	
//CREATE créer un point de retrait à partir d'un article (par défaut: adresse du vendeur)	
	@Override
	public void insertRetraitPoint(Article article) {
		String sql = "INSERT INTO RETRAITS (no_Article, rue, code_postal, ville) "
				+ "VALUES (:noArticle, :rue, :code_postal, :ville);";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("noArticle", article.getNoArticle());
		namedParameters.addValue("rue", article.getUtilisateur().getRue());
		namedParameters.addValue("code_postal", article.getUtilisateur().getCodePostal());
		namedParameters.addValue("ville", article.getUtilisateur().getVille());
		namedParameterJdbcTemplate.update(sql, namedParameters);		
	}

//READ récupérer une adresse de retrait à partir du noArticle
	@Override
	public Retrait ReadById(Integer noArticle) {
		String sql = "SELECT no_article, rue, code_postal, ville FROM RETRAITS WHERE no_article =:noArticle; ";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("noArticle", noArticle);
		Retrait retrait = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, 
				new BeanPropertyRowMapper<>(Retrait.class));
		return retrait;
	}
	
//UPDATE  un retrait (selon le noArticle)
	@Override
	public void updateRetrait(Retrait retrait) {
	    String sql = "UPDATE RETRAITS SET rue = :rue, code_postal = :codePostal, ville = :ville WHERE no_article = :noArticle;";
	    BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(retrait);
		namedParameterJdbcTemplate.update(sql, namedParameters);
	}
	
//DELETE un retrait	 à partir du noArticle
	@Override
	public void deleteRetrait(Integer noArticle) {
	    String sql = "DELETE FROM RETRAITS WHERE no_article = :noArticle;";
	    MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	    namedParameters.addValue("noArticle", noArticle);
	    namedParameterJdbcTemplate.update(sql, namedParameters);
	}	
	
}
