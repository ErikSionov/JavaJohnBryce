package coupons.core.facade;

import java.util.ArrayList;

import coupons.core.beans.Coupon;
import coupons.core.beans.Coupon.Category;
import coupons.core.exceptions.CouponSystemException;
import coupons.core.exceptions.FacadeException;

/**
 * @author Erik Sionov
 *
 */
public class CompanyFacade extends ClientFacade {

	private int companyId;

	@Override
	public boolean login(String email, String password) throws FacadeException {
		if (email == null || password == null) {
			System.out.println("ERROR: login() email or password can't be null");
			return false;
		}
		try {
			if (companyDao.isCompanyExists(email, password)) {
				companyId = companyDao.getOneCompany(email, password).getId();
				System.out.println("login() company logged in successfully.");
				return true;
			} else {
				System.out.println("login() company failed to log in.");
				return false;
			}
		} catch (CouponSystemException e) {
			throw new FacadeException("login() failed " +e.getMessage(), e);
		}
	}

	// base COTR
	public CompanyFacade() {

	}

	/** adds one Coupon() object to DB, can't have the same title or couponId as other coupons in DB
	 * @param coupon coupon() object to add to DB
	 * @throws FacadeException
	 */
	public void addCoupon(Coupon coupon) throws FacadeException {
		
		try {
			ArrayList<Coupon> couponList = couponDao.getAllCouponsOfCompany(coupon.getTitle(), this.companyId);
			if (couponList.size() > 0) {
				throw new FacadeException("addCoupon() couldn't add the coupon, same title or couponId used.");
			} else {
				couponDao.addCoupon(coupon);
			}
		} catch (
			CouponSystemException e) {
			throw new FacadeException("addCoupon() failed " +e.getMessage(), e);
		}
	}

	/** update a coupon in DB, can't update coupon's id or its company's Id
	 * @param coupon 
	 * @throws FacadeException
	 */
	public void updateCoupon(Coupon coupon) throws FacadeException {
		try {
			Coupon coup = couponDao.getOneCoupon(coupon.getId());
			if (coup != null) {
				if (coupon.equals(coup) && coupon.getCompanyId() == coup.getCompanyId()) {
					couponDao.updateCoupon(coupon);
				} else {
					throw new FacadeException("updateCoupon() can't update coupon's Id or it's parent companyId");
				}
			} else {
				throw new FacadeException("updateCoupon() couldn't find a similar coupon");
			}
		} catch (CouponSystemException e) {
			throw new FacadeException("updateCoupon() failed " +e.getMessage(), e);
		}
	}

	/**delete a coupon from DB using its couponId, also deletes all coupon purchases of the same coupon
	 * @param couponId
	 * @throws FacadeException
	 */
	public void deleteCoupon(int couponId) throws FacadeException {
		try {
			couponDao.deleteAllCouponPurchases(couponId);
			couponDao.deleteCoupon(couponId);
		} catch (CouponSystemException e) {
			throw new FacadeException("deleteCoupon() failed " +e.getMessage(), e);
		}
	}

	/**retrieves a coupon list of a company that uses this facade
	 * @return ArrayList of coupon()
	 * @throws FacadeException
	 */
	public ArrayList<Coupon> getCompanyCoupons() throws FacadeException {
		try {
			ArrayList<Coupon> coupons = couponDao.getAllCouponsOfCompany(companyId);
			return coupons;
		} catch (CouponSystemException e) {
			throw new FacadeException("getCompanyCoupons() failed " +e.getMessage(), e);
		}
	}

	/**retrieves coupon list of the company that uses this facade BY category 
	 * @param category coupon().category enum
	 * @return arrayList of coupon()
	 * @throws FacadeException
	 */
	public ArrayList<Coupon> getCompanyCoupons(Category category) throws FacadeException {
		try {
			ArrayList<Coupon> coupons = couponDao.getAllCouponsOfCompany(category, companyId);
			return coupons;
		} catch (CouponSystemException e) {
			throw new FacadeException("getCompanyCoupons() failed " +e.getMessage(), e);
		}
	}

	/**retrieves coupon list of the company that uses this facade By maximum price and all below it
	 * @param maxPrice the price of the coupons and all below it
	 * @return arrayList of coupon()
	 * @throws FacadeException
	 */
	public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws FacadeException {
		try {
			ArrayList<Coupon> coupons = couponDao.getAllCouponsOfCompany(maxPrice, companyId);
			return coupons;
		} catch (CouponSystemException e) {
			throw new FacadeException("getCompanyCoupons() failed " +e.getMessage(), e);
		}
	}

}
