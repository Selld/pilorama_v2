package services.services_impl;

import dao.CustomerDAO;
import domain.Customer;
import domain.Wood;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import services.CustomerService;
import util.ApplicationContextProvider;

import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
@Transactional
public class CustomerServiceImpl extends UserServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    public CustomerServiceImpl() {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        customerDAO = (CustomerDAO) applicationContext.getBean("customerDAO");
        setGenericDAO(customerDAO);
    }

    public List<Customer> getMostActiveCustomers(int count) {
        return customerDAO.getMostActiveCustomers(count);
    }
}
