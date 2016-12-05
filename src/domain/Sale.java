package domain;

import java.util.Date;
import java.util.List;
import custom_exceptions.DomainConstraintsViolationException;

import javax.persistence.*;

@Entity
public class Sale {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_sale")
	private List<SaleContent> saleContent;

    @Column(name = "sale_date")
    @Temporal(TemporalType.TIMESTAMP)
	private Date saleDate;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "i_sale")
	private int saleId;

    @ManyToOne
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

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