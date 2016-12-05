package services;

import custom_exceptions.DomainConstraintsViolationException;
import domain.Supply;
import javassist.NotFoundException;

import java.util.Date;
import java.util.List;

public interface SupplyService extends GenericService<Supply> {

	List<Supply> getSupplyByDate(Date date) throws DomainConstraintsViolationException;

	List<Supply> getSupplyByForester(int foresterId) throws NotFoundException;

	List<Supply> getSupplyByWood(int woodId) throws NotFoundException;

	boolean rollbackSupply(int supplyId) throws NotFoundException;

}