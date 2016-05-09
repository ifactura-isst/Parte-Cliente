package es.upm.dit.isst;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.facturas.dao.FacturasDAO;
import es.upm.dit.isst.facturas.dao.FacturasDAOImpl;
import es.upm.dit.isst.facturas.model.Factura;

public class ShowFactura extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		FacturasDAO dao = FacturasDAOImpl.getInstance();
		
		List<Factura> facturas = dao.getFacturas(user);
		req.getSession().setAttribute("facturas", new ArrayList<Factura>(facturas));
	
		if (user == null){
			resp.sendRedirect("/"); //Si no estoy logueado me devuelve a Inicio
		} else {
			RequestDispatcher view = req.getRequestDispatcher("ShowFactura.jsp");
	        try {
				view.forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		Long id = Long.parseLong(req.getParameter("id_factura"));
		
		if (user == null) {
			System.out.println("Usuario no logeado");
			resp.sendRedirect("/");
			
		}else {
			FacturasDAO dao = FacturasDAOImpl.getInstance();
			dao.delete(id);
			resp.sendRedirect("/showFactura");
		}
	}
	
}
