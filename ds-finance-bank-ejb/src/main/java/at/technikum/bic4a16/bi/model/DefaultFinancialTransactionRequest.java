package at.technikum.bic4a16.bi.model;

public class DefaultFinancialTransactionRequest implements FinancialTransactionRequest {
    
    private Company company;
    private Customer customer;
    private int numberOfShares;
    private Action action;

    @Override
    public Company getCompany() {
        return this.company;
    }

    @Override
    public Customer getCustomer() {
        return this.customer;
    }

    @Override
    public int getNumberOfShares() {
        return this.numberOfShares;
    }

    @Override
    public Action getAction() {
        return this.action;
    }

    @Override
    public String toString() {
        return this.customer.getName() + " " + this.action + "s " + numberOfShares + " shares of " + company.getName();
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
