package mysql_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {


		/* Login */
		
		// -------- Temp Code---------------
		
		String[] logininfo = new String[2];
		logininfo[0] = "userName";
		logininfo[1] = "pass";
		
		// -------- Temp Code---------------

		
		
		Connection conn = ConnManagerWithProperties.getConnection();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users");			


			
			//verify login
			if(verifyLogin(rs, logininfo))
			{
				//login phase passed
				System.out.println("login verified");
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
	
	
	public static boolean verifyLogin(ResultSet rs, String[] loginInfo) {
		
		try {
			while(rs.next())
			{
				if(rs.getString(2).equals(loginInfo[0]))	//if username matches
				{
					if(rs.getString(3).equals(loginInfo[1]))	//if password matches
					{
						return true;	
					}
				}
			}
		} catch (SQLException e) {
			//
			e.printStackTrace();
		}
		
		return false;	//no match
	}
	

}
