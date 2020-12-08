package ec.edu.ups.jpa;

import ec.edu.ups.dao.PersonDAO;
import ec.edu.ups.entities.Person;

public class JPAPersonDAO extends JPAGenericDAO<Person, Integer> implements PersonDAO{

	public JPAPersonDAO() {
		super(Person.class);
	}
	
}
