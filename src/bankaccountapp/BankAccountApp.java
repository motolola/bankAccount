package bankaccountapp;

public class BankAccountApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Read from CSV ...
		
		//Create Checking Account ...
		CheckingAccount checkingAccount = new CheckingAccount("SAMUEL PETERS", "676788775", 1500);
		
		SavingAccount savingAccount = new SavingAccount("Troy Deeney", "667755432", 2000);
		
		checkingAccount.showInfo();
		System.out.println("*****************************");
		savingAccount.showInfo();
		savingAccount.compound();
	}

}
