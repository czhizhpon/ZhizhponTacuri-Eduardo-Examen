package ec.edu.ups.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
	
	public void create(T entity);
	
	public void read(ID id);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public void deleteByID(ID id);
	
	public List<T> find(String[] attributes, String[] values, String order, 
			int index, int size, boolean isDistinct);
}
