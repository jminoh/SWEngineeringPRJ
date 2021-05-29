package model.dto;

public class Account {
	private String accountNumber; // ���� ��ȣ
	private int balance; // ���� �ܾ�
	private int tradingResult = -100; // �ŷ� ��� ���
	
	public Account() {}
	
	public Account(String accountNumber, int balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public int setTradingResult (int result) {
		tradingResult = result;
		return tradingResult;
	}
	
	public int getTradingResult () {
		return tradingResult;
	}
}
