package ec.edu.ups.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
		em.getTransaction().begin();
		try {
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(">>>> ERROR>JPAGenericDAO:create: " + e);
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		
	}

	@Override
	public T read(ID id) {
		T entity = null;
		em.clear();
		entity = em.find(persistenceClass, id);
		em.refresh(entity);
		return entity;
	}

	@Override
	public void update(T entity) {
		em.getTransaction().begin();
		try {
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(">>>> ERROR>JPAGenericDAO:update: " + e);
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		
	}

	@Override
	public void delete(T entity) {
		em.getTransaction().begin();
		try {
			em.remove(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(">>>> ERROR>JPAGenericDAO:delete: " + e);
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		
	}

	@Override
	public void deleteByID(ID id) {
		T entity = this.read(id);
		if (entity != null) {
			this.delete(entity);
		}
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<T> find(String order,int index, int size) {
		em.clear();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(this.persistenceClass);
		
		Root<T> root = criteriaQuery.from(this.persistenceClass);
		
		criteriaQuery.select(root);
		
		if (order != null) criteriaQuery.orderBy(criteriaBuilder.asc(root.get(order)));
		
		if (index >= 0 && size > 0) {
			TypedQuery<T> tq = em.createQuery(criteriaQuery);
			tq.setFirstResult(index);
			tq.setMaxResults(size);
			return (List<T>) tq.getResultList();
		} else {
			Query query = em.createQuery(criteriaQuery);
			return (List<T>) query.getResultList();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<T> findByPath(String[][] attributes, String[] values, String order, 
			int index, int size, boolean isDistinct) {
		em.clear();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(this.persistenceClass);
		
		Root<T> root = criteriaQuery.from(this.persistenceClass);
		
		criteriaQuery.select(root);
		
		//Creación del predicado usando un path 
		Predicate predicate = criteriaBuilder.conjunction();
		for (int i = 0; i < attributes.length; i++) {
			//Iteración del path
			Path path = root.get(attributes[i][0]);
			for (int j = 1; j < attributes[i].length; j++) {
				path = path.get(attributes[i][j]);
			}
			predicate = criteriaBuilder.and(predicate, getSig(criteriaBuilder, path.as(String.class), values[i]));
		}
		criteriaQuery.where(predicate);
		
		if (order != null) criteriaQuery.orderBy(criteriaBuilder.asc(root.get(order)));
		
		criteriaQuery.distinct(isDistinct);
		
		if (index >= 0 && size > 0) {
			TypedQuery<T> tq = em.createQuery(criteriaQuery);
			tq.setFirstResult(index);
			tq.setMaxResults(size);
			return (List<T>) tq.getResultList();
		} else {
			Query query = em.createQuery(criteriaQuery);
			return (List<T>) query.getResultList();
		}
	}
	
	/**
	 * 
	 * Función para obtener una operación en específico, enviando una llave
	 * valor desde un String.
	 * 
	 * @param criteriaBuilder
	 * @param exp, Expression
	 * @param value	| Ej. "like&Eduardo"
	 * @return Un predicado aplicando la operación detectada
	 */
	public Predicate getSig(CriteriaBuilder criteriaBuilder, 
			javax.persistence.criteria.Expression<String> exp, String value) {
		Predicate sig = null;
		String[] keys = value.split("&");
		switch (keys[0]) {
		case "like":
			sig = criteriaBuilder.like(exp, keys[1]);
			break;
		case "notLike":
			sig = criteriaBuilder.notLike(exp, keys[1]);
			break;
		case ">":
			sig = criteriaBuilder.greaterThan(exp, keys[1]);
			break;
		default:
			System.out.println(">>>> ERROR>JPAGenericDAO:getSig: No se encuentra la opción: " + keys[0]);
			break;
		}
		return sig;

	}
}
