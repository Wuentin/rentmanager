package com.epf.rentmanager.model;

public class Vehicle {

	private int id;
	private String constructeur;
	private int nbPlaces;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConstructeur() {
		return constructeur;
	}

	public void setConstructeur(String constructeur) {
		this.constructeur = constructeur;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", constructeur=" + constructeur + ", nbPlaces=" + nbPlaces + "]";
	}

	public Vehicle(int id, String constructeur, int nbPlaces) {
		super();
		this.id = id;
		this.constructeur = constructeur;
		this.nbPlaces = nbPlaces;
	}

	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}

}
