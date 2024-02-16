package fr.eni.projet.encheres.bll.service;

import java.util.List;

import fr.eni.projet.encheres.bll.exceptions.BusinessException;
import fr.eni.projet.encheres.bo.Utilisateur;

public interface UtilisateurService {

	void add(Utilisateur utilisateur) throws BusinessException;

	List<Utilisateur> getAllUsers();

	Utilisateur getUserById(Integer noUtilisateur);

	Utilisateur getUserByPseudo(String pseudo);

	void updateCreditUser(Utilisateur utilisateur, Integer credit);

	void updateUser(Utilisateur utilisateur, String mdpNouveau);

	void deleteUser(Integer noUtilisateur);

	boolean emailExisteDeja(String email);

	boolean pseudoExisteDeja(String pseudo);

	List<Utilisateur> getUsersArticlesEnCours();
}
