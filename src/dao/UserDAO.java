package dao;

import custom_exceptions.DomainConstraintsViolationException;
import domain.User;

/**
 * Created by selld on 24.10.16.
 */
public interface UserDAO extends GenericDAO {
    User getUserByLastName(String lastName) throws DomainConstraintsViolationException;

    User getUserByLogin(String login) throws DomainConstraintsViolationException;

    User getUserByLoginAndPassword(String login, String password) throws DomainConstraintsViolationException;
}
