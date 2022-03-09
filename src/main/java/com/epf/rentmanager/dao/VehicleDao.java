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
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class VehicleDao {

	private static VehicleDao instance = null;

	private VehicleDao() {

	}

//	public static VehicleDao getInstance() {
//		if (instance == null) {
//			instance = new VehicleDao();
//		}
//		return instance;
//	}

	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String COUNT = "SELECT COUNT(*)  FROM Vehicle";
	private static final String MODIFY = "UPDATE Vehicle SET constructeur=?,nb_places=? WHERE id=?;";

	public long create(Vehicle vehicle) throws DaoException {

		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_VEHICLE_QUERY);

			pstmt.setString(1, vehicle.getConstructeur());
			pstmt.setInt(2, vehicle.getNbPlaces());

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
			PreparedStatement pstmt = conn.prepareStatement(DELETE_VEHICLE_QUERY);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public Optional<Vehicle> findById(int id) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLE_QUERY);

			ResultSet rs = pstmt.executeQuery();

			rs.next();

			String constructeur = rs.getString("constructeur");
			int nbPlaces = rs.getInt("nb_places");

			Vehicle vehicle = new Vehicle(id, constructeur, nbPlaces);

			return Optional.of((vehicle));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Vehicle> findAll() throws DaoException {
		try {

			List<Vehicle> vehicles = new ArrayList<Vehicle>();

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLES_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("id");
				String constructeur = rs.getString("constructeur");
				int nbPlaces = rs.getInt("nb_places");

				Vehicle vehicle = new Vehicle(id, constructeur, nbPlaces);

				vehicles.add(vehicle);
			}

			return (vehicles);

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

	public long edit(Vehicle vehicle) throws DaoException {

		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(MODIFY);
			pstmt.setString(1, vehicle.getConstructeur());
			pstmt.setInt(2, vehicle.getNbPlaces());
			pstmt.setInt(3, vehicle.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}

}
