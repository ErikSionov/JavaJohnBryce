package bankSystem;

public class Application
{

	public static void main(String[] args) throws InterruptedException
	{
		//Debugging test to see the app in work
		
		Bank bank = Bank.getInstance();
		Client client01 = new RegularClient(000001, "yossi", 2000f);
		Client client02 = new GoldClient(000002, "kravitz", 1000f);
		Account acc01 = new Account(01,5000);
		Account acc02 = new Account(02,4000);
		System.out.println(acc01.toString());
		System.out.println("client 01 fortune = " + client01.getFortune());
		System.out.println("client 02 fortune = " + client02.getFortune());
		client01.addAccount(acc01);
		client01.addAccount(acc02);
		bank.addClient(client01);
		bank.addClient(client02);
		
		System.out.println("client 01 interest rate " + client01.interestRate);
		System.out.println("client 01 interest rate " + client02.interestRate);
		//print client list
		bank.printClientList();
		//print accounts of each bank
		System.out.println(bank.getClients().get(0).getAccounts());
		System.out.println(bank.getClients().get(0));
		System.out.println(bank.getClients().get(0).getName() + " " + bank.getClients().get(0).getFortune());
		System.out.println(bank.getClients().get(0).getAccounts());
		bank.store();
		System.out.println("client 01 fortune = " + bank.getClients().get(0).getFortune());
		System.out.println("client 02 fortune = " + bank.getClients().get(1).getFortune());
		Thread.sleep(2000);
		
		System.out.println(bank.getClients().get(0).getFortune());
		System.out.println(bank.getClients().get(1).getFortune());
		Thread.sleep(5000);
		
		System.out.println(bank.getClients().get(0).getFortune());
		System.out.println(bank.getClients().get(1).getFortune());
		Thread.sleep(5000);
		bank.stopAccountUpdater();
		
	}
}
