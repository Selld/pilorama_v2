package services;

import domain.Forester;

import java.util.List;

/**
 * @author selld
 * @version 1.0
 * @created 12-Oct-2016 12:48:05 AM
 */
public interface ForesterService extends UserService {

	public List<Forester> getMostActiveForesters(int count);

}