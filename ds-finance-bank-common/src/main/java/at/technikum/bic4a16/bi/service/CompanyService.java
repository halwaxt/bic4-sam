package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.Company;

import javax.ejb.Stateless;

/**
 * Created by Thomas on 25.02.2016.
 */
@Stateless
public interface CompanyService {
    // get a company by its unique symbol
    Company getCompany(String symbol);

    // get all companies
    Company[] getAllCompanies();
}
