package fr.eni.projet.encheres.bll.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;
	// Atribut liste des exceptions
	private List<String> listeException = new ArrayList<String>();

	// les constructeurs
	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	// la fonction pour ajouter une exception à la liste
	public void addException(String message) {
		listeException.add(message);
	}

	// boolean si la liste est vide = pas de problème
	public boolean isValid() {
		return listeException.isEmpty();
	}

	// les getters
	public List<String> getListeException() {
		return listeException;
	}

	public void setListeException(List<String> listeException) {
		this.listeException = listeException;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
