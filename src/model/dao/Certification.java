package model.dao;

public interface Certification {
	/**
	 * 본인 인증
	 * @param accountNumber
	 */
	public void deauthentication(String accountNumber);

	/**
	 * 인증번호 업데이트
	 * 8자리 난수를 만들어서 인증번호 생성
	 * @param accountNumber
	 */
	public void setCertNumber(String accountNumber);
	
	/**
	 * 인증번호 가져오기
	 * @return 인증번호
	 */
	public int getCertNumber();
}
