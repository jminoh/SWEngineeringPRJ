package model.dao;

public interface Certification {
	/**
	 * ���� ����
	 * @param accountNumber
	 * @return
	 */
	public int deauthentication(String accountNumber);

	/**
	 * ������ȣ ������Ʈ
	 * 8�ڸ� ������ ���� ������ȣ ����
	 * @param accountNumber
	 * @return
	 */
	public int setCertNumber(String accountNumber);
	
	/**
	 * ������ȣ ��������
	 * @param accountNumber
	 * @return
	 */
	public int getCertNumber(String accountNumber);
	
	/**
	 * �������� ��������
	 * (�������� ������Ʈ�� �ۿ��� ����)
	 * @param accountNumber
	 * @return
	 */
	public int getCertification(String accountNumber);
}
