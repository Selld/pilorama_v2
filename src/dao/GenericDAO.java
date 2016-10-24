package dao;

import java.util.List;

/**
 * Created by selld on 23.10.16.
 */
public interface GenericDAO {
    <T> List<T> getAll();

    <T> T getById(int id);

    <T> boolean remove(T obj);

    <T> boolean save(T obj);
}
