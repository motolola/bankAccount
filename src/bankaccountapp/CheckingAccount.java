package bankaccountapp;

public class CheckingAccount extends Account {

	//List properties specific to a Checking Account ...
	int debitCardNumber;
	int debitCardPIN;
	
	//Constructor to initialise checking account ...
	public CheckingAccount(String name, String sSN, double initDeposit) {
		super(name, sSN, initDeposit);
		accountNumber = "2"+accountNumber;
		setDebiCard();
	}
	
	private void setDebiCard() {
		debitCardNumber = (int) (Math.random()*Math.pow(10, 16));
		debitCardPIN    = (int) (Math.random()*Math.pow(10, 4));	
	}

	public void showInfo() {
		
		super.showInfo();
		System.out.println("Your Checking features are: "+
		"\nDebit Card Number: "+ this.debitCardNumber+
		"\nDebit Card PIN: "+ this.debitCardPIN);
		
	}

	@Override
	public void setRate() {
		rate = getBaseRate() - 0.25;
	}
	
	//List methods specific to checking accounts ...
}
