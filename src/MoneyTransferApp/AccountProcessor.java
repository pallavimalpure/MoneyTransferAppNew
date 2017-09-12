package MoneyTransferApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import MoneyTransferCache.DataCache;
import UserAccountData.AccountProfile;
import Validations.AccountValidations;

public class AccountProcessor 
{		
	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
	public void addAccount(String userId)
	{
		try
		{
			String buffer = null;
			System.out.println("Enter Account Number: ");
			buffer = bf.readLine();	
			int accountNum = 0;
			try
			{
				accountNum = Integer.parseInt(buffer);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please enter Positive and Numeric Account number: ");
				buffer = bf.readLine();
				accountNum = Integer.parseInt(buffer);
			}
			
			System.out.println("Enter Account Transfer Limit: ");
			buffer = bf.readLine();	
			double userTransLimit = 0;
			try
			{
				userTransLimit = Double.parseDouble(buffer);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please enter Positive Numeric Amount for Transfer Limit: ");
				buffer = bf.readLine();	
				userTransLimit = Double.parseDouble(buffer);
			}
			
			addAccount(userId,accountNum,userTransLimit);
		}
		catch(IOException io)
		{
			System.out.println(io.getStackTrace());
		}
	}

	
	public AccountProfile addAccount(String userid, int accountNum, double accountTranLim )
	{
		AccountProfile accountDetails = new AccountProfile();
		accountDetails.setUserid(userid);
		accountDetails.setAccountNumber(accountNum);
		accountDetails.setAccountTransferLimit(accountTranLim);
		
		AccountValidations accntValidations = new AccountValidations();
		if(!accntValidations.addAccountValidations(accountDetails))
		{
			return null;
		}
		
		return DataCache.getDatacache().getAccountDetails().put(userid, accountDetails);
	}
}
