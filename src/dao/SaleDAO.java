package dao;

import custom_exceptions.DomainConstraintsViolationException;
import domain.Customer;
import domain.Product;
import domain.Sale;

import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public interface SaleDAO extends GenericDAO<Sale> {
    List<Sale> getSaleByCustomer(Customer customer);

    List<Sale> getSalesByProduct(Product product);
}
