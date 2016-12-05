package dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by selld on 23.10.16.
 */
public interface GenericDAO <T> {
    List<T> getAll();

    T getById(int id);

    void remove(T obj);

    void save(T obj);
}
