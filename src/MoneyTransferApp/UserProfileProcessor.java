package MoneyTransferApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import MoneyTransferCache.DataCache;
import UserAccountData.AccountProfile;
import UserAccountData.UserProfile;
import Validations.UserProfileValidations;

public class UserProfileProcessor 
{
	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
	
	public UserProfile addUser()
	{
		try
		{
			String buffer = null;
			System.out.println("Enter User Id: ");
			String userId = bf.readLine();	

			System.out.println("Enter User Contact Number: ");
			buffer = bf.readLine();
			
			int userContactNum = 0;
			try
			{
				userContactNum = Integer.parseInt(buffer);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please enter Correct Contact number: ");
				buffer = bf.readLine();	
				userContactNum = Integer.parseInt(buffer);
			}
		
			return addUser(userId,userContactNum);
		
		}
		catch(IOException io)
		{
			io.getStackTrace();
			return null;
		}
	}

	public UserProfile addUser(String userid, int userContactNum)
	{
		UserProfile useracct = new UserProfile();
		UserProfileValidations userProfileVal = new UserProfileValidations();
		
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	    Date today = Calendar.getInstance().getTime();
	    String logDate = dateFormat.format(today);
	    
		useracct.setUserid(userid);
		useracct.setUserContactNumber(userContactNum);
		useracct.setCreateDateTime(logDate);
		
		if(!userProfileVal.addUserValidations(useracct))
		{
			return null;
		}
		
		DataCache.getDatacache().getUserData().put(userid, useracct);
		return useracct;
	}
	
	public void viewUserDetails()
	{
		try
		{
			System.out.println("Enter User Id: ");
			String userId = bf.readLine();	
		
			viewUserDetails(userId);
		}
		catch(IOException io)
		{
			io.getStackTrace();
		}
		
	}
	

	
	public void viewUserDetails(String userid)
	{
		try
		{
			UserProfile useracct = DataCache.getDatacache().getUserData().get(userid);
			AccountProfile acctDetails = DataCache.getDatacache().getAccountDetails().get(userid);
			System.out.println("User Details: ");
			System.out.println("User Id: " + useracct.getUserid() + " User Contact Number: "+ useracct.getUserContactNumber());
			System.out.println("User Created on: " + useracct.getCreateDateTime());
			System.out.println("Account Number: " + acctDetails.getAccountNumber() +" Account Balance: "+ acctDetails.getAccountBalance() +" Account Transaction Limit: "+ acctDetails.getAccountTransferLimit());
		}
		catch(NullPointerException e)
		{
			System.out.println("User Account Not Present");
		}
	}
}
