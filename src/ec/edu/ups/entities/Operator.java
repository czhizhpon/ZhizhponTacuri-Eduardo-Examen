package ec.edu.ups.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Operator
 *
 */
@Entity

public class Operator implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	public Operator() {
		super();
	}
   
}
