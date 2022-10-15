package mysql_connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Users {
	
	private int id;
	private String username;
	private ArrayList<Show> planToWatch; 
	private ArrayList<Show> inprogress;
	private ArrayList<Show> completed;
	
	public Users(int id) {
		Connection conn = ConnManagerWithProperties.getConnection();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users where usersID = "+id);	
			rs.next();
			username=rs.getString(2);
			this.id=id;
			rs = stmt.executeQuery("select shows.showID , showName from plantowatch Join shows on plantowatch.showID=shows.showID where usersID ="+id+";");
			planToWatch =DatabaseHandler.getShows(rs);
			rs = stmt.executeQuery("select shows.showID , showName from inProgress Join shows on inProgress.showID=shows.showID where usersID ="+id+";");
			inprogress = DatabaseHandler.getShows(rs);
			rs = stmt.executeQuery("select shows.showID , showName from completed Join shows on completed.showID=shows.showID where usersID ="+id+";");
			completed = DatabaseHandler.getShows(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void Save() {
		DatabaseHandler.saveComplete(completed, id);
		DatabaseHandler.saveInProgress(inprogress, id);
		DatabaseHandler.savePlanToWatch(planToWatch, id);


	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ArrayList<Show> getInprogress() {
		return inprogress;
	}

	public void setInprogress(ArrayList<Show> inprogress) {
		this.inprogress = inprogress;
	}

	public ArrayList<Show> getCompleted() {
		return completed;
	}


	public void setCompleted(ArrayList<Show> completed) {
		this.completed = completed;
	}

	
}
