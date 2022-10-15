package mysql_connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.Scanner;


public class Main {

	public static void main(String[] args) {


		/* Login */
		
		String[] logininfo = Login.userDetails();

		Users user;
		ArrayList<Show> showList =new ArrayList<Show>();
		Connection conn = ConnManagerWithProperties.getConnection();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users");			
			int id = verifyLogin(rs, logininfo);
			
			rs = stmt.executeQuery("select * from shows");
			
			showList=DatabaseHandler.getShows(rs);
			
			
			//verify login

				
				while(id == -1)
				{
					try {
						
						throw new LoginException("\nCustom Exception: LoginException - This is not a valid login ...");
						
					} catch (LoginException e) {
						
						System.out.println(e.getMessage());
						
						Scanner scan = new Scanner(System.in);
						System.out.println("\n\tWhat do you want to do?\n"
								+ "1. Login\n2. Exit");
						switch(scan.nextInt())
						{
						case 1:
							logininfo = Login.userDetails();
							id = verifyLogin(rs, logininfo);
							break;
							
						case 2:
							System.exit(0);
							
						}
					}
				}
				
				//login phase passed 
				user= new Users(id); 
				System.out.println("login verified"); 
				displayAll(showList,user.getPlanToWatch(),user.getInprogress(),user.getCompleted()); 
				changeStatus(user,showList); 
				displayPlanToWatch(user.getPlanToWatch()); 
				displayInProgress(user.getInprogress()); 
				displayCompleted(user.getCompleted()); 

			
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
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
		System.out.println("Picked Show Number");
		int showNum = scan.nextInt()-1;
		System.out.println("1.Plan to Watch");
		System.out.println("2.In Progress");
		System.out.println("3.Complete");
		System.out.println("Set as:");
		int c = scan.nextInt();
		Show show =showList.get(showNum);
		u.changeStatus(c,show);
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
