package dao.dao_jpa;

import custom_exceptions.DomainConstraintsViolationException;
import dao.SupplyDAO;
import domain.Forester;
import domain.Supply;
import domain.Wood;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by selld on 04.12.16.
 */
public class SupplyDAO_JPA extends GenericDAO_JPA<Supply> implements SupplyDAO {

    public SupplyDAO_JPA() {
        super.setClass(Supply.class);
    }

    @Override
    public List<Supply> getSupplyByDate(Date date) {
        Query q = entityManager.createQuery("select s from Supply s where  s.supplyDate = :date");
        q.setParameter("date", date);
        return q.getResultList();
    }

    @Override
    public List<Supply> getSupplyByForester(Forester forester) {
        Query q = entityManager.createQuery("select s from Supply s where  s.forester = :forester");
        q.setParameter("forester", forester);
        return q.getResultList();
    }

    @Override
    public List<Supply> getSupplyByWood(Wood wood) {
        Query q = entityManager.createQuery("select DISTINCT (sc.supply) from SupplyContent sc where  sc.wood = :wood");
        q.setParameter("wood", wood);
        return q.getResultList();
    }
}
