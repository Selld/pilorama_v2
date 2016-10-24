package services;

import domain.Customer;

import java.util.List;


/**
 * @author selld
 * @version 1.0
 * @created 12-Oct-2016 12:48:06 AM
 */
public interface CustomerService extends UserService {
	/**
	 * 
	 * @param count
	 */
	List<Customer> getMostActiveCustomers(int count);

}