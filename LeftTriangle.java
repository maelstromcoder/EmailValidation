// 	import java.util.Scanner;

public class LeftTriangle {

//	static Scanner console = new Scanner(System.in);
	static int asterik;
	
	public static void main(String[] args) {
		
// 		asterik = console.nextInt();
		asterik = Integer.parseInt(args[0]);
		if (asterik >= 0) {
			printReverseRightTriangle(asterik);
		}
		else {
			System.out.println("Error: Input value must be >=0");
		}
	}
	
	public static void printReverseRightTriangle(int x) {
		
		VerifyIfOne(x);
		
		int space = asterik - x;
		if (space < asterik) {
			for (int i = space; i > 0; i--) {
				System.out.print(" ");
			}
		}
		
		for (int i = x; i > 0; i--) {
			System.out.print("*");
		}
		
		System.out.println();
		if (x > 0) {
			printReverseRightTriangle(x-1);
		}
	}
	
	public static void VerifyIfOne(int x) {
		
		if (asterik == 1) {
			System.out.println("*\n");
			System.out.println("Process finished with exit code 0");
			System.exit(0);
		}
		else {
			return;
		}
	}
}
	

