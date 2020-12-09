package ec.edu.ups.controller.person;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.PersonDAO;
import ec.edu.ups.entities.Person;

/**
 * Servlet implementation class CreatePerson
 */
@WebServlet("/CreatePerson")
public class CreatePerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonDAO personDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePerson() {
        super();
        personDAO = DAOFactory.getFactory().getPersonDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");
			String idCard = request.getParameter("idCard");
			String password = request.getParameter("password");
			
			String out = "Se ha creado correctamente";
			
			Person p = new Person(idCard, name, lastname, email, password);
			if(!personDAO.create(p).equals("0")) {
				out = "notice:Cédula o correo electrónico ya existe";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(out);
			}else {
				String[][] attributes = {{"idCard"}};
				String[] values = {"equal&" + idCard};
				p = personDAO.findByPath(attributes, values, null, 0, 1, false).get(0);
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append("id:" + p.getId());
			}
		} catch (Exception e) {
			response.getWriter().append("notice:Hubo un error al crear el usuario");
		}
	}

}
