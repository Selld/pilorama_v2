package dao;

import domain.Forester;

import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public interface ForesterDAO extends UserDAO {
    List<Forester> getMostActiveForesters(int count);
}
