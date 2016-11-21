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
public class Wood {

    public int getWoodCount() {
        return woodCount;
    }

    public void setWoodCount(int woodCount) throws DomainConstraintsViolationException {
        if (woodCount < 0) {
            throw new DomainConstraintsViolationException(
                    "Trying to set strange woodCount " + woodCount + " for wood : " + this.getName());
        }
        this.woodCount = woodCount;
    }

    public boolean isBuying() {
        return isBuying;
    }

    public void setIsBuying(boolean isBuying) {
        this.isBuying = isBuying;
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

    public WoodType getType() {
        return type;
    }

    private void setType(WoodType type) {
        this.type = type;
    }

    private int getWoodsId() {
        return woodsId;
    }

    private void setWoodsId(int woodsId) {
        this.woodsId = woodsId;
    }

    @Column(name = "wood_count")
    private int woodCount;

    @Column(name = "is_buying")
    private boolean isBuying;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "price")
	private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private WoodType type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_wood")
    List<ProductMaterial> materialList = new ArrayList<ProductMaterial>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_wood")
    List<SupplyContent> supplyContentList = new ArrayList<SupplyContent>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "i_wood")
	private int woodsId;

	private Wood(){

	}

    public Wood(String name, WoodType type, int price) throws DomainConstraintsViolationException {
        setIsBuying(true);
        setWoodCount(0);
        setName(name);
        setType(type);
        setPrice(price);
    }


}