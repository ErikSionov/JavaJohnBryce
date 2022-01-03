package coupons.tests;

import java.time.LocalDate;
import java.util.ArrayList;

import coupons.core.beans.Company;
import coupons.core.beans.Coupon;
import coupons.core.beans.Coupon.Category;
import coupons.core.beans.Customer;
import coupons.core.dao.CouponDbDao;
import coupons.core.db.ConnectionPool;
import coupons.core.exceptions.CouponSystemException;
import coupons.core.exceptions.FacadeException;
import coupons.core.facade.AdminFacade;
import coupons.core.facade.ClientType;
import coupons.core.facade.CompanyFacade;
import coupons.core.facade.CustomerFacade;
import coupons.core.facade.LoginManager;
import coupons.core.job.CouponExpirationDailyJob;

public class Test {

	Thread job;
	ConnectionPool con;

	public void testAll() {
		
		try {

			// 1. Start system and Expired Coupons Cleaning job

			doStartUp();

			// 2. Connecting as administrator and testing facade

			doAdminTest();

			// 3. Connecting as company and testing facade

			doCompanyTest();

			// 4. Connecting as customer and testing facade

			doCustomerTest();

			// 5. Close Thread Job

			doCloseUp();

		} catch (Exception e) {
			System.err.println("TestAll() failed in one of the tests");
		} finally {
			job.interrupt();
		}
	}
	
	/// TEST METHODS
	
	
	
	private void doStartUp() {
		System.out.println("=========START TEST ALL==========");
		con = ConnectionPool.getInstance();
		job = new Thread(new CouponExpirationDailyJob());
		job.start();
	}
	
	
	
	public void doCloseUp() throws CouponSystemException, InterruptedException {
		job.interrupt();
		job.join();
		ConnectionPool.getInstance().closeAllConnections();
		System.out.println("===========END TEST ALL==========");
		System.out.println();
	}

	
	
	
	/** do all admin facade tests
	 * @throws FacadeException
	 */
	public static void doAdminTest() throws FacadeException {
		System.out.println();
		System.out.println("====STARTING ADMIN TEST====");

		AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);

		Company company = new Company(104, "JohnBryce", "ggg@mail.com", "1234");
		Customer customer = new Customer(105, "Derp", "derpius", "aaf@gma.com", "1234");

