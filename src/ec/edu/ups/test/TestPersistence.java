package ec.edu.ups.test;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.OperatorDAO;
import ec.edu.ups.dao.PersonDAO;
import ec.edu.ups.dao.PhoneDAO;
import ec.edu.ups.dao.PhoneTypeDAO;
import ec.edu.ups.entities.Operator;
import ec.edu.ups.entities.Person;
import ec.edu.ups.entities.Phone;
import ec.edu.ups.entities.PhoneType;

public class TestPersistence {

	public static void main(String[] args) {
		PersonDAO personDAO = DAOFactory.getFactory().getPersonDAO();
		PhoneDAO phoneDAO = DAOFactory.getFactory().getPhoneDAO();
		PhoneTypeDAO phoneTypeDAO = DAOFactory.getFactory().getPhoneTypeDAO();
		OperatorDAO operatorDAO = DAOFactory.getFactory().getOperatorDAO();
		
		Person p = new Person("001", "Eduardo", "Zhizhpon", "eduardo@gmail.com", "pass");
		Operator o = new Operator("Movistar");
		PhoneType pt = new PhoneType("Movil");
		Phone ph = new Phone("0999999", o, pt);
		
		operatorDAO.create(o);
		phoneTypeDAO.create(pt);
		personDAO.create(p);
		
		o = operatorDAO.read(1);
		pt = phoneTypeDAO.read(1);
		p = personDAO.read(1);
		
		ph.setPerson(p);
		phoneDAO.create(ph);
		
//		List<Phone> phs = phoneDAO.findByPath(attributes, values, order, index, size, isDistinct)
		
		System.out.println(personDAO.read(1));
		
		System.out.println(personDAO.read(1).getPhones().get(0));
	}

}
