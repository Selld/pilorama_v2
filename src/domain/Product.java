package domain;

import custom_exceptions.DomainConstraintsViolationException;

import java.util.List;

/**
 * @author selld
 * @version 1.0
 * @created 10-Oct-2016 10:56:59 PM
 */
public class Product {

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public List<ProductMaterials> getMaterials() {
		return materials;
	}

	private void setMaterials(List<ProductMaterials> materials) throws DomainConstraintsViolationException {
		for (ProductMaterials m : materials) {
			if (!m.getWood().isBuying()) {
				throw new DomainConstraintsViolationException(
						"Cannot create product from wood which is not buying now" + m.getWood().getName());
			}
		}
		this.materials = materials;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	private void setPrice(int price) throws DomainConstraintsViolationException {
		if (price <= 0) {
			throw new DomainConstraintsViolationException(
					"Trying to set strange price " + price + " for wood : " + this.getName());
		}
		this.price = price;
	}

	private int getProductId() {
		return productId;
	}

	private void setProductId(int productId) {
		this.productId = productId;
	}

	public Product(List<ProductMaterials> materials, String name, int price) throws DomainConstraintsViolationException {
		setBalance(0);
		setMaterials(materials);
		setPrice(price);
		setName(name);
	}

	private int balance;
	private List<ProductMaterials> materials;
	private String name;
	private int price;
	private int productId;

	private Product(){

	}

}