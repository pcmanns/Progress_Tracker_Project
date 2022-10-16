package mysql_connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuOptions {

	public static void menu() {

		System.out.println("		  /$$$$$$  /$$   /$$  /$$$$$$  /$$      /$$       /$$$$$$$$ /$$$$$$$   /$$$$$$   /$$$$$$  /$$   /$$ /$$$$$$$$ /$$$$$$$ ");	
		System.out.println("		 /$$__  $$| $$  | $$ /$$__  $$| $$  /$ | $$      |__  $$__/| $$__  $$ /$$__  $$ /$$__  $$| $$  /$$/| $$_____/| $$__  $$ ");
		System.out.println("		| $$  \\__/| $$  | $$| $$  \\ $$| $$ /$$$| $$         | $$   | $$  \\ $$| $$  \\ $$| $$  \\__/| $$ /$$/ | $$      | $$  \\ $$ ");
		System.out.println("		|  $$$$$$ | $$$$$$$$| $$  | $$| $$/$$ $$ $$         | $$   | $$$$$$$/| $$$$$$$$| $$      | $$$$$/  | $$$$$   | $$$$$$$/ ");
		System.out.println("		 \\____  $$| $$__  $$| $$  | $$| $$$$_  $$$$         | $$   | $$__  $$| $$__  $$| $$      | $$  $$  | $$__/   | $$__  $$ ");
		System.out.println("		 /$$  \\ $$| $$  | $$| $$  | $$| $$$/ \\  $$$         | $$   | $$  \\ $$| $$  | $$| $$    $$| $$\\  $$ | $$      | $$  \\ $$ ");
		System.out.println("		|  $$$$$$/| $$  | $$|  $$$$$$/| $$/   \\  $$         | $$   | $$  | $$| $$  | $$|  $$$$$$/| $$ \\  $$| $$$$$$$$| $$  | $$ ");
		System.out.println("		 \\______/ |__/  |__/ \\______/ |__/     \\__/         |__/   |__/  |__/|__/  |__/ \\______/ |__/  \\__/|________/|__/  |__/ ");
		                                                                                                                       
		System.out.println("Please Login");
		                                                                                                                       

		
		int userInput = 0;
		boolean logInStatus = false;
		String[] logininfo = userDetails();

		Users user = null;
		ArrayList<Show> showList =new ArrayList<Show>();
		Connection conn = ConnManagerWithProperties.getConnection();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users");			
			int id = verifyLogin(rs, logininfo);
				
			while(logInStatus==false)
			{
				try {
					if(id>-1) {
						user=new Users(id);
						logInStatus =true;
						
					}else {
						throw new LoginException("\nCustom Exception: LoginException - This is not a valid login ...");
					}
				} catch (LoginException e) {
					
					System.out.println(e.getMessage());
					
					Scanner scan = new Scanner(System.in);
					System.out.println("\n\tWhat do you want to do?\n"
							+ "1. Login\n2. Exit");
					boolean loop=true;
					do {
						try {
							userInput = scan.nextInt();
							if(userInput<0 || userInput>2) {
								throw new InvalidEntryException("\nCustom Exception: InvalidEntryException - Keep the Number Between 1 and 2");
							}
							loop=false;

						} catch (InputMismatchException r) {
							System.out.println("Only Enter a Number");
							scan.next();
						} catch (InvalidEntryException r) {
							System.out.println(r.getMessage());
						} catch (Exception r) {
							//Never have a Empty Exception
							r.printStackTrace();
						} 
					} while (loop);
					scan.nextLine();
					switch(userInput)
					{
					case 1:
						logininfo = userDetails();
						rs = stmt.executeQuery("select * from users");	
						id = verifyLogin(rs, logininfo);
						break;
						
					case 2:
						System.exit(0);
						
					}
				}
			}
			rs = stmt.executeQuery("select * from shows");
			showList=DatabaseHandler.getShows(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		while (logInStatus) {
		    displayAll(showList,user.getPlanToWatch(),user.getInprogress(),user.getCompleted());
			
			System.out.println("\nPlease choose a show from menu");

			System.out.println("\n1. Press 1 to Change the Status of the Show\n" + "2. Press 2 for Plan to Watch Table\n" + "3. Press 3 for InProgress Table\n"+"4. Press 4 for Completed Table\n"+"5. Press 5 to Quit");
			
		    Scanner scan= new Scanner(System.in);

		    boolean loop=true;
			do {
				try {
					userInput = scan.nextInt();
					if(userInput<0 || userInput>5) {
						throw new InvalidEntryException("\nCustom Exception: InvalidEntryException - Keep the Number Between 1 and 5");
					}
					loop=false;

				} catch (InputMismatchException e) {
					System.out.println("Only Enter a Number");
					scan.next();
				} catch (InvalidEntryException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					//Never have a Empty Exception
					e.printStackTrace();
				} 
			} while (loop);
		

			System.out.println();
			switch (userInput) {
			case 1:
				// To Change the Status of the Show
				changeStatus(user,showList);
				break;

			case 2:
				//Shows Plan To Watch Table
				displayPlanToWatch(user.getPlanToWatch());
				System.out.println("\nPress Any Button to go back");
				scan.nextLine();
				scan.nextLine();
				break;

			case 3:
				// shows InProgress Table
				displayInProgress(user.getInprogress());
				System.out.println("\nPress Any Button to go back");
				scan.nextLine();
				scan.nextLine();
				break;
			case 4:
				//Shows Completed Table
				displayCompleted(user.getCompleted());
				System.out.println("\nPress Any Button to go back");
				scan.nextLine();
				scan.nextLine();
				break;
			case 5:
				// To Quit
				logInStatus=false;
				break;
			default:
				System.out.println("\nPlease Select a value between 1 and 6.");

				break;
			}
		}
	}
	

		public static String[] userDetails() {
			String[] LoginInfo = new String[2];	
			
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter Username:");
			LoginInfo[0] = scan.next();
		
		
		
			System.out.println("Enter Password:");
			LoginInfo[1] = scan.next();
			return LoginInfo;
		
		}
		
		
		
		/* ------------------------- Functionality Helper Methods ------------------------------- */
		/*
		 * Description: Master display method for Main page - Displays all shows and category it appears in
		 */
		public static void displayAll(ArrayList<Show> shows, ArrayList<Show> c1, ArrayList<Show> c2, ArrayList<Show> c3) {
			
			System.out.println("                                                    Planning To Watch         In-Progress         Completed");
			System.out.println("--------------------------------------------------------------------------------------------------------------");
			
			for(int x=0;x<shows.size();x++)
			{
				Show s= shows.get(x);
				System.out.print("*  " + s.getShowName());
				boolean flag=true;
				for(int a=0;a<c1.size();a++) {
					if(c1.get(a).getShowName().equals(s.getShowName())) {   //Category: Plan To Watch
					addSpace(50 - s.getShowName().length());
					addSpace(6);
					System.out.print("X");
					addSpace(49);
					System.out.print("*");
					flag=false;
					}
				}
				for(int a=0;a<c2.size();a++) {
					if(c2.get(a).getShowName().equals(s.getShowName())) {   //Category: In-Progress
						addSpace(50 - s.getShowName().length());
						addSpace(30);
						System.out.print("X");
						addSpace(25);
						System.out.print("*");
						flag=false;
					}
				}
				for(int a=0;a<c3.size();a++) {
					if(c3.get(a).getShowName().equals(s.getShowName())) {   //Category: Completed
					addSpace(50 - s.getShowName().length());
					addSpace(49);
					System.out.print("X");
					addSpace(6);
					System.out.print("*");
					flag=false;
					}
				}
				if(flag) {
					addSpace(50 - s.getShowName().length()+56);
					System.out.print("*");
				}
				System.out.println("\n--------------------------------------------------------------------------------------------------------------");
			}
		}
		
		/*
		 * Description: Displays all shows for a user from the Plan To Watch Category
		 */
		public static void displayPlanToWatch(ArrayList<Show> planToWatch) {
			
			System.out.println("------------------- Planning to Watch --------------------");
			
			for(Show s : planToWatch)
			{
				System.out.print("*\t" + s.getShowName());
				addSpace(50 - s.getShowName().length() - 1);
				System.out.println("*");
			}
			
			System.out.println("----------------------------------------------------------");
		}
		
		/*
		 * Description: Displays all shows for a user from the In-Progress Category
		 */
		public static void displayInProgress(ArrayList<Show> inProgress) {
			
			System.out.println("---------------------- In-Progress -----------------------");
			
			for(Show s : inProgress)
			{
				System.out.print("*\t" + s.getShowName());
				addSpace(50 - s.getShowName().length() - 1);
				System.out.println("*");
			}
			
			System.out.println("----------------------------------------------------------");
		}
		
		/*
		 * Description: Displays all shows for a user from the Completed Category
		 */
		public static void displayCompleted(ArrayList<Show> completed) {
			
			System.out.println("----------------------- Completed ------------------------");
			
			for(Show s : completed)
			{
				System.out.print("*\t" + s.getShowName());
				addSpace(50 - s.getShowName().length() - 1);
				System.out.println("*");
			}
			
			System.out.println("----------------------------------------------------------");
		}
		
	  public static void changeStatus(Users u,ArrayList<Show> showList) {
		  System.out.println("------------------- Shows -------------------");
		  for(int x=0;x<showList.size();x++)
			{
				Show s= showList.get(x);
				System.out.print("*\t"+(x+1)+".  " + s.getShowName());
				addSpace(31 - s.getShowName().length());
				if(x<9) {
					System.out.print(" ");
				}
				System.out.println("*");
			}
		  System.out.println("---------------------------------------------");
		  
			Scanner scan = new Scanner(System.in);
			
			int showNum=-1;
			boolean loop=true;
			do {
				try {
					System.out.println("Picked Show Number");
					showNum = scan.nextInt()-1;
					if(showNum<0 || showNum>showList.size()-1) {
						throw new InvalidEntryException("\nCustom Exception: InvalidEntryException - Keep the Number Between 1 and "+showList.size());
					}
					loop=false;

				} catch (InputMismatchException e) {
					System.out.println("Only Enter a Number");
					scan.next();
				} catch (InvalidEntryException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					//Never have a Empty Exception
					e.printStackTrace();
				} 
			} while (loop);
			
			
			System.out.println("1.Plan to Watch");
			System.out.println("2.In Progress");
			System.out.println("3.Complete");
			System.out.println("Set as:");
			int choice = -1;

			loop=true;
			do {
				try {
					choice = scan.nextInt();
					if(choice<0 || choice>3) {
						throw new InvalidEntryException("\nCustom Exception: InvalidEntryException - Keep the Number Between 1 and 3");
					}
					loop=false;

				} catch (InputMismatchException e) {
					System.out.println("Only Enter a Number");
					scan.next();
				} catch (InvalidEntryException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					//Never have a Empty Exception
					e.printStackTrace();
				} 
			} while (loop);
			
			Show show =showList.get(showNum);
			u.changeStatus(choice,show);
		}
		
		
		
		/*
		 * Description: Add whitespace for @num amount of times
		 */
		public static void addSpace(int num) {
			for(int i = 0; i < num; i++)
			{
				System.out.print(" ");
			}
		}
		
		
		
		public static int verifyLogin(ResultSet rs, String[] loginInfo) {
			
			try {
				while(rs.next())
				{
					if(rs.getString(2).equals(loginInfo[0]))	//if username matches
					{
						if(rs.getString(3).equals(loginInfo[1]))	//if password matches
						{
							return rs.getInt(1);	
						}
					}
				}
			} catch (SQLException e) {
				//
				e.printStackTrace();
			}
			
			return -1;	//no match
		}
	
	
}
