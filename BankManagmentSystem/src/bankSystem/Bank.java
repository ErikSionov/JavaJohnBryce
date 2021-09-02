package bankSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Bank {
	private ArrayList<Client> clients;
	private static float commissionSum;
	private static Bank instance;
	private File file;
	private AutoUpdater autoUpdater;
	private Thread thread;

	private Bank() {
		file = new File("c:/bank.data");
		if (file.exists()) {
			load();
		}else if (clients == null) {
			clients = new ArrayList<>();
		}
		autoUpdater = new AutoUpdater(clients);
		thread = new Thread(autoUpdater, "Auto Updater");
		startAccountUpdater();
	}

	public static Bank getInstance() {
		if (instance == null) {
			instance = new Bank();
		}
		return instance;
	}

	public ArrayList<Client> getClients() {
		return clients;
	}

	private float setBalance() {
		float total = 0;
		for (Client client : clients) {
			total += client.getFortune();
		}
		total += commissionSum;
		return total;
	}

	public float getBalance() {
		return setBalance();
	}

	public void addClient(Client client) {
		if (client == null) {
			throw new NullPointerException();
		}
		if(!clients.contains(client)) {
			clients.add(client);
			Log log = new Log(System.currentTimeMillis(), client.getId(), "client have been added to the bank", client.getFortune());
			Logger.log(log);
			return;
		}
		System.out.println("client id= " + client.getId() + "is already present");
	}

	public void removeClient(Client client) {
		clients.remove(client);
		Log log = new Log(System.currentTimeMillis(), client.getId(), "client is removed from the bank", client.getFortune());
		Logger.log(log);
	}

	public void viewLogs() {
		// prints all logs stored in a logger
	}

	public void startAccountUpdater() {
		thread.start();
		
	}

	public void stopAccountUpdater() {
		this.store();
		thread.interrupt();
	}
	
	public static void addCommission(float sum) {
		commissionSum += sum;
	}

	public float getBankFortune() {
		return commissionSum;
	}

	public void printClientList() {
		System.out.println("====== Full Client List ======");
		for (Client client : clients) {
			if(client != null) {
				System.out.println(client.toString());
			}
		}
		System.out.println("===== End Of Client List =====");
	}

	public void store() {

		File file = new File("c:/bank.data");
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
			out.writeObject(getClients());
			System.out.println("FILE WRITE complete");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void load() {

		if (file.canRead()) {
			try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
				try {
					Object clientList = in.readObject();
					this.clients = (ArrayList<Client>) clientList;
					System.out.println("FILE READ complete");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			throw new RuntimeException("file at - " + file.getPath() + " not present , try to store() first");
		}

	}

}