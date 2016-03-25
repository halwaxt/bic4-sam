package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.dao.CustomerEntityDAO;
import at.technikum.bic4a16.bi.entity.CustomerEntity;
import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.CustomerModel;
import at.technikum.bic4a16.bi.model.FinancialTransaction;
import at.technikum.bic4a16.bi.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;



@Stateless
@PermitAll
public class DefaultCustomerService implements CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCustomerService.class);

    @EJB
    private CustomerEntityDAO customerEntityDAO;

    @EJB
    private ModelMapper modelMapper;

    @Override
    public Customer getCustomer(int id) {
        final CustomerEntity customerEntity = customerEntityDAO.findById(id);
        return customerEntity != null ? modelMapper.toCustomer(customerEntity) : null;
    }

    @Override
    public Customer getCustomer(String name) {
        throw new NotImplementedException();
    }

    @Override
    public Customer[] getAllCustomers() {
        throw new NotImplementedException();
    }

    @Override
    public Customer createCustomer(String name) {

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(name);

        LOGGER.info("persisted new customer. id is now: " + customerEntity.getId());

        return modelMapper.toCustomer(customerEntity);
    }

    @Override
    public FinancialTransaction[] getTransactions(Customer customer) {
        throw new NotImplementedException();
    }

    @Override
    public Stock[] getPortfolio(Customer customer) {
        throw new NotImplementedException();
    }
}
