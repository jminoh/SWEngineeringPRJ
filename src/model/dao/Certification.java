package model.dao;

public interface Certification {
	/**
	 * 본인 인증
	 * @param accountNumber
	 */
	public void deauthentication(String accountNumber);
}
