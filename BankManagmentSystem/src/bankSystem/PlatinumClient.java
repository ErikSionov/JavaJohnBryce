package bankSystem;

public class PlatinumClient extends Client {
	
	private static final long serialVersionUID = -1184805515379741865L;
	
	public PlatinumClient(int id, String name, float balance) {
		super(id, name, balance);
		commissionRate = 0.1f;
		interestRate = 0.5f;
	}
	@Override
	public String toString() {
		return super.toString() + "[Platinium Client]";
	}
}
