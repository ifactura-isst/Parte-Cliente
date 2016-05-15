package es.upm.dit.isst;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.dao.SubastaDAO;
import es.upm.dit.isst.dao.SubastaDAOImpl;

public class ComprasColectivasUncheck extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		Long id = Long.parseLong(req.getParameter("id_subasta"));
		int usersApuntados = Integer.parseInt(req.getParameter("usuarios_apuntados"));
		
		if (user == null) {
			System.out.println("Usuario no logeado");
			resp.sendRedirect("/");
			
		}else {
			SubastaDAO dao = SubastaDAOImpl.getInstance();
			ArrayList<String> customers = dao.getCustomersOfSubasta(id);
			
			if (!customers.contains(user.getEmail())){
				PrintWriter out = resp.getWriter();
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">");
				out.println("alert(\"¡Aún no estás apuntado!\");");
				out.println("window.location.href=\"ComprasColectivas.jsp\";");
				out.println("</script>");
				//resp.sendRedirect("/comprasColectivas");	
			} else {
				usersApuntados--;
				customers.remove(user.getEmail());
				dao.updateCustomers(id, customers);
				dao.updateUsersApuntados(id, usersApuntados);
				
				resp.sendRedirect("/comprasColectivas");
			}
		}
	}

}
