package domain;

import custom_exceptions.DomainConstraintsViolationException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author selld
 * @version 1.0
 * @created 10-Oct-2016 10:56:56 PM
 */
@Entity
@DiscriminatorValue("Customer")
public class Customer extends User {

	public Customer(String login, String password, String firstName, String lastName)
			throws DomainConstraintsViolationException
	{
		super(login, password, firstName, lastName);
		this.saleList = new ArrayList<>();
	}

	public void addSale(Sale sale) {
		this.saleList.add(sale);
	}

	public List<Sale> getSaleList() {
		return saleList;
	}

	private void setSaleList(List<Sale> saleList) {
		this.saleList = saleList;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "i_user")
	private List<Sale> saleList;

	private Customer() {

	}

}