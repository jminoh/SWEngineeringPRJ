package model.service;

import model.dto.Account;

public interface ATMService {

	/**
	 * ���� üũ �� ���� ��������
	 * @param account_number
	 * @return
	 */
	public int checkAccount(String accountNnumber);
	
	/**
	 * ī�� üũ �� ���� ��������
	 * @param account_number
	 * @return
	 */
	public int checkCard(String accountNumber);
	
	/**
	 * �Ա�
	 * @param account
	 * @param amount
	 * @return
	 */
	public Account deposit(String accountNumber, int amount);
	
	/**
	 * ���
	 * @param account
	 * @param amount
	 * @return
	 */
	public Account withdraw(String accountNumber, int amount);
	
	/**
	 * �۱�
	 * @param userAccount
	 * @param transferAccount
	 * @param amount
	 * @return
	 */
	public Account transfer(String accountNumber, String transferAccountNumber, int amount);
	
	/**
	 * ���� ��ȣ ����
	 * @param accountNumber
	 * @return
	 */
	public int setCertification(String accountNumber);
	
	/**
	 * ���� ���� üũ
	 * @param accountNumber
	 * @return
	 */
	public int getCertification(String accountNumber);
	
	/**
	 * ���� ����
	 * @param accountNumber
	 * @return
	 */
	public int deauthentication(String accountNumber);
}
