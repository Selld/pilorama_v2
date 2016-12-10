package services.services_impl;

import dao.ForesterDAO;
import domain.Forester;
import domain.Wood;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import services.ForesterService;
import services.services_impl.UserServiceImpl;
import util.ApplicationContextProvider;

import java.util.List;

@Transactional
public class ForesterServiceImpl extends UserServiceImpl implements ForesterService {

    private ForesterDAO foresterDAO;

    public ForesterServiceImpl() {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        foresterDAO = (ForesterDAO) applicationContext.getBean("foresterDAO");
        setGenericDAO(foresterDAO);
    }

    @Override
    public List<Forester> getMostActiveForesters(int count) {
        return foresterDAO.getMostActiveForesters(count);
    }
}