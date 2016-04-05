package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.dao.CustomerEntityDAO;
import at.technikum.bic4a16.bi.dao.FinancialTransactionDAO;
import at.technikum.bic4a16.bi.entity.CustomerEntity;
import at.technikum.bic4a16.bi.entity.FinancialTransactionEntity;
import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransaction;
import at.technikum.bic4a16.bi.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;


@Stateless
@PermitAll
public class DefaultCustomerService implements CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCustomerService.class);

    @EJB
    CustomerEntityDAO customerEntityDAO;

    @EJB
    FinancialTransactionDAO financialTransactionDAO;

    @Override
    public Customer getCustomer(Integer id) {
        return customerEntityDAO.get(id);
    }

    @Override
    public Customer[] getCustomer(String name) {
        final List<CustomerEntity> entities = customerEntityDAO.findByName(name);
        return entities.toArray(new Customer[entities.size()]);
    }

    @Override
    public Customer[] getAllCustomers() {
        final List<CustomerEntity> entities = customerEntityDAO.getAll();
        return entities.toArray(new Customer[entities.size()]);
    }

    @Override
    public Customer createCustomer(String name) {

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(name);

        customerEntityDAO.persist(customerEntity);
        LOGGER.info("persisted new customer. id is now: " + customerEntity.getId());

        return customerEntity;
    }

    @Override
    public FinancialTransaction[] getTransactions(Customer customer) {
        final List<FinancialTransactionEntity> entities = financialTransactionDAO.findByCustomer((CustomerEntity) customer);
        return entities.toArray(new FinancialTransaction[entities.size()]);
    }

    @Override
    public Stock[] getPortfolio(Customer customer) {
        final List<Stock> portfolio = financialTransactionDAO.getPortfolio((CustomerEntity) customer);
        return portfolio.toArray(new Stock[portfolio.size()]);
    }
}
