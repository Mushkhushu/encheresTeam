package fr.eni.projet.encheres.bll.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import fr.eni.projet.encheres.bll.exceptions.BusinessCode;
import fr.eni.projet.encheres.bll.exceptions.BusinessException;
import fr.eni.projet.encheres.bll.service.UtilisateurService;
import fr.eni.projet.encheres.bo.Utilisateur;
import fr.eni.projet.encheres.dal.dao.ArticleDAO;
import fr.eni.projet.encheres.dal.dao.UtilisateurDAO;
@Service
public class UtilisateurServiceImpl implements UtilisateurService{
//les attributs
	private UtilisateurDAO utilisateurDAO;
	PasswordEncoder passwordEncoder;
	private ArticleDAO articleDAO;
//les constructeurs
public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO,PasswordEncoder passwordEncoder, ArticleDAO articleDAO) {
	super();
	this.utilisateurDAO = utilisateurDAO;
	this.passwordEncoder = passwordEncoder;
	this.articleDAO = articleDAO;
}

@Override
public void add(Utilisateur utilisateur) throws BusinessException{
	BusinessException businessException = new BusinessException();
	boolean isValid = true;
	isValid &= validerUtilisateurString(utilisateur, businessException);
	isValid &= validerUtilisateurPseudo(utilisateur, businessException);
	isValid &= validerUtilisateurEmail(utilisateur, businessException);
	if (isValid) {
		//encodage a garder
		if(utilisateur.getMotDePasse() != null) {
			utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
			}
		utilisateurDAO.add(utilisateur);	
	} else {
		throw businessException;
	}
	
	
}

@Override
public List<Utilisateur> getAllUsers() {
	return utilisateurDAO.getAllUsers();
}
@Override
public Utilisateur getUserById(Integer noUtilisateur) {
	Utilisateur utilisateur = utilisateurDAO.getUserById(noUtilisateur);
	
	return utilisateur;
}
@Override
public void updateUser(Utilisateur utilisateur) {
	utilisateurDAO.updateUser(utilisateur);
	
}
@Override
public void deleteUser(Integer noUtilisateur) {
	utilisateurDAO.deleteUser(noUtilisateur);
	
}
@Override
public void updateCreditUser(Utilisateur utilisateur, Integer credit) {
	utilisateurDAO.updateCreditUser(utilisateur, credit);
}


// METHODES DE VALIDATION DE LA BO -------------------------------------------------------
@Override
public boolean emailExisteDeja(String email) {
	boolean existEmail=utilisateurDAO.emailExisteDeja(email);
	return existEmail;
}
// IL FAUT FAIRE LA CONFIRMATION DU MDP



@Override
public boolean pseudoExisteDeja(String pseudo) {
	boolean existPseudo=utilisateurDAO.pseudoExisteDeja(pseudo);
	return existPseudo;
}

private boolean validerUtilisateurEmail(Utilisateur utilisateur, BusinessException businessException) {
	if (utilisateur == null) {
		businessException.addException(BusinessCode.VALIDATION_UTILISATEUR_NULL);
		return false;
		}
	if (emailExisteDeja(utilisateur.getEmail())==false) {
		businessException.addException(BusinessCode.VALIDATION_UTILISATEUR_EMAIL_SOLO);
		return false;
		}
	if (utilisateur.getEmail().length()<4||utilisateur.getEmail().length()>20) {
		businessException.addException(BusinessCode.VALIDATION_UTILISATEUR_EMAIL_TAILLE);
		return false;
		}
	return true;
}
private boolean validerUtilisateurPseudo(Utilisateur utilisateur, BusinessException businessException) {
	String regex ="[a-zA-Z0-9]*" ;
	if (pseudoExisteDeja(utilisateur.getPseudo())==false) {
		businessException.addException(BusinessCode.VALIDATION_UTILISATEUR_PSEUDO_SOLO);
		return false;
		}
	if (!utilisateur.getPseudo().matches(regex)) {
		businessException.addException(BusinessCode.VALIDATION_UTILISATEUR_PSEUDO_CARAC);
		return false;
		}
	if (utilisateur.getPseudo().length()<4||utilisateur.getPseudo().length()>30) {
		businessException.addException(BusinessCode.VALIDATION_UTILISATEUR_PSEUDO_TAILLE);
		return false;
		}
	return true;
}


private boolean validerUtilisateurString(Utilisateur utilisateur, BusinessException businessException) {
	if (utilisateur.getNom().length()<4||utilisateur.getNom().length()>30) {
		businessException.addException(BusinessCode.VALIDATION_UTILISATEUR_NOM_TAILLE);
		return false;
		}
	if (utilisateur.getPrenom().length()<4||utilisateur.getPrenom().length()>30) {
		businessException.addException(BusinessCode.VALIDATION_UTILISATEUR_PRENOM_TAILLE);
		return false;
		}
	return true;	
}


}
