package app.core.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.AdminServiceException;
import app.core.exceptions.CouponSystemException;

@Service
@Transactional(rollbackFor = CouponSystemException.class)
@Scope("prototype")
public class AdminService extends ClientService {

	@Value("${admin.email}")
	private String emailProp;
	@Value("${admin.pass}")
	private String passProp;

	@Override
	public boolean login(String email, String password) throws AdminServiceException {
		if (email.equals(emailProp) && password.equals(passProp)) {
			return true;
		} else {
			return false;
		}
	}

	public void addCompany(Company company) throws AdminServiceException {
		try {
			List<Company> companies = companyRepository.findByNameOrEmail(company.getName(), company.getEmail());
			if (companies.isEmpty()) {
				companyRepository.save(company);
				System.out.println("company added with id: " + company.getId());
			} else {
				throw new AdminServiceException("Company with same name or email present in DB.");
			}
		} catch (Exception e) {
			throw new AdminServiceException("addCompany ERROR: " + e.getMessage());
		}
	}

	public void updateCompany(Company company) throws AdminServiceException {
		try {
			Company c = getCompany(company.getId());
			if (c != null) {
				// check if company's name not changed
				if (company.getName().equals(c.getName())) {
					c.setEmail(company.getEmail());
					c.setPassword(company.getPassword());
					c.setCoupons(company.getCoupons());
				} else {
					throw new AdminServiceException("can't change company name.");
				}
			} else {
				throw new AdminServiceException("no company with id=" + company.getId() + " present in DB.");
			}
		} catch (Exception e) {
			throw new AdminServiceException("updateCompany ERROR: " + e.getMessage());
		}
	}

	public void deleteCompany(int companyId) throws AdminServiceException {
		Company company = companyRepository.getById(companyId);
		if (company != null) {
			companyRepository.delete(company);
		} else {
			throw new AdminServiceException("no company with id=" + companyId + " present in DB.");
		}
	}

	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	public Company getCompany(int companyId) throws AdminServiceException {
		Optional<Company> opt = companyRepository.findById(companyId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new AdminServiceException("getCompany ERROR: couldn't find company with id: " + companyId);
		}
	}

	public void addCustomer(Customer customer) throws AdminServiceException {
		try {
			List<Customer> list = customerRepository.findByFirstNameOrEmail(null, customer.getEmail());
			if (list.isEmpty()) {
				customerRepository.save(customer);
			} else {
				throw new AdminServiceException("can't add customer with same email.");
			}
		} catch (Exception e) {
			throw new AdminServiceException("addCustomer ERROR: " + e.getMessage());
		}
	}

	public void updateCustomer(Customer customer) throws AdminServiceException {
		try {
			Optional<Customer> opt = customerRepository.findById(customer.getId());
			if (opt.isPresent()) {
				opt.get().setFirstName(customer.getFirstName());
				opt.get().setLastName(customer.getLastName());
				opt.get().setEmail(customer.getEmail());
				opt.get().setPassword(customer.getPassword());
				opt.get().setCoupons(customer.getCoupons());
			} else {
				throw new AdminServiceException("no customer with id=" + customer.getId() + " present in DB.");
			}
		} catch (Exception e) {
			throw new AdminServiceException("updateCustomer ERROR: " + e.getMessage());
		}
	}

	public void deleteCustomer(int customerId) throws AdminServiceException {
		Customer customer = getCustomer(customerId);
		if (customer != null) {
			customerRepository.delete(customer);
		} else {
			throw new AdminServiceException("no customer with id=" + customerId + " present in DB.");
		}
	}

	public Customer getCustomer(int customerId) throws AdminServiceException {
		Optional<Customer> opt = customerRepository.findById(customerId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new AdminServiceException("getCustomer ERROR: couldn't find customer with id: " + customerId);
		}
	}
	
	public Customer getCustomerWithCoupons(int customerId) throws AdminServiceException {
		Customer customer = customerRepository.findByIdAndFetchCoupons(customerId);
		if (customer != null) {
			return customer;
		} else {
			throw new AdminServiceException("getCustomer ERROR: couldn't find customer with id: " + customerId);
		}
	}
	
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}

	public void deleteAllOutdatedCoupons(LocalDate date) {
		couponRepository.deleteAllByEndDateLessThan(date);
	}

	public List<Coupon> getAllCompanyCoupons(int companyId) {
		List<Coupon> coupons = couponRepository.findAllByCompany_id(companyId);
		return coupons;
	};
	
	public List<Coupon> getAllCustomerCoupons(Customer customer){
		List<Coupon> coupons = couponRepository.findAllByCustomers(customer);
		return coupons;
	}
	
	public Customer getCustomerByEmail(String email) {
		List<Customer> customers = customerRepository.findByFirstNameOrEmail(null, email);
		if(!customers.isEmpty()) {
			return customers.get(0);
		}else {
			return null;
		}
	}
	
	public Company checkIfCompanyExistsByNameOrEmail(String name, String email) {
		List<Company> companies = companyRepository.findByNameOrEmail(name, email);
		if(!companies.isEmpty()) {
			return companies.get(0);
		}else {
			return null;
		}
	}
}
