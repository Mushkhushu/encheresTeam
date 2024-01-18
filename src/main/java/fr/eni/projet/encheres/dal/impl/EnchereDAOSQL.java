package fr.eni.projet.encheres.dal.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Enchere;
import fr.eni.projet.encheres.dal.dao.EnchereDAO;

@Repository
public class EnchereDAOSQL implements EnchereDAO {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public EnchereDAOSQL(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.jdbcTemplate = jdbcTemplate;
	}
	  
	  @Override
	public void addEncherir(Article article) {
		
		String sql = "INSERT INTO ENCHERES(no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (:noUtilisateur, :noArticle, :dateEnchere, :montantEnchere)";
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("noArticle",article.getNoArticle());
		namedParameters.addValue("noUtilisateur",article.getUtilisateur().getNoUtilisateur());
		
		}
		
	
	@Override

	// Selectionner un article par son ID

	public List <Enchere> getEncherebyArticleId(Integer noArticle) {
		String sql = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_article =:noArticle; ";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("noArticle", noArticle);

		List <Enchere> listeEncheres = namedParameterJdbcTemplate.query(sql, namedParameters,
				new BeanPropertyRowMapper<>(Enchere.class));
		return listeEncheres;

	}

	@Override

	// Selectionner un utilisateur par son ID

	public List <Enchere> getEncherebyUserId(Integer noUtilisateur){
		
		String sql = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_article =:noArticle; ";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("noUtilisateur", noUtilisateur);

		List <Enchere> listeEncheres = namedParameterJdbcTemplate.query(sql, namedParameters,
				new BeanPropertyRowMapper<>(Enchere.class));
		return listeEncheres;
	}

	
	
}
