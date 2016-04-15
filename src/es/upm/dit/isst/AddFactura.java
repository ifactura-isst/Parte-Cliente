package es.upm.dit.isst;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.swing.JOptionPane;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import es.upm.dit.isst.facturas.dao.*;

public class AddFactura extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		String nombre = req.getParameter("name");
		String apellidos = req.getParameter("surname");
		String municipio = req.getParameter("municipio");
		String provincia = req.getParameter("provincia");
		String empresa = req.getParameter("empresa");
		//String startDate = req.getParameter("start_date");
		//String endDate = req.getParameter("end_date");
		String tipo = req.getParameter("tipoFactura");
		Long importeTotal = Long.parseLong(req.getParameter("total"));
		
		if (nombre == "" | apellidos == "" | municipio == "" | provincia == "" 
				| empresa == "" | tipo == "" | importeTotal.equals(null)) {
			//alert("Campos vacíos");
			resp.sendRedirect("/");
		}else {
			FacturasDAO dao = FacturasDAOImpl.getInstance();
			dao.add(nombre, apellidos, tipo, empresa, importeTotal, municipio, provincia, user);
		
			resp.sendRedirect("/showFactura");
		}
	}
	
	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
	
		if (user == null){
			resp.sendRedirect("/"); //Si no estoy logueado me devuelve a Inicio
		} else{
			RequestDispatcher view = req.getRequestDispatcher("AddFactura.jsp");
	        try {
				view.forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
	}

}
