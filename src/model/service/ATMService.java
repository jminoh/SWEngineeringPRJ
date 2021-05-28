package model.service;

import model.dto.Account;

public interface ATMService {

	/**
	 * ���� üũ �� ���� ��������
	 * @param account_number
	 * @return
	 */
	public Account checkAccount(String account_number);
	
	/**
	 * ī�� üũ �� ���� ��������
	 * @param account_number
	 * @return
	 */
	public Account checkCard(String account_number);
	
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
