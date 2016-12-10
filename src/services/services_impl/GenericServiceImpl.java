package services.services_impl;

import org.springframework.transaction.annotation.Transactional;
import services.GenericService;
import dao.GenericDAO;

import java.util.List;

@Transactional
public abstract class GenericServiceImpl<T> implements GenericService<T> {

    private GenericDAO<T> genericDAO;

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
