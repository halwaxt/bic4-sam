package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.entity.CompanyEntity;
import at.technikum.bic4a16.bi.entity.CustomerEntity;
import at.technikum.bic4a16.bi.entity.FinancialTransactionEntity;
import at.technikum.bic4a16.bi.model.Company;
import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransaction;

import javax.ejb.Remote;

@Remote
public interface ModelMapper {
    // mapping for company
    Company toCompany(CompanyEntity companyEntity);

    CompanyEntity toCompanyEntity(Company company);

    // mapping for customer
    Customer toCustomer(CustomerEntity customerEntity);

    CustomerEntity toCustomerEntity(Customer customer);

    FinancialTransaction toFinancialTransaction(FinancialTransactionEntity financialTransactionEntity);
    FinancialTransactionEntity toFinancialTransactionEntity(FinancialTransaction financialTransaction);

}