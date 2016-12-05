package dao.dao_mock;

import dao.GenericDAO;

import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public class GenericDAO_Mock implements GenericDAO {
    @Override
    public <T> List<T> getAll(Class objClass) {
        return null;
    }

    @Override
    public <T> T getById(int id, Class objClass) {
        return null;
    }

    @Override
    public <T> boolean remove(T obj) {
        return false;
    }

    @Override
    public <T> boolean save(T obj) {
        return false;
    }
}
