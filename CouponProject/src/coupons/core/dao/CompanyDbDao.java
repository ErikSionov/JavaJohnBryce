package coupons.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import coupons.core.beans.Company;
import coupons.core.db.ConnectionPool;
import coupons.core.exceptions.DaoException;
import coupons.core.exceptions.CouponSystemException;

/**
 * @author Erik Sionov
 *
 */
public class CompanyDbDao implements CompanyDao {

	private ConnectionPool conPool = ConnectionPool.getInstance();

	@Override
	public boolean isCompanyExists(String email, String password) throws DaoException, CouponSystemException {
		
		if (email == null) {
			throw new DaoException("ERROR: email can't be null");
		}
		if (password == null) {
			throw new DaoException("ERROR: password can't be null");
		}
		
		Connection con = conPool.getConnection();
		
		String sql = "select * from COMPANY where email=? and password=?";
		
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String companyEmail = rs.getString(3);
				String companyPassword = rs.getString(4);

				if (companyEmail.equals(email) && companyPassword.equals(password)) {
					System.out.println("isCompanyExists() successfully completed query as TRUE");
					return true;
				}
			}
			System.out.println("isCompanyExists() successfully completed query as FALSE");
			return false;
			
		} catch (SQLException e1) {
			throw new DaoException("getAllCompanies() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public void addCompany(Company company) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "insert into COMPANY values(0, ?, ?, ?)";

		try (PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, company.getName());
			stmt.setString(2, company.getEmail());
			stmt.setString(3, company.getPassword());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			int companyId = rs.getInt(1);
			company.setId(companyId);
			System.out.println("addCompany() added a company successfully " + company.toString());
		} catch (SQLException e1) {
			throw new DaoException("addCompany() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public void updateCompany(Company company) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "update COMPANY set `name`=? ,`email`=?,`password`=? where id=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(4, company.getId());
			stmt.setString(1, company.getName());
			stmt.setString(2, company.getEmail());
			stmt.setString(3, company.getPassword());
			int rs = stmt.executeUpdate();
			if (rs > 0) {
				System.out.println("updateCompany() updated a company successfully " + company.toString());
			} else {
				System.out.println("updateCompany() couldn't find a company with ID " + company.getId());
			}
		} catch (SQLException e1) {
			throw new DaoException("updateCompany() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public void deleteCompany(int companyId) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "delete from COMPANY where id=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, companyId);
			int rs = stmt.executeUpdate();
			if (rs > 0) {
				System.out.println("deleteCompany() deleted a company successfully with companyID= " + companyId);
			} else {
				System.out.println("deleteCompany() didn't find a company with companyID= " + companyId);
			}
		} catch (SQLException e1) {
			throw new DaoException("deleteCompany() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public ArrayList<Company> getAllCompanies() throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();
		
		ArrayList<Company> companies = new ArrayList<Company>();

		String sql = "select * from COMPANY";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				Company company = new Company();
				company.setId(rs.getInt(1));
				company.setName(rs.getString(2));
				company.setEmail(rs.getString(3));
				company.setPassword(rs.getString(4));
				companies.add(company);
			}

			System.out.println("getAllCompanies() successfully completed query");

		} catch (SQLException e1) {
			throw new DaoException("getAllCompanies() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}

		return companies;
	}

	@Override
	public Company getOneCompanyById(int companyId) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "select * from COMPANY where id=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, companyId);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				int id = rs.getInt(1);
				String companyName = rs.getString(2);
				String companyEmail = rs.getString(3);
				String companyPassword = rs.getString(4);

//				System.out.println("getOneCompany() retrieved a company successfully with companyID= " + companyId);
				return new Company(id, companyName, companyEmail, companyPassword);
			}

			throw new DaoException("getOneCompany() couldn't retrive a company with ID number= " + companyId);

		} catch (SQLException e1) {
			throw new DaoException("getOneCompany() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public Company getOneCompanyByEmail(String companyMail) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "select * from COMPANY where email=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, companyMail);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				int id = rs.getInt(1);
				String companyName = rs.getString(2);
				String companyEmail = rs.getString(3);
				String companyPassword = rs.getString(4);

//				System.out.println("getOneCompany() retrieved a company successfully with companyEmail= " + companyMail);
				return new Company(id, companyName, companyEmail, companyPassword);
			}

			return null;

		} catch (SQLException e1) {
			throw new DaoException("getOneCompany() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public Company getOneCompanyByName(String name) throws DaoException, CouponSystemException {

		Connection con = conPool.getConnection();

		String sql = "select * from COMPANY where `name`=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, name);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				int id = rs.getInt(1);
				String companyName = rs.getString(2);
				String companyEmail = rs.getString(3);
				String companyPassword = rs.getString(4);

				return new Company(id, companyName, companyEmail, companyPassword);
			}

			return null;

		} catch (SQLException e1) {
			throw new DaoException("getOneCompany() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

	@Override
	public Company getOneCompany(String email, String password) throws DaoException, CouponSystemException {
		Connection con = conPool.getConnection();

		String sql = "select * from COMPANY where email=? and password=?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Company comp = new Company();
				comp.setId(rs.getInt(1));
				comp.setName(rs.getString(2));
				comp.setEmail(rs.getString(3));
				comp.setPassword(rs.getString(4));
				return comp;
			}
			
			return null;
		} catch (SQLException e1) {
			throw new DaoException("getOneCompany() failed to send statement to DB " +e1.getMessage(), e1);
		} finally {
			conPool.restoreConnection(con);
		}
	}

}
