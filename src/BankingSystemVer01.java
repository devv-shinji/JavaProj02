import java.util.InputMismatchException;
import java.util.Scanner;

import ver01.Account;
import ver01.MenuChoice;

public class BankingSystemVer01 implements MenuChoice {

	public static void main(String[] args) {
		Account account = new Account(50); 
		
		while (true) {
			Scanner scan = new Scanner(System.in);
			account.showMenu();
			
			try {
				int choice = scan.nextInt();
										
				switch (choice) {
				case MAKE:
					account.makeAccount();
					break;
				case DEPOSIT:
					account.depositMoney();
					break;
				case WITHDRAW:
					account.withdrawMoney();
					break;
				case INQUIRE:
					account.showAccInfo();
					break;
				case EXIT:
					System.out.println("프로그램을 종료합니다.");
					return;
				}
			}
			catch (InputMismatchException e){
				System.out.println("숫자를 입력해야 합니다.");
			}
			
		}////end of while
		
	}////end of main
	
}
