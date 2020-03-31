package ver04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;



public class AccountManager implements CustomSpecialRate {
//	Account[] account;
	int numOfaccount;
	HashSet<Account> set = new HashSet<Account>();
	
	public AccountManager() {
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
		String iAccount = null; //계좌번호
		String iName = null; //고객이름
		int iBalance = 0; //잔고
		int iInterest = 0; //이율
		String iCredit; //신용등급
		
		int iChoice; //메뉴선택
		
		boolean searchFlag = false;
		Iterator<Account> itr = set.iterator();
		
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
			
			//중복 허용 여부 확인
			while(itr.hasNext()) {
				Account account = itr.next();
				if(iName.equals(account.name)) {
					System.out.println("동일한 이름이 존재합니다. 계속 진행하시겠습니까? Y:1, N:2");
					int num = scan1.nextInt();
					
					if(num==1) {
						searchFlag = true;
					}
					else {
						searchFlag = false;
						return;
					}
				}
			}
			
			System.out.println("잔고:");
			iBalance = scan2.nextInt();
			
			System.out.println("기본이자(%)정수로입력:");
			iInterest = scan2.nextInt();
			
			//신용등급 
			Scanner scan3 = new Scanner(System.in);
			switch (iChoice) {
			case 1:
				NormalAccount account1 = new NormalAccount(iAccount, iName, iBalance, iInterest);
				set.add(account1);
				break;
			case 2:
				System.out.println("신용등급(A,B,C등급):");
				iCredit = scan3.nextLine();
				
				HighCreditAccount account2 = new HighCreditAccount(iAccount, iName, iBalance, iInterest, iCredit);
				set.add(account2);
				break;
			}
			System.out.println("신규 계좌개설이 완료되었습니다.");
		}
		catch(InputMismatchException e) {
			System.out.println("입력형식이 맞지 않습니다.");
		}
		
	}
		
	//입금
	public void depositMoney() {
		Scanner scan = new Scanner(System.in);
		double iBalance; //입금액
		String searchAccount; //계좌번호
		double iCredit = 0; //신용등급별 이자율
		double totalDeposit; //이자계산까지 더한 입금액
		
		boolean searchFlag = false;
		Iterator<Account> itr = set.iterator();
		
		try {
			System.out.println("계좌번호:");
			searchAccount = scan.nextLine();
		
			System.out.println("입금액:");
			iBalance = scan.nextDouble();
			
			if (iBalance>0 && iBalance%500==0) { //입금액은 양수와 500원 단위로만 가능
				while(itr.hasNext()) {
					Account account = itr.next();
					if(searchAccount.equals(account.account)) {
						//일반계좌
						if(account instanceof NormalAccount) {
							NormalAccount accountN = (NormalAccount)account;
							//이자 포함 계산
							totalDeposit = (double)(account.balance)*(accountN.interest * 0.01) + iBalance;
							account.balance += (int)totalDeposit;
						}
						//신용계좌
						else {
							HighCreditAccount accountH = (HighCreditAccount)account;
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
							//이자 포함 계산
							totalDeposit = (double)(account.balance)*(accountH.interest * 0.01) + 
									((account.balance) * (iCredit * 0.01)) + iBalance;
							account.balance += (int)totalDeposit;
						}
						System.out.println("입금이 완료되었습니다.");
					}
				}
			}////end of if
			else {
				System.out.println("입금액은 0보다 작을 수 없으며, 500원 단위로 가능합니다.");
			}
		}
		catch(InputMismatchException e) {
			System.out.println("숫자를 입력해주세요.");
		}
	}
	
	//출금
	public void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		int iBalance;
		String searchAccount;
		boolean searchFlag = false;
		
		System.out.println("계좌번호:");
		searchAccount = scan.nextLine();
		
		Iterator<Account> itr = set.iterator();
		
		while(itr.hasNext()) {
			Account account = itr.next();
			if(searchAccount.equals(account.account)) {
				System.out.println("출금액:");
				iBalance = scan.nextInt();
				
				if (iBalance<account.balance) { //출금은 잔고보다 적은 금액일 때 가능
					if (iBalance>0 && iBalance%1000==0) { //출금액은 양수와 1000원 단위로만 가능
						account.balance -= iBalance;
						System.out.println("출금이 완료되었습니다.");
					}
					else {
						System.out.println("출금액은 0보다 작을 수 없으며, 1000원 단위로 가능합니다.");
					}
				}
				else {
					Scanner scan1 = new Scanner(System.in);
					System.out.println("잔액이 부족합니다. 금액 전체를 출금할까요?");
					System.out.println(" - YES: 금액전체 출금처리");
					System.out.println(" - NO: 출금요청 취소");
					
					String yesORno = scan1.nextLine();
					if(yesORno.equals("YES")) {
						account.balance = 0;
						System.out.println("출금이 완료되었습니다.");
					}
					else if(yesORno.equals("NO")) {
						System.out.println("출금이 취소되었습니다.");
						break;
					}
				}
			}
		}
	}////end of withdraw
		
	//전체계좌정보출력
	public void showAccInfo() {
		for(Account ac : set){
			ac.showAccInfo();
		}
	}
	
	//데이터 로드
	public void loadData() {
		try {
			ObjectInputStream in = 
					new ObjectInputStream(new FileInputStream("src/ver04/account_info.obj"));
			
			HashSet<Account> account = (HashSet<Account>)in.readObject();
			set = account;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//데이터 저장
	public void saveData() {
		try {
			ObjectOutputStream out = 
					new ObjectOutputStream(new FileOutputStream("src/ver04/account_info.obj"));
			
			out.writeObject(set);
		}
		catch(Exception e){
			System.out.println("예외발생");
			e.printStackTrace();
		}
	}
	
}////end of AccountManager
