package ec.edu.ups.jpa;

import ec.edu.ups.dao.PhoneTypeDAO;
import ec.edu.ups.entities.PhoneType;

public class JPAPhoneTypeDAO extends JPAGenericDAO<PhoneType, Integer> implements PhoneTypeDAO{
	public JPAPhoneTypeDAO() {
		super(PhoneType.class);
	}
}
