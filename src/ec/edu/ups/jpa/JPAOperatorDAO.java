package ec.edu.ups.jpa;

import ec.edu.ups.dao.OperatorDAO;
import ec.edu.ups.entities.Operator;

public class JPAOperatorDAO extends JPAGenericDAO<Operator, Integer> implements OperatorDAO{

	public JPAOperatorDAO() {
		super(Operator.class);
	}

}
