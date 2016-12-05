package services.services_impl;

import domain.Wood;
import services.GenericService;
import dao.GenericDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class GenericServiceImpl<T> implements GenericService<T> {

    private GenericDAO<T> genericDAO;

    @PersistenceContext
    protected EntityManager entityManager;

    protected void setGenericDAO(GenericDAO<T> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    public List<T> getAll() {
        return genericDAO.getAll();
    }

    @Override
    public T getById(int id) {
        return genericDAO.getById(id);
    }

    @Override
    public void remove(T obj) {
        genericDAO.remove(obj);
    }

    @Override
    public void save(T obj) {
        genericDAO.save(obj);
    }
}
