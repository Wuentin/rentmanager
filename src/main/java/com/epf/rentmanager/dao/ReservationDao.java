package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ReservationDao {

	private static ReservationDao instance = null;

	private ReservationDao() {
	}

//	public static ReservationDao getInstance() {
//		if (instance == null) {
//			instance = new ReservationDao();
//		}
//		return instance;
//	}

	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String COUNT = "SELECT COUNT(id) AS count FROM Reservation;";
	private static final String COUNT_BY_ID = "SELECT COUNT(*) FROM Reservation WHERE client_id=?; ";
	private static final String MODIFY = "UPDATE Reservation SET client_id=?,vehicle_id=?,debut=?,fin=? WHERE id=?;";

	public long create(Reservation reservation) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_RESERVATION_QUERY);

			pstmt.setInt(1, reservation.getIdClient());
			pstmt.setInt(2, reservation.getIdVehicule());
			Date addDateStart = Date.valueOf(reservation.getDateStart());
			pstmt.setDate(3, addDateStart);
			Date addDateEnd = Date.valueOf(reservation.getDateEnd());
			pstmt.setDate(4, addDateEnd);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public long delete(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_RESERVATION_QUERY);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public List<Reservation> findResaByClientId(int clientId) throws DaoException {
		try {
			List<Reservation> reservations = new ArrayList<Reservation>();
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			pstmt.setInt(1, clientId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("id");
				// int idClient = rs.getInt("client_id");
				int idVehicule = rs.getInt("vehicle_id");
				LocalDate dateStart = rs.getDate("debut").toLocalDate();
				LocalDate dateEnd = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(id, clientId, idVehicule, dateStart, dateEnd);
				reservations.add(reservation);

			}
			return (reservations);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Optional<Reservation> findResaByVehicleId(int clientId) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			pstmt.setInt(1, clientId);

			ResultSet rs = pstmt.executeQuery();

			int id = rs.getInt("id");
			int idClient = rs.getInt("client_id");
			int idVehicule = rs.getInt("vehicle_id");
			LocalDate dateStart = rs.getDate("debut").toLocalDate();
			LocalDate dateEnd = rs.getDate("fin").toLocalDate();

			Reservation reservation = new Reservation(id, idClient, idVehicule, dateStart, dateEnd);

			return Optional.of(reservation);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();

	}

	public List<Reservation> findAll() throws DaoException {
		try {

			List<Reservation> reservations = new ArrayList<Reservation>();

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("id");
				int idClient = rs.getInt("client_id");
				int idVehicule = rs.getInt("vehicle_id");
				LocalDate dateStart = rs.getDate("debut").toLocalDate();
				LocalDate dateEnd = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(id, idClient, idVehicule, dateStart, dateEnd);

				reservations.add(reservation);
			}

			return (reservations);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}

	}

	public int count() throws DaoException, SQLException {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(COUNT);

		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int compte = rs.getInt(1);
		return compte;
	}

	public int countresa(int id) throws DaoException, SQLException {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(COUNT_BY_ID);

		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();

		int compte = rs.getInt(1);

		return compte;

	}

	public long edit(Reservation reservation) throws DaoException {

		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(MODIFY);

			pstmt.setInt(1, reservation.getIdClient());
			pstmt.setInt(2, reservation.getIdVehicule());
			Date addDateStart = Date.valueOf(reservation.getDateStart());
			pstmt.setDate(3, addDateStart);
			Date addDateEnd = Date.valueOf(reservation.getDateEnd());
			pstmt.setDate(4, addDateEnd);
			pstmt.setInt(5, reservation.getId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}

}
