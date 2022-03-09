package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.sql.SQLException;

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

@WebServlet("/users/details")
public class UserDetailsServlet extends HttpServlet {

	@Autowired
	ClientService clientService;
	@Autowired
	VehicleService vehicleService;
	@Autowired
	RentService rentService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO

		try {
			String strIdrecup = request.getQueryString();
			strIdrecup = strIdrecup.substring(3);
			int idRecup = Integer.valueOf(strIdrecup);

			request.setAttribute("client", clientService.findById(idRecup));

			request.setAttribute("reservations", rentService.findResaByClientId(idRecup));

			request.setAttribute("nbr_reservations", rentService.countresa(idRecup));

		} catch (ServiceException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);

	}

}
