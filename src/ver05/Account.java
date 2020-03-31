package ver05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	Connection con;
	Statement stmt;
	ResultSet rs;
	PreparedStatement psmt;
	
	public Account() {
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:orcl", "kosmo", "1234");
			System.out.println("오라클 DB 연결 성공");
		}
		catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
		catch(SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("알 수 없는 예외발생");
			e.printStackTrace();
		}
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
		try {
			String query = "INSERT INTO banking_tb VALUES (seq_banking.nextval,?,?,?)";
			psmt = con.prepareStatement(query);
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("계좌번호:");
			String iAccount = scan.nextLine();
			
			System.out.println("고객이름:");
			String iName = scan.nextLine();
			
			System.out.println("잔고:");
			int iBalance = scan.nextInt();
			
			psmt.setString(1, iAccount);
			psmt.setString(2, iName);
			psmt.setInt(3, iBalance);
			
			psmt.executeUpdate();
			
			System.out.println("신규 계좌개설이 완료되었습니다.");
		}
		catch(SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("알 수 없는 예외발생");
			e.printStackTrace();
		} 
	}
	
	//입금
	public void depositMoney() {
		try {
			String query = "UPDATE banking_tb SET balance=balance+? WHERE account=?";
			psmt = con.prepareStatement(query);
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("계좌번호:");
			String searchAccount = scan.nextLine();
			
			System.out.println("입금액:");
			int iBalance = scan.nextInt();
			
			psmt.setInt(1, iBalance);
			psmt.setString(2, searchAccount);
			
			psmt.executeUpdate();
			System.out.println("입금이 완료되었습니다.");
		}
		catch(SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("알 수 없는 예외발생");
			e.printStackTrace();
		}  
		
	}
	
	//출금
	public void withdrawMoney() {
		try {
			String query = "UPDATE banking_tb SET balance=balance-? where account=?";
			psmt = con.prepareStatement(query);
			
			Scanner scan = new Scanner(System.in);

			System.out.println("계좌번호:");
			String searchAccount = scan.nextLine();
			
			System.out.println("출금액:");
			int iBalance = scan.nextInt();
			
			psmt.setInt(1, iBalance);
			psmt.setString(2, searchAccount);
			
			psmt.executeUpdate();
			System.out.println("출금이 완료되었습니다.");
		}
		catch(SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("알 수 없는 예외발생");
			e.printStackTrace();
		}  
	}
	
	//전체계좌정보출력
	public void showAccInfo() {
		try {
			stmt = con.createStatement();
			
			String query = "SELECT account, name, balance FROM banking_tb";
			
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String account = rs.getString(1);
				String name = rs.getString(2);
				int balance = rs.getInt(3);
				System.out.println("-----------------");
				System.out.println("계좌번호:"+ account);
				System.out.println("예금주명:"+ name);
				System.out.println("잔고:"+ balance);
			}
			System.out.println("조회가 완료되었습니다.");
		}
		catch(SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("알 수 없는 예외발생");
			e.printStackTrace();
		}  
	}
	
	
}
