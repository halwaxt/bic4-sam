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

    @EJB
    ModelMapper modelMapper;

    // get a company by its unique symbol
    @Override
    public Company getCompany(String symbol) {
        final CompanyEntity companyEntity = companyEntityDAO.findBySymbol(symbol);
        return companyEntity != null ? modelMapper.toCompany(companyEntity) : null;
    }

    // get all companies
    @Override
    public Company[] getAllCompanies() {
        final List<CompanyEntity> companyEntities = companyEntityDAO.findByName("%");

        return companyEntities.stream()
            .map(entity -> modelMapper.toCompany(entity))
            .toArray(size -> new Company[companyEntities.size()]);
    }
}
