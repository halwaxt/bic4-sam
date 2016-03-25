package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.entity.CompanyEntity;
import at.technikum.bic4a16.bi.entity.CustomerEntity;
import at.technikum.bic4a16.bi.entity.FinancialTransactionEntity;
import at.technikum.bic4a16.bi.model.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.security.PermitAll;
import javax.ejb.Singleton;

@Singleton
@PermitAll
public class DefaultModelMapper implements ModelMapper{

    @Override
    public Company toCompany(CompanyEntity companyEntity) {
        CompanyModel company = new CompanyModel();

        company.setLastTradingPrice(companyEntity.getLastTradingPrice());
        company.setName(companyEntity.getName());
        company.setStockExchange(companyEntity.getStockExchange());
        company.setSymbol(companyEntity.getSymbol());
        company.setFloatShares(companyEntity.getfloatShares());

        return company;
    }

    @Override
    public CompanyEntity toCompanyEntity(Company company) {
        CompanyEntity entity = new CompanyEntity();

        entity.setName(company.getName());
        entity.setSymbol(company.getSymbol());
        entity.setStockExchange(company.getStockExchange());
        entity.setfloatShares(company.getFloatShares());
        entity.setLastTradingPrice(company.getLastTradingPrice());

        return entity;
    }

    @Override
    public Customer toCustomer(CustomerEntity customerEntity) {
        CustomerModel customer = new CustomerModel();
        customer.setId(customerEntity.getId());
        customer.setName(customerEntity.getName());
        return customer;
    }

    @Override
    public CustomerEntity toCustomerEntity(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setName(customer.getName());
        entity.setID(customer.getId());
        return entity;
    }

    @Override
    public FinancialTransaction toFinancialTransaction(FinancialTransactionEntity entity) {
        throw new NotImplementedException();
    }

    @Override
    public FinancialTransactionEntity toFinancialTransactionEntity(FinancialTransaction financialTransaction) {
        throw new NotImplementedException();
    }
}
