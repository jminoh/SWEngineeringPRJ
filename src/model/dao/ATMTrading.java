package model.dao;

import model.dto.Account;

public interface ATMTrading {
	
	/**
	 * 계좌 체크
	 * @param account_number
	 * @return
	 */
	public int checkAccount(String account_number);
	
	/**
	 * 카드 체크
	 * @param account_number
	 * @return
	 */
	public int checkCard(String account_number);
	
	/**
	 * 계좌 정보 가져오기
	 * @param account_number
	 * @return
	 */
	public Account getAccount(String account_number);

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
