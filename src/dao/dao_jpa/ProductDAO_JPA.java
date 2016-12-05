package dao.dao_jpa;

import custom_exceptions.DomainConstraintsViolationException;
import domain.ProductMaterial;
import dao.ProductDAO;
import domain.Product;
import domain.Wood;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by selld on 05.12.16.
 */
public class ProductDAO_JPA extends GenericDAO_JPA<Product> implements ProductDAO {
    @Override
    public List<Product> getMostPopularProducts(int count) {
        Query q = entityManager.
                createQuery("select p" +
                        " from Product p, SaleContent sc " +
                        "where p = sc.product " +
                        "group by p " +
                        "order by sum(p.id)");
        q.setMaxResults(count);
        return q.getResultList();
    }

    @Override
    public Product getProductByName(String name) {
        Query q = entityManager.createQuery("select p from Product p where p.name = :name");
        q.setParameter("name", name);
        return (Product) q.getSingleResult();
    }

    @Override
    public List<Product> getProductsByWood(Wood wood) {
        Query q = entityManager.
                createQuery("select p " +
                        "from Product p, ProductMaterial pm " +
                        "where pm.wood = :wood and pm.product = p");
        q.setParameter("wood", wood);
        return q.getResultList();
    }
}
