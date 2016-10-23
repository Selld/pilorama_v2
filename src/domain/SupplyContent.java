package domain;

import custom_exceptions.DomainConstraintsViolationException;

public class SupplyContent {

	public int getCount() {
		return count;
	}

	private void setCount(int count) throws DomainConstraintsViolationException {
		if (count <= 0) {
			throw new DomainConstraintsViolationException(
					"Trying to set strange count " + count + " less or equal then zero");
		}
		this.count = count;
	}

	private int getSupplyContentId() {
		return supplyContentId;
	}

	private void setSupplyContentId(int supplyContentId) {
		this.supplyContentId = supplyContentId;
	}

	public Wood getWood() {
		return wood;
	}

	private void setWood(Wood wood) {
		this.wood = wood;
	}

	private int count;
	private int supplyContentId;
	private Wood wood;

	private SupplyContent(){

	}

	public SupplyContent(Wood wood, int count) throws DomainConstraintsViolationException {
		setWood(wood);
		setCount(count);
	}

}