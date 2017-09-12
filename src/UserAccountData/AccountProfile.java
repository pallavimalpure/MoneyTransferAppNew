package UserAccountData;

public class AccountProfile 
{
	private String userid;
	private int accountNumber;
	private double accountBalance;
	private double accountTransferLimit;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public double getAccountTransferLimit() {
		return accountTransferLimit;
	}
	public void setAccountTransferLimit(double accountTransferLimit) {
		this.accountTransferLimit = accountTransferLimit;
	}
	
	
}
