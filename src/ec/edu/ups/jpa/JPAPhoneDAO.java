package ec.edu.ups.jpa;

import ec.edu.ups.dao.PhoneDAO;
import ec.edu.ups.entities.Phone;

public class JPAPhoneDAO extends JPAGenericDAO<Phone, Integer> implements PhoneDAO{
	public JPAPhoneDAO() {
		super(Phone.class);
	}
}
