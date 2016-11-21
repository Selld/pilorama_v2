package domain;

import custom_exceptions.DomainConstraintsViolationException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author selld
 * @version 1.0
 * @created 10-Oct-2016 10:56:57 PM
 */
@Entity
@DiscriminatorValue("Forester")
public class Forester extends User {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_user")
	private List<Supply> supplyList;

	private Forester(){
        super();
	}

    public List<Supply> getSupplyList() {
        return supplyList;
    }

    private void setSupplyList(List<Supply> supplyList) {
        this.supplyList = supplyList;
    }

    public void addSupply(Supply supply) {
        this.supplyList.add(supply);
    }

    public Forester(String login, String password, String firstName, String lastName)
            throws DomainConstraintsViolationException
    {
        super(login, password, firstName, lastName);
        this.supplyList = new ArrayList<>();
    }

}