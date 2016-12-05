package services;

import custom_exceptions.AlreadyExistsException;
import custom_exceptions.DomainConstraintsViolationException;
import domain.User;

public interface UserService extends GenericService<User> {

	User getUserByLastName(String lastName) throws DomainConstraintsViolationException;

	User getUserByLogin(String login) throws DomainConstraintsViolationException;

	User authenticateUser(String login, String password) throws DomainConstraintsViolationException;

	void registerUser(User user) throws AlreadyExistsException, DomainConstraintsViolationException;

}