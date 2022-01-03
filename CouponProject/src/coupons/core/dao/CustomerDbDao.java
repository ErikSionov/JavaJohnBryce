package coupons.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import coupons.core.beans.Customer;
import coupons.core.db.ConnectionPool;
import coupons.core.exceptions.DaoException;
import coupons.core.exceptions.CouponSystemException;

/**
 * @author Erik Sionov
 *
 */
public class CustomerDbDao implements CustomerDao {

	private ConnectionPool conPool = ConnectionPool.getInstance();

	@Override
	public boolean isCustomerExists(String email, String password) throws DaoException, CouponSystemException {

		if (email == null) {
			throw new DaoException("ERROR: email can't be null");
		}
		if (password == null) {
			throw new DaoException("ERROR: password can't be null");
		}

		Connection con = conPool.getConnection();

		String sql = "select * from customer where `email`=? and `password`=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String customerEmail = rs.getString(4);
				String customerPassword = rs.getString(5);
				if (customerEmail.equals(email) && customerPassword.equals(password)) {
					System.out.println("iscustomerExists() successfully completed query as TRUE");
					return true;
				}
			}

			System.out.println("iscustomerExists() successfully completed query as FALSE");
			return false;

		} catch (SQLException e1) {
			throw new DaoException("getAllCompanies() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public void addCustomer(Customer customer) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "insert into customer values(0, ?, ?, ?,?)";

		try (PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getEmail());
			stmt.setString(4, customer.getPassword());
			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			int customerId = rs.getInt(1);
			customer.setId(customerId);

			System.out.println("addcustomer() added a customer successfully " + customer.toString());

		} catch (SQLException e1) {
			throw new DaoException("addcustomer() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public void updateCustomer(Customer customer) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "update customer set `first_name`=?, `last_name`=?  ,`email`=?,`password`=? where id=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(5, customer.getId());
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getEmail());
			stmt.setString(4, customer.getPassword());
			int rs = stmt.executeUpdate();
			if (rs > 0) {
				System.out.println("updatecustomer() updated a customer successfully " + customer.toString());
			} else {
				System.out.println("updatecustomer() couldn't find a customer with ID " + customer.getId());
			}
		} catch (SQLException e1) {
			throw new DaoException("updatecustomer() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}
	
	@Override
	public void deleteCustomer(int customerId) throws DaoException, CouponSystemException {
		
		Connection con = conPool.getConnection();

		String sql1 = "delete from customer where id=?";

		try (PreparedStatement stmt1 = con.prepareStatement(sql1)) {
			stmt1.setInt(1, customerId);
			int rs1 = stmt1.executeUpdate();
			if(rs1 > 0) {
				System.out.println("deleteCustomer() deleted customer with customer Id= " + customerId);
			}else {
				System.out.println("deletecustomer() didn't find any coupons associated with customer Id= " + customerId);
			}
		} catch (SQLException e1) {
			throw new DaoException("deletecustomer() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}


	@Override
	public ArrayList<Customer> getAllCustomers() throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		ArrayList<Customer> customers = new ArrayList<Customer>();

		String sql = "select * from customer";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			// TODO ask Eldar, how to lower time complexity to O(logN) as now it's O(n)
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setEmail(rs.getString(4));
				customer.setPassword(rs.getString(5));
				customers.add(customer);
			}

			System.out.println("getAllcustomers() successfully completed query");

		} catch (SQLException e1) {
			throw new DaoException("getAllcustomers() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}

		return customers;
	}

	@Override
	public Customer getOneCustomer(int customerId) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "select * from customer where id=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, customerId);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				int id = rs.getInt(1);
				String customerFirstName = rs.getString(2);
				String customerLastName = rs.getString(3);
				String customerEmail = rs.getString(4);
				String customerPassword = rs.getString(5);

				System.out.println("getOnecustomer() retrieved a customer successfully with customerID= " + customerId);
				Customer c1 = new Customer(id, customerFirstName, customerLastName, customerEmail, customerPassword);
				if(c1 != null) {
					return c1;
				}
			}
			System.out.println("getOnecustomer() returned NULL as there's no customer with id=" +customerId);
			return null;
			
		} catch (SQLException e1) {
			throw new DaoException("getOnecustomer() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public Customer getOneCustomer(String email) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "select * from customer where email=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				int id = rs.getInt(1);
				String customerFirstName = rs.getString(2);
				String customerLastName = rs.getString(3);
				String customerEmail = rs.getString(4);
				String customerPassword = rs.getString(5);

				System.out.println("getOneCustomer() retrieved a customer successfully with email= " + email);
				return new Customer(id, customerFirstName, customerLastName, customerEmail, customerPassword);
			}
			
			return null;
			
//			throw new DaoException("getOneCustomer() couldn't retrive a customer with email= " + email);

		} catch (SQLException e1) {
			throw new DaoException("getOneCustomer() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
		
	}
	
	@Override
	public Customer getOneCustomer(String email, String password) throws DaoException, CouponSystemException {
		
		Connection con = conPool.getConnection();

		String sql = "select * from CUSTOMER where email=? and password=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String customerFirstName = rs.getString(2);
				String customerLastName = rs.getString(3);
				String customerEmail = rs.getString(4);
				String customerPassword = rs.getString(5);

				System.out.println("getOneCustomer() retrieved a customer successfully with email= " + email);
				return new Customer(id, customerFirstName, customerLastName, customerEmail, customerPassword);
			}
			
			//if no result set, then there is no company with same email and password
			return null;
		} catch (SQLException e1) {
			throw new DaoException("getOneCustomer() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

}
