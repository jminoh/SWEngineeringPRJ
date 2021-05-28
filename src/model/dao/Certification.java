package model.dao;

public interface Certification {
	/**
	 * 본인 인증
	 * @param account_number
	 */
	public void deauthentication(String account_number);
}
