import java.util.InputMismatchException;
import java.util.Scanner;

import ver04.Account;
import ver04.AccountManager;
import ver04.MenuChoice;
import ver04.MenuSelectException;

public class BankingSystemVer04 implements MenuChoice {

	public static void main(String[] args) {
		AccountManager manager = new AccountManager(); 
		manager.loadData();
		
		while (true) {
			Scanner scan = new Scanner(System.in);
			manager.showMenu();
			
			try {
				int choice = scan.nextInt();
				//1~5사이 이외의 숫자 입력 시 예외처리
				try {
					if(choice<1 || choice>5) {
						MenuSelectException menuEx = new MenuSelectException();
						throw menuEx;
					}
				}
				catch(MenuSelectException e) {
					System.out.println("예외메세지:"+ e.getMessage());
				}
				//1~5 메뉴 구분
				switch (choice) {
				case MAKE:
					manager.makeAccount();
					break;
				case DEPOSIT:
					manager.depositMoney();
					break;
				case WITHDRAW:
					manager.withdrawMoney();
					break;
				case INQUIRE:
					manager.showAccInfo();
					break;
				case EXIT:
					System.out.println("프로그램을 종료합니다.");
					manager.saveData();
					return;
				}
			}
			catch (InputMismatchException e){
				System.out.println("숫자를 입력해야 합니다.");
			}
			
		}////end of while
		
	}////end of main
	
}
