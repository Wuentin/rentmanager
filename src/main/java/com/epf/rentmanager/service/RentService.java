package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;

@Service
public class RentService {

	private ReservationDao reservationDao;
	public static RentService instance;

	private RentService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	public long create(Reservation reservation) throws ServiceException {
		try {
			return this.reservationDao.create(reservation);

		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long edit(Reservation reservation) throws ServiceException {
		try {
			return this.reservationDao.edit(reservation);

		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Optional<Reservation> findResaByVehicleId(int id) throws ServiceException {
		try {
			return this.reservationDao.findResaByVehicleId(id);

		} catch (DaoException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Reservation> findResaByClientId(int id) throws ServiceException {
		try {
			return this.reservationDao.findResaByClientId(id);

		} catch (DaoException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Reservation> findAll() throws ServiceException {
		try {
			return this.reservationDao.findAll();

		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int count() throws SQLException {
		try {
			return this.reservationDao.count();

		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long delete(int id) throws ServiceException {

		try {
			return this.reservationDao.delete(id);

		} catch (DaoException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public int countresa(int id) throws SQLException {
		try {
			return this.reservationDao.countresa(id);

		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
