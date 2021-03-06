package ec.edu.ups.controller.phonetype;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.PhoneTypeDAO;
import ec.edu.ups.entities.PhoneType;

/**
 * Servlet implementation class CreatePhoneType
 */
@WebServlet("/CreatePhoneType")
public class CreatePhoneType extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PhoneTypeDAO phoneTypeDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePhoneType() {
        super();
        phoneTypeDAO = DAOFactory.getFactory().getPhoneTypeDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String out = "Se han creado los tipos de tel�fonos";
		
		PhoneType pt = new PhoneType("Movil");
		if(!phoneTypeDAO.create(pt).equals("0")) {
			out = "Movil ya existe";
		}
		
		pt = new PhoneType("Convencional");
		if (!phoneTypeDAO.create(pt).equals("0")){
			out = out + " Convencional ya existe";
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(out);
	}

}
