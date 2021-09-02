package bankSystem;

public class GoldClient extends Client {
	
	private static final long serialVersionUID = 5125776264574986581L;

	public GoldClient(int id, String name, float balance) {
		super(id, name, balance);
		commissionRate = 0.2f;
		interestRate = 0.3f;
	}

	@Override
	public String toString() {
		return super.toString() + "[Gold Client]";
	}
}
