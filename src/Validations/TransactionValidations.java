package Validations;

import MoneyTransferCache.DataCache;
import UserAccountData.AccountProfile;
import UserAccountData.Transaction;

public class TransactionValidations 
{
	public boolean depositMoneyValidation(Transaction transaction)
	{
		if(!amountValidation(transaction.getAmount()))
		{
			return false;
		}
		
		if(!userIdAccntValidation(transaction))
		{
			return false;
		}
		
		return true;
	}
	
	private boolean amountValidation(double amount)
	{
		return true;
	}
	
	private boolean userIdAccntValidation(Transaction transaction)
	{
		AccountProfile accountDetails = null;
		try
		{
			accountDetails = DataCache.getDatacache().getAccountDetails().get(transaction.getUserId());
		}
		catch(NullPointerException e)
		{
			return false;
		}
		
		try
		{
			int userAccountNum = accountDetails.getAccountNumber();
		
			if (userAccountNum != transaction.getAccountNumber())
			{
				return false; 
			}
		}
		catch(NullPointerException e)
		{
			return false;
		}
		
		return true;
	}
	
	
	public boolean withdrawMoneyValidation(Transaction transaction)
	{
		return true;
	}
}
