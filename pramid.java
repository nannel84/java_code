package practice;

import java.util.Scanner;

public class pramid {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("정수를 입력하세요.");
		int num = scanner.nextInt();
		
		

			for (int i=0; i<=num;i++) {
				for (int k=1; k<=i;k++) {
					System.out.print("*");
				}
				System.out.println("");
	}
	}
}



