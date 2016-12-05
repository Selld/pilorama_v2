package dao.dao_jpa;

import custom_exceptions.DomainConstraintsViolationException;
import dao.WoodDAO;
import domain.Wood;
import domain.WoodType;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by selld on 04.12.16.
 */
public class WoodDAO_JPA extends GenericDAO_JPA<Wood> implements WoodDAO  {

    public WoodDAO_JPA() {
        super.setClass(Wood.class);
    }

    @Override
    public Wood getWoodByName(String name) throws DomainConstraintsViolationException {
        return (Wood)entityManager.
                createQuery("select w from Wood w where id = " + name).
                getSingleResult();
    }

    @Override
    public List<Wood> getMostPopularWood(int count) {
        Query q = entityManager.
                createQuery("select w" +
                        " from Wood w, SupplyContent sc " +
                        "where w = sc.wood " +
                        "group by w.id " +
                        "order by sum(w.id)");
        q.setMaxResults(count);
        return q.getResultList();
    }

    @Override
    public List<Wood> getWoodByType(WoodType type) {
        Query q = entityManager.createQuery("select w from Wood w where w.type = :type");
        q.setParameter("type", type);
        return q.getResultList();
    }

    @Override
    public boolean woodExists(Wood wood) {
        Query q = entityManager.createQuery("select w from Wood w where  w.id = :id");
        q.setParameter("id", wood.getWoodsId());
        return q.getSingleResult() != null;
    }
}
