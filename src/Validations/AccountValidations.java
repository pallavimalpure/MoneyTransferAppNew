package Validations;

import UserAccountData.AccountProfile;

public class AccountValidations 
{
	public boolean addAccountValidations(AccountProfile accntProfile)
	{
		if(!userIdValiation(accntProfile.getUserid()))
		{
			return false;
		}
		
		if(!accountNoValidation(accntProfile.getAccountNumber()))
		{
			return false;
		}
		
		if(!transferLimitValidation(accntProfile.getAccountTransferLimit()))
		{
			return false;
		}
		
		return true;
	}
	
	private boolean userIdValiation(String userId)
	{
		return true;
	}
	
	private boolean accountNoValidation(int contactNo)
	{
		return true;
	}
	
	private boolean transferLimitValidation(double transferLimit)
	{
		return true;
	}
	
}
