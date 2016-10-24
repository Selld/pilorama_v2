package services.services_impl;

import custom_exceptions.AlreadyExistsException;
import custom_exceptions.DomainConstraintsViolationException;
import dao.UserDAO;
import domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.UserService;

/**
 * Created by selld on 24.10.16.
 */
public class UserServiceImpl extends GenericServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        userDAO = (UserDAO) context.getBean("userDAO");
    }

    @Override
    public User getUserByLastName(String lastName) throws DomainConstraintsViolationException {
        return userDAO.getUserByLastName(lastName);
    }

    @Override
    public User getUserByLogin(String login) throws DomainConstraintsViolationException {
        return userDAO.getUserByLogin(login);
    }

    @Override
    public User authenticateUser(String login, String password) throws DomainConstraintsViolationException {
        return  userDAO.getUserByLoginAndPassword(login, password);
    }

    @Override
    public void registerUser(User user) throws AlreadyExistsException, DomainConstraintsViolationException {
        if (userDAO.getUserByLogin(user.getLogin()) != null) {
            throw new AlreadyExistsException("User with login " + user.getLogin() + " already exists");
        }
        userDAO.save(user);
    }
}
