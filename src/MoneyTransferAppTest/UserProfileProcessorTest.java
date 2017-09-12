package MoneyTransferAppTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import MoneyTransferApp.UserProfileProcessor;
import UserAccountData.UserProfile;

public class UserProfileProcessorTest 
{
	UserProfileProcessor userProfile = new UserProfileProcessor();
	
  @Test
  public void testIsUserAddedSuccessfully() 
  {
	  UserProfile userProf = userProfile.addUser("1", 02345556);
	  
	  Assert.assertEquals(userProf.getUserid(),"1");
	  Assert.assertEquals(userProf.getUserContactNumber(),02345556);
	  
  }
  
  @Test
  public void testIsUserAddedSuccessfullyForAlphnumercId() 
  {
	  UserProfile userProf = userProfile.addUser("TestId1", 02345556);
	  
	  Assert.assertEquals(userProf.getUserid(),"TestId1");
	  
  }
  
  @Test
  public void testIsUserAddedForNonNumericContactNumber() 
  {
	  //UserProfile userProf = userProfile.addUser("TestId1", "TestName1", "TestAddress 1", hgjhj);
	  
	  //Assert.assertEquals(userProf.getUserid(),"TestId1");
	  
  } 
  
}
