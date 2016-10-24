package dao;

import custom_exceptions.DomainConstraintsViolationException;
import domain.Supply;

import java.util.Date;
import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public interface SupplyDAO extends GenericDAO {
    List<Supply> getSupplyByDate(Date date) throws DomainConstraintsViolationException;

    List<Supply> getSupplyByForester(int foresterId);

    List<Supply> getSupplyByWood(int woodId);
}
