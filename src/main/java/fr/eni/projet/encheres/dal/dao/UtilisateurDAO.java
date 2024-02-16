package fr.eni.projet.encheres.dal.dao;

import java.util.List;

import fr.eni.projet.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	int add(Utilisateur utilisateur);

	Utilisateur getUserById(Integer noUtilisateur);

	void updateUser(Utilisateur utilisateur);

	void updateCreditUser(Utilisateur utilisateur, Integer credit);

	List<Utilisateur> getAllUsers();

	void deleteUser(Integer noUtilisateur);

	boolean emailExisteDeja(String email);

	boolean pseudoExisteDeja(String pseudo);

	Utilisateur getUserByPseudo(String pseudo);

	List<Utilisateur> getUsersArticlesEnCours();
}
