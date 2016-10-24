package services.services_impl;

import custom_exceptions.AlreadyExistsException;
import custom_exceptions.DomainConstraintsViolationException;
import dao.ProductDAO;
import domain.Product;
import domain.ProductMaterials;
import domain.Wood;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.ProductService;

import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public class ProductServiceImpl  extends GenericServiceImpl implements ProductService {

    private ProductDAO productDAO;

    public ProductServiceImpl() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        productDAO = (ProductDAO) context.getBean("productDAO");
    }

    @Override
    public List<Product> getMostPopularProduct(int count) {
        return productDAO.getMostPopularProducts(count);
    }

    @Override
    public Product getProductByName(String name) throws DomainConstraintsViolationException {
        return productDAO.getProductByName(name);
    }

    @Override
    public List<ProductMaterials> getProductMaterials(Product product) {
        return product.getMaterials();
    }

    @Override
    public List<Product> getProductsFromWood(Wood wood) throws DomainConstraintsViolationException {
        return productDAO.getProductsByWood(wood);
    }

    @Override
    public void addNewProduct(Product product) throws AlreadyExistsException, DomainConstraintsViolationException {
        if (productDAO.getProductByName(product.getName()) != null) {
            throw new AlreadyExistsException("Product with name " + product.getName() + " is already exists");
        }
        productDAO.save(product);
    }
}
