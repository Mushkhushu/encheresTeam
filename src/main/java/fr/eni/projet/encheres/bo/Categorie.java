package fr.eni.projet.encheres.bo;

public class Categorie {
//les atttributs

private Integer noCategorie;
private String libelle;
//les cosntructeurs
public Categorie(Integer noCategorie, String libelle) {
	super();
	this.noCategorie = noCategorie;
	this.libelle = libelle;
}
public Categorie() {
	super();
}
//les G/S
public Integer getNoCategorie() {
	return noCategorie;
}
public void setNoCategorie(int noCategorie) {
	this.noCategorie = noCategorie;
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}
//le ToString
@Override
public String toString() {
	return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
}
}
