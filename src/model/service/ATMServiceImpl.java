package model.service;

import model.dao.ATMTrading;
import model.dao.ATMTradingImpl;
import model.dao.Certification;
import model.dao.CertificationImpl;
import model.dto.Account;

public class ATMServiceImpl implements ATMService{
	ATMTrading atmTrading = new ATMTradingImpl();
	//[TODO] 계속거래, 본인인증

	@Override
	public int checkAccount(String accountNumber) {
		int exist = atmTrading.checkAccount(accountNumber); // 계좌 존재 여부 확인
		System.out.println(exist);
		return exist;
	}

	@Override
	public int checkCard(String accountNumber) {
		int exist = atmTrading.checkAccount(accountNumber); // 계좌 존재 여부 확인
		System.out.println(exist);
		return exist;
	}

	@Override
	public Account deposit(String accountNumber, int amount) {
		Account account = null;
		System.out.println("accountNumber" + accountNumber+ " amount "+ amount );
		account = atmTrading.getAccount(accountNumber); // 계좌 정보 가져오기
		System.out.println(account.getAccountNumber() +" "+ account.getBalance());
		if (account != null) {
			account = atmTrading.deposit(account, amount); // 정상 완료시 입금	
		}
		return account;
	}

	@Override
	public Account withdraw(String accountNumber, int amount) {
		Account account = null;
		System.out.println("accountNumber" + accountNumber+ " amount "+ amount );
		account = atmTrading.getAccount(accountNumber); // 계좌 정보 가져오기
		System.out.println(account.getAccountNumber() +" "+ account.getBalance());
		if (account != null) {
			account = atmTrading.withdraw(account, amount); // 정상 완료시 출금
		}
		return account;
	}

	@Override
	public Account transfer(String accountNumber, String transferAccountNumber, int amount) {
		Account account = null;
		Account transferAccount = null;
		// 상대방 계좌 확인
		int exist = checkAccount(transferAccountNumber); // 상대방 계좌 존재 여부 확인
		System.out.println(exist);		
		if (exist < 0) {
			account = null;
			return account;
		}

		// [TODO] 트랜잭션으로 둘 다 오류가 없어야만 기능이 확정되도록 변경
		account = withdraw(accountNumber, amount); // 내 계좌 출금
		if(account != null && account.getTradingResult() == 200) { // 성공 : 내 계좌 출금
			transferAccount = deposit(transferAccountNumber, amount); // 송금 계좌에 입금
			
			if (transferAccount == null || account.getTradingResult() != 200) { // 오류 : 계좌 송금
				account.setTradingResult(transferAccount.getTradingResult()); // 처리결과 오류 표시
				transferAccount = deposit(accountNumber, amount); // 내 계좌 재입금
			}
		}

		return account;
	}

	@Override
	public int certification(String accountNumber, int checkNumber) {
		// TODO Auto-generated method stub
		Certification cert = new CertificationImpl();
		cert.setCertNumber(accountNumber);
		int result = 0;
		System.out.println("본인인증번호를 입력 : " + checkNumber);
	
		if(checkNumber == cert.getCertNumber()) {
			System.out.println("본인인증에 성공했습니다.");
			result = 200;
		}
		else if( checkNumber != cert.getCertNumber()) {
			System.out.println("본인인증에 실패하였습니다.");
			result = 600;
		}
		return result;
			
		
	}
	
}