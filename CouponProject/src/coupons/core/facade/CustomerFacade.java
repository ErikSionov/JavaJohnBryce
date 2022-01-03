package coupons.core.facade;

import java.time.LocalDate;
import java.util.ArrayList;

import coupons.core.beans.Coupon;
import coupons.core.beans.Coupon.Category;
import coupons.core.beans.Customer;
import coupons.core.exceptions.CouponSystemException;
import coupons.core.exceptions.FacadeException;

/**
 * @author Erik Sionov
 *
 */
public class CustomerFacade extends ClientFacade {

	private int customerId;

	@Override
	public boolean login(String email, String password) throws FacadeException {
		if (email == null || password == null) {
			System.out.println("ERROR: login() email or password can't be null");
			return false;
		}
		try {
			if (customerDao.isCustomerExists(email, password)) {
				customerId = customerDao.getOneCustomer(email, password).getId();
				System.out.println("login() customer logged in successfully as customerId= " + customerId);
				return true;
			} else {
				System.out.println("login() customer failed to log in.");
				return false;
			}
		} catch (CouponSystemException e) {
			throw new FacadeException("login() failed" +e.getMessage(), e);
		}
	}

	/** add the coupon as purchased into customer_vs_coupon table as long as it's not out dated, already purchased or coupon amount is 0.
	 * added to the customer that uses this facade
	 * @param coupon coupon() object to add as purchased
	 * @throws FacadeException
	 */
	public void purchaseCoupon(Coupon coupon) throws FacadeException {
		try {
			if (coupon.getAmount() <= 0) {
				throw new FacadeException(
						"purchaseCoupon() couldn't finish, coupon amount is 0");
			} else if (coupon.getEndDate().isBefore(LocalDate.now())) {
				throw new FacadeException(
						"purchaseCoupon() couldn't finish, coupon outdated");
			} else if (couponDao.checkCouponPresent(customerId, coupon.getId())) {
				throw new FacadeException(
						"purchaseCoupon() couldn't finish, coupon already purchased");
			} else {
				coupon.setAmount(coupon.getAmount() - 1);
				couponDao.addCouponPurchase(customerId, coupon.getId());
				couponDao.updateCoupon(coupon);
				System.out.println("addCouponPurchase() added purchase successfully.");
			}
		} catch (CouponSystemException e) {
			throw new FacadeException("purchaseCoupon() in customerFacade failed " +e.getMessage(), e);
		}
	}

	/** retrieve all customer's coupons from DB that uses this facade
	 * @return ArrayList of coupons
	 * @throws FacadeException
	 */
	public ArrayList<Coupon> getCustomerCoupons() throws FacadeException {
		try {
			ArrayList<Coupon> couponList = couponDao.getAllCouponsOfCustomer(customerId);
			if (couponList != null) {
				System.out.println(
						"getCustomerCoupons() by category complete query with " + couponList.size() + " items");
				return couponList;
			} else {
				System.out.println("getCustomerCoupons() by category complete query without any items");
				return null;
			}
		} catch (CouponSystemException e) {
			throw new FacadeException("getCustomerCoupons() by category in customerFacade failed " +e.getMessage(), e);
		}

	}

	/** retrieve all customer's coupons from DB that uses this facade by category
	 * @return ArrayList of coupons
	 * @throws FacadeException
	 */
	public ArrayList<Coupon> getCustomerCoupons(Category category) throws FacadeException {

		try {
			ArrayList<Coupon> couponList = couponDao.getAllCouponsOfCustomer(category, customerId);
			if (couponList != null) {
				System.out.println(
						"getCustomerCoupons() by category complete query with " + couponList.size() + " items");
				return couponList;
			} else {
				System.out.println("getCustomerCoupons() by category complete query without any items");
				return null;
			}
		} catch (CouponSystemException e) {
			throw new FacadeException("getCustomerCoupons( )by category in customerFacade failed " +e.getMessage(), e);
		}

	}
	/** retrieve all customer's coupons from DB that uses this facade by maximum price and all prices below it
	 * @return ArrayList of coupons
	 * @throws FacadeException
	 */
	public ArrayList<Coupon> getCustomerCouponsByPrice(double maxPrice) throws FacadeException {
		try {
			ArrayList<Coupon> couponList = couponDao.getAllCouponsOfCustomer(maxPrice, this.customerId);
			if (couponList != null) {
				System.out.println(
						"getCustomerCoupons() by maxPrice complete query with " + couponList.size() + " items");
				return couponList;
			} else {
				System.out.println("getCustomerCoupons() by maxPrice complete query without any items");
				return null;
			}
		} catch (CouponSystemException e) {
			throw new FacadeException("getCustomerCoupons() by maxPrice failed " +e.getMessage(), e);
		}
	}

	/**retrieves the detail from DB of the customer that uses this facade
	 * @return Customer() object
	 * @throws FacadeException
	 */
	public Customer getCustomerDetails() throws FacadeException {
		
		try {
			return customerDao.getOneCustomer(this.customerId);
		} catch (CouponSystemException e) {
			throw new FacadeException("getCustomerDetails() failed " +e.getMessage(), e);
		}
	}

}
