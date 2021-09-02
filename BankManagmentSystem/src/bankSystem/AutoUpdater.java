package bankSystem;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AutoUpdater implements Runnable {

	private List<Client> clientList;

	public AutoUpdater(List<Client> clientList) {
		this.clientList = clientList;
	}

	@Override
	public void run() {
		Thread currThread = Thread.currentThread();
		while (true) {
			System.out.println(currThread.getName() + " updating client accounts");
			System.out.println(clientList);
			for (Client client : clientList) {
				if (client != null) {
					client.autoUpdateAccounts();
				}
			}
			try {
				 TimeUnit.HOURS.sleep(24);
			} catch (InterruptedException e) {
				System.out.println("auto updater thread interrupted");
				e.printStackTrace();
				break;
			}
		}
	}

}
