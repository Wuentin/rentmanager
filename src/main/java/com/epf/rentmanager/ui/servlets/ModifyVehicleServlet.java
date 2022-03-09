package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars/modify")
public class ModifyVehicleServlet extends HttpServlet {

	@Autowired
	VehicleService vehicleService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO

		getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/modify.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String strIdrecup = request.getQueryString();
		strIdrecup = strIdrecup.substring(3);
		int idRecup = Integer.valueOf(strIdrecup);

		String constructeur = request.getParameter("manufacturer");
		String nbPlaces = request.getParameter("seats");
		int intNbPlaces = Integer.parseInt(nbPlaces);

		Vehicle modifiedVehicle = new Vehicle(idRecup, constructeur, intNbPlaces);

		try {
			request.setAttribute("modifiedVehicule", this.vehicleService.edit(modifiedVehicle));
		} catch (ServiceException e) {

		}

		this.doGet(request, response);
	}

}
