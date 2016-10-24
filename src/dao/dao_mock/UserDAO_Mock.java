package dao.dao_mock;

import custom_exceptions.DomainConstraintsViolationException;
import dao.UserDAO;
import domain.Admin;
import domain.User;

/**
 * Created by selld on 25.10.16.
 */
public class UserDAO_Mock extends GenericDAO_Mock implements UserDAO {

    @Override
    public User getUserByLastName(String lastName) throws DomainConstraintsViolationException {
        return new Admin("test", "1234567", "admin", lastName);
    }

    @Override
    public User getUserByLogin(String login) throws DomainConstraintsViolationException {
        return new Admin(login, "1234567", "2229", "login");
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DomainConstraintsViolationException {
        return new Admin(login, password, "test", "test");
    }
}
