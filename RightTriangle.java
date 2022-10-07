//	import java.util.Scanner;

public class RightTriangle {

//	static Scanner console = new Scanner(System.in);
	static int asterik;
	
	public static void main(String[] args) {
		
//		asterik = console.nextInt();
		asterik = Integer.parseInt(args[0]);
		if (asterik >= 0) {
			printRightTriangle(asterik);
		}
		else {
			System.out.println("Error: Input value must be >=0");
		}
	}
	
	public static void printRightTriangle(int x) {
		
		VerifyIfOne(x);
				
		int space = (asterik - x) + 1;
		
		for (int i = 0; i < x - 1; i++) {
			System.out.print(" ");
		}
		
		for (int i = 0; i < space; i++) {
			if (space <= asterik) {
				System.out.print("*");
			}
			else {
				return;
			}
		}
		
		System.out.println();
		if (x > 0) {
			printRightTriangle(x - 1);
		}	
	}
	
	public static void VerifyIfOne(int x) {
		
		if (asterik == 1) {
			System.out.println("*");
			System.exit(0);
		}
		else {
			return;
		}
	}
}
	

