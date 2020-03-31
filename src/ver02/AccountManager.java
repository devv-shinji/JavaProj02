package ver02;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager implements CustomSpecialRate {
	Account[] account;
	int numOfaccount;
	
	public AccountManager(int num) {
		account = new Account[num];
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
		String iAccount = null;
		String iName = null;
		int iBalance = 0;
		int iInterest = 0;
		String iCredit;
		int iChoice;
		
		try {
			Scanner scan1 = new Scanner(System.in);
			
			System.out.println("--계좌선택--");
			System.out.println("1.보통계좌");
			System.out.println("2.신용신뢰계좌");
			iChoice = scan1.nextInt();
			
			Scanner scan2 = new Scanner(System.in);
			System.out.println("계좌번호:");
			iAccount = scan2.nextLine();
			
			System.out.println("고객이름:");
			iName = scan2.nextLine();
			
			System.out.println("잔고:");
			iBalance = scan2.nextInt();
			
			System.out.println("기본이자(%)정수로입력:");
			iInterest = scan2.nextInt();
			
			//신용등급 
			Scanner scan3 = new Scanner(System.in);
			switch (iChoice) {
			case 1:
				NormalAccount account1 = new NormalAccount(iAccount, iName, iBalance, iInterest);
				account[numOfaccount++] = account1;
				break;
			case 2:
				System.out.println("신용등급(A,B,C등급):");
				iCredit = scan3.nextLine();
				
				HighCreditAccount account2 = new HighCreditAccount(iAccount, iName, iBalance, iInterest, iCredit);
				account[numOfaccount++] = account2;
				break;
			}
		}
		catch(InputMismatchException e) {
			System.out.println("1~2사이 숫자를 입력해주세요.");
		}
		System.out.println("신규 계좌개설이 완료되었습니다.");
		
	}
		
	//입금
	public void depositMoney() {
		Scanner scan = new Scanner(System.in);
		double iBalance; //입금액
		String searchAccount; //계좌번호
		double iCredit = 0; //신용등급별 이자율
		double totalDeposit; //이자계산까지 더한 입금액
		
		System.out.println("계좌번호:");
		searchAccount = scan.nextLine();
		
		for(int i=0; i<numOfaccount; i++) {
			
			if(searchAccount.compareTo(account[i].name)==0) {
				//일반계좌
				if(account[i] instanceof NormalAccount) {
					NormalAccount accountN = (NormalAccount)account[i];
					System.out.println("입금액:");
					iBalance = scan.nextInt();
								
					totalDeposit = (double)(account[i].balance)*(accountN.interest * 0.01) + iBalance;
					account[i].balance += (int)totalDeposit;
				}
				//신용계좌
				else {
					HighCreditAccount accountH = (HighCreditAccount)account[i];
					System.out.println("입금액:");
					iBalance = scan.nextInt();
					
					//신용등급별 이율
					if (accountH.creditLevel.equals("A")) {
						iCredit = 7;
					}
					else if(accountH.creditLevel.equals("B")) {
						iCredit = 4;
					}
					else if(accountH.creditLevel.equals("C")) {
						iCredit = 2;
					}
					
					totalDeposit = (double)(account[i].balance)*(accountH.interest * 0.01) + 
							((account[i].balance) * (iCredit * 0.01)) + iBalance;
					account[i].balance += (int)totalDeposit;
				}
				System.out.println("입금이 완료되었습니다.");
			}////end of if
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
			if(searchAccount.compareTo(account[i].name)==0) {
				
				System.out.println("출금액:");
				iBalance = scan.nextInt();
				account[i].balance -= iBalance;
				
				System.out.println("출금이 완료되었습니다.");
			}
		}
	}
		
	//전체계좌정보출력
	public void showAccInfo() {
		for(int i=0; i<numOfaccount; i++){
			account[i].showAccInfo();
		}
	}
}
