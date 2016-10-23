package domain;

import custom_exceptions.DomainConstraintsViolationException;

/**
 * @author selld
 * @version 1.0
 * @created 10-Oct-2016 10:56:54 PM
 */
public class User {

	public User(String login, String password, String firstName, String lastName) throws DomainConstraintsViolationException {
		setFirstName(firstName);
		setLastName(lastName);
		setLogin(login);
		setPassword(password);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private int getHumanId() {
		return humanId;
	}

	private void setHumanId(int humanId) {
		this.humanId = humanId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	private void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws DomainConstraintsViolationException {
		if (password.length() < MINIMAL_PASSWORD_LENGTH) {
			throw new DomainConstraintsViolationException(
					"Got password " + password + " with length less than MINIMAL_PASSWORD_LENGTH = "
							+ MINIMAL_PASSWORD_LENGTH);
		}
		this.password = password;
	}

	private String firstName;
	private int humanId;
	private String lastName;
	private String login;
	private String password;

	protected User(){

	}

	static private final int MINIMAL_PASSWORD_LENGTH = 6;

}