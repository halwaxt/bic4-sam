package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.Company;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

@Stateless
@PermitAll
public class DefaultCompanyService implements CompanyService{
    // get a company by its unique symbol
    @Override
    public Company getCompany(String symbol) {
        return null;
    }

    // get all companies
    @Override
    public Company[] getAllCompanies() {
        return new Company[0];
    }
}
