package at.technikum.bic4a16.bi.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import at.technikum.bic4a16.bi.model.Company;

/**
 * @author Patrik
 */

@Path("/company")
public class CompanyResource {

	@Path("/{name}")
	@GET
	@Produces("application/json")
	public Company getCompanyBySymbol(@PathParam("name") String symbol) {
		//Call method getCompanyBySymbol from CompanyService
		return null;
	}
	
	@Path("/")
	@GET
	@Produces("application/json")
	public List<Company> getAllCompanys() {
		//Call method getAllCompanys from CompanyService
		return null;
	}
}