package puzzle;

import java.util.Random;
import java.util.Scanner;

interface direction {
	int LEFT=1, RIGHT=2, UP=3, DOWN=4;
}

public class PuzzleMain implements direction {
	
	static int col=2;
	static int row=2;
	
	public static Scanner scan = new Scanner(System.in);
	
	//키보드입력
	public static int direction_key() {
		System.out.println("좌:A, 우:D, 상:W, 하:S");
		System.out.println("방향입력>>");
		String key = scan.nextLine();
		
		if (key.equalsIgnoreCase("A")) {
			return 1;
		}
		else if(key.equalsIgnoreCase("D")) {
			return 2;
		}
		else if(key.equalsIgnoreCase("W")) {
			return 3;
		}
		else if(key.equalsIgnoreCase("S")) {
			return 4;
		}
		else {
			return 0;
		}
	}
	
	//콘솔 출력
	public static void print_puzzle(int[][] puzzle) {
		System.out.println("-----------");
		
		for(int r=0; r<puzzle.length; r++) {
			for(int c=0; c<puzzle[r].length; c++) {
				if(puzzle[r][c] != 0) {
					System.out.printf("%3d", puzzle[r][c]);
				}
				else {
					System.out.print("   ");
				}
			}
			System.out.println();
		}
		
		System.out.println("-----------");
	}
	
	//완성여부
	public static boolean is_ending(int[][] puzzle) {
		int[][] org_puzzle = {
				{1,2,3},
				{4,5,6},
				{7,8,0}
		};
		
		for(int r=0; r<puzzle.length; r++) {
			for(int c=0; c<puzzle[r].length; c++) {
				if(!(puzzle[r][c] == org_puzzle[r][c])) {
					return false;
				}
			}
			System.out.println();
		}
		return true;		
	}
	
	//숫자섞기
	public static int[][] shuffle (int[][] puzzle) {
		
		Random r = new Random();
		
		//지정한 숫자까지 랜덤 섞기
		for(int i=1; i<30; i++) {
			try {
				int ranN = r.nextInt(4)+1;
				
				switch (ranN) {
				case LEFT:
						puzzle[row][col] = puzzle[row][col+1];
						puzzle[row][col+1] = 0;
						col++;
					break;
				case RIGHT:
						puzzle[row][col] = puzzle[row][col-1];
						puzzle[row][col-1] = 0;
						col--;
					break;
				case UP:
						puzzle[row][col] = puzzle[row+1][col];
						puzzle[row+1][col] = 0;
						row++;
					break;
				case DOWN:
						puzzle[row][col] = puzzle[row-1][col];
						puzzle[row-1][col] = 0;
						row--;
					break;
				}
			
			}
			catch(Exception e) {
			}
		}
		return puzzle;
		
	}
	
	//게임실행
	public static void gamestart() {
		int[][] puzzle = {
				{1,2,3},
				{4,5,6},
				{7,8,0}
		};
		int key;
		
		shuffle(puzzle);
		
		while(!is_ending(puzzle)) {
			try {
				print_puzzle(puzzle);
				key = direction_key();
				
				switch (key) {
				case LEFT:
						puzzle[row][col] = puzzle[row][col+1];
						puzzle[row][col+1] = 0;
						col++;
					break;
				case RIGHT:
						puzzle[row][col] = puzzle[row][col-1];
						puzzle[row][col-1] = 0;
						col--;
					break;
				case UP:
						puzzle[row][col] = puzzle[row+1][col];
						puzzle[row+1][col] = 0;
						row++;
					break;
				case DOWN:
						puzzle[row][col] = puzzle[row-1][col];
						puzzle[row-1][col] = 0;
						row--;
					break;
				}
			}
			catch(ArrayIndexOutOfBoundsException e) {
			}
		}
		System.out.println("=====퍼즐 완성=====");
		print_puzzle(puzzle);
		restart();
	}
	
	//게임 재실행 여부
	public static void restart() {
		System.out.println("재시작 하시겠습니까? Y:재시작");
		System.out.println("종료하려면 아무키나 누르세요.");
		String restart = scan.nextLine();
		
		if(restart.equalsIgnoreCase("Y")) {
			gamestart();
		}
		else {
			System.out.println("게임이 종료됩니다...");
			System.exit(0);
		}
	}
	
	//메인
	public static void main(String[] args) {
		gamestart();
		restart();
	}
}