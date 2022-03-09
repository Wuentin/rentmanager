package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.RentService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents")

public class RentListServlet extends HttpServlet {

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
			request.setAttribute("reservations", rentService.findAll());
			String strIdClient = request.getParameter("id_client");
			
			//Partie pour afficher un nom de client et non pas un id client
			// System.out.println(strIdClient);
			// if (strIdClient != null) {
			/// int idClientRecup = Integer.valueOf(strIdClient);

			// request.setAttribute("clients", clientService.findById(2));

			// }

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getServletContext().getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String strIdrecup = request.getParameter("id");
			int idRecup = Integer.valueOf(strIdrecup);
			this.rentService.delete(idRecup);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.doGet(request, response);

	}

}