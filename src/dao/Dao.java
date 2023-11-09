package dao;

import java.util.List;

public interface Dao<T> {
	T get(long id);

	List<T> getAll();

	T save(T t);

	void update(T t, String str);

	void delete(T t);

	void deleteAll();

	void delete(long id);

}
