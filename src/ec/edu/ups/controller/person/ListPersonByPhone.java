package ec.edu.ups.controller.person;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.PhoneDAO;
import ec.edu.ups.entities.Phone;

/**
 * Servlet implementation class ListPerson
 */
@WebServlet("/ListUsersByPhone")
public class ListPersonByPhone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PhoneDAO phoneDAO;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPersonByPhone() {
        super();
        phoneDAO = DAOFactory.getFactory().getPhoneDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String s = request.getParameter("s") == null ? "" : request.getParameter("s");
		String[][] attributes = {{"number"}};
		String[] values = {"like&%" + s +"%"};
		List<Phone> phones = phoneDAO.findByPath(attributes, values, "number", 0, 0, false);
		request.setAttribute("s", s);
		request.setAttribute("phones", phones);
		getServletContext().getRequestDispatcher("/jsp/list_users_by_number.jsp").forward(request, response);
	}

}
