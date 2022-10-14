package mysql_connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Users {
	
	private int id;
	private String name;
	private String username;
	private ArrayList<Show> planToWatch; 
	private ArrayList<Show> WatchListed;
	
	public Users(int id) {
		Connection conn = ConnManagerWithProperties.getConnection();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users where usersID = "+id);	
			rs.next();
			username=rs.getString(2);
			this.id=id;
			rs = stmt.executeQuery("select shows.showID , showName , episodes, runTime from plantowatch Join shows on plantowatch.showID=shows.showID where usersID ="+id+";");
			planToWatch =DatabaseHandler.getShows(rs);	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<Show> getPlanToWatch() {
		return planToWatch;
	}

	public void setPlanToWatch(ArrayList<Show> planToWatch) {
		this.planToWatch = planToWatch;
	}

	public ArrayList<Show> getWatchListed() {
		return WatchListed;
	}

	public void setWatchListed(ArrayList<Show> watchListed) {
		WatchListed = watchListed;
	}

	
}
