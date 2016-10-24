import dao.ForesterDAO;
import domain.Forester;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.ForesterService;
import services.services_impl.UserServiceImpl;

import java.util.List;

public class ForesterServiceImpl extends UserServiceImpl implements ForesterService {

    private ForesterDAO foresterDAO;

    public ForesterServiceImpl() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        foresterDAO = (ForesterDAO) context.getBean("foresterDAO");
    }

    @Override
    public List<Forester> getMostActiveForesters(int count) {
        return foresterDAO.getMostActiveForesters(count);
    }
}