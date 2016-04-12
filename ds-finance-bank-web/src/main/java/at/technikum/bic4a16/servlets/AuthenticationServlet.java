package at.technikum.bic4a16.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.technikum.bic4a16.bi.model.User;
import at.technikum.bic4a16.bi.service.AuthenticationService;
import at.technikum.bic4a16.bi.service.CustomerService;

/**
 * Created by Michael on 28.03.2016.
 */
@SessionScoped
public class AuthenticationServlet extends HttpServlet {
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationServlet.class);

	@EJB
	AuthenticationService authenticationService;
	
	@EJB
	CustomerService customerService;

	public static Map<String, User> userSessionMap = new HashMap<String, User>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();

		String n = request.getParameter("username");
		String p = request.getParameter("password");

		try {
			User user = null;
			if ((user = authenticationService.authenticate(n, p)) != null) {
				HttpSession session = request.getSession();
				String sessionId = session.getId();
				user.setSessionId(sessionId);

				userSessionMap.put(sessionId, user);

				Cookie sessionCookie = new Cookie("sessionid", sessionId);
				response.addCookie(sessionCookie);

				objectMapper.writeValue(out, user);

			} else {
				response.setStatus(400);
			}
		} catch (Exception e2) {
			System.out.println(e2);
		}

		out.close();

	}
}
