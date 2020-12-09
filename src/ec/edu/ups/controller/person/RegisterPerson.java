package ec.edu.ups.controller.person;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.OperatorDAO;
import ec.edu.ups.dao.PhoneTypeDAO;
import ec.edu.ups.entities.Operator;
import ec.edu.ups.entities.PhoneType;

/**
 * Servlet implementation class RegisterPerson
 */
@WebServlet("/register")
public class RegisterPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OperatorDAO operatorDAO;
	private PhoneTypeDAO phoneTypeDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterPerson() {
        super();
        operatorDAO = DAOFactory.getFactory().getOperatorDAO();
        phoneTypeDAO = DAOFactory.getFactory().getPhoneTypeDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Operator> operators = operatorDAO.find(null, 0, 0);
			List<PhoneType> phoneTypes = phoneTypeDAO.find(null, 0, 0);
			System.out.println(operators);
			request.setAttribute("operators", operators);
			request.setAttribute("phoneTypes", phoneTypes);
			
			getServletContext().getRequestDispatcher("/jsp/register.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
