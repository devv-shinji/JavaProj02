import java.util.InputMismatchException;
import java.util.Scanner;

import ver02.Account;
import ver02.AccountManager;
import ver02.MenuChoice;

public class BankingSystemVer02 implements MenuChoice {

	public static void main(String[] args) {
		AccountManager manager = new AccountManager(50); 
		
		while (true) {
			Scanner scan = new Scanner(System.in);
			manager.showMenu();
			
			try {
				int choice = scan.nextInt();
										
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
					return;
				}
			}
			catch (InputMismatchException e){
				System.out.println("숫자를 입력해야 합니다.");
			}
			
		}////end of while
		
	}////end of main
	
}
