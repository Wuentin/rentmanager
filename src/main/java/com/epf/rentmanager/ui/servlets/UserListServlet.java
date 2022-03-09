package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users")

public class UserListServlet extends HttpServlet {
	// ClientService clientService = ClientService.getInstance();
//	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
//	ClientService clientService = context.getBean(ClientService.class);
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
			request.setAttribute("clients", clientService.findAll());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp") ;
//		requestDispatcher.forward(request, response) ;
		getServletContext().getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String strIdrecup = request.getParameter("id");
			int idRecup = Integer.valueOf(strIdrecup);
			this.clientService.delete(idRecup);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.doGet(request, response);

	}

}