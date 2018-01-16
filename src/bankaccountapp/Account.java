package bankaccountapp;

public abstract class Account implements IBaseRate {
	//List common properties for savings anc checking ...
	String name;
	String sSN;
	double balance;
	static int index = 10000;
	String accountNumber;
	double rate;
	
	//Constructors to set base properties ....
	
	public Account(String name, String sSN, double initDeposit) {
		this.name    = name;
		this.sSN     = sSN;
		this.balance = initDeposit;
		
		//set Account Number
		index++;
		this.accountNumber = setAccountNumber();
		setRate();
	}
	
	public abstract void setRate();
	
	//List common methods ....
	
	private String setAccountNumber()
	{
		String lastTwoOfSSN = sSN.substring(sSN.length()-2, sSN.length());
		int uniqueID = index;
		int randomNumber = (int) (Math.random() * Math.pow(10, 3));
		return lastTwoOfSSN+uniqueID+randomNumber;
	}
	
	public void showInfo() {
		System.out.println("Name: "+name +
				" \nAccount Number: "+ accountNumber +
				"\nBalance: "+balance+
				"\nRate: "+ rate+"%");
	}
	public void deposit(double amount) {
		balance = balance + amount;
		System.out.println("Depositing £"+amount);
		printBalance();	
	}
	
	public void withdraw(double amount) {
		balance = balance - amount;
		System.out.println("Withdrawing £"+amount);
		printBalance();
	}
	
	public void transfer(String toWhere, double amount) {
		balance = balance - amount;
		System.out.println("Tranferring £"+amount+" to "+toWhere);
		printBalance();
	}
	
	public void printBalance() {
		System.out.println("Ypur balance is "+balance);
	}

}
