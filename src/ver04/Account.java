package ver04;

import java.io.Serializable;
import java.util.Scanner;

abstract public class Account implements Serializable{
	
	String account; //계좌번호
	String name; //이름
	int balance; //잔액
	
	public Account(String account, String name, int balance) {
		this.account = account;
		this.name = name;
		this.balance = balance;
	}
	
	//계좌정보출력
	public void showAccInfo() {
		System.out.println("--------");
		System.out.println("계좌번호:"+ account);
		System.out.println("고객이름:"+ name);
		System.out.println("잔고:"+ balance);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + balance;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Account other = (Account) obj;
		
		System.out.println("오버라이딩한 equals()메소드 호출 증거다!");
		
		if (this.account == other.account) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
//	@Override
//	public int hashCode() {
//		int hc1 = name.hashCode();
//		int hc2 = phone.hashCode();
//		
//		int result = hc1 + hc2;
//		
//		return result;
//	}
//
//	
//	@Override
//	public boolean equals(Object obj) {
//		
//		if (obj == null)
//			return false;
//		
//		PhoneInfo comparePhoneInfo = (PhoneInfo)obj;
//		
//		//자동호출되는지 확인
//		System.out.println("오버라이딩한 equals()메소드 호출 증거다!");
//		
//		if(comparePhoneInfo.name==this.name) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
}
