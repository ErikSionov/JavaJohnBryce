package coupons.core.facade;

import coupons.core.dao.CompanyDbDao;
import coupons.core.dao.CouponDbDao;
import coupons.core.dao.CustomerDbDao;
import coupons.core.exceptions.FacadeException;

/**
 * @author Erik Sionov
 *
 */
public abstract class ClientFacade {

	protected CompanyDbDao companyDao = new CompanyDbDao();
	protected CustomerDbDao customerDao = new CustomerDbDao();
	protected CouponDbDao couponDao = new CouponDbDao();
	
	public abstract boolean login(String email, String password) throws FacadeException;


}