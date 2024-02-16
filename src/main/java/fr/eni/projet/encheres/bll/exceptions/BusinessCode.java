package fr.eni.projet.encheres.bll.exceptions;

public class BusinessCode {
	// ne contient que des constantes: les messages d'erreur spécifiques
	public static final String VALIDATION_UTILISATEUR_NULL = "l'utilisateur ne peut pas être nul";
	public static final String VALIDATION_UTILISATEUR_EMAIL_SOLO = "l'email existe déjà";
	public static final String VALIDATION_UTILISATEUR_EMAIL_TAILLE = "l'email doit avoir entre 4 et 20 caractères";

	public static final String VALIDATION_UTILISATEUR_PSEUDO_SOLO = "le pseudo existe déjà";
	public static final String VALIDATION_UTILISATEUR_PSEUDO_CARAC = "le pseudo ne doit contenir que des chiffres et des lettres";
	public static final String VALIDATION_UTILISATEUR_PSEUDO_TAILLE = "le pseudo doit être compris entre 4 et 30 caractères";

	public static final String VALIDATION_UTILISATEUR_NOM_TAILLE = "le nom doit être compris entre 4 et 30 caractères";
	public static final String VALIDATION_UTILISATEUR_PRENOM_TAILLE = "le prénom doit être compris entre 4 et 30 caractères";
}
