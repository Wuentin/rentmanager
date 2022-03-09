package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Client {
	private String nom;
	private String prenom;
	private String email;
	private LocalDate bornDate;

	private int idClient;

	public String toString() {
		return nom.toString() + "," + prenom.toString() + "," + email.toString() + "," + bornDate.toString();
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBornDate() {
		return bornDate;
	}

	public void setBornDate(LocalDate bornDate) {
		this.bornDate = bornDate;
	}

	public Client(String nom, String prenom, String email, LocalDate bornDate, int idClient) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.bornDate = bornDate;
		this.idClient = idClient;
	}

	public Client() {
		super();
	}

}
