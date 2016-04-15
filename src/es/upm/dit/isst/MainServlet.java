package es.upm.dit.isst;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class MainServlet extends HttpServlet {
	
	private static final Long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		String url = userService.createLoginURL(req.getRequestURI());
		String urlLinktext = "Login";
		
		if (user != null){
		    url = userService.createLogoutURL(req.getRequestURI());
		    urlLinktext = "Logout";
		}
		
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		
		try {
			boolean admin = userService.isUserAdmin();
			if (admin) {
				RequestDispatcher view = req.getRequestDispatcher("index2.jsp");
			    view.forward(req, resp);
			} else {
				if (user != null) {
					RequestDispatcher view = req.getRequestDispatcher("index2.jsp");
			        view.forward(req, resp);
				}
			}
		} catch (IllegalStateException e) {
			System.out.println("User is not logged in");
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
	        view.forward(req, resp);
		}
		
	}
}
