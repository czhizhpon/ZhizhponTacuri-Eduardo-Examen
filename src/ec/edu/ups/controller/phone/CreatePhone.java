package ec.edu.ups.controller.phone;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.OperatorDAO;
import ec.edu.ups.dao.PersonDAO;
import ec.edu.ups.dao.PhoneDAO;
import ec.edu.ups.dao.PhoneTypeDAO;
import ec.edu.ups.entities.Operator;
import ec.edu.ups.entities.Person;
import ec.edu.ups.entities.Phone;
import ec.edu.ups.entities.PhoneType;

/**
 * Servlet implementation class CreatePhone
 */
@WebServlet("/CreatePhone")
public class CreatePhone extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OperatorDAO operatorDAO;
    private PhoneTypeDAO phoneTypeDAO;
    private PhoneDAO phoneDAO;
    private PersonDAO personDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePhone() {
        super();
        operatorDAO = DAOFactory.getFactory().getOperatorDAO();
        phoneTypeDAO = DAOFactory.getFactory().getPhoneTypeDAO();
        phoneDAO = DAOFactory.getFactory().getPhoneDAO();
        personDAO = DAOFactory.getFactory().getPersonDAO();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		try{ 
			int oId = Integer.parseInt(request.getParameter("operator.id"));
			int ptId = Integer.parseInt(request.getParameter("phoneType.id"));
			int pId = Integer.parseInt(request.getParameter("person.id"));
			String number = request.getParameter("number");
			Operator operator = operatorDAO.read(oId);
			PhoneType phoneType = phoneTypeDAO.read(ptId);
			Person person = personDAO.read(pId);
			Phone ph = new Phone(number, operator, phoneType);
			ph.setPerson(person);
			if(!phoneDAO.create(ph).equals("0")) {
				response.getWriter().append("notice:El teléfono " + number + " ya existe.");
				return;
			}else {
				String[][] attributes = {{"number"}};
				String[] values = {"equal&" + ph.getNumber()};
				ph = phoneDAO.findByPath(attributes, values, null, 0, 1, false).get(0);
				response.getWriter().append("success:" + ph.getId());
			}
		} catch (Exception e) {
			response.getWriter().append("notice:No se pudo registrar el teléfono");
		}
	}

}
