package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;

@Service
public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;

	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

//	public static ClientService getInstance() {
//		if (instance == null) {
//			instance = new ClientService();
//		}
//		
//		return instance;
//	}

	public long create(Client client) throws ServiceException {
		// TODO: créer un client
		try {
			return this.clientDao.create(client);

		} catch (DaoException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public long delete(int id) throws ServiceException {
		// TODO: créer un client
		try {
			return this.clientDao.delete(id);

		} catch (DaoException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public Client findById(int id) throws ServiceException {
		// TODO: récupérer un client par son id
		try {
			return this.clientDao.findById(id);

		} catch (DaoException e) {
			e.printStackTrace();
		}

		return null;

	}

	public List<Client> findAll() throws ServiceException {
		try {
			return this.clientDao.findAll();

		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int count() throws SQLException {
		try {
			return this.clientDao.count();

		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long edit(Client client) throws DaoException {
		try {
			return this.clientDao.edit(client);

		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
