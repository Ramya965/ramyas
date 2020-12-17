package dxc.myproject.firstservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dxc.myproject.customer.Customer;
import dxc.myproject.customer.CustomerDAO;

/**
 * Servlet implementation class AddCustomer
 */
public class EditCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		String description = request.getParameter("description");
		
		Customer customer = new Customer();
		customer.setId(id);
		customer.setName(name);
		customer.setStartdate(startdate);
		customer.setEnddate(enddate);
		customer.setDescription(description);
		
		CustomerDAO empDAO = new CustomerDAO();
		int status = 0;
		try {
			status = empDAO.editUpdateCustomer(customer);
		} catch (SQLException e) {
			pw.println(e);
		}
		if(status > 0) {
		pw.println("Record Saved Successfully");
		request.getRequestDispatcher("index.html").include(request, response);
		}
		else
			pw.println("Sorry Unable to Save Record Successfully");
	}

}
