package coupons.core.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import coupons.core.beans.Coupon;
import coupons.core.beans.Coupon.Category;
import coupons.core.exceptions.CouponSystemException;

/**
 * @author Erik Sionov
 *
 */
public interface CouponDao {

	/**
	 * adds a coupon to the DB using a Coupon() object
	 * 
	 * @param coupon
	 *            a Coupon() object
	 * @throws CouponSystemException
	 */
	public void addCoupon(Coupon coupon) throws CouponSystemException;

	/**
	 * updated an already present coupon in the DB
	 * 
	 * @param coupon
	 *            a coupon() object to update
	 * @throws CouponSystemException
	 */
	public void updateCoupon(Coupon coupon) throws CouponSystemException;

	/**
	 * deletes a coupon present in the DB
	 * 
	 * @param couponId
	 *            coupon's Id in DB
	 * @throws CouponSystemException
	 */
	public void deleteCoupon(int couponId) throws CouponSystemException;

	/**
	 * retrieves all present coupons from the DB
	 * 
	 * @return ArrayList of Coupon() from the DB
	 * @throws CouponSystemException
	 */
	public ArrayList<Coupon> getAllCoupons() throws CouponSystemException;

	/**
	 * retrieves one coupon object from DB by its ID
	 * 
	 * @param couponId
	 * @return one Coupon() object from DB
	 * @throws CouponSystemException
	 */
	public Coupon getOneCoupon(int couponId) throws CouponSystemException;

	/**
	 * add a coupon to customer_vs_coupon table in DB as a purchase
	 * 
	 * @param customerId
	 *            the purchasing customers ID
	 * @param couponId
	 *            Id of the purchased coupon from DB
	 * @throws CouponSystemException
	 */
	void addCouponPurchase(int customerId, int couponId) throws CouponSystemException;

	/**
	 * delete a coupon from customer_vs_coupon table in DB
	 * 
	 * @param customerId
	 *            the purchasing customers ID
	 * @param couponId
	 *            Id of the purchased coupon from DB
	 * @throws CouponSystemException
	 */
	void deleteCouponPurchase(int customerId, int couponId) throws CouponSystemException;

	/**
	 * delete all coupons of one company from customer_vs_coupon table from DB
	 * 
	 * @param companyId
	 *            Id of the company which coupons need to be deleted
	 * @throws CouponSystemException
	 */
	void deleteAllCompanyCouponPurchases(int companyId) throws CouponSystemException;

	/**
	 * delete all coupons of one company by its companyId from DB
	 * 
	 * @param companyId
	 *            Id of the company which coupons need to be deleted
	 * @throws CouponSystemException
	 */
	void deleteAllCompanyCoupons(int companyId) throws CouponSystemException;

	/**
	 * delete all coupons of one customer by its customerId from DB
	 * 
	 * @param customerId
	 *            Id of the customer which coupons need to be deleted
	 * @throws CouponSystemException
	 */
	void deleteAllCustomerCoupons(int customerId) throws CouponSystemException;

	/**
	 * retrieves all coupons in the DB
	 * 
	 * @return ArrayList of all coupons present in DB
	 * @throws CouponSystemException
	 */
	ArrayList<Coupon> getAllExpiredCoupons() throws CouponSystemException;

	/**
	 * retrieves all coupons from one company, from the DB
	 * 
	 * @param companyId
	 *            company's id to retrieve it's coupons
	 * @return ArrayList of coupons from a single company
	 * @throws CouponSystemException
	 */
	ArrayList<Coupon> getAllCouponsOfCompany(int companyId) throws CouponSystemException;

	/**
	 * retrieves all coupons from a single category, from the DB
	 * 
	 * @param category
	 *            Coupon().Category enum
	 * @return ArrayList of coupons by category
	 * @throws CouponSystemException
	 */
	ArrayList<Coupon> getAllCoupons(Category category) throws CouponSystemException;

	/**
	 * retrieves all coupons of a single company by category, from the DB
	 * 
	 * @param category
	 *            Coupon().Category enum
	 * @param companyId
	 *            company's id to retrieve it's coupons
	 * @return ArrayList of coupons from a single company by category
	 * @throws CouponSystemException
	 */
	ArrayList<Coupon> getAllCouponsOfCompany(Category category, int companyId) throws CouponSystemException;

	/**
	 * retrieves all coupons with max price and below, from the DB
	 * 
	 * @param maxPrice
	 *            the maximum price of coupons to retrieve
	 * @return ArrayList of coupons by their price
	 * @throws CouponSystemException
	 */
	ArrayList<Coupon> getAllCouponsByMaxPrice(double maxPrice) throws CouponSystemException;

	/**
	 * retrieves all coupons with same title and company Id from the DB
	 * 
	 * @param title
	 *            the title of the coupon to check
	 * @param companyId
	 *            company's Id to check
	 * @return ArrayList of coupons by their title and companyId
	 * @throws CouponSystemException
	 */
	ArrayList<Coupon> getAllCouponsOfCompany(String title, int companyId) throws CouponSystemException;

	/**
	 * retrieves all coupons with max price and below and by company Id from the DB
	 * 
	 * @param maxPrice
	 *            top price to retrieve and all below it
	 * @param companyId
	 *            company's Id to check
	 * @return ArrayList of coupons by their title and companyId
	 * @throws CouponSystemException
	 */
	ArrayList<Coupon> getAllCouponsOfCompany(double maxPrice, int companyId) throws CouponSystemException;

	/**
	 * checks DB if the customer bought the coupon with couponId
	 * 
	 * @param customerId
	 *            customer's Id to search
	 * @param couponId
	 *            coupon's Id to search
	 * @return true if coupon in DB, false if not or has issue with retrieval
	 * @throws CouponSystemException
	 */
	boolean checkCouponPresent(int customerId, int couponId) throws CouponSystemException;

	/**
	 * deletes all coupons from the date parameter and older from the DB
	 * 
	 * @param date
	 *            usually <code>LocalDate.now()</code> to check against
	 * @throws CouponSystemException
	 */
	void deleteAllExpiredCoupons(LocalDate date) throws CouponSystemException;

	/**
	 * deletes all coupon purchases from the customer_vs_coupon DB
	 * 
	 * @param couponId
	 *            delete all coupon purchases from the customer_vs_coupon table in DB
	 * @throws CouponSystemException
	 */
	void deleteAllCouponPurchases(int couponId) throws CouponSystemException;

	/**
	 * retrieves all coupons by max price of coupons and the customer who purchased it from DB
	 * 
	 * @param maxPrice
	 *            the maximum price to searh and all below it
	 * @param customerId
	 *            customer's Id in DB to search for
	 * @return ArrayList of coupons by price and customerId
	 * @throws CouponSystemException
	 */
	ArrayList<Coupon> getAllCouponsOfCustomer(double maxPrice, int customerId) throws CouponSystemException;

	/**
	 * retrieves all coupons by category of coupons and the customer who purchased it from DB
	 * 
	 * @param category
	 *            coupon().category enum to search in DB
	 * @param customerId
	 *            customer's Id in DB to search for
	 * @return ArrayList of coupons by category and customerId
	 * @throws CouponSystemException
	 */
	ArrayList<Coupon> getAllCouponsOfCustomer(Category category, int customerId) throws CouponSystemException;

	/**
	 * retrieves all coupons purchased by customer by its customerId from DB
	 * 
	 * @param customerId
	 *            customer's Id in DB to search for
	 * @return ArrayList of coupons purchased by the customer
	 * @throws CouponSystemException
	 */
	ArrayList<Coupon> getAllCouponsOfCustomer(int customerId) throws CouponSystemException;
}
