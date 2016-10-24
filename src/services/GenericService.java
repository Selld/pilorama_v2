package services;

import java.util.List;

/**
 * @author selld
 * @version 1.0
 * @created 12-Oct-2016 12:48:01 AM
 */
public interface GenericService {

	<T> List<T> getAll();

	<T> T getById(int id);

	<T> boolean remove(T obj);

	<T> boolean save(T obj);

}