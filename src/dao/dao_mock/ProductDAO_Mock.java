package dao.dao_mock;

import custom_exceptions.DomainConstraintsViolationException;
import dao.ProductDAO;
import domain.Product;
import domain.ProductMaterial;
import domain.Wood;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public class ProductDAO_Mock extends GenericDAO_Mock implements ProductDAO {
    @Override
    public List<Product> getMostPopularProducts(int count) {
        return new ArrayList<>();
    }

    @Override
    public Product getProductByName(String name) throws DomainConstraintsViolationException {
        return new Product(new ArrayList<>(), name, 1);
    }

    @Override
    public List<Product> getProductsByWood(Wood wood) throws DomainConstraintsViolationException {
        List<ProductMaterial> materials = new ArrayList<>();
        materials.add(new ProductMaterial(wood, 1));

        List<Product> products = new ArrayList<>();
        products.add(new Product(materials, "test", 1));
        return products;
    }
}
