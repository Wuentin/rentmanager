package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users/create")
public class CreateUserServlet extends HttpServlet {

	@Autowired
	ClientService newClientService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO

		getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nom = request.getParameter("last_name");
		String prenom = request.getParameter("first_name");
		String email = request.getParameter("email");
		String borndate = request.getParameter("birthdate");

		LocalDate borndatelocal = LocalDate.parse(borndate);

//   		Tentative pour vérifier que le nouveau client ait bien 18 ans
//        LocalDate date=LocalDate.now();       
//        Period period  = Period.between(borndatelocal, date);
//        System.out.println(period.getYears());
//        
//        if (period.getYears()>18) {}

		Client addClient = new Client(nom, prenom, email, borndatelocal, 0);

		try {
			this.newClientService.create(addClient);

		} catch (ServiceException e) {

		}

		this.doGet(request, response);

	}

}
