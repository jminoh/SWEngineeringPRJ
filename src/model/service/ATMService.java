package model.service;

import model.dto.Account;

public interface ATMService {

	/**
	 * 계좌 체크 및 계좌 가져오기
	 * @param account_number
	 * @return
	 */
	public int checkAccount(String accountNnumber);
	
	/**
	 * 카드 체크 및 계좌 가져오기
	 * @param account_number
	 * @return
	 */
	public int checkCard(String accountNumber);
	
	/**
	 * 입금
	 * @param account
	 * @param amount
	 * @return
	 */
	public Account deposit(String accountNumber, int amount);
	
	/**
	 * 출금
	 * @param account
	 * @param amount
	 * @return
	 */
	public Account withdraw(String accountNumber, int amount);
	
	/**
	 * 송금
	 * @param userAccount
	 * @param transferAccount
	 * @param amount
	 * @return
	 */
	public Account transfer(String accountNumber, String transferAccountNumber, int amount);
	
	/**
	 * 인증 번호 생성
	 * @param accountNumber
	 * @return
	 */
	public int setCertification(String accountNumber);
	
	/**
	 * 인증 여부 체크
	 * @param accountNumber
	 * @return
	 */
	public int getCertification(String accountNumber);
	
	/**
	 * 인증 해제
	 * @param accountNumber
	 * @return
	 */
	public int deauthentication(String accountNumber);
}