package services.services_impl;

import dao.CustomerDAO;
import domain.Customer;
import domain.Wood;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.CustomerService;

import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public class CustomerServiceImpl extends UserServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    public CustomerServiceImpl() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        customerDAO = (CustomerDAO) context.getBean("customerDAO");
        setGenericDAO(customerDAO);
    }

    public List<Customer> getMostActiveCustomers(int count) {

        return customerDAO.getMostActiveCustomers(count);
    }
}
