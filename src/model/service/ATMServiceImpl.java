package model.service;

import model.dao.ATMTrading;
import model.dao.ATMTradingImpl;
import model.dto.Account;

public class ATMServiceImpl implements ATMService{
	ATMTrading atmTrading = new ATMTradingImpl();

	// [TODO] �������� �ٸ� ���� �����ϱ� (ĳ�ó� ���� ��)
	Account account = new Account();

	@Override
	public Account checkAccount(String accountNumber) {
		int exist = atmTrading.checkAccount(accountNumber); // ���� ���� ���� Ȯ��
		System.out.println(exist);
		if (exist > 0) {
			account = atmTrading.getAccount(accountNumber); // ���� ���� ��������
			System.out.println(account.getAccount_number() +" "+ account.getBalance());
		}else {
			account = null; // ���� ���� ����
		}
		return account;
	}


	@Override
	public Account checkCard(String accountNumber) {
		int exist = atmTrading.checkAccount(accountNumber); // ���� ���� ���� Ȯ��
		System.out.println(exist);
		if (exist > 0) {
			account = atmTrading.getAccount(accountNumber); // ���� ���� ��������
			System.out.println(account.getAccount_number() +" "+ account.getBalance());
		}else {
			account = null; // ���� ���� ����
		}
		return account;
	}

	@Override
	public Account deposit(Account account, int amount) {
		this.account = atmTrading.deposit(this.account, amount);
		return account;
	}

	@Override
	public Account withdraw(Account account, int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account transfer(Account userAccount, Account transferAccount, int amount) {
		// TODO Auto-generated method stub
		return null;
	}
	
}