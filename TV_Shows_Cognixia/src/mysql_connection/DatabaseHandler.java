package mysql_connection;

import java.sql.Connection;
//import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseHandler {
	public static ArrayList<Show> getShows(ResultSet rs) {
		ArrayList<Show> showList= new ArrayList<Show>();
		try {

			while(rs.next())
			{				
				showList.add(new Show(rs.getInt(1),rs.getString(2)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return showList;
	}

	public static boolean saveInProgress(ArrayList<Show> list,int userID) {

		Connection conn = ConnManagerWithProperties.getConnection();

		try {
			Statement stmt = conn.createStatement();
			stmt.execute("delete from inProgress where usersId ="+userID);
			for(Show s : list)	{
				stmt.execute("insert into inProgress(usersID,showID) values ("+userID+","+s.getShowId()+");");
			}
			return true; 
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	public static boolean savePlanToWatch(ArrayList<Show> list,int userID) {

		Connection conn = ConnManagerWithProperties.getConnection();

		try {
			Statement stmt = conn.createStatement();
			stmt.execute("delete from planToWatch where usersId ="+userID);
			for(Show s : list)	{
				stmt.execute("insert into planToWatch(usersID,showID) values ("+userID+","+s.getShowId()+");");
			}
			return true; 
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	public static boolean saveComplete(ArrayList<Show> list,int userID) {

		Connection conn = ConnManagerWithProperties.getConnection();

		try {
			Statement stmt = conn.createStatement();
			stmt.execute("delete from completed where usersId ="+userID);
			for(Show s : list)	{
				stmt.execute("insert into completed(usersID,showID) values ("+userID+","+s.getShowId()+");");
			}
			return true; 
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}