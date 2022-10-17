package com.cognixia.jump.teamproject;

import java.util.Scanner;

public class MenuOptions {

	public static void main(String[] args) {
		menu();
	}
	public static void menu() {
		int userInput = 0;
		boolean logoutStatus = false;
		

		while (logoutStatus == false) {
			System.out.println("\nPlease choose a show from menu");

			System.out.println("\n1. Press 1 to Change the Status of the Show\n" + "2. Press 2 to Logout\n" + "3. Press 3 to Change User\n");
			
		    Scanner scan= new Scanner(System.in);
		    userInput = scan.nextInt();


			System.out.println();
			switch (userInput) {
			case 1:
				// To Change the Status of the Show
				
				break;

			case 2:
				// To Logout
				//String response = null;
				//System.out.println("\nDo you want to log out? [Y/N]");
				//Scanner scan2 = new Scanner(System.in);
				//response = scan2.next();

				//if (response.equalsIgnoreCase("y")) {
					
					//logoutStatus = true;
				//}
				System.exit(0);
				break;

			case 3:
				// To Change User
				
				break;

				
			default:
				System.out.println("\nPlease Select a value between 1 and 3.");

				break;
			}
		}
	}
}
