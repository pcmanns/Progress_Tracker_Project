package com.cognixia.jump.teamproject;

import java.util.Scanner;
public class Login    {

	public static String[] userDetails() {
	String[] LoginInfo = new String[2];	
		
		
	Scanner scan = new Scanner(System.in);
	System.out.println("Enter Username");
	LoginInfo[0] = scan.next();
	
	
	
	System.out.println("Enter Password");
	LoginInfo[1] = scan.next();
		
	return LoginInfo; 
	

	}
}
