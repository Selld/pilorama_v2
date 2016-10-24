package services.services_impl;

import services.GenericService;
import dao.GenericDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public abstract class GenericServiceImpl implements GenericService {

    private GenericDAO genericDAO;

    public GenericServiceImpl() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        genericDAO = (GenericDAO) context.getBean("genericDAO");
    }

    @Override
    public <T> List<T> getAll() {
        return genericDAO.getAll();
    }

    @Override
    public <T> T getById(int id) {
        return genericDAO.getById(id);
    }

    @Override
    public <T> boolean remove(T obj) {
        return genericDAO.remove(obj);
    }

    @Override
    public <T> boolean save(T obj) {
        return genericDAO.save(obj);
    }
}
