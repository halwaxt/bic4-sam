package at.technikum.bic4a16.bi.resource;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import at.technikum.bic4a16.bi.model.Action;
import at.technikum.bic4a16.bi.model.Company;
import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransaction;
import at.technikum.bic4a16.bi.model.FinancialTransactionRequest;
import at.technikum.bic4a16.bi.service.FinancialService;

/**
 * @author Thomas U.
 *
 */
@Path("/financial")
public class FinancialResource {

	@EJB
	FinancialService financialService;

	@Path("/submit")
	@POST
	@Produces("application/json")
	public FinancialTransaction submitTransaction(FinancialTransactionRequest request){
		//Call method submitTransaction from FinancialService
		return null;
	}
	
	@Path("/request")
	@POST
	@Produces("application/json")
    public FinancialTransactionRequest createRequest(Customer customer, Company company, long shares, Action action){
    	//Call method createRequest from FinancialService
    	return null;
    }
}
