package model.service;

import model.dto.Account;

public interface ATMService {

	/**
	 * 계좌 체크 및 계좌 가져오기
	 * @param account_number
	 * @return
	 */
	public Account checkAccount(String account_number);
	
	/**
	 * 카드 체크 및 계좌 가져오기
	 * @param account_number
	 * @return
	 */
	public Account checkCard(String account_number);
	
	/**
	 * 입금
	 * @param account
	 * @param amount
	 * @return
	 */
	public Account deposit(Account account, int amount);
	
	/**
	 * 출금
	 * @param account
	 * @param amount
	 * @return
	 */
	public Account withdraw(Account account, int amount);
	
	/**
	 * 송금
	 * @param userAccount
	 * @param transferAccount
	 * @param amount
	 * @return
	 */
	public Account transfer(Account userAccount, Account transferAccount, int amount);
}
