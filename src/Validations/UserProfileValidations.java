package Validations;

import UserAccountData.UserProfile;

public class UserProfileValidations 
{
	public boolean addUserValidations(UserProfile userProfile)
	{
		if(!userIdValiation(userProfile.getUserid()))
		{
			return false;
		}
		
		if(!contactNoValidation(userProfile.getUserContactNumber()))
		{
			return false;
		}
		
		return true;
	}
	
	private boolean userIdValiation(String userId)
	{
		return true;
	}
	
	private boolean contactNoValidation(int contactNo)
	{
		return true;
	}

}
