package MoneyTransferApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import MoneyTransferCache.DataCache;
import UserAccountData.AccountProfile;
import UserAccountData.Transaction;

public class TransactionProcessor 
{
	AccountProfile accountDetails = null; 
	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	String uniqueID = null;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    Date today = null;
    
	public void depositeMoney()
	{
		try
		{
			String buffer = null;
			System.out.println("Enter User Id: ");
			String userId = bf.readLine();
			
			System.out.println("Enter Account number: ");
			buffer = bf.readLine();	
			int userAcctNum = 0;
			try
			{
				userAcctNum = Integer.parseInt(buffer);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please enter Positive Numeric Account number: ");
				buffer = bf.readLine();	
				userAcctNum = Integer.parseInt(buffer);
			}
			
			System.out.println("Enter Amount to Deposite: ");
			try
			{
				buffer = bf.readLine();	
				double userDepMoney = Double.parseDouble(buffer);
				
				if (userDepMoney < 0)
				{
					System.out.println("Invalid Amount");
					return;
				}
				
				Boolean accountStatus = depositeMoney(userId,userAcctNum, userDepMoney);
				
				if(!accountStatus)
				{
					System.out.println("User or Account Does Not Exists");
				}
			}
			catch(NumberFormatException ne)
			{
				System.out.println("Invalid Amount");
			}
		}
		catch(IOException io)
		{
			io.getStackTrace();
		}
	}
	
	public void withdrawMoney()
	{
		try
		{
			String buffer = null;
			System.out.println("Enter User Id: ");
			String userId = bf.readLine();
			
			System.out.println("Enter Account number: ");
			buffer = bf.readLine();	
			int userAcctNum = 0;
			try
			{
				userAcctNum = Integer.parseInt(buffer);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please enter Positive Numeric Account number: ");
				buffer = bf.readLine();	
				userAcctNum = Integer.parseInt(buffer);
			}
			
			System.out.println("Enter Amount to Withdraw: ");
			try
			{
				buffer = bf.readLine();	
				double userWithdrawMoney = Double.parseDouble(buffer);
				
				if (userWithdrawMoney < 0)
				{
					System.out.println("Invalid Amount");
					return;
				}
				
				Boolean acctStatus = withdrawMoney(userId,userAcctNum, userWithdrawMoney);
				if(!acctStatus)
				{
					System.out.println("User or Account Does Not Exists");
				}
			}
			catch(NumberFormatException ne)
			{
				System.out.println("Invalid Amount");
			}
		}
		catch(IOException io)
		{
			io.getStackTrace();
		}
	}
	
	public void balanceEnquiry()
	{
		try
		{
			System.out.println("Enter User Id: ");
			String userId = bf.readLine();
			
			balanceEnquiry(userId);
			}
		catch(IOException io)
		{
			io.getStackTrace();
		}
	}

