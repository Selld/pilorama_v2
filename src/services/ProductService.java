package services;

import custom_exceptions.AlreadyExistsException;
import custom_exceptions.DomainConstraintsViolationException;
import domain.Product;
import domain.ProductMaterials;
import domain.Wood;

import java.util.List;

public interface ProductService extends GenericService {

	List<Product> getMostPopularProduct(int count);

	Product getProductByName(String name) throws DomainConstraintsViolationException;

	List<ProductMaterials> getProductMaterials(Product product);

	List<Product> getProductsFromWood(Wood wood) throws DomainConstraintsViolationException;

	void addNewProduct(Product product) throws AlreadyExistsException, DomainConstraintsViolationException;

}