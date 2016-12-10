package debug;

import custom_exceptions.DomainConstraintsViolationException;
import domain.Wood;
import domain.WoodType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.CustomerService;
import services.WoodService;
import services.services_impl.*;
import util.ApplicationContextProvider;

/**
 * Created by selld on 07.12.16.
 */
public class MainTest {

    public static void main(String[]args) {
        System.out.println("Hello world");

        try {
            Wood wood = new Wood("derevo6", WoodType.HARDWOOD, 42);

            ApplicationContext context =
                    new ClassPathXmlApplicationContext("Beans.xml");

            WoodService woodService = (WoodService) context.getBean("woodService");

            woodService.save(wood);

            CustomerService csr = (CustomerService) context.getBean("customerService");
            csr.getMostActiveCustomers(5);
        } catch (DomainConstraintsViolationException e) {
            e.printStackTrace();
        }

    }

}
