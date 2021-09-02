package bankSystem;

public class WithdrawException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6333672532340615916L;

	private int clientId;
	private float currentBalance;
	private float withdrawAmount;

	public WithdrawException(String message, int clientId, float currBalance,
			float withdrawAmount) {
		System.out.println(message);
		this.clientId = clientId;
		this.currentBalance = currBalance;
		this.withdrawAmount = withdrawAmount;
	}

	public int getClientId() {
		return this.clientId;
	}

	public float getCurrentBalance() {
		return this.currentBalance;
	}
	
	public float getWithdrawAmount() {
		return this.withdrawAmount;
	}
}
