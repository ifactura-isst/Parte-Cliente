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

import es.upm.dit.isst.dao.FacturasDAO;
import es.upm.dit.isst.dao.FacturasDAOImpl;
import es.upm.dit.isst.dao.*;

public class AddFactura extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		String municipio = checkNull(req.getParameter("municipio"));
		String provincia = checkNull(req.getParameter("provincia"));
		String empresa = checkNull(req.getParameter("empresa"));
		String startDate = checkNull(req.getParameter("start_date"));
		String endDate = checkNull(req.getParameter("end_date"));
		String cuotasForm = checkNull(req.getParameter("cuotas"));
		String consumosForm = checkNull(req.getParameter("consumos"));
		String sinImpuestosForm = checkNull(req.getParameter("sinImpuestos"));
		String totalForm = checkNull(req.getParameter("total"));
		String datosForm = checkNull(req.getParameter("datos"));
		String minutosForm = checkNull(req.getParameter("minutos"));
		
		if ((municipio == "" | provincia == "" | empresa == "" | startDate == "" 
				| endDate == "" | cuotasForm == "" | consumosForm == "" | sinImpuestosForm == "" 
				| totalForm == "" | datosForm == "" | minutosForm == "") || (user == null)) {
			//alert("Campos vacíos"); o prompt?
			System.out.println("Campos vacíos. Rellenar todo");
			resp.sendRedirect("/");
			
		/*} else if (!isFechaValida(startDate) || !isFechaValida(endDate)){
			System.out.println("Formato de fecha no válido: dd/mm/yyyy. Introducido: "+startDate +"-"+endDate);
			resp.sendRedirect("/");*/
			
		}else {
			Double cuotas = Double.parseDouble(cuotasForm);
			Double consumos = Double.parseDouble(consumosForm);
			Double sinImpuestos = Double.parseDouble(sinImpuestosForm);
			Double importeTotal = Double.parseDouble(totalForm);
			Double datos = Double.parseDouble(datosForm);
			Double minutos = Double.parseDouble(minutosForm);

			FacturasDAO dao = FacturasDAOImpl.getInstance();
			dao.add(empresa, startDate, endDate, cuotas, consumos, sinImpuestos, importeTotal, 
					datos, minutos, municipio, provincia, user);
			System.out.println("->Añadida factura. Provincia y municipio: "+provincia+ " "+municipio
					+". Empresa: "+empresa+ " con fecha de facturación: "+startDate+"-"+endDate
					+". Cuotas-consumos: "+cuotas+"-"+consumos+". Importe antes y después de impuestos: "
					+sinImpuestos+"-"+importeTotal+". Datos y minutos contratados: "+datos+"GB y "
					+minutos+"min.");
			resp.sendRedirect("/showFactura");
		}
	}
	
	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
	
	//Comprueba si el formato de fecha introducido es correcto
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
