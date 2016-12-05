package dao.dao_jpa;

import dao.ForesterDAO;
import domain.Forester;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by selld on 05.12.16.
 */
public class ForesterDAO_JPA extends UserDAO_JPA implements ForesterDAO {
    @Override
    public List<Forester> getMostActiveForesters(int count) {
        Query q = entityManager.
                createQuery("select f" +
                        " from Forester f, Supply s " +
                        "where f = s.forester " +
                        "group by f.id " +
                        "order by sum(f.id)");
        q.setMaxResults(count);
        return q.getResultList();
    }
}
