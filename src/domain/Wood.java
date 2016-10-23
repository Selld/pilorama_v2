package domain;

import custom_exceptions.DomainConstraintsViolationException;
import domain.WoodType;

/**
 * @author selld
 * @version 1.0
 * @created 10-Oct-2016 10:56:57 PM
 */
public class Wood {

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) throws DomainConstraintsViolationException {
        if (balance < 0) {
            throw new DomainConstraintsViolationException(
                    "Trying to set strange balance " + balance + " for wood : " + this.getName());
        }
        this.balance = balance;
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

    private void setType(WoodsType type) {
        this.type = type;
    }

    private int getWoodsId() {
        return woodsId;
    }

    private void setWoodsId(int woodsId) {
        this.woodsId = woodsId;
    }

    private int balance;
	private boolean isBuying;
	private String name;
	private int price;
	private WoodType type;
	private int woodsId;

	private Wood(){

	}

    public Wood(String name, WoodType type, int price) throws DomainConstraintsViolationException {
        setIsBuying(true);
        setBalance(0);
        setName(name);
        setType(type);
        setPrice(price);
    }


}