package model.dto;

public class User {
	String name; // 이름
	int accountNumber; // 계좌번호

	public User(String name, int accountNumber) {
		this.name = name;
		this.accountNumber = accountNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
