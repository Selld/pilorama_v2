package services;

import custom_exceptions.DomainConstraintsViolationException;
import custom_exceptions.InvalidUserException;
import domain.Customer;
import domain.Product;
import domain.Sale;
import domain.SaleContent;

import java.util.List;

public interface SaleService extends GenericService<Sale> {

	public List<Sale> getSaleByCustomer(Customer customer) throws DomainConstraintsViolationException;

	public List<SaleContent> getSaleContent(Sale sale);

	public List<Sale> getSalesByProduct(Product product) throws DomainConstraintsViolationException;

	public boolean rollbackSale(Sale sale);

	public void makeSale(List<SaleContent> saleContents, int customerId) throws DomainConstraintsViolationException, InvalidUserException;

}