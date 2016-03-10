package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.entity.CompanyEntity;
import at.technikum.bic4a16.bi.entity.CustomerEntity;
import at.technikum.bic4a16.bi.entity.FinancialTransactionEntity;
import at.technikum.bic4a16.bi.model.Company;
import at.technikum.bic4a16.bi.model.CompanyModel;
import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransaction;

import javax.ejb.Singleton;

@Singleton
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
        return null;
    }

    @Override
    public CustomerEntity toCustomerEntity(Customer customer) {
        return null;
    }

    @Override
    public FinancialTransaction toFinancialTransaction(FinancialTransactionEntity financialTransactionEntity) {
        return null;
    }

    @Override
    public FinancialTransactionEntity toFinancialTransactionEntity(FinancialTransaction financialTransaction) {
        return null;
    }
}
