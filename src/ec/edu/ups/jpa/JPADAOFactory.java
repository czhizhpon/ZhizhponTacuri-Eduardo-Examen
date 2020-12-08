package ec.edu.ups.jpa;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.OperatorDAO;
import ec.edu.ups.dao.PersonDAO;
import ec.edu.ups.dao.PhoneDAO;
import ec.edu.ups.dao.PhoneTypeDAO;

public class JPADAOFactory extends DAOFactory{

	@Override
	public OperatorDAO getOperatorDAO() {
		return new JPAOperatorDAO();
	}

	@Override
	public PersonDAO getPersonDAO() {
		return new JPAPersonDAO();
	}

	@Override
	public PhoneDAO getPhoneDAO() {
		return new JPAPhoneDAO();
	}

	@Override
	public PhoneTypeDAO getPhoneTypeDAO() {
		return new JPAPhoneTypeDAO();
	}

}