		// test add company
		System.out.println();
		try {
			adminFacade.addCompany(company);
			System.out.println("====== TEST PASS addCompany()");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

		// test update company
		System.out.println();
		try {
			adminFacade.updateCompany(company);
			System.out.println("====== TEST PASS updateCompany()");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

		// test delete company
		System.out.println();
		try {
			adminFacade.deleteCompany(company.getId());
			System.out.println("====== TEST PASS deleteCompany()");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

		// test get one company
		System.out.println();
		try {
			adminFacade.getOneCompany(5);
			System.out.println(adminFacade.getOneCompany(5));
			System.out.println("====== TEST PASS getOneCompany()");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

		// test get all companies
		System.out.println();
		try {
			ArrayList<Company> companies = adminFacade.getAllCompanies();
			System.out.println(companies.toString());
			System.out.println("====== TEST PASS getAllCompanies()");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

		// test add new customer
		System.out.println();
		try {
			adminFacade.addNewCustomer(customer);
			System.out.println("====== TEST PASS addNewCustomer()");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

		// test update customer
		System.out.println();
		try {
			adminFacade.updateCustomer(customer);
			System.out.println("====== TEST PASS updateCustomer() ");
		} catch (CouponSystemException e) {
			System.err.println("===== TEST FAILED: " + e.getMessage());
		}

		// test delete customer
		System.out.println();
		try {
			adminFacade.deleteCustomer(customer.getId());
			System.out.println("====== TEST PASS deleteCustomer()");
		} catch (CouponSystemException e) {
			System.err.println("===== TEST FAILED: " + e.getMessage());
		}

		// test get one customer
		System.out.println();
		try {
			adminFacade.getOneCustomer(5);
			System.out.println("====== TEST PASS getOneCustomer()");
		} catch (CouponSystemException e) {
			System.err.println("===== TEST FAILED: " + e.getMessage());
		}

		// test get all customers
		System.out.println();
		try {
			ArrayList<Customer> customers = adminFacade.getAllCustomers();
			System.out.println(customers);
			System.out.println("====== TEST PASS getAllCustomers()");
		} catch (CouponSystemException e) {
			System.err.println("===== TEST FAILED: " + e.getMessage());
		}

	}

	
	
	/** do all company facade tests
	 * @throws FacadeException
	 */
	public static void doCompanyTest() throws FacadeException {
		System.out.println();
		System.out.println("====STARTING COMPANY FACADE TEST====");

		CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login("bbb@mail", "bbbPass", ClientType.COMPANY);

		Coupon coupon = new Coupon(105, 5, Category.CAMPING, "helloW", "babaganush", LocalDate.now(), LocalDate.of(2021, 12, 21), 100, 10.5d, "image-05");

		// test add coupon
		System.out.println();
		try {
			companyFacade.addCoupon(coupon);
			System.out.println("====== TEST PASS addCoupon()");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

		// test update coupon
		System.out.println();
		try {
			companyFacade.updateCoupon(coupon);
			System.out.println("====== TEST PASS updateCoupon()");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

		// test delete coupon
		System.out.println();
		try {
			companyFacade.deleteCoupon(coupon.getId());
			System.out.println("====== TEST PASS deleteCoupon()");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

		// test get all company coupons coupon
		System.out.println();
		try {
			ArrayList<Coupon> coupons = companyFacade.getCompanyCoupons();
			System.out.println(coupons);
			System.out.println("====== TEST PASS getCompanyCoupons()");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

		// test get all company coupons by category coupon
		System.out.println();
		try {
			ArrayList<Coupon> coupons = companyFacade.getCompanyCoupons(Category.FOOD);
			System.out.println(coupons);
			System.out.println("====== TEST PASS getCompanyCoupons() by category");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

		// test get all company coupons by maxPrice coupon
		System.out.println();
		try {
			ArrayList<Coupon> coupons = companyFacade.getCompanyCoupons(100d);
			System.out.println(coupons);
			System.out.println("====== TEST PASS getCompanyCoupons() by maxPrice");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}
	}

	
	
	/**do all customer facade tests
	 * @throws FacadeException
	 */
	public static void doCustomerTest() throws FacadeException {
		System.out.println();
		System.out.println("====STARTING CUSTOMER TEST====");

		CustomerFacade customerFacade = (CustomerFacade) LoginManager.getInstance().login("ggg@mail", "gggPass", ClientType.CUSTOMER);

		CouponDbDao cDao = new CouponDbDao();

		// test add purchase coupon
		System.out.println();
		try {
			customerFacade.purchaseCoupon(cDao.getOneCoupon(3));
			System.out.println("====== TEST PASS purchaseCoupon()");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

		// test get all customer coupons
		System.out.println();
		try {
			ArrayList<Coupon> coupons = customerFacade.getCustomerCoupons();
			System.out.println(coupons);
			System.out.println("====== TEST PASS getCustomerCoupons()");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

		// test get all customer coupons by category
		System.out.println();
		try {
			ArrayList<Coupon> coupons = customerFacade.getCustomerCoupons(Category.HOME);
			System.out.println(coupons);
			System.out.println("====== TEST PASS getCustomerCoupons() by category");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

		// test get all customer coupons by maxPrice
		System.out.println();
		try {
			ArrayList<Coupon> coupons = customerFacade.getCustomerCouponsByPrice(20);
			System.out.println(coupons);
			System.out.println("====== TEST PASS getCustomerCoupons() by maxPrice");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

		// test get customer details
		System.out.println();
		try {
			customerFacade.getCustomerDetails();
			System.out.println(customerFacade.getCustomerDetails());
			System.out.println("====== TEST PASS getCustomerDetails()");
		} catch (CouponSystemException e) {
			System.err.println("=====TEST FAILED: " + e.getMessage());
		}

	}

}