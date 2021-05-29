package model.dao;

import model.dto.Account;

public interface ATMTrading {
	
	/**
	 * ���� üũ
	 * @param accountNumber
	 * @return
	 */
	public int checkAccount(String accountNumber);
	
	/**
	 * ī�� üũ
	 * @param accountNumber
	 * @return
	 */
	public int checkCard(String accountNumber);
	
	/**
	 * ���� ���� ��������
	 * @param accountNumber
	 * @return
	 */
	public Account getAccount(String accountNumber);

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
}
