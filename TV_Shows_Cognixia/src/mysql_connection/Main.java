package mysql_connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {


		/* Login */
		
		// -------- Temp Code---------------
		
		String[] logininfo = Login.userDetails();
	//	logininfo[0] = "userName";
	//	logininfo[1] = "pass";
		
		// -------- Temp Code---------------
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
