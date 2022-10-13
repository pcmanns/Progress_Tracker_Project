package mysql_connection;

import java.util.ArrayList;

public class User {
	
	private int id;
	private String name;
	private String username;
	private ArrayList<Show> planToWatch; 
	private ArrayList<Show> WatchListed;
	
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

	public User(String username,String password) {
		
	}
	
	
	
}
