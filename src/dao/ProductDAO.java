package dao;

import custom_exceptions.DomainConstraintsViolationException;
import domain.Product;
import domain.Wood;

import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public interface ProductDAO extends GenericDAO {
    List<Product> getMostPopularProducts(int count);

    Product getProductByName(String name) throws DomainConstraintsViolationException;

    List<Product> getProductsByWood(Wood wood) throws DomainConstraintsViolationException;
}
