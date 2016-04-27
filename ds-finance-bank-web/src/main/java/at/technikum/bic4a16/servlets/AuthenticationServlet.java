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
		ResponseHelper.SetAccessControlHeaders(response);
		response.setContentType("application/json");

		String username = request.getParameter("username");
		if (username == null || username.length() == 0) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			LOG.warn("no username found");
			return;
		}

		String password = request.getParameter("password");
		if (password == null || password.length() == 0) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			LOG.warn("no password found");
			return;
		}

		try {
			User user = null;
			if ((user = authenticationService.authenticate(username, password)) == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				LOG.warn("no user found or wrong password for " + username);
				return;
			}

			HttpSession session = request.getSession();

			String sessionId = session.getId();
			user.setSessionId(sessionId);

			userSessionMap.put(sessionId, user);

			Cookie sessionCookie = new Cookie("sessionid", sessionId);
			response.addCookie(sessionCookie);

			PrintWriter out = response.getWriter();
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.writeValue(out, user);

			out.flush();
			out.close();

		} catch (Exception e2) {
			LOG.warn("authentication failed", e2);
		}
	}
}
