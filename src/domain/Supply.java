package domain;

import custom_exceptions.DomainConstraintsViolationException;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author selld
 * @version 1.0
 * @created 10-Oct-2016 10:56:52 PM
 */
@Entity
public class Supply {

	public Forester getForester() {
		return forester;
	}

	private void setForester(Forester forester) {
		this.forester = forester;
	}

	public List<SupplyContent> getSupplyContent() {
		return supplyContent;
	}

	public void setSupplyContent(List<SupplyContent> supplyContent) throws DomainConstraintsViolationException {
		if (supplyContent.isEmpty()) {
            throw new DomainConstraintsViolationException(
                    "Trying to create empty supply");
		}
		WoodType supplyWoodType = supplyContent.get(0).getWood().getType();
        for (SupplyContent sc : supplyContent) {
            if (sc.getWood().getType() != supplyWoodType) {
                throw new DomainConstraintsViolationException(
                        "Not all wood in supply is of the same type: "  + supplyContent.toString());
            }
        }
		this.supplyContent = supplyContent;
	}

	public Date getSupplyDate() {
		return supplyDate;
	}

	public void setSupplyDate(Date supplyDate) throws DomainConstraintsViolationException {
		if (supplyDate.after(new Date())) {
			throw new DomainConstraintsViolationException(
					"Failed to set supply date " + supplyDate.toString() + " it is in future");
		}
		this.supplyDate = supplyDate;
	}

    private int getSupplyId() {
		return supplyId;
	}

	private void setSupplyId(int supplyId) {
		this.supplyId = supplyId;
	}

	@ManyToOne
	private Forester forester;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "i_supply")
	private List<SupplyContent> supplyContent;

	@Column(name = "date")
	private Date supplyDate;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "i_supply")
	private int supplyId;

	private Supply(){

	}

	public Supply(Forester forester, List<SupplyContent> supplyContent)
            throws DomainConstraintsViolationException {
        setForester(forester);
        setSupplyDate(new Date());
        setSupplyContent(supplyContent);
    }

}