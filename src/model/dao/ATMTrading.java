package model.dao;

import model.dto.Account;

public interface ATMTrading {
	
	/**
	 * ���� üũ
	 * @param account_number
	 * @return
	 */
	public int checkAccount(String account_number);
	
	/**
	 * ī�� üũ
	 * @param account_number
	 * @return
	 */
	public int checkCard(String account_number);
	
	/**
	 * ���� ���� ��������
	 * @param account_number
	 * @return
	 */
	public Account getAccount(String account_number);

	/**
	 * �Ա�
	 * @param account
	 * @param amount
	 * @return
	 */
	public Account deposit(Account account, int amount);
	
	/**
	 * ���
	 * @param account
	 * @param amount
	 * @return
	 */
	public Account withdraw(Account account, int amount);
	
	/**
	 * �۱�
	 * @param userAccount
	 * @param transferAccount
	 * @param amount
	 * @return
	 */
	public Account transfer(Account userAccount, Account transferAccount, int amount);

}
