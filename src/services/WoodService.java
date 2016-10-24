package services;

import custom_exceptions.AlreadyExistsException;
import custom_exceptions.DomainConstraintsViolationException;
import domain.Wood;
import domain.WoodType;

import java.util.List;


public interface WoodService extends GenericService {

	Wood getByName(String name) throws DomainConstraintsViolationException;

	List<Wood> getMostPopularWood(int count);

	void addNewWood(Wood wood) throws AlreadyExistsException;

	List<Wood> getWoodsByType(WoodType type);

	@Override
	Wood remove(Wood wood);

}