package ec.edu.ups.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ec.edu.ups.dao.GenericDAO;

public class JPAGenericDAO<T, ID> implements GenericDAO<T, ID>{

	private Class<T> persistenceClass;
	protected EntityManager em;
	
	public JPAGenericDAO(Class<T> persistenceClass) {
		this.persistenceClass = persistenceClass;
		this.em = Persistence.createEntityManagerFactory("jpa").createEntityManager();
	}
	
	@Override
	public void create(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(ID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByID(ID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> find(String[] attributes, String[] values, String order, 
			int index, int size, boolean isDistinct) {
		// TODO Auto-generated method stub
		return null;
	}

}
