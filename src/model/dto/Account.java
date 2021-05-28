package model.dto;

public class Account {
	private String account_number;
	private int balance;
	
	public Account() {}
	
	public Account(String account_number, int balance) {
		this.account_number = account_number;
		this.balance = balance;
	}
	
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	
	public String getAccount_number() {
		return account_number;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public int getBalance() {
		return balance;
	}
}
