package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.RentService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents/modify")
public class ModifyRentServlet extends HttpServlet {
	@Autowired
	RentService rentService;

	@Autowired
	VehicleService vehicleService;

	@Autowired
	ClientService clientService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO
		try {
			request.setAttribute("vehicles", vehicleService.findAll());
			request.setAttribute("clients", clientService.findAll());

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getServletContext().getRequestDispatcher("/WEB-INF/views/rents/modify.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String strIdrecup = request.getQueryString();
		strIdrecup = strIdrecup.substring(3);
		int idRecup = Integer.valueOf(strIdrecup);

		String idClient = request.getParameter("client_id");
		String idVehicule = request.getParameter("vehicle_id");

		int intidClient = Integer.parseInt(idClient);
		int intVehicule = Integer.parseInt(idVehicule);

		String dateStart = request.getParameter("begin");
		String dateEnd = request.getParameter("end");

		LocalDate strDateStart = LocalDate.parse(dateStart);
		LocalDate strDateEnd = LocalDate.parse(dateEnd);

		Reservation modifiedReservation = new Reservation(idRecup, intidClient, intVehicule, strDateStart, strDateEnd);

		try {
			request.setAttribute("modifiedReservation", this.rentService.edit(modifiedReservation));
		} catch (ServiceException e) {

		}

		this.doGet(request, response);
	}
}
