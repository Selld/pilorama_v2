package dao.dao_jpa;

import dao.GenericDAO;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by selld on 04.12.16.
 */

@Transactional
public abstract class GenericDAO_JPA<T> implements GenericDAO<T> {
    private Class<T> clazz;

    @PersistenceContext(unitName = "piloramaPersistence")
    protected EntityManager entityManager;

    public final void setClass( Class< T > clazzToSet ){
        this.clazz = clazzToSet;
    }

    @Override
    public List<T> getAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    @Override
    public T getById(int id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public void remove(T obj) {
        entityManager.remove(obj);
    }

    @Override
    public void save(T obj) {
        entityManager.persist(obj);
    }
}
