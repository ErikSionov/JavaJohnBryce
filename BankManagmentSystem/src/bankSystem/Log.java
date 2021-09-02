package bankSystem;

import java.util.Date;

public class Log {

	private long timeStamp;
	private int clientId;
	private String description;
	private float amount;
	private Date date;

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {

		this.timeStamp = timeStamp;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Log(long tStamp, int Id, String descript, float sum) {
		date = new Date(tStamp);
		setClientId(Id);
		setDescription(descript);
		setAmount(sum);
	}

	@Override
	public String toString() {
		String log = "Time stamp: " + date + " | clientID: " + getClientId()
				+ " | log: " + getDescription() + " | current balance: "
				+ getAmount();
		return log;
	}

}
