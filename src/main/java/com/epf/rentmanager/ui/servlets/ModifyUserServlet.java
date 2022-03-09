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
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users/modify")
public class ModifyUserServlet extends HttpServlet {
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

		getServletContext().getRequestDispatcher("/WEB-INF/views/users/modify.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String strIdrecup = request.getQueryString();
		strIdrecup = strIdrecup.substring(3);
		int idRecup = Integer.valueOf(strIdrecup);

		String prenom = request.getParameter("first_name");
		String nom = request.getParameter("last_name");
		String email = request.getParameter("email");
		String borndate = request.getParameter("birthdate");

		LocalDate borndatelocal = LocalDate.parse(borndate);

		Client modifiedClient = new Client(nom, prenom, email, borndatelocal, idRecup);

		try {
			request.setAttribute("modifiedClient", this.clientService.edit(modifiedClient));
		} catch (DaoException e) {

		}

		this.doGet(request, response);
	}
}
