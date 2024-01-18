package fr.eni.projet.encheres.bo;

import java.time.LocalDateTime;

public class Enchere {
//les attributs
private LocalDateTime dateEnchere;
private Integer montantEnchere;
//les constructeurs
public Enchere(LocalDateTime dateEnchere, Integer montantEnchere) {
	super();
	this.dateEnchere = dateEnchere;
	this.montantEnchere = montantEnchere;
}
public Enchere() {
	super();
}
public LocalDateTime getDateEnchere() {
	return dateEnchere;
}
public void setDateEnchere(LocalDateTime dateEnchere) {
	this.dateEnchere = dateEnchere;
}
public Integer getMontantEnchere() {
	return montantEnchere;
}
public void setMontantEnchere(int montantEnchere) {
	this.montantEnchere = montantEnchere;
}
//le ToString
@Override
public String toString() {
	return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + "]";
}
}
