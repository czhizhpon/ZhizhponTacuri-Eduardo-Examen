package ec.edu.ups.controller.operator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.OperatorDAO;
import ec.edu.ups.entities.Operator;

/**
 * Servlet implementation class CreateOperator
 */
@WebServlet("/CreateOperator")
public class CreateOperator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OperatorDAO operatorDAO;

    /**
     * Default constructor. 
     */
    public CreateOperator() {
        operatorDAO = DAOFactory.getFactory().getOperatorDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String out = "Se han creado las operadoras";
		Operator o = new Operator("Movistar");
		o = new Operator("Claro");
		if(!operatorDAO.create(o).equals("0")) {
			out = "Claro ya existe!";
		}
		o = new Operator("CNT");
		if(!operatorDAO.create(o).equals("0")) {
			out = out +  " CNT ya existe!";
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(out);
	}
}
