package dao;

import custom_exceptions.DomainConstraintsViolationException;
import domain.Wood;
import domain.WoodType;

import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public interface WoodDAO extends GenericDAO {
    Wood getWoodByName(String name) throws DomainConstraintsViolationException;

    List<Wood> getMostPopularWood(int count);

    List<Wood> getWoodByType(WoodType type);

    boolean woodExists(Wood wood);
}
