package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.dao.CompanyEntityDAO;
import at.technikum.bic4a16.bi.entity.CompanyEntity;
import at.technikum.bic4a16.bi.model.Company;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@PermitAll
public class DefaultCompanyService implements CompanyService{


    @EJB
    CompanyEntityDAO companyEntityDAO;

    // @EJB
    //DefaultModelMapper mm;

    // get a company by its unique symbol
    @Override
    public Company getCompany(String symbol) {
        return null;
    }

    // get all companies
    @Override
    public Company[] getAllCompanies() {
        List<CompanyEntity> companyEntities = companyEntityDAO.findByName("%");

        companyEntities.add(mm.toCompany(companyEntities.get(0)));

        return companyEntities;
    }
}
