package dao;

import custom_exceptions.DomainConstraintsViolationException;
import domain.Product;
import domain.Sale;

import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public interface SaleDAO extends GenericDAO {
    List<Sale> getSaleByCustomer() throws DomainConstraintsViolationException;

    List<Sale> getSalesByProduct(Product product) throws DomainConstraintsViolationException;
}
