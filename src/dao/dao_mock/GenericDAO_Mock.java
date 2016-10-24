package dao.dao_mock;

import dao.GenericDAO;

import java.util.List;

/**
 * Created by selld on 24.10.16.
 */
public class GenericDAO_Mock implements GenericDAO {
    @Override
    public <T> List<T> getAll() {
        return null;
    }

    @Override
    public <T> T getById(int id) {
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
