package dxc.myproject.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// DAO: Data Access Object
public class CustomerDAO {

	String JDBC_DRIVER, DB_URL, USER, PASS;
	static Connection conn;
	public CustomerDAO() {
		super();
		// Step 1: Declaration of Driver Name
				JDBC_DRIVER = "com.mysql.jdbc.Driver";
				
				// Step 2: Loading of Driver with Connection String and Username and Password of the DB
				DB_URL = "jdbc:mysql://localhost:3306/customerservicesdatabase";
				USER = "root";
				PASS = "R@mya";

				// Step 3: Creating Connection Class object to Actually Connect to the Database
				try {
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	public Customer getCustomer(String customerid) throws SQLException {
		// logic here for Getting Customer
		Customer customer = new Customer();
		PreparedStatement ps = conn.prepareStatement("select * from customerdata where id = ?");
		ps.setString(1, customerid);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			customer.setId(rs.getString(1));
			customer.setName(rs.getString(2));
			customer.setStartdate(rs.getString(3));
			customer.setEnddate(rs.getString(4));
			customer.setDescription(rs.getString(5));
		}
		return customer;
	}
	
	public List<Customer> getAllEmployees() throws SQLException {
		// logic here for Getting all Customers
		List<Customer> mList = new ArrayList<Customer>();
		PreparedStatement ps = conn.prepareStatement("select * from customerdata");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Customer customer = new Customer();
			customer.setId(rs.getString(1));
			customer.setName(rs.getString(2));
			customer.setStartdate(rs.getString(3));
			customer.setEnddate(rs.getString(4));
			customer.setDescription(rs.getString(5));
			mList.add(customer);
		}
		return mList;
	}
	
	public int addCustomer(Customer customer) throws SQLException {
		// Logic here for Adding Customers
		PreparedStatement ps =
		conn.prepareStatement("insert into customerdata(id, name, startdate, enddate, description)  values(?,?,?,?,?)");
		ps.setString(1, customer.getId());
		ps.setString(2, customer.getName());
		ps.setString(3, customer.getStartdate());
		ps.setString(4, customer.getEnddate());
		ps.setString(5, customer.getDescription());
		return ps.executeUpdate();
	}
	
	public int editUpdateCustomer(Customer customer) throws SQLException {
		// Logic here for Editing Customer
		PreparedStatement ps = 
		conn.prepareStatement("update customerdata set name=?, startdate=?, enddate=?, description=? where id=?");
		ps.setString(1, customer.getName());
		ps.setString(2, customer.getStartdate());
		ps.setString(3, customer.getEnddate());
		ps.setString(4, customer.getDescription());
		ps.setString(5, customer.getId());
		return ps.executeUpdate();
	}
	
	public static int deleteCustomer(String customerid) throws SQLException {
		// Logic here for Deleting Customer
			PreparedStatement ps = conn.prepareStatement("delete from customerdata where id = ?");
			ps.setString(1, customerid);
			return ps.executeUpdate();
	}
}
