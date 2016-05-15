package es.upm.dit.isst;

import java.io.IOException;
import java.io.PrintWriter;
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

import es.upm.dit.isst.dao.SubastaDAO;
import es.upm.dit.isst.dao.SubastaDAOImpl;
import es.upm.dit.isst.model.Subasta;

public class ComprasColectivas extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		SubastaDAO dao = SubastaDAOImpl.getInstance();
		
		List<Subasta> subastas = dao.readSubastas();
		
		req.getSession().setAttribute("subastas", new ArrayList<Subasta>(subastas));
	
		if (user == null){
			resp.sendRedirect("/"); //Si no estoy logueado me devuelve a Inicio
		} else {
			RequestDispatcher view = req.getRequestDispatcher("ComprasColectivas.jsp");
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
		
		Long id = Long.parseLong(req.getParameter("id_subasta"));
		int usersApuntados = Integer.parseInt(req.getParameter("usuarios_apuntados"));
		int usersMax = Integer.parseInt(req.getParameter("usuarios_max"));
		
		if (user == null) {
			System.out.println("Usuario no logeado");
			resp.sendRedirect("/");
			
		}else {
			SubastaDAO dao = SubastaDAOImpl.getInstance();
			ArrayList<String> customers = dao.getCustomersOfSubasta(id);
			
			if (customers.contains(user.getEmail())){
				PrintWriter out = resp.getWriter();
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">");
				out.println("alert(\"¡Ya estás apuntado!\");");
				out.println("window.location.href=\"ComprasColectivas.jsp\";");
				out.println("</script>");
				//resp.sendRedirect("/comprasColectivas");	
			} else {
				usersApuntados++;
				customers.add(user.getEmail());
				dao.updateCustomers(id, customers);
				dao.updateUsersApuntados(id, usersApuntados);
				
				if(usersApuntados >= usersMax) {
					dao.updateState(id, true);
				}
				
				resp.sendRedirect("/comprasColectivas");
			}
		}
	}
	
}
