package mysql_connection;

import java.util.Scanner;
public class Login {

	public static String[] userDetails() {
	String[] LoginInfo = new String[2];	
		
		
	Scanner scan = new Scanner(System.in);
	System.out.println("Enter Username");
	LoginInfo[0] = scan.next();
	
	
	
	System.out.println("Enter Password");
	LoginInfo[1] = scan.next();
	scan.close();
	return LoginInfo;
	

	}
}