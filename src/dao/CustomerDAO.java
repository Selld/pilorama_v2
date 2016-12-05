package dao;

import domain.Customer;

import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public interface CustomerDAO extends UserDAO {
    List<Customer> getMostActiveCustomers(int count);
}
