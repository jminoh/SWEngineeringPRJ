package model.service;

import model.dao.ATMTrading;
import model.dao.ATMTradingImpl;
import model.dao.Certification;
import model.dao.CertificationImpl;
import model.dto.Account;

public class ATMServiceImpl implements ATMService{
	ATMTrading atmTrading = new ATMTradingImpl();
	//[TODO] ��Ӱŷ�, ��������

	@Override
	public int checkAccount(String accountNumber) {
		int exist = atmTrading.checkAccount(accountNumber); // ���� ���� ���� Ȯ��
		System.out.println(exist);
		return exist;
	}

	@Override
	public int checkCard(String accountNumber) {
		int exist = atmTrading.checkAccount(accountNumber); // ���� ���� ���� Ȯ��
		System.out.println(exist);
		return exist;
	}

	@Override
	public Account deposit(String accountNumber, int amount) {
		Account account = null;
		System.out.println("accountNumber" + accountNumber+ " amount "+ amount );
		account = atmTrading.getAccount(accountNumber); // ���� ���� ��������
		System.out.println(account.getAccountNumber() +" "+ account.getBalance());
		if (account != null) {
			account = atmTrading.deposit(account, amount); // ���� �Ϸ�� �Ա�	
		}
		return account;
	}

	@Override
	public Account withdraw(String accountNumber, int amount) {
		Account account = null;
		System.out.println("accountNumber" + accountNumber+ " amount "+ amount );
		account = atmTrading.getAccount(accountNumber); // ���� ���� ��������
		System.out.println(account.getAccountNumber() +" "+ account.getBalance());
		if (account != null) {
			account = atmTrading.withdraw(account, amount); // ���� �Ϸ�� ���
		}
		return account;
	}

	@Override
	public Account transfer(String accountNumber, String transferAccountNumber, int amount) {
		Account account = null;
		Account transferAccount = null;
		// ���� ���� Ȯ��
		int exist = checkAccount(transferAccountNumber); // ���� ���� ���� ���� Ȯ��
		System.out.println(exist);		
		if (exist < 0) {
			account = null;
			return account;
		}

		// [TODO] Ʈ��������� �� �� ������ ����߸� ����� Ȯ���ǵ��� ����
		account = withdraw(accountNumber, amount); // �� ���� ���
		if(account != null && account.getTradingResult() == 200) { // ���� : �� ���� ���
			transferAccount = deposit(transferAccountNumber, amount); // �۱� ���¿� �Ա�
			
			if (transferAccount == null || account.getTradingResult() != 200) { // ���� : ���� �۱�
				account.setTradingResult(transferAccount.getTradingResult()); // ó����� ���� ǥ��
				transferAccount = deposit(accountNumber, amount); // �� ���� ���Ա�
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
		System.out.println("����������ȣ�� �Է� : " + checkNumber);
	
		if(checkNumber == cert.getCertNumber()) {
			System.out.println("���������� �����߽��ϴ�.");
			result = 200;
		}
		else if( checkNumber != cert.getCertNumber()) {
			System.out.println("���������� �����Ͽ����ϴ�.");
			result = 600;
		}
		return result;
			
		
	}
	
}