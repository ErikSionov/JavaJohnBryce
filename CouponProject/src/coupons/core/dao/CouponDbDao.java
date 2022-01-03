package coupons.core.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import coupons.core.beans.Coupon;
import coupons.core.beans.Coupon.Category;
import coupons.core.db.ConnectionPool;
import coupons.core.exceptions.CouponSystemException;
import coupons.core.exceptions.DaoException;

/**
 * @author Erik Sionov
 *
 */
public class CouponDbDao implements CouponDao {

	private ConnectionPool conPool = ConnectionPool.getInstance();

	@Override
	public void addCoupon(Coupon coupon) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "insert into COUPON values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			stmt.setInt(1, coupon.getId());
			stmt.setInt(2, coupon.getCompanyId());
			stmt.setString(3, coupon.getCategory().toString());
			stmt.setString(4, coupon.getTitle());
			stmt.setString(5, coupon.getDescription());
			stmt.setDate(6, Date.valueOf(coupon.getStartDate()));
			stmt.setDate(7, Date.valueOf(coupon.getEndDate()));
			stmt.setInt(8, coupon.getAmount());
			stmt.setDouble(9, coupon.getPrice());
			stmt.setString(10, coupon.getImage());
			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			int couponId = rs.getInt(1);
			coupon.setId(couponId);

			System.out.println("addCoupon() added a coupon successfully " + coupon.toString());

		} catch (SQLException e1) {
			throw new DaoException("addCoupon() failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public void updateCoupon(Coupon coupon) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "update COUPON set `company_id`=? ,`category_id`=?, `title`=?, `description`=?, `start_date`=?, `end_date`=?, `amount`=?, `price`=?, `image`=? where id=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(10, coupon.getId());
			stmt.setInt(1, coupon.getCompanyId());
			stmt.setString(2, coupon.getCategory().toString());
			stmt.setString(3, coupon.getTitle());
			stmt.setString(4, coupon.getDescription());
			stmt.setDate(5, Date.valueOf(coupon.getStartDate()));
			stmt.setDate(6, Date.valueOf(coupon.getEndDate()));
			stmt.setInt(7, coupon.getAmount());
			stmt.setDouble(8, coupon.getPrice());
			stmt.setString(9, coupon.getImage());

			int rs = stmt.executeUpdate();

			if (rs > 0) {
				System.out.println("updateCoupon() updated a coupon successfully " + coupon.toString());
			} else {
				System.out.println("updateCoupon() couldn't find a coupon with ID " + coupon.getId());
			}

		} catch (SQLException e1) {
			throw new DaoException("updateCoupon() failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public void deleteCoupon(int couponId) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "delete from COUPON where id=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, couponId);
			int rs = stmt.executeUpdate();

			if (rs > 0) {
				System.out.println("deleteCoupon() deleted a coupon successfully with couponId= " + couponId);
			} else {
				System.out.println("deleteCoupon() couldn't find a coupon with couponId= " + couponId);
			}

		} catch (SQLException e1) {
			throw new DaoException("deleteCoupon() failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	/**
	 * returns all the coupons stored in the COUPON table in the DB
	 * 
	 * @throws CouponSystemException
	 */
	@Override
	public ArrayList<Coupon> getAllCoupons() throws DaoException, CouponSystemException  {

		ArrayList<Coupon> coupons = new ArrayList<Coupon>();

		Connection con = conPool.getConnection();

		String sql = "select * from COUPON";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Coupon.Category.valueOf(rs.getString(3)));
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6).toLocalDate());
				coupon.setEndDate(rs.getDate(7).toLocalDate());
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getDouble(9));
				coupon.setImage(rs.getString(10));

				coupons.add(coupon);
			}

			System.out.println("getAllCoupons() successfully completed query");

		} catch (SQLException e1) {
			throw new DaoException("getAllCoupons() failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}

		return coupons;
	}

	@Override
	public ArrayList<Coupon> getAllExpiredCoupons() throws DaoException, CouponSystemException {

		ArrayList<Coupon> coupons = new ArrayList<Coupon>();

		Connection con = conPool.getConnection();

		String sql = "select * from COUPON where end_date <=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setDate(1, Date.valueOf(LocalDate.now()));
			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Coupon.Category.valueOf(rs.getString(3)));
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6).toLocalDate());
				coupon.setEndDate(rs.getDate(7).toLocalDate());
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getDouble(9));
				coupon.setImage(rs.getString(10));

				coupons.add(coupon);
			}

			System.out.println("getAllExpiredCoupons() successfully completed query with " + coupons.size() + " items");

		} catch (SQLException e1) {
			throw new DaoException("getAllExpiredCoupons() failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}

		return coupons;
	}

	@Override
	public ArrayList<Coupon> getAllCouponsOfCompany(int companyId) throws DaoException, CouponSystemException {

		ArrayList<Coupon> coupons = new ArrayList<Coupon>();

		Connection con = conPool.getConnection();

		String sql = "select * from COUPON where company_id=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, companyId);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Coupon.Category.valueOf(rs.getString(3)));
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6).toLocalDate());
				coupon.setEndDate(rs.getDate(7).toLocalDate());
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getDouble(9));
				coupon.setImage(rs.getString(10));

				coupons.add(coupon);
			}

			System.out.println("getAllCoupons() by COMPANY_ID successfully completed query");

		} catch (SQLException e1) {
			throw new DaoException("getAllCoupons() failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}

		return coupons;
	}

	@Override
	public ArrayList<Coupon> getAllCoupons(Category category) throws DaoException, CouponSystemException {

		ArrayList<Coupon> coupons = new ArrayList<Coupon>();

		Connection con = conPool.getConnection();

		String sql = "select * from COUPON where category_id=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, category.toString());
			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Coupon.Category.valueOf(rs.getString(3)));
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6).toLocalDate());
				coupon.setEndDate(rs.getDate(7).toLocalDate());
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getDouble(9));
				coupon.setImage(rs.getString(10));

				coupons.add(coupon);
			}

			System.out.println("getAllCoupons() by CATEGORY successfully completed query");

		} catch (SQLException e1) {
			throw new DaoException("getAllCoupons() by CATEGORY failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}

		return coupons;
	}

	@Override
	public ArrayList<Coupon> getAllCouponsOfCompany(Category category, int companyId) throws DaoException, CouponSystemException {

		ArrayList<Coupon> coupons = new ArrayList<Coupon>();

		Connection con = conPool.getConnection();

		String sql = "select * from COUPON where category_id=? and company_id=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, category.toString());
			stmt.setInt(2, companyId);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Coupon.Category.valueOf(rs.getString(3)));
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6).toLocalDate());
				coupon.setEndDate(rs.getDate(7).toLocalDate());
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getDouble(9));
				coupon.setImage(rs.getString(10));

				coupons.add(coupon);
			}

			System.out.println("getAllCoupons() by CATEGORY successfully completed query");

		} catch (SQLException e1) {
			throw new DaoException("getAllCoupons() by CATEGORY failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}

		return coupons;
	}

	@Override
	public ArrayList<Coupon> getAllCouponsByMaxPrice(double maxPrice) throws DaoException, CouponSystemException {

		ArrayList<Coupon> coupons = new ArrayList<Coupon>();

		Connection con = conPool.getConnection();

		String sql = "select * from COUPON where price<=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setDouble(1, maxPrice);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Coupon.Category.valueOf(rs.getString(3)));
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6).toLocalDate());
				coupon.setEndDate(rs.getDate(7).toLocalDate());
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getDouble(9));
				coupon.setImage(rs.getString(10));

				coupons.add(coupon);
			}

			System.out.println("getAllCoupons() to maxPrice successfully completed query");

		} catch (SQLException e1) {
			throw new DaoException("getAllCoupons() to maxPrice failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}

		return coupons;
	}

	@Override
	public ArrayList<Coupon> getAllCouponsOfCompany(String title, int companyId) throws DaoException, CouponSystemException {

		ArrayList<Coupon> coupons = new ArrayList<Coupon>();

		Connection con = conPool.getConnection();

		String sql = "select * from COUPON where title=? and company_id=? ";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, title);
			stmt.setInt(2, companyId);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Coupon.Category.valueOf(rs.getString(3)));
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6).toLocalDate());
				coupon.setEndDate(rs.getDate(7).toLocalDate());
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getDouble(9));
				coupon.setImage(rs.getString(10));
				coupons.add(coupon);
			}

			System.out.println("getAllCoupons() successfully completed query with " + coupons.size() + " items.");

		} catch (SQLException e1) {
			throw new DaoException("getAllCoupons() with same title and companyId failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}

		return coupons;
	}

	@Override
	public ArrayList<Coupon> getAllCouponsOfCompany(double maxPrice, int companyId) throws DaoException, CouponSystemException {

		ArrayList<Coupon> coupons = new ArrayList<Coupon>();

		Connection con = conPool.getConnection();

		String sql = "select * from COUPON where price<=? and company_id=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setDouble(1, maxPrice);
			stmt.setInt(2, companyId);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Coupon.Category.valueOf(rs.getString(3)));
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6).toLocalDate());
				coupon.setEndDate(rs.getDate(7).toLocalDate());
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getDouble(9));
				coupon.setImage(rs.getString(10));

				coupons.add(coupon);
			}

			System.out.println("getAllCoupons() to maxPrice successfully completed query");

		} catch (SQLException e1) {
			throw new DaoException("getAllCoupons() to maxPrice failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}

		return coupons;
	}

	@Override
	public Coupon getOneCoupon(int couponId) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "select * from COUPON where id=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, couponId);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();

			if (rs.next()) {

				int id = rs.getInt(1);
				int companyId = rs.getInt(2);
				Coupon.Category category = Category.valueOf(rs.getString(3));
				String title = rs.getString(4);
				String description = rs.getString(5);
				LocalDate starDate = rs.getDate(6).toLocalDate();
				LocalDate endDate = rs.getDate(7).toLocalDate();
				int amount = rs.getInt(8);
				Double price = rs.getDouble(9);
				String image = rs.getString(10);

				System.out.println("getOneCoupon() retrieved a coupon successfully with couponID= " + couponId);
				return new Coupon(id, companyId, category, title, description, starDate, endDate, amount, price, image);
			} else {
				System.out.println("getOneCoupon() couldn't retrive coupon with couponId= " + couponId);
			}

			throw new DaoException("getOneCoupon() couldn't retrive a coupon with ID number= " + couponId);

		} catch (SQLException e1) {
			throw new DaoException("getOneCoupon() failed to send statement to DB", e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public void addCouponPurchase(int customerId, int couponId) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "insert into CUSTOMER_VS_COUPON values(?,?)";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, customerId);
			stmt.setInt(2, couponId);

			if (stmt.executeUpdate() > 0) {
				System.out.println("addCouponPurchase() added a coupon successfully");
			} else {
				throw new DaoException("addCouponPurchase() failed to add the coupon purchase to DB");
			}
		} catch (SQLException e1) {
			throw new DaoException("addCouponPurchase() failed, already present in DB " + e1.getMessage());
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public boolean checkCouponPresent(int customerId, int couponId) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "select * from CUSTOMER_VS_COUPON where customer_id=? and coupon_id=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, customerId);
			stmt.setInt(2, couponId);
			ResultSet rs = stmt.executeQuery();
			if (rs.getFetchSize() > 0) {
				System.out.println("checkCouponPresent() returned query with " + stmt.getFetchSize() + " items");
				return true;
			} else {
				return false;
			}
		} catch (SQLException e1) {
			throw new DaoException("checkCouponPresent() failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public void deleteCouponPurchase(int customerId, int couponId) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "delete from CUSTOMER_VS_COUPON where customerId=? and couponId=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, customerId);
			stmt.setInt(2, couponId);

			if (stmt.executeUpdate() > 0) {
				System.out.println("deleteCouponPurchase() deleted a coupon successfully ");
			} else {
				throw new DaoException("deleteCouponPurchase() failed to delete the coupon purchase from db");
			}
		} catch (SQLException e1) {
			throw new DaoException("deleteCouponPurchase() failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public void deleteAllExpiredCoupons(LocalDate date) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "delete from CUSTOMER_VS_COUPON where coupon_id in(select id from COUPON where end_date <=?)";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setDate(1, Date.valueOf(date));
			stmt.executeUpdate();

			String sql2 = "delete from COUPON where end_date<=?";

			try (PreparedStatement stmt2 = con.prepareStatement(sql2)) {

				stmt2.setDate(1, Date.valueOf(date));
				stmt2.executeUpdate();
			}
		} catch (SQLException e1) {
			throw new DaoException("deleteAllExpiredCoupons() failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public void deleteAllCompanyCouponPurchases(int companyId) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "delete from CUSTOMER_VS_COUPON where coupon_id in(select id from coupon where company_id = ?)";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, companyId);

			if (stmt.executeUpdate() > 0) {
				System.out.println("deleteAllCouponPurchases() deleted all coupons successfully of company id= " + companyId);
			} else {
				System.out.println("deleteAllCouponPurchases() didn't find a coupons with companyId= " + companyId);
			}
		} catch (SQLException e1) {
			throw new DaoException("deleteAllCouponPurchases() failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public void deleteAllCompanyCoupons(int companyId) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "delete from coupon where company_id=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, companyId);

			if (stmt.executeUpdate() > 0) {
				System.out.println("deleteAllCompanyCoupons() deleted all coupons successfully of company id= " + companyId);
			} else {
				System.out.println("deleteAllCompanyCoupons() didn't find coupons with company id= " + companyId);
			}
		} catch (SQLException e1) {
			throw new DaoException("deleteAllCompanyCoupons() failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public void deleteAllCustomerCoupons(int customerId) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "delete from customer_vs_coupon where customer_Id=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, customerId);

			if (stmt.executeUpdate() > 0) {
				System.out.println("deleteAllCustomerCoupons() deleted all coupons successfully of customer id= " + customerId);
			}
		} catch (SQLException e1) {
			throw new DaoException("deleteAllCustomerCoupons() failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public void deleteAllCouponPurchases(int couponId) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "delete from customer_vs_coupon where coupon_id=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, couponId);
			if (stmt.executeUpdate() > 0) {
				System.out.println("deleteAllCouponPurchases() deleted all coupons purchases successfully with couponId= " + couponId);
			} else {
				System.out.println("deleteAllCouponPurchases() couldn't find coupon purchases with coupon Id= " + couponId);
			}
		} catch (SQLException e1) {
			throw new DaoException("deleteAllCouponPurchases() failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public ArrayList<Coupon> getAllCouponsOfCustomer(double maxPrice, int customerId) throws DaoException, CouponSystemException {

		ArrayList<Coupon> coupons = new ArrayList<Coupon>();

		Connection con = conPool.getConnection();

		String sql = "select * from coupon where price<=? and id in(select coupon_id from customer_vs_coupon where customer_id=?)";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setDouble(1, maxPrice);
			stmt.setInt(2, customerId);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Coupon.Category.valueOf(rs.getString(3)));
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6).toLocalDate());
				coupon.setEndDate(rs.getDate(7).toLocalDate());
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getDouble(9));
				coupon.setImage(rs.getString(10));
				coupons.add(coupon);
			}
			System.out.println("getAllCouponsOfCustomer() by maxPrice and customerId successfully completed query");

		} catch (SQLException e1) {
			throw new DaoException("getAllCouponsOfCustomer() by maxPrice and customerId failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}

		return coupons;
	}

	@Override
	public ArrayList<Coupon> getAllCouponsOfCustomer(Category category, int customerId) throws DaoException, CouponSystemException {
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();

		Connection con = conPool.getConnection();

		String sql = "select * from coupon where category_id=? and id in(select coupon_id from customer_vs_coupon where customer_id=?)";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, category.toString());
			stmt.setInt(2, customerId);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Coupon.Category.valueOf(rs.getString(3)));
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6).toLocalDate());
				coupon.setEndDate(rs.getDate(7).toLocalDate());
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getDouble(9));
				coupon.setImage(rs.getString(10));
				coupons.add(coupon);
			}
			System.out.println("getAllCouponsOfCustomer() by category and customerId successfully completed query");

		} catch (SQLException e1) {
			throw new DaoException("getAllCouponsOfCustomer() by category and customerId failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}

		return coupons;
	}

	@Override
	public ArrayList<Coupon> getAllCouponsOfCustomer(int customerId) throws DaoException, CouponSystemException {
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();

		Connection con = conPool.getConnection();

		String sql = "select * from coupon where id in(select coupon_id from customer_vs_coupon where customer_id=?)";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, customerId);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt(1));
				coupon.setCompanyId(rs.getInt(2));
				coupon.setCategory(Coupon.Category.valueOf(rs.getString(3)));
				coupon.setTitle(rs.getString(4));
				coupon.setDescription(rs.getString(5));
				coupon.setStartDate(rs.getDate(6).toLocalDate());
				coupon.setEndDate(rs.getDate(7).toLocalDate());
				coupon.setAmount(rs.getInt(8));
				coupon.setPrice(rs.getDouble(9));
				coupon.setImage(rs.getString(10));
				coupons.add(coupon);
			}
			System.out.println("getAllCouponsOfCustomer() by customerId successfully completed query");

		} catch (SQLException e1) {
			throw new DaoException("getAllCouponsOfCustomer() by customerId failed to send statement to DB " + e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}

		return coupons;
	}

}
