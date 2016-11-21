package domain;

import custom_exceptions.DomainConstraintsViolationException;

import javax.persistence.*;
import java.util.List;

/**
 * @author selld
 * @version 1.0
 * @created 10-Oct-2016 10:56:59 PM
 */

@Entity
public class Product {

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public List<ProductMaterial> getMaterials() {
		return materials;
	}

	private void setMaterials(List<ProductMaterial> materials) throws DomainConstraintsViolationException {
		for (ProductMaterial m : materials) {
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

	public Product(List<ProductMaterial> materials, String name, int price) throws DomainConstraintsViolationException {
		setProductCount(0);
		setMaterials(materials);
		setPrice(price);
		setName(name);
	}

	@Column(name = "product_count")
	private int productCount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_product_material")
	private List<ProductMaterial> materials;

    @Column(name = "name", unique = true)
	private String name;

	@Column(name = "price")
	private int price;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "i_product")
	private int productId;

	private Product(){

	}

}