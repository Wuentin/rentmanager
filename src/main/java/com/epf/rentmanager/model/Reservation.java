package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Reservation {

	private int id;
	private int idClient;
	private int idVehicule;

	private LocalDate dateStart;
	private LocalDate dateEnd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdVehicule() {
		return idVehicule;
	}

	public void setIdVehicule(int idVehicule) {
		this.idVehicule = idVehicule;
	}

	public LocalDate getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDate getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Reservation(int id, int idClient, int idVehicule, LocalDate dateStart, LocalDate dateEnd) {
		super();
		this.id = id;
		this.idVehicule = idVehicule;
		this.idClient = idClient;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public Reservation() {
		super();
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", idClient=" + idClient + ", idVehicule=" + idVehicule + ", dateStart="
				+ dateStart + ", dateEnd=" + dateEnd + "]";
	}
}
