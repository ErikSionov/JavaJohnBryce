package bankSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * creates a client class with id, name, balance, accounts
 * 
 * @author Erik Sionov
 *
 */
public abstract class Client implements Serializable {

	private static final long serialVersionUID = -820518581791795926L;

	private int id;
	private String name;
	private float balance;
	private ArrayList<Account> accounts;
	protected float commissionRate;
	protected float interestRate;

	public Client(int id, String name, float balance) {
		setName(name);
		this.id = id;
		Logger.log(new Log(System.currentTimeMillis(), getId(), "new client created", balance));
		this.balance = balance;
		accounts = new ArrayList<>();
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private void setBalance(float balance) {
		this.balance = balance;
		Log log = new Log(System.currentTimeMillis(), this.getId(), "client balance updated to" + balance, this.getBalance());
		Logger.log(log);
	}

	public void deposit(float amount) {
		this.setBalance(getBalance() + amount);
	}

	public void withdraw(float amount) throws WithdrawException {
		if ((amount + ((amount / 100F) * commissionRate)) > getBalance()) {
			throw new WithdrawException("amount to withdraw is larger than the client balance", getId(), getBalance(), amount);
		} else {
			Bank.addCommission((amount / 100F) * commissionRate);
			setBalance(getBalance() - (amount + ((amount / 100F) * commissionRate)));
		}

	}

	public int getId() {
		return id;
	}

	public float getBalance() {
		return this.balance;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void addAccount(Account account) {
		if (accounts.size() < 5) {
			if (!accounts.contains(account)) {
				accounts.add(account);
				Log log = new Log(System.currentTimeMillis(), this.id, "new account added, account id " + account.getId(), account.getBalance());
				Logger.log(log);
				return;
			} else {
				System.out.println("account " + account.toString() + " is present in clients " + this.getId() + " account list");
				return;
			}
		}
		System.out.println("max amount of accounts reached for " + getId() + "\n didn't complete operation.");
	}

	public Account getAccount(int index) {

		if (accounts.get(index) == null) {
			return null;
		} else {
			return accounts.get(index);
		}
	}

	public void removeAccount(Account account) {
		float money = account.getBalance();
		if (accounts.contains(account)) {
			this.setBalance(getBalance() + money);
			accounts.remove(account);
			Log log = new Log(System.currentTimeMillis(), getId(), "Clients account removed and money added to his overall balance", money);
			Logger.log(log);
		} else {
			System.out.println("account with Id: " + account.getId() + " is not present. \"Remove Account\" operation is not complete.");
		}
	}

	public void autoUpdateAccounts() {
		for (Account account : accounts) {
			account.setBalance(account.getBalance() + ((account.getBalance() / 100f) * this.interestRate));
		}
		this.setBalance(this.getBalance() + ((this.getBalance() / 100f) * this.interestRate));
	}

	public float getFortune() {
		float sum = 0;

		sum += this.getBalance();
		for (Account account : accounts) {
			if (account != null) {
				sum += account.getBalance();
			}
		}

		return sum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Client)) {
			return false;
		}
		Client other = (Client) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "[Client = " + this.getName() + " \tId = " + this.getId() + "]";
	}

}
