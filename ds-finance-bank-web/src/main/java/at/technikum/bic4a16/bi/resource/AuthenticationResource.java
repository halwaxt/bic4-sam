package at.technikum.bic4a16.bi.resource;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import at.technikum.bic4a16.bi.model.User;

/**
 * @author Thomas U.
 *
 */
@Path("/user")
public class AuthenticationResource {
	@Path("/authenticate")
	@POST
	@Produces("application/json")
	public User authenticate(@PathParam("username") String username, @PathParam("password") String password) {
		// Call authenticate method from AuthenticationService
		return null;
	}

	@Path("/")
	@POST
	@Produces("application/json")
	public User createUser(@PathParam("username") String username, @PathParam("password") String password,
			@PathParam("isEmployee") Boolean isEmployee) {
		// Call createUser method from AuthenticationService
		return null;
	}

	@Path("/")
	@PUT
	public void updateUser(@PathParam("user") User user) {
		// Call updateUser method from AuthenticationService
	}

}
