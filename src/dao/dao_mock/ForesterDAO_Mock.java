package dao.dao_mock;

import dao.ForesterDAO;
import domain.Forester;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public class ForesterDAO_Mock extends GenericDAO_Mock implements ForesterDAO {
    @Override
    public List<Forester> getMostActiveForesters(int count) {
        return new ArrayList<>();
    }
}
