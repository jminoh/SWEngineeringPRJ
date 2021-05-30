package model.dao;

public interface Certification {
	/**
	 * 본인 인증
	 * @param accountNumber
	 * @return
	 */
	public int deauthentication(String accountNumber);

	/**
	 * 인증번호 업데이트
	 * 8자리 난수를 만들어서 인증번호 생성
	 * @param accountNumber
	 * @return
	 */
	public int setCertNumber(String accountNumber);
	
	/**
	 * 인증번호 가져오기
	 * @param accountNumber
	 * @return
	 */
	public int getCertNumber(String accountNumber);
	
	/**
	 * 인증여부 가져오기
	 * (인증여부 업데이트는 앱에서 진행)
	 * @param accountNumber
	 * @return
	 */
	public int getCertification(String accountNumber);
}
