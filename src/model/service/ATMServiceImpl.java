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
	public int setCertification(String accountNumber) {
		// TODO Auto-generated method stub
		Certification cert = new CertificationImpl();
		int result = cert.setCertNumber(accountNumber);
		
		if(result > 0) {
			System.out.println("���������� ���� ������ȣ ���� �Ϸ�.");
			result = 200;
		}
		else {
			System.out.println("���������� �����Ͽ����ϴ�.");
			result = 600;
		}
		return result;
	}
	
	public int getCertification(String accountNumber) {
		Certification cert = new CertificationImpl();
		int certification = cert.getWebCert(accountNumber); //에러로 인해 임시로 아무 함수나 가져다 썼음
		int result = 0;
	
		if(certification > 0) {
			System.out.println("���������� �����߽��ϴ�.");
			result = 200;
		}
		else {
			System.out.println("���������� �����Ͽ����ϴ�.");
			result = 600;
		}
		return result;
		
	} 
	
	@Override
	public int deauthentication(String accountNumber) {
		Certification cert = new CertificationImpl();
		int result = cert.deauthentication(accountNumber);	
		
		if(result > 0) {
			System.out.println("�������� �����߽��ϴ�.");
			result = 200;
		}
		else {
			System.out.println("�������� �����Ͽ����ϴ�.");
			result = 600;
		}
		return result;
	}
	
}