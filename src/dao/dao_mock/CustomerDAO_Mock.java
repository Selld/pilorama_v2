package dao.dao_mock;

import dao.CustomerDAO;
import domain.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public class CustomerDAO_Mock extends GenericDAO_Mock implements CustomerDAO {
    @Override
    public List<Customer> getMostActiveCustomers(int count) {
        return new ArrayList<>();
    }
}
