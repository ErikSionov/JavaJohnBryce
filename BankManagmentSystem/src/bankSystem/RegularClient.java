package bankSystem;

public class RegularClient extends Client {

	private static final long serialVersionUID = 273774019725736660L;

	public RegularClient(int id, String name, float balance) {
		super(id, name, balance);
		commissionRate = 0.3f;
		interestRate = 0.1f;
	}

	@Override
	public String toString() {
		return super.toString() + "[Regular Client]";
	}
}
