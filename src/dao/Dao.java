package dao;

import java.util.List;
/**
 * Interface for all CRUD operations of DAO classes
 * @author awi (Åke Wallin)
 *
 * @param <T> One of the intended model classes that a DAO
 * implementation should be able to handle
 */

public interface Dao<T> {
	/**
	 * Returns the object T associated with 'id' as key
	 * @param id
	 * @return the object T associated with the 'id' or null if no object is found
	 */
	T get(long id);
	
	/**
	 * Returns a List<T> of all objects T in database
	 * @return List of all objects T if such objects exists, otherwise an empty List of T 
	 */
	List<T> getAll();
	
	/**
	 * Takes a object of type T and store it in the database
	 * The object should have no 'id' set
	 * @param t An instance of object T without 'id'
	 * @return The saved object including new 'id'
	 * Returns an empty object of type T if unsuccessful.
	 */
	T save(T t);
	
	/**
	 * Updates corresponding database record based on 'id'
	 * @param t An object T wich carries new data for update
	 * @param params A String array with the attribute names that should be updated
	 */
	T update(T t, String[] params);
	
	/**
	 * Simplified version of update.
	 * Updates corresponding database record based on 'id'
	 * All attributes will be overwritten, even empty ones.
	 * @param t An instance of object T with valid 'id'.
	 * If no valid 'id' is found no update is performed
	 * Returns object t if successful, otherwise an empty object of type T
	 * NOTE: Pay attention to connections between tables that should be removed
	 * during update, i.e. a student no longer has a certain book - the update
	 * method should handle that properly somehow.. It is not the same as
	 * deleting the book, it just no longer 'belongs to' a certain student.
	 */
	T update(T t);
	
	/**
	 * Deletes corresponding database record based on 'id' in object t <br>
	 * @param t An object T wich have a valid 'id' that matches a database record.<br>
	 * If a corresponding record is found that record is removed and a corresponding object of type T is returned.<br>
	 * If no corresponding record is found, no operation is performed and an empty object of type T is returned
	 */
	T delete(T t);
	
	/**
	 * Simplified version of Delete that uses the 'id' directly to identify
	 * a database record.
	 * @param id A id corresponding to primary key on a database record <br>
	 * If a matching record is found that record is removed and the 
	 * corresponding object T is returned
	 * If no such record is found no record is removed and an empty object T is returned.
	 * NOTE: The use of Exception could be considered.. but maybe this is just "normal" operations.
	 */
	T delete(long id);
	
	
	/**
	 * NOTE: Save and Update may be redundant and can merge together in
	 * a new method called 'saveOrUpdate'. If a object don't have an valid 'id'
	 * then a 'save' is performed but if a object has a valid 'id' a 'update'
	 * is performed.
	 */
}
