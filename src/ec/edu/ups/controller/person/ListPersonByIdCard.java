package ec.edu.ups.controller.person;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.PersonDAO;
import ec.edu.ups.dao.PhoneDAO;
import ec.edu.ups.entities.Person;
import ec.edu.ups.entities.Phone;

/**
 * Servlet implementation class ListPersonByIdCard
 */
@WebServlet("/ListPersonByIdCard")
public class ListPersonByIdCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonDAO personDAO;
	private PhoneDAO phoneDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPersonByIdCard() {
        super();
        personDAO = DAOFactory.getFactory().getPersonDAO();
        phoneDAO = DAOFactory.getFactory().getPhoneDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String s = request.getParameter("s") == null ? "" : request.getParameter("s");
		String[][] attributes = {{"idCard"}};
		String[] values = {"like&%" + s + "%"};
		List<Person> people = personDAO.findByPath(attributes, values, "name", 0, 0, false);
		for (Person person : people) {
			String[][] attributesPhone = {{"person", "id"}};
			String[] valuesPhone = {"equal&" + person.getId()};
			List<Phone> phones = phoneDAO.findByPath(attributesPhone, valuesPhone, null, 0, 0, false);
			person.setPhones(phones);
		}
		request.setAttribute("s", s);
		request.setAttribute("people", people);
		getServletContext().getRequestDispatcher("/jsp/list_users_by_idcard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
