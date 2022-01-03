package coupons.core.facade;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import coupons.core.beans.Company;
import coupons.core.beans.Customer;
import coupons.core.exceptions.CouponSystemException;
import coupons.core.exceptions.FacadeException;

/**
 * @author Erik Sionov
 *
 */
public class AdminFacade extends ClientFacade {

	@Override
	public boolean login(String email, String password) {
		// loading properties file. can edit admin email and password from there
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("files/app.properties")));
		} catch (IOException e) {
			System.out.println("properties file failed to load " + e.getMessage());
			return false;
		} ;

		if (email == null || password == null) {
			System.out.println("ERROR: login() email or password can't be null");
			return false;
		}
		if (email.equals(prop.getProperty("db.admin_email")) && password.equals(prop.getProperty("db.admin_password"))) {
			System.out.println("login() administrator logged in successfully");
			return true;
		} else {
			System.out.println("login() administrator failed to log in");
			return false;
		}
	}

	// base CTOR
	public AdminFacade() {
	}

	/**
	 * adds a new company to the DB. the company mustn't have a similar name and email to existing company in the DB.
	 * 
	 * @param company
	 *            company to be added
	 * @throws FacadeException
	 */
	public void addCompany(Company company) throws FacadeException {
		try {
			if (companyDao.getOneCompanyByEmail(company.getEmail()) != null) {
				throw new FacadeException("addCompany() can't add a company with the same Email as an existing one or NULL");
			}
			if (companyDao.getOneCompanyByName(company.getName()) != null) {
				throw new FacadeException("addCompany() can't add a company with the same name as an existing one or NULL");
			} else {
				companyDao.addCompany(company);
			}
		} catch (CouponSystemException e) {
			throw new FacadeException("addCompany() failed " + e.getMessage(), e);
		}
	}

	/**
	 * updates already present company in DB, can't update Id or Name of the company
	 * 
	 * @param company
	 * @throws FacadeException
	 */
	public void updateCompany(Company company) throws FacadeException {
		try {
			Company comp = companyDao.getOneCompanyById(company.getId());
			if (company.getId() == comp.getId() && company.getName().equals(comp.getName())) {
				companyDao.updateCompany(company);
			} else {
				throw new FacadeException("updateCompany() can't update the Id or Name of Company");
			}
		} catch (CouponSystemException e) {
			throw new FacadeException("updateCompany() failed " + e.getMessage(), e);
		}
	}

	/**
	 * delete a company by it's companyId, while doing so ALSO will delete all company's coupons and their purchases
	 * 
	 * @param companyId
	 * @throws FacadeException
	 */
	public void deleteCompany(int companyId) throws FacadeException {
		try {
			couponDao.deleteAllCompanyCouponPurchases(companyId);
			couponDao.deleteAllCompanyCoupons(companyId);
			companyDao.deleteCompany(companyId);
			System.out.println("deleteCompany() deleted company id= " + companyId);
		} catch (CouponSystemException e) {
			throw new FacadeException("deleteCompany() failed " + e.getMessage(), e);
		}
	}

	/**
	 * retrieves one company by it's Id from DB
	 * 
	 * @param companyId
	 * @return one Company() object
	 * @throws FacadeException
	 */
	public Company getOneCompany(int companyId) throws FacadeException {
		try {
			return companyDao.getOneCompanyById(companyId);
		} catch (CouponSystemException e) {
			throw new FacadeException("getOneCompany() failed " + e.getMessage(), e);
		}
	}

	/**
	 * retrieves a list of all companies in the DB
	 * 
	 * @return ArrayList of Company() objects
	 * @throws FacadeException
	 */
	public ArrayList<Company> getAllCompanies() throws FacadeException {
		try {
			return companyDao.getAllCompanies();
		} catch (CouponSystemException e) {
			throw new FacadeException("getAllCompanies() failed " + e.getMessage(), e);
		}
	}

	/**
	 * adds a new customer to the DB, mustn't have the same email as an existing customer
	 * 
	 * @param customer
	 *            Customer() object to be added to the DB
	 * @throws FacadeException
	 */
	public void addNewCustomer(Customer customer) throws FacadeException {
		try {
			if (customerDao.getOneCustomer(customer.getEmail()) != null) {
				throw new FacadeException("addNewCustomer() can't add a customer with same email");
			}
			customerDao.addCustomer(customer);
		} catch (CouponSystemException e) {
			throw new FacadeException("addNewCustomer() failed " + e.getMessage(), e);
		}
	}

	/**
	 * update a customer in DB, can't update it's Id
	 * 
	 * @param customer
	 * @throws FacadeException
	 */
	public void updateCustomer(Customer customer) throws FacadeException {
		try {
			Customer c1 = customerDao.getOneCustomer(customer.getEmail());
			if (c1 == null) {
				throw new FacadeException("updateCustomer() couldn't find a customer with same mail");
			}

			if (customer.getId() == c1.getId()) {
				customerDao.updateCustomer(customer);
			} else {
				throw new FacadeException("updateCustomer() can't update the Id of customer");
			}
		} catch (CouponSystemException e) {
			throw new FacadeException("updateCustomer() failed " + e.getMessage(), e);
		}
	}

	/**
	 * delete a customer from DB using it's customerId, also deletes all coupon purchases by the customer
	 * 
	 * @param customerId
	 * @throws FacadeException
	 */
	public void deleteCustomer(int customerId) throws FacadeException {
		try {
			couponDao.deleteAllCustomerCoupons(customerId);
			customerDao.deleteCustomer(customerId);
		} catch (CouponSystemException e) {
			throw new FacadeException("deleteCustomer() failed " + e.getMessage(), e);
		}
	}

	/**
	 * retrieves on customer() object from DB by its customerId
	 * 
	 * @param customerId
	 * @return
	 * @throws FacadeException
	 */
	public Customer getOneCustomer(int customerId) throws FacadeException {
		try {
			return customerDao.getOneCustomer(customerId);
		} catch (CouponSystemException e) {
			throw new FacadeException("getOneCustomer() failed " + e.getMessage(), e);
		}
	}

	/**
	 * retrieves a list of customer() objects from DB
	 * 
	 * @return ArrayList of Customer() objects
	 * @throws FacadeException
	 */
	public ArrayList<Customer> getAllCustomers() throws FacadeException {
		try {
			ArrayList<Customer> customers = customerDao.getAllCustomers();
			return customers;
		} catch (CouponSystemException e) {
			throw new FacadeException("getAllCustomers() failed " + e.getMessage(), e);
		}
	}

}
