package bankaccountapp;

public class SavingAccount extends Account {
	
	//List properties specific to a Savings Account ...
	int safetyDepositBoxID;
	int safetyDepositBoxKey;
	
		//Constructor to initialise saving account ...
	public SavingAccount(String name, String sSN, double initDeposit) {
		super(name, sSN, initDeposit);
		accountNumber = "1"+accountNumber;
		setSafetyDepositBox();
	}
	
	@Override
	public void setRate() {
		rate = getBaseRate() * 0.15;
	}
	
	private void setSafetyDepositBox() {
		safetyDepositBoxID = (int) (Math.random() * Math.pow(10, 3));
		safetyDepositBoxKey = (int) (Math.random() * Math.pow(10, 4));
		System.out.println(safetyDepositBoxID);
	}

	public void showInfo() {
		System.out.println("Account Type: Saving");
		super.showInfo();
		System.out.println("Your saving Account Features: \n"+ 
		"Safety Deposit Box Id "+ safetyDepositBoxID +
		"\nSafety Deposit Box Key "+ safetyDepositBoxKey+
		"\nRate: "+ rate);
		
	}
		
		//List methods specific to saving accounts ...

}
