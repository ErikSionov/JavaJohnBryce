package coupons.core.dao;

import java.util.ArrayList;

import coupons.core.beans.Customer;
import coupons.core.exceptions.DaoException;
import coupons.core.exceptions.CouponSystemException;

/**
 * @author Erik Sionov
 *
 */
public interface CustomerDao {

	/**
	 * return true or false if customer exists in the DB
	 * 
	 * @param email
	 *            customer's email address
	 * @param password
	 *            customer's password
	 * @return
	 * @throws DaoException
	 *             if there is problems with Dao
	 * @throws CouponSystemException
	 *             if there is problems with connection pool
	 */
	public boolean isCustomerExists(String email, String password) throws CouponSystemException;

	/**
	 * adds one customer object to DB
	 * 
	 * @param customer
	 *            a Customer() object to be added
	 * @throws CouponSystemException
	 */
	public void addCustomer(Customer customer) throws CouponSystemException;

	/**
	 * update a customer object that's already present in DB
	 * 
	 * @param customer
	 *            a customer to be updated
	 * @throws CouponSystemException
	 */
	public void updateCustomer(Customer customer) throws CouponSystemException;

	/**
	 * delete a customer from DB by its customerID
	 * 
	 * @param customerId
	 *            customer's Id from DB
	 * @throws CouponSystemException
	 */
	public void deleteCustomer(int customerId) throws CouponSystemException;

	/**
	 * retrieves all customers from the DB
	 * 
	 * @return ArrayList of customer from DB
	 * @throws CouponSystemException
	 */
	public ArrayList<Customer> getAllCustomers() throws CouponSystemException;

	/**
	 * retrieves one customer from DB by its customerID
	 * 
	 * @param customerId
	 *            customer id to retrieve
	 * @return customer() object
	 * @throws CouponSystemException
	 */
	public Customer getOneCustomer(int customerId) throws CouponSystemException;

	/**
	 * retrieves one customer by its email and password from DB
	 * 
	 * @param email
	 * @param password
	 * @return one Customer() object
	 * @throws CouponSystemException
	 */
	Customer getOneCustomer(String email, String password) throws CouponSystemException;

	/**
	 * retrieves one customer by its email only from DB
	 * 
	 * @param email
	 * @return one Customer() object
	 * @throws CouponSystemException
	 */
	Customer getOneCustomer(String email) throws CouponSystemException;
}
