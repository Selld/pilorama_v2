package domain;

import custom_exceptions.DomainConstraintsViolationException;

/**
 * @author selld
 * @version 1.0
 * @created 10-Oct-2016 10:56:59 PM
 */
public class ProductMaterials {

	public ProductMaterials(Wood wood, int count) {
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

	private int count;
	private int productMaterialsId;
	private Wood wood;

	private ProductMaterials(){

	}

}