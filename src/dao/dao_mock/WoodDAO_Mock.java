package dao.dao_mock;

import custom_exceptions.DomainConstraintsViolationException;
import dao.WoodDAO;
import domain.Wood;
import domain.WoodType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by selld on 25.10.16.
 */
public class WoodDAO_Mock extends GenericDAO_Mock implements WoodDAO {
    @Override
    public Wood getWoodByName(String name) throws DomainConstraintsViolationException {
        return new Wood(name, WoodType.HARDWOOD, 1);
    }

    @Override
    public List<Wood> getMostPopularWood(int count) {
        return new ArrayList<>();
    }

    @Override
    public List<Wood> getWoodByType(WoodType type) {
        return new ArrayList<>();
    }

    @Override
    public boolean woodExists(Wood wood) {
        return false;
    }
}
