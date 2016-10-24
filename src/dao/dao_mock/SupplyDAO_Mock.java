package dao.dao_mock;

import custom_exceptions.DomainConstraintsViolationException;
import dao.SupplyDAO;
import domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public class SupplyDAO_Mock extends GenericDAO_Mock implements SupplyDAO {

    private static Wood testWood = null;

    static {
        try {
            testWood = new Wood("sosna", WoodType.HARDWOOD, 42);
        } catch (DomainConstraintsViolationException e) {
            e.printStackTrace();
        }
    }

    private static Forester testForester = null;

    static {
        try {
            testForester = new Forester("pupsik228", "1234567", "grisha", "tur");
        } catch (DomainConstraintsViolationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Supply> getSupplyByDate(Date date) throws DomainConstraintsViolationException {

        List<SupplyContent> sc = new ArrayList<>();
        sc.add(new SupplyContent(testWood, 1));

        List<Supply> supplies = new ArrayList<>();
        supplies.add(new Supply(testForester, sc));

        supplies.get(0).setSupplyDate(date);

        return supplies;
    }

    @Override
    public List<Supply> getSupplyByForester(int foresterId) {
        return null;
    }

    @Override
    public List<Supply> getSupplyByWood(int woodId) {
        return null;
    }
}
