package model.dao;

public interface Certification {
	/**
	 * ���� ����
	 * @param accountNumber
	 */
	public void deauthentication(String accountNumber);

	/**
	 * ������ȣ ������Ʈ
	 * 8�ڸ� ������ ���� ������ȣ ����
	 * @param accountNumber
	 */
	public void setCertNumber(String accountNumber);
	
	/**
	 * ������ȣ ��������
	 * @return ������ȣ
	 */
	public int getCertNumber();
}
