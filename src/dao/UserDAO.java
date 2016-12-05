package dao;

import custom_exceptions.DomainConstraintsViolationException;
import domain.User;

/**
 * Created by selld on 24.10.16.
 */
public interface UserDAO extends GenericDAO<User> {
    User getUserByLastName(String lastName);

    User getUserByLogin(String login);

    User getUserByLoginAndPassword(String login, String password) throws DomainConstraintsViolationException;
}
