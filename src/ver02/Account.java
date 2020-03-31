package ver02;

import java.util.Scanner;

public class Account {
	
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
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
}
