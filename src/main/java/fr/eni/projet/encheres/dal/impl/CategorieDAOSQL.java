package fr.eni.projet.encheres.dal.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Categorie;
import fr.eni.projet.encheres.dal.dao.CategorieDAO;
@Repository
public class CategorieDAOSQL implements CategorieDAO{
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	
	public CategorieDAOSQL(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Categorie getCategorie(Integer noCategorie) {
		String sql = "SELECT libelle FROM Categories WHERE no_categorie = :no_categorie";
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("no_categorie", noCategorie);
		
		Categorie categorie = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,new BeanPropertyRowMapper<>(Categorie.class) );	
		return categorie;
	}

	@Override
	public List<Categorie> getAllCategories() {
		String sql = "SELECT * FROM Categories";
		
		List<Categorie> listeCategories = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Categorie.class));
		
		return listeCategories;
	}

}
