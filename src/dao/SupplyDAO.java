package dao;

import custom_exceptions.DomainConstraintsViolationException;
import domain.Forester;
import domain.Supply;
import domain.Wood;

import java.util.Date;
import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public interface SupplyDAO extends GenericDAO<Supply> {
    List<Supply> getSupplyByDate(Date date);

    List<Supply> getSupplyByForester(Forester forester);

    List<Supply> getSupplyByWood(Wood wood);
}
