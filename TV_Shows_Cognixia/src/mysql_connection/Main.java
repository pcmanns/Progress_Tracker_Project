package mysql_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {


		/* Login */
		
		Connection conn = ConnManagerWithProperties.getConnection();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from actor");
			

			while(rs.next())
			{
				System.out.println("actor_id: " + rs.getInt("actor_id")); 
			}
			
			conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
