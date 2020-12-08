package ec.edu.ups.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Phone
 *
 */
@Entity

public class Phone implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	public Phone() {
		super();
	}
   
}
