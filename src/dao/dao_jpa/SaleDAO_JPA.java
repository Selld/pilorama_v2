package dao.dao_jpa;

import domain.SaleContent;
import dao.SaleDAO;
import domain.Customer;
import domain.Product;
import domain.Sale;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by selld on 05.12.16.
 */
@Transactional
public class SaleDAO_JPA extends GenericDAO_JPA<Sale> implements SaleDAO {
    @Override
    public List<Sale> getSaleByCustomer(Customer customer) {
        Query q = entityManager.createQuery("select s from Sale s where s.customer = :customer");
        q.setParameter("customer", customer);
        return  q.getResultList();
    }

    @Override
    public List<Sale> getSalesByProduct(Product product) {
        Query q = entityManager.
                createQuery("select distinct (s) " +
                        "from Sale s, SaleContent sc " +
                        "where sc.sale = s and sc.product = :product");
        q.setParameter("product", product);
        return q.getResultList();
    }
}
