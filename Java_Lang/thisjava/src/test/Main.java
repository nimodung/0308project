package test;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int count = sc.nextInt();
		int input = 0;
		int[] shout_number = new int[24];
		for(int i = 0; i < count; i++) {
			input = sc.nextInt();
			shout_number[input]++;
		}
		
		for(int i = 1; i < 24; i++) {
			System.out.print(shout_number[i] + " ");
		}

	}

}
