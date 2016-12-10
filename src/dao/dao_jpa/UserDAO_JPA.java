package dao.dao_jpa;

import custom_exceptions.DomainConstraintsViolationException;
import dao.UserDAO;
import domain.User;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

/**
 * Created by selld on 04.12.16.
 */
@Transactional
public class UserDAO_JPA extends GenericDAO_JPA<User> implements UserDAO {

    public UserDAO_JPA() {
        super.setClass(User.class);
    }

    @Override
    public User getUserByLastName(String lastName) {
        Query q = entityManager.createQuery("select u from User u where u.lastName = :lastname");
        q.setParameter("lastname", lastName);
        return (User) q.getSingleResult();
    }

    @Override
    public User getUserByLogin(String login) {
        Query q = entityManager.createQuery("select u from User u where u.login = :login");
        q.setParameter("login", login);
        return (User) q.getSingleResult();
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        Query q = entityManager.createQuery("select u from User u where u.login = :login and u.password = :password");
        q.setParameter("login", login);
        q.setParameter("password", password);
        return (User) q.getSingleResult();
    }
}
