package mysql_connection;

//import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseHandler {

	public static ArrayList<Show> getShows(ResultSet rs) {
		ArrayList<Show> showList= new ArrayList<Show>();
		try {
		
			while(rs.next())
			{
				showList.add(new Show(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(4)));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return showList;
	}
	
	public static ArrayList<Episode> getEpisodes(ResultSet rs) {
		ArrayList<Episode> episodeList= new ArrayList<Episode>();
		try {
		
			while(rs.next())
			{
				episodeList.add(new Episode(rs.getInt(1),rs.getInt(2),rs.getString(2),rs.getInt(4)));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return episodeList;
	}


}
