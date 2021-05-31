package model.dto;

public class Account {
	private String accountNumber; // 계좌 번호
	private int balance; // 계좌 잔액
	private int tradingResult = -100; // 거래 결과 출력
	
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