package at.technikum.bic4a16.bi.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransaction;
import at.technikum.bic4a16.bi.model.Stock;

/**
 * 
 * @author Thomas U.
 *
 */
@Path("/customer")
public class CustomerResource {
	
	@Path("/{id}")
	@GET
	@Produces("application/json")
	public Customer getCustomer(@PathParam("id") int id) {
		//Call method getCustomer from CustomerService
		return null;
	}

	@Path("/{name}")
	@GET
	@Produces("application/json")
	public Customer getCustomer(@PathParam("name") String name) {
		//Call method createCustomer from CustomerService
		return null;
	}

	@Path("/")
	@GET
	@Produces("application/json")
	public Customer[] getAllCustomers() {
		//Call method getAllCustomers from CustomerService
		return null;
	}

	@Path("/")
	@POST
	@Produces("application/json")
	public Customer createCustomer(String name) {
		//Call method createCustomer from CustomerService
		return null;
	}

	@Path("/{id}/financialTransaction")
	@GET
	@Produces("application/json")
	public FinancialTransaction[] getTransactions(Customer customer) {
		//Call method getTransactions from CustomerService
		return null;
	}

	@Path("/{id}/portfolio")
	@GET
	@Produces("application/json")
	public Stock[] getPortfolio(Customer customer) {
		//Call method getPortfolio from CustomerService
		return null;
	}
}
