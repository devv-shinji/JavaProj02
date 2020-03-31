package ver01;

import java.util.Scanner;

class AccountList {
	
	String account; //계좌번호
	String name; //이름
	int balance; //잔액
	
	public AccountList(String account, String name, int balance) {
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

public class Account {
	int numOfaccount;
	private AccountList[] accountList;
	
	public Account(int num) {
		accountList = new AccountList[num];
		numOfaccount = 0;
	}
	
	//메뉴출력
	public void showMenu() {
		System.out.println("\n===원하는 메뉴를 선택하세요===");
		System.out.println("1.계좌개설");
		System.out.println("2.입    금");
		System.out.println("3.출    금");
		System.out.println("4.계좌정보 출력");
		System.out.println("5.프로그램 종료");
		System.out.print("메뉴선택>>>\n");
	}
	
	//계좌개설
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		String iAccount;
		String iName;
		int iBalance;
		
		System.out.println("계좌번호:");
		iAccount = scan.nextLine();
		
		System.out.println("고객이름:");
		iName = scan.nextLine();
		
		System.out.println("잔고:");
		iBalance = scan.nextInt();
		
		AccountList newAccount = new AccountList(iAccount, iName, iBalance);
		accountList[numOfaccount++] = newAccount;
		
		System.out.println("신규 계좌개설이 완료되었습니다.");
		
	}
	
	//입금
	public void depositMoney() {
		Scanner scan = new Scanner(System.in);
		int iBalance;
		String searchAccount;
		
		System.out.println("계좌번호:");
		searchAccount = scan.nextLine();
		
		for(int i=0; i<numOfaccount; i++) {
			if(searchAccount.compareTo(accountList[i].name)==0) {
				
				System.out.println("입금액:");
				iBalance = scan.nextInt();
				accountList[i].balance += iBalance;
				
				System.out.println("입금이 완료되었습니다.");
			}
		}
	}
	
	//출금
	public void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		int iBalance;
		String searchAccount;
		
		System.out.println("계좌번호:");
		searchAccount = scan.nextLine();
		
		for(int i=0; i<numOfaccount; i++) {
			if(searchAccount.compareTo(accountList[i].name)==0) {
				
				System.out.println("출금액:");
				iBalance = scan.nextInt();
				accountList[i].balance -= iBalance;
				
				System.out.println("출금이 완료되었습니다.");
			}
		}
	}
	
	//전체계좌정보출력
	public void showAccInfo() {
		for(int i=0; i<numOfaccount; i++){
			accountList[i].showAccInfo();
		}
	}
	
	
}
