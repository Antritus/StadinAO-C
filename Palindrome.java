package me.antritus.edu.stadinao;

import java.util.Scanner;

public final class Palindrome {
	private final boolean debug;

	public Palindrome(boolean debug) {
		this.debug = debug;
	}

	public static void main(String[] args){
		Palindrome palindrome = new Palindrome(false);
		Scanner scanner = new Scanner(System.in);

		while (true){
			String input = scanner.next();
			if (input.equalsIgnoreCase("stop")){
				System.exit(0);
			}

			boolean isPalindrome = palindrome.isPalindrome(input);

			if (isPalindrome){
				System.out.printf("%s is a palindrome", input);
			} else {
				System.out.printf("%s is not a palindrome", input);
			}

			System.out.println();
		}
	}

	public boolean isPalindrome(String input){
		StringBuilder reverse = new StringBuilder();
		String[] split = input.split("");

		for (String val : split){
			reverse.insert(0, val);
		}
		if (debug) {
			System.out.println("Reversing: " + input);
			System.out.println("Reversed: " + reverse);
		}
		return reverse.toString().equalsIgnoreCase(input);
	}
}
