package domain;

import custom_exceptions.DomainConstraintsViolationException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author selld
 * @version 1.0
 * @created 10-Oct-2016 10:56:55 PM
 */

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User {

	private Admin() {
		super();
	}

	public Admin(String login, String password, String firstName, String lastName)
			throws DomainConstraintsViolationException
	{
		super(login, password, firstName, lastName);
	}

}