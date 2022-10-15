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


		Connection conn = ConnManagerWithProperties.getConnection();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users");			
			int id = verifyLogin(rs, logininfo);
			
			//verify login
			if(id!=-1)
			{
				//login phase passed
				user= new Users(id);
				System.out.println("login verified");
				
				for(Show s : user.getPlanToWatch())	{
					System.out.println(s.getShowName());
				}
			}
			else
			{
				//re-do login or system.exit()
				// leave if next steps want to be out of above if()
				System.out.println("invalid login");
			}
			
			
			
			
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}












	/* ------------------------- Functionality Helper Methods ------------------------------- */

















	/*
	 * Description: Master display method for Main page - Displays all shows and category it appears in
	 */
	public static void displayAll(ArrayList<Show> shows, ArrayList<Show> c1, ArrayList<Show> c2, ArrayList<Show> c3) {

		System.out.println("                                                    Planning To Watch         In-Progress         Completed");
		System.out.println("--------------------------------------------------------------------------------------------------------------\n");

		for(Show s : shows)
		{
			System.out.print("*  " + s);

			if(c1.contains(s))	//Category: Planning to Watch
			{
				addSpace(50 - s.getShowName().length());
				addSpace(6);
				System.out.println("X");
			}
			else if(c2.contains(s))	//Category: In-Progress
			{
				addSpace(50 - s.getShowName().length());
				addSpace(30);
				System.out.println("X");
			}
			else if(c3.contains(s))	//Category: Completed
			{
				addSpace(50 - s.getShowName().length());
				addSpace(49);
				System.out.println("X");
			}
			else
			{
//				addSpace(50 - s.getShowName().length());
//				System.out.println("Error: displayAll()");
			}

		}

		System.out.println("\n--------------------------------------------------------------------------------------------------------------");
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

  public static void changeStatus(Users u,ArrayList<Show> ShowList) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Picked Show Number");
		int showNum = scan.nextInt();
		System.out.println("1.Plan to Watch");
		System.out.println("2.In Progress");
		System.out.println("3.Complete");
		System.out.println("Set as:");
		int c = scan.nextInt();
		Show show =ShowList.get(showNum);
		switch(c) {
			case 1:
				u.getPlanToWatch().add(show);
				for(int x=0;x<u.getInprogress().size();x++) {
					if(u.getInprogress().get(x).getShowName().equals(show.getShowName())) {
						u.getInprogress().remove(x);
					}
				}
				for(int x=0;x<u.getCompleted().size();x++) {
					if(u.getCompleted().get(x).getShowName().equals(show.getShowName())) {
						u.getCompleted().remove(x);
					}
				}
				break;
			case 2:
				u.getInprogress().add(show);
				for(int x=0;x<u.getPlanToWatch().size();x++) {
					if(u.getPlanToWatch().get(x).getShowName().equals(show.getShowName())) {
						u.getPlanToWatch().remove(x);
					}
				}
				for(int x=0;x<u.getCompleted().size();x++) {
					if(u.getCompleted().get(x).getShowName().equals(show.getShowName())) {
						u.getCompleted().remove(x);
					}
				}
				break;
			case 3:
				u.getCompleted().add(show);
				for(int x=0;x<u.getPlanToWatch().size();x++) {
					if(u.getPlanToWatch().get(x).getShowName().equals(show.getShowName())) {
						u.getPlanToWatch().remove(x);
					}
				}
				for(int x=0;x<u.getInprogress().size();x++) {
					if(u.getInprogress().get(x).getShowName().equals(show.getShowName())) {
						u.getInprogress().remove(x);
					}
				}
				break;

		} 
		u.Save();
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