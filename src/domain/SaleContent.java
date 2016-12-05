package domain;

import custom_exceptions.DomainConstraintsViolationException;

import javax.persistence.*;

/**
 * @author selld
 * @version 1.0
 * @created 10-Oct-2016 10:57:01 PM
 */
@Entity
public class SaleContent {
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

	public Product getProduct() {
		return product;
	}

	private void setProduct(Product product) {
		this.product = product;
	}

	private int getSaleContentId() {
		return saleContentId;
	}

	private void setSaleContentId(int saleContentId) {
		this.saleContentId = saleContentId;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	@Column(name = "count")
	private int count;

	@ManyToOne
	private Product product;

	@ManyToOne
	private Sale sale;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "i_sale_content")
	private int saleContentId;

    private SaleContent(){

	}

	public SaleContent(Product product, int count) throws DomainConstraintsViolationException {
        setProduct(product);
        setCount(count);
	}

}