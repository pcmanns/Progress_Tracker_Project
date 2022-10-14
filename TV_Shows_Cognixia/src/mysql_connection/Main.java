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
		ArrayList<Show> ShowList= new ArrayList<Show>();
		
		Connection conn = ConnManagerWithProperties.getConnection();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users");			
			
			int id = verifyLogin(rs, logininfo);
			rs = stmt.executeQuery("select * from shows");
			ShowList=DatabaseHandler.getShows(rs);
			
			//verify login
			if(id!=-1)
			{
				//login phase passed
				user= new Users(id);
				for(Show s : user.getPlanToWatch())	{
					System.out.println(s.getShowName());
				}
				System.out.println("login verified");
				changeStatus(user,ShowList);
				
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
	
	
	public static void changeStatus(Users u,ArrayList<Show> ShowList) {
		for(int i=0; i<ShowList.size();i++)	{
			Show s = ShowList.get(i);
			System.out.println(i+". "+s.getShowName());
		}
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
