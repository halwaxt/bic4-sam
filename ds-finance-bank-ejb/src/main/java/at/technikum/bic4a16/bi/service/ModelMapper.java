package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.entity.CompanyEntity;
import at.technikum.bic4a16.bi.entity.CustomerEntity;
import at.technikum.bic4a16.bi.entity.FinancialTransactionEntity;
import at.technikum.bic4a16.bi.model.Company;
import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransaction;

public interface ModelMapper {
    // mapping for company
    Company mapToCompany(CompanyEntity companyEntity);

    CompanyEntity mapFromCompany(Company company);

    // mapping for customer
    Customer mapToCustomer(CustomerEntity customerEntity);

    CustomerEntity mapFromCustomer(Customer customer);

    FinancialTransaction mapToFinancialTransaction(FinancialTransactionEntity financialTransactionEntity);
    FinancialTransactionEntity mapFromFinancialTransaction(FinancialTransaction financialTransaction);

}