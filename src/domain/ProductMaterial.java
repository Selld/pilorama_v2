package domain;

import custom_exceptions.DomainConstraintsViolationException;

import javax.persistence.*;

/**
 * @author selld
 * @version 1.0
 * @created 10-Oct-2016 10:56:59 PM
 */

@Entity
public class ProductMaterial {

	public ProductMaterial(Wood wood, int count) throws DomainConstraintsViolationException {
		setWood(wood);
		setCount(count);
	}

	public int getCount() {
		return count;
	}

	public Wood getWood() {
		return wood;
	}

	private void setCount(int count) throws DomainConstraintsViolationException {
		if (count <= 0) {
			throw new DomainConstraintsViolationException(
					"Trying to set strange count " + count + " less or equal then zero");
		}
		this.count = count;
	}

	private int getProductMaterialsId() {
		return productMaterialsId;
	}

	private void setProductMaterialsId(int productMaterialsId) {
		this.productMaterialsId = productMaterialsId;
	}

	private void setWood(Wood wood) {
		this.wood = wood;
	}

	@Column(name = "count")
	private int count;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "i_product_material")
	private int productMaterialsId;

	@ManyToOne
	private Wood wood;

	@ManyToOne
	private  Product product;

	private ProductMaterial(){

	}

}