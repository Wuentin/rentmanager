package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ClientDao {

	private static ClientDao instance = null;

	private ClientDao() {
	}

//	public static ClientDao getInstance() {
//		if (instance == null) {
//			instance = new ClientDao();
//		}
//		return instance;
//	}

	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT id,nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String COUNT = "SELECT COUNT(*)  FROM Client";
	private static final String MODIFY = "UPDATE Client SET nom=?,prenom=?,email=?,naissance=? WHERE id=?;";

	public long create(Client client) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_CLIENT_QUERY);

			pstmt.setString(1, client.getNom());
			pstmt.setString(2, client.getPrenom());
			pstmt.setString(3, client.getEmail());
			Date addDate = Date.valueOf(client.getBornDate());
			pstmt.setDate(4, addDate);

			pstmt.executeUpdate();

			return 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public long delete(int id) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_CLIENT_QUERY);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public Client findById(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENT_QUERY);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			rs.next();

			String clientLastname = rs.getString("nom");
			String clientFirstname = rs.getString("prenom");
			String clientEmail = rs.getString("email");
			LocalDate clientBirthdate = rs.getDate("naissance").toLocalDate();

			Client client = new Client(clientLastname, clientFirstname, clientEmail, clientBirthdate, id);

			return (client);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public List<Client> findAll() throws DaoException {
		try {

			List<Client> clients = new ArrayList<Client>();

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENTS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String clientLastname = rs.getString("nom");
				String clientFirstname = rs.getString("prenom");
				String clientEmail = rs.getString("email");
				LocalDate clientBirthdate = rs.getDate("naissance").toLocalDate();

				Client client = new Client(clientLastname, clientFirstname, clientEmail, clientBirthdate, id);

				clients.add(client);
			}

			return (clients);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

		// return null;
	}

	public int count() throws DaoException, SQLException {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(COUNT);

		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int compte = rs.getInt(1);
		return compte;
	}

	public long edit(Client client) throws DaoException {

		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(MODIFY);

			pstmt.setString(1, client.getNom());
			pstmt.setString(2, client.getPrenom());
			pstmt.setString(3, client.getEmail());
			Date addDate = Date.valueOf(client.getBornDate());
			pstmt.setDate(4, addDate);
			pstmt.setInt(5, client.getIdClient());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}

}
