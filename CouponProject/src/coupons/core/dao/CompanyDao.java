package coupons.core.dao;

import java.util.ArrayList;

import coupons.core.beans.Company;
import coupons.core.exceptions.CouponSystemException;
import coupons.core.exceptions.DaoException;

/**
 * @author Erik Sionov
 *
 */
public interface CompanyDao {

	/**
	 * return true or false if company exists in the DB
	 * 
	 * @param email
	 *            company's email address
	 * @param password
	 *            company's password
	 * @return
	 * @throws DaoException
	 *             if there is problems with Dao
	 * @throws CouponSystemException
	 *             if there is problems with connection pool
	 */
	public boolean isCompanyExists(String email, String password) throws CouponSystemException;

	/**
	 * add a company object to DB
	 * 
	 * @param company
	 *            company object to be added to the DB
	 * @throws DaoException
	 * @throws CouponSystemException
	 */
	public void addCompany(Company company) throws CouponSystemException;

	/**
	 * updated already present company in the DB
	 * 
	 * @param company
	 *            company to be updated
	 * @throws DaoException
	 * @throws CouponSystemException
	 */
	public void updateCompany(Company company) throws CouponSystemException;

	/**
	 * deletes a company from the DB
	 * 
	 * @param companyId
	 *            company Id number in the DB
	 * @throws DaoException
	 * @throws CouponSystemException
	 */
	public void deleteCompany(int companyId) throws CouponSystemException;

	/**
	 * returns an array list of companies in the DB
	 * 
	 * @return return an ArrayList of Company objects
	 * @throws DaoException
	 * @throws CouponSystemException
	 */
	public ArrayList<Company> getAllCompanies() throws CouponSystemException;

	/**
	 * returns one company by its Id from DB
	 * 
	 * @param companyId
	 *            company Id number in the DB
	 * @return
	 * @throws DaoException
	 * @throws CouponSystemException
	 */
	public Company getOneCompanyById(int companyId) throws CouponSystemException;

	/**
	 * returns one company by its Email from DB
	 * 
	 * @param companyMail
	 *            company's email address in DB
	 * @return
	 * @throws DaoException
	 * @throws CouponSystemException
	 */
	Company getOneCompanyByEmail(String companyMail) throws CouponSystemException;

	/**
	 * returns one company by its Name from DB
	 * 
	 * @param name
	 *            company's name in DB
	 * @return
	 * @throws DaoException
	 * @throws CouponSystemException
	 */
	Company getOneCompanyByName(String name) throws CouponSystemException;

	Company getOneCompany(String email, String password) throws CouponSystemException;

}
