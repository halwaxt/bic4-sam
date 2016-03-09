package at.technikum.bic4a16.bi.model;

public class DefaultFinancialTransactionrequest implements FinancialTransactionRequest{

    public DefaultFinancialTransactionrequest() {

    }


    @Override
    public Company getCompany() {
        return null;
    }

    @Override
    public Customer getCustomer() {
        return null;
    }

    @Override
    public long getNumberOfShares() {
        return 0;
    }


	@Override
	public Action getAction() {
		// TODO Auto-generated method stub
		return null;
	}
}
