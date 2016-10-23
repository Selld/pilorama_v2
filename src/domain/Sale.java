package domain;

import java.util.Date;
import java.util.List;
import custom_exceptions.DomainConstraintsViolationException;

public class Sale {

	private List<SaleContent> saleContent;
	private Date saleDate;
	private int saleId;

    public List<SaleContent> getSaleContent() {
        return saleContent;
    }

    private Sale () {

    }

    private void setSaleContent(List<SaleContent> saleContent) throws DomainConstraintsViolationException {
        if (saleContent.isEmpty()) {
            throw new DomainConstraintsViolationException(
                    "Trying to create empty sale");
        }
        this.saleContent = saleContent;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    private void setSaleDate(Date saleDate) throws DomainConstraintsViolationException {
        if (saleDate.after(new Date())) {
            throw new DomainConstraintsViolationException(
                    "Failed to set sale date " + saleDate.toString() + " it is in future");
        }
        this.saleDate = saleDate;
    }

    private int getSaleId() {
        return saleId;
    }

    private void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (SaleContent sc : saleContent) {
            totalPrice += sc.getProduct().getPrice() * sc.getCount();
        }
        return totalPrice;
    }

    public Sale(List<SaleContent> saleContent)
			throws DomainConstraintsViolationException
	{
        setSaleContent(saleContent);
        setSaleDate(new Date());
	}

}