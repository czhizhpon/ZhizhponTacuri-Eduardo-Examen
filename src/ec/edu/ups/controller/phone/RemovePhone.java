package ec.edu.ups.controller.phone;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.PhoneDAO;

/**
 * Servlet implementation class RemovePhone
 */
@WebServlet("/RemovePhone")
public class RemovePhone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PhoneDAO phoneDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemovePhone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 phoneDAO = DAOFactory.getFactory().getPhoneDAO();
		 phoneDAO.deleteByID(Integer.parseInt(request.getParameter("phone_id")));
		 response.getWriter().append("success:Se ha eliminado");
	}

}
