package at.technikum.bic4a16.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.technikum.bic4a16.servlets.AuthenticationServlet;

/**
 * Created by Thomas Unger on 07.04.2016
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter("/SessionFilter")
public class SessionFilter implements Filter {

	private AuthenticationServlet authenticationServlet;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		if (authenticationServlet == null) {
			authenticationServlet = new AuthenticationServlet();
		}

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String path = request.getRequestURI();
		if (path.equals("/ds-finance-bank-web/") || path.contains("/authenticate") || path.contains("/register") || path.contains("/companies")) {
			chain.doFilter(request, response);
		} else {
			String sessionid = null;
			Boolean skip = false;
			Cookie[] cookies = null;

			String headerSession = request.getHeader("sessionid");
			if (headerSession != null) {
				sessionid = headerSession;
			} else if ((cookies = request.getCookies()) != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("sessionid")) {
						sessionid = cookies[i].getValue();
						break;
					}
				}
			} else {
				sessionid = request.getParameter("sessionid");
			}
			if (request.getParameter("skip") != null) {
				skip = true;
			}

			System.out.println("SESSION ID=" + sessionid);
			if (sessionid != null || skip) {
				// compare to sessionid stored in user
				if (authenticationServlet.userSessionMap.containsKey(sessionid) || skip) {
					chain.doFilter(request, response);
				} else {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				}
			} else {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("INIT SessionFilter");
	}

}
