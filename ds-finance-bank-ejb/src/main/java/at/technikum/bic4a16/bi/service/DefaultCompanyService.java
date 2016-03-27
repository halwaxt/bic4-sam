package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.dao.CompanyEntityDAO;
import at.technikum.bic4a16.bi.entity.CompanyEntity;
import at.technikum.bic4a16.bi.model.Company;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collections;
import java.util.List;

@Stateless
@PermitAll
public class DefaultCompanyService implements CompanyService{


    @EJB
    CompanyEntityDAO companyEntityDAO;


    // get a company by its unique symbol
    @Override
    public Company getCompany(String symbol) {
        return companyEntityDAO.findBySymbol(symbol);
    }

    // get all companies
    @Override
    public Company[] getAllCompanies() {
        final List<CompanyEntity> entities = companyEntityDAO.findByName("%");
        return entities.toArray(new Company[entities.size()]);
    }
}
