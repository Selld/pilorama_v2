package dao.dao_jpa;

import dao.GenericDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by selld on 04.12.16.
 */
public abstract class GenericDAO_JPA<T> implements GenericDAO<T> {
    private Class<T> clazz;

    @PersistenceContext
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
