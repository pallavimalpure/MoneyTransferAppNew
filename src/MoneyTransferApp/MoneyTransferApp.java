package MoneyTransferApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import UserAccountData.UserProfile;

public class MoneyTransferApp {
	
	static UserProfileProcessor userProcessor = new UserProfileProcessor();
	static TransactionProcessor tranProcessor = new TransactionProcessor();
	static AccountProcessor acctProcessor = new AccountProcessor();
	
	static BufferedReader bf = null;

	public static void main(String[] args)
	{
		bf = new BufferedReader(new InputStreamReader(System.in));
		String buffer = null;
		int option = -1;

		System.out.println("********* Hello, Welcome To Money Transfer App! *********");
		printInstructions();
		
		do{
			try
			{
				buffer = bf.readLine();	
				option = Integer.parseInt(buffer);
			}
			catch(IOException io)
			{
				io.getStackTrace();
			}
			catch(NumberFormatException ne)
			{
				System.out.println("Please Enter Valid Numeric Option from 0 to 7");
				printInstructions();
				continue;
			}
			
			switch(option)
			{
				case 0:
					break;
				
				case 1:
					UserProfile userProfile = userProcessor.addUser();
					if (userProfile != null)
					{
						System.out.println("User Successfully Added");
						acctProcessor.addAccount(userProfile.getUserid());
					}
					break;
				
				case 2:	
					userProcessor.viewUserDetails();
					break;
				
				case 3:
					tranProcessor.depositeMoney();
					break;

				case 4:
					tranProcessor.withdrawMoney();	
					break;
					
				case 5:
					tranProcessor.balanceEnquiry();
					break;			
				
				case 6:
					tranProcessor.transferMoney();
					break;												

				case 7:
					tranProcessor.viewTransactionsForAccount();
					
				default:
					break;
		
			}
			printInstructions();
		}while (option != 0);
		
	}
	
	//Console Menu Options
	private static void printInstructions()
	{
		System.out.println("Please Select Operation Number:");
		System.out.println("0. Exit");
		System.out.println("1. Add User");
		System.out.println("2. View User Details");
		System.out.println("3. Deposit Money");
		System.out.println("4. Withdraw Money");
		System.out.println("5. Balance Enquiry");
		System.out.println("6. Transfer Monry");
		System.out.println("7. View Transactions For Account");
	}	
}
