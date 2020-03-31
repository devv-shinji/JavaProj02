package ver03;

public class HighCreditAccount extends Account implements CustomSpecialRate {
	int interest;
	String creditLevel;
	
	public HighCreditAccount(String account, String name, int balance, int interest, String creditLevel) {
		super(account, name, balance);
		this.interest = interest;
		this.creditLevel = creditLevel;
	}
	
	//계좌정보출력
	@Override
	public void showAccInfo() {
		System.out.println("--------");
		System.out.println("계좌번호:"+ account);
		System.out.println("고객이름:"+ name);
		System.out.println("잔고:"+ balance);
		System.out.println("기본이자:"+ interest);
		System.out.println("신용등급:"+ creditLevel);
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
}
