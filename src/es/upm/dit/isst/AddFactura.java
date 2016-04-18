package es.upm.dit.isst;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
		
		String nombre = checkNull(req.getParameter("name"));
		String apellidos = checkNull(req.getParameter("surname"));
		String municipio = checkNull(req.getParameter("municipio"));
		String provincia = checkNull(req.getParameter("provincia"));
		String empresa = checkNull(req.getParameter("empresa"));
		String startDate = checkNull(req.getParameter("start_date"));
		String endDate = checkNull(req.getParameter("end_date"));
		String tipo = checkNull(req.getParameter("tipoFactura"));
		String importe = checkNull(req.getParameter("total"));
		
		if ((nombre == "" | apellidos == "" | municipio == "" | provincia == "" 
				| empresa == "" | startDate == "" | endDate == "" | tipo == "" | importe == "") || (user == null)) {
			//alert("Campos vacíos");
			System.out.println("Campos vacíos. Rellenar todo");
			resp.sendRedirect("/");
			
		} else if (!isFechaValida(startDate) || !isFechaValida(endDate)){
			System.out.println("Formato de fecha no válido: dd/mm/yyyy. Introducido: "+startDate +"-"+endDate);
			resp.sendRedirect("/");
			
		}else {
			Double importeTotal = Double.parseDouble(importe);

			FacturasDAO dao = FacturasDAOImpl.getInstance();
			dao.add(nombre, apellidos, tipo, empresa, startDate, endDate, importeTotal, municipio, provincia, user);
			System.out.println("->Añadida factura de tipo "+tipo+" de "+nombre+" "+apellidos
					+". Provincia y municipio: "+provincia+ " "+municipio
					+". Empresa: "+empresa+ " con fecha de facturación: "+startDate+"-"+endDate
					+". Importe total: "+importe+"€");
			resp.sendRedirect("/showFactura");
		}
	}
	
	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
	
	public static boolean isFechaValida(String fecha) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
			formatoFecha.setLenient(false);
			formatoFecha.parse(fecha);
		} catch (ParseException e) {
			return false;
		}
		return true;
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
