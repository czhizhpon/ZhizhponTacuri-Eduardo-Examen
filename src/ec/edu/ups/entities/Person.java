package ec.edu.ups.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Persona
 *
 */
@Entity

public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	public Person() {
		super();
	}
   
}
