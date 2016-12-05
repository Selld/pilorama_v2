package dao.dao_jpa;

import dao.CustomerDAO;
import domain.Customer;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by selld on 05.12.16.
 */
public class CustomerDAO_JPA extends UserDAO_JPA implements CustomerDAO {
    @Override
    public List<Customer> getMostActiveCustomers(int count) {
        Query q = entityManager.
                createQuery("select c" +
                        " from Customer c, Sale sc " +
                        "where c = sc.customer " +
                        "group by c.id " +
                        "order by sum(c.id)");
        q.setMaxResults(count);
        return q.getResultList();
    }
}
