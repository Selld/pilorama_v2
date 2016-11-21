package domain;

import custom_exceptions.DomainConstraintsViolationException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author selld
 * @version 1.0
 * @created 10-Oct-2016 10:56:54 PM
 */

@Entity
@DiscriminatorValue("Worker")
public class Worker extends User {
    private Worker() {
        super();
    }

    public Worker(String login, String password, String firstName, String lastName)
            throws DomainConstraintsViolationException
    {
        super(login, password, firstName, lastName);
    }
}