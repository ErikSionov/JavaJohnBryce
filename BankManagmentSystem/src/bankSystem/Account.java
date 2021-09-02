package bankSystem;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {
	
	private static final long serialVersionUID = -5178895744261140251L;
	
	private int id;
	private float balance;

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		Log log = new Log(System.currentTimeMillis(), getId(), "account balance updated to " + balance, this.balance);
		Logger.log(log);
		this.balance = balance;
	}

	public Account(int ID, float balance) {
		setId(ID);
		setBalance(balance);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Account)) {
			return false;
		}
		Account other = (Account) obj;
		return id == other.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	
	@Override
	public String toString() {
		return "[account id= "+this.getId() + ", balance= " + this.getBalance() + "]";
	}
}
