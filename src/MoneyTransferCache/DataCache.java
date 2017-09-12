package MoneyTransferCache;


import java.util.HashMap;
import java.util.List;

import UserAccountData.AccountProfile;
import UserAccountData.Transaction;
import UserAccountData.UserProfile;

public class DataCache 
{
	private HashMap<String,UserProfile> userData = new HashMap<String,UserProfile>();

	private HashMap<String,AccountProfile> accountDetails = new HashMap<String,AccountProfile>();
	
	private HashMap<Integer,List<Transaction>> transactionDetails = new HashMap<Integer,List<Transaction>>();

	static DataCache datacache = new DataCache();
	
	public static DataCache getDatacache() 
	{
		return datacache;
	}


	private DataCache()
	{
		
	}
	
	
	public HashMap<String, UserProfile> getUserData() {
		return userData;
	}

	public HashMap<String, AccountProfile> getAccountDetails() {
		return accountDetails;
	}

	public HashMap<Integer, List<Transaction>> getTransactionDetails() {
		return transactionDetails;
	}

	
	
}

