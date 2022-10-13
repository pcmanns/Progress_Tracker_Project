package mysql_connection;

import java.util.ArrayList;

public class Show {
private int showId;
private String showName;
private int episodesNumb;
private int seasonsNumb;
private int runTime;
private ArrayList<Episode> EpisodeList;

public Show(int showId, String showName, int episodeNumb, int seasonNumb,int runTime) {
	setShowId(showId);
	setShowName(showName);
	setEpisodesNumb(episodeNumb);
	setSeasonsNumb(seasonNumb);
	setRunTime(runTime);
	
}

public int getShowId() {
	return showId;
}
public void setShowId(int showId) {
	this.showId = showId;
}
public String getShowName() {
	return showName;
}
public void setShowName(String showName) {
	this.showName = showName;
}
public int getEpisodesNumb() {
	return episodesNumb;
}
public void setEpisodesNumb(int episodesNumb) {
	this.episodesNumb = episodesNumb;
}
public int getSeasonsNumb() {
	return seasonsNumb;
}
public void setSeasonsNumb(int seasonsNumb) {
	this.seasonsNumb = seasonsNumb;
}
public int getRunTime() {
	return runTime;
}
public void setRunTime(int runTime) {
	this.runTime = runTime;
}

public ArrayList<Episode> getEpisodeList() {
	return EpisodeList;
}

public void setEpisodeList(ArrayList<Episode> episodeList) {
	EpisodeList = episodeList;
}

}
