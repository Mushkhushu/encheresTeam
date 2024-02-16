package fr.eni.projet.encheres.dal.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.dal.dao.UtilisateurDAO;

@Repository
public class UtilisateurDAOSQL implements UtilisateurDAO {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public UtilisateurDAOSQL(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int add(Utilisateur utilisateur) {
		// Manipulation de la clef primaire auto-générée : IDENTIY
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO UTILISATEURS(pseudo,nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, role) VALUES (:pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :motDePasse, 0, 1)";
		// mappage du client
		BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(utilisateur);
		int nbenregistrement = namedParameterJdbcTemplate.update(sql, namedParameters, keyHolder);
		if (keyHolder.getKey() != null) {
			utilisateur.setNoUtilisateur(keyHolder.getKey().intValue());
		}
		return nbenregistrement;
	}

	@Override
	public Utilisateur getUserById(Integer noUtilisateur) {
		String sql = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, role FROM Utilisateurs WHERE no_utilisateur =:noUtilisateur; ";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("noUtilisateur", noUtilisateur);

		Utilisateur utilisateur = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
				new BeanPropertyRowMapper<>(Utilisateur.class));
		return utilisateur;
	}

	@Override
	public void updateUser(Utilisateur utilisateur) {

		String sql = "UPDATE utilisateurs SET pseudo = :pseudo, nom = :nom, prenom = :prenom, email = :email, telephone = :telephone, rue = :rue, code_postal = :codePostal, ville = :ville, mot_de_passe = :motDePasse WHERE no_utilisateur = :noUtilisateur";
		// mappage du client
		
		BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(utilisateur);
		namedParameterJdbcTemplate.update(sql, namedParameters);

	}

	@Override
	public void updateCreditUser(Utilisateur utilisateur, Integer credit) {
		String sql = "UPDATE utilisateurs SET credit = :credit";
		BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(utilisateur);
		namedParameterJdbcTemplate.update(sql, namedParameters);

	}

	@Override
	public List<Utilisateur> getAllUsers() {
		String sql = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, role FROM UTILISATEURS;";
		List<Utilisateur> listeUtilisateurs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Utilisateur.class));
		return listeUtilisateurs;
	}

	@Override
	public void deleteUser(Integer noUtilisateur) {
		String sql = "DELETE FROM utilisateurs WHERE no_utilisateur = :no_utilisateur";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("no_utilisateur", noUtilisateur);
		namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public boolean emailExisteDeja(String email) {
		String sql = "SELECT COUNT(*) FROM UTILISATEURS WHERE email = ?";
		// Utilisation de jdbcTemplate.queryForObject pour obtenir le résultat de la
		// requête
		int count = jdbcTemplate.queryForObject(sql, Integer.class, email);
		// Si le count est supérieur à 0, l'e-mail existe déjà
		if (count > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean pseudoExisteDeja(String pseudo) {
		String sql = "SELECT COUNT(*) FROM UTILISATEURS WHERE pseudo = ?";
		// Utilisation de jdbcTemplate.queryForObject pour obtenir le résultat de la
		// requête
		int count = jdbcTemplate.queryForObject(sql, Integer.class, pseudo);
		// Si le count est supérieur à 0, le pseudo existe déjà
		if (count > 0) {
			return false;
		}
		return true;
	}

	@Override
	public Utilisateur getUserByPseudo(String pseudo) {
		String sql = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, role FROM Utilisateurs WHERE pseudo = :pseudo; ";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("pseudo", pseudo);

		Utilisateur utilisateur = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
				new BeanPropertyRowMapper<>(Utilisateur.class));
		return utilisateur;
	}
	
	
	@Override
	public List<Utilisateur> getUsersArticlesEnCours() {
		String sql = "SELECT * FROM Articles inner join utilisateurs on utilisateurs.no_utilisateur = articles.no_utilisateur WHERE etat= 'en cours'";
		List<Utilisateur> listeUtilisateursAvecArticlesEnCours = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<>(Utilisateur.class));

		return listeUtilisateursAvecArticlesEnCours;
	}
	

}
