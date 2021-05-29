package model.dao;

import model.dto.Account;

public interface ATMTrading {
	
	/**
	 * 계좌 체크
	 * @param accountNumber
	 * @return
	 */
	public int checkAccount(String accountNumber);
	
	/**
	 * 카드 체크
	 * @param accountNumber
	 * @return
	 */
	public int checkCard(String accountNumber);
	
	/**
	 * 계좌 정보 가져오기
	 * @param accountNumber
	 * @return
	 */
	public Account getAccount(String accountNumber);

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
}