	public void transferMoney()
	{
		try
		{
			System.out.println("Enter From User Id: ");
			String userIdfrom = bf.readLine();
	
			
			System.out.println("Enter To User Id: ");
			String userIdTo = bf.readLine();
	
			
			System.out.println("Enter Amount to Transfer: ");
			String buffer = bf.readLine();	
			double tranferAmount = 0;
			try
			{
				tranferAmount = Double.parseDouble(buffer);
				if(tranferAmount < 0)
				{
					System.out.println("Invalid Amount");
					return;
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid Amount");
			}
	
			transferMoney(userIdfrom, userIdTo, tranferAmount);
		}
		catch(IOException io)
		{
			io.getStackTrace();
		}
	}
	
	public void viewTransactionsForAccount()
	{
		try
		{
			System.out.println("Enter Account number: ");
			String buffer = bf.readLine();
			try
			{
				int userAcctNum = Integer.parseInt(buffer);
				viewTransactionsForAccount(userAcctNum);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid Account Number ");
			}
		}
		catch(IOException io)
		{
			io.getStackTrace();
		}
		
	}

	public boolean depositeMoney(String userId, int accountNum, double depMoney)
	{
		Transaction tranDetails = new Transaction();
		tranDetails.setAccountNumber(accountNum);
		tranDetails.setUserId(userId);
		tranDetails.setAmount(depMoney);
		
		double currentBalance = accountDetails.getAccountBalance();
		double updatedBalance = currentBalance + depMoney;
		accountDetails.setAccountBalance(updatedBalance);
		
	    today = Calendar.getInstance().getTime();
	    String logDate = dateFormat.format(today);
	    
		List<Transaction> tranList = getTransactionList(accountNum);
		
		tranDetails.setCurrentBalance(updatedBalance);
		tranDetails.setPreviousBalance(currentBalance);
		tranDetails.setTransactionType("Credit");
		uniqueID = UUID.randomUUID().toString();
		tranDetails.setTransactionId(uniqueID);
		tranDetails.setTransactionDateTime(logDate);
		
		tranList.add(tranDetails);
		
		DataCache.getDatacache().getTransactionDetails().put(accountNum, tranList);
		
		return true;
		
	}
	
	public boolean withdrawMoney(String userid, int accountNum, double WithdrawMoney)
	{
		AccountProfile accountDetails = null;
		try
		{
		accountDetails = DataCache.getDatacache().getAccountDetails().get(userid);
		}
		catch(NullPointerException e)
		{
			return false;
		}
		
		try
		{
			int userAccountNum = accountDetails.getAccountNumber();
		
			if (userAccountNum != accountNum)
			{
				return false; 
			}
		}catch(NullPointerException e)
		{
			return false;
		}
		
		double updatedBalance = 0;

		double currentBalance = accountDetails.getAccountBalance();
		if (currentBalance >= WithdrawMoney)
		{
			updatedBalance = currentBalance - WithdrawMoney;
			accountDetails.setAccountBalance(updatedBalance);
		}
		else
		{
			System.out.println("Account has Insufficient Balance");
			return false;
		}
		
	    today = Calendar.getInstance().getTime();
	    String logDate = dateFormat.format(today);
	    
		List<Transaction> tranList = getTransactionList(accountNum);
		Transaction tranDetails = new Transaction();
		tranDetails.setAccountNumber(accountNum);
		tranDetails.setCurrentBalance(updatedBalance);
		tranDetails.setPreviousBalance(currentBalance);
		tranDetails.setTransactionType("Debit");
		uniqueID = UUID.randomUUID().toString();
		tranDetails.setTransactionId(uniqueID);
		tranDetails.setTransactionDateTime(logDate);
		
		tranList.add(tranDetails);
		
		DataCache.getDatacache().getTransactionDetails().put(accountNum, tranList);
		
		return true;

	}
	
	public void balanceEnquiry(String userid)
	{
		try
		{
			AccountProfile useracct = DataCache.getDatacache().getAccountDetails().get(userid);
			System.out.println(String.format("Current Balance for Account %d is %f: " ,useracct.getAccountNumber(), useracct.getAccountBalance()));
		}
		catch(NullPointerException e)
		{
			System.out.println("User Id does Not Exists");
		}
	}
	
	public void transferMoney(String userFromAccount, String userToAccount, double transferAmount )
	{
		try
		{
			AccountProfile userFromAcct = DataCache.getDatacache().getAccountDetails().get(userFromAccount);
			AccountProfile userToAcct = DataCache.getDatacache().getAccountDetails().get(userToAccount);
		
			double currentBalanceFromAcct = userFromAcct.getAccountBalance();
			int fromAccountNumber = userFromAcct.getAccountNumber();
		
			double currentBalalnceToAcct = userToAcct.getAccountBalance();
			int toAccountNumber = userToAcct.getAccountNumber();
			
			double transLimit = userFromAcct.getAccountTransferLimit();
		
			if (currentBalanceFromAcct >= transferAmount && transLimit >= transferAmount  )
			{
				currentBalalnceToAcct = currentBalalnceToAcct + transferAmount;
				currentBalanceFromAcct = currentBalanceFromAcct - transferAmount;
			
				userFromAcct.setAccountBalance(currentBalanceFromAcct);
				userToAcct.setAccountBalance(currentBalalnceToAcct);
				
			    today = Calendar.getInstance().getTime();
			    String logDate = dateFormat.format(today);
			    
				List<Transaction> tranList = getTransactionList(userFromAcct.getAccountNumber());
				Transaction tranDetails = new Transaction();
				tranDetails.setAccountNumber(fromAccountNumber);
				tranDetails.setCurrentBalance(currentBalanceFromAcct);
				tranDetails.setPreviousBalance(currentBalanceFromAcct+transferAmount);
				uniqueID = UUID.randomUUID().toString();
				tranDetails.setTransactionId(uniqueID);
				tranDetails.setTransactionType("Debit");
				tranDetails.setTransactionDateTime(logDate);
				
				tranList.add(tranDetails);
				
				DataCache.getDatacache().getTransactionDetails().put(fromAccountNumber, tranList);
				
				tranList = getTransactionList(userToAcct.getAccountNumber());
				tranDetails = new Transaction();
				tranDetails.setAccountNumber(toAccountNumber);
				tranDetails.setCurrentBalance(currentBalalnceToAcct);
				tranDetails.setPreviousBalance(currentBalalnceToAcct-transferAmount);
				uniqueID = UUID.randomUUID().toString();
				tranDetails.setTransactionId(uniqueID);
				tranDetails.setTransactionType("Credit");
				tranDetails.setTransactionDateTime(logDate);
				
				tranList.add(tranDetails);
				
				DataCache.getDatacache().getTransactionDetails().put(toAccountNumber, tranList);
				
			}
			else
			{
				System.out.println("Account has Insufficient Balance or Transfer Limit is Exceeded");
			}
		}
		catch(NullPointerException e)
		{
			System.out.println("Either of Account Not Present");
		}
		
	}
	
	public void viewTransactionsForAccount(int accountNumber)
	{
		try
		{
			List<Transaction> viewTrans  = DataCache.getDatacache().getTransactionDetails().get(accountNumber);
			for(Transaction tran : viewTrans)
			{
				if (tran.getAccountNumber() == accountNumber)
				{
					System.out.println("Transaction Details:" +" "+ tran.getAccountNumber() +" "+ tran.getTransactionId() +" "+ tran.getTransactionType() +" "+ tran.getCurrentBalance() +" "+ tran.getPreviousBalance());
				}
			}
		}
		catch(NullPointerException e)
		{
			System.out.println("Account Does Not Exists");
		}
	}
	
	public List<Transaction> getTransactionList(int accountNumber)
	{
		List<Transaction> tranList = null;
		if(DataCache.getDatacache().getTransactionDetails().containsKey(accountNumber) == false)
		{
			tranList = new ArrayList<Transaction>();
			DataCache.getDatacache().getTransactionDetails().put(accountNumber, tranList);
		}
		else
		{
			tranList = DataCache.getDatacache().getTransactionDetails().get(accountNumber);			
		}
		return tranList;
	}

}
