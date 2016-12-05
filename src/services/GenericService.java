package services;

import java.util.List;

/**
 * @author selld
 * @version 1.0
 * @created 12-Oct-2016 12:48:01 AM
 */
public interface GenericService<T> {

	List<T> getAll();

	T getById(int id);

	void remove(T obj);

	void save(T obj);

}