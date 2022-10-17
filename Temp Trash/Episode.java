package mysql_connection;

public class Episode {
private int episodeNumber;
private int seasonsNumber;
private String name;
private int runTime;


public Episode(int enumber,int snumber ,String name, int runtime) {
	setEpisodeNumber(enumber);
	setSeasonsNumber(snumber);
	setName(name);
	setRunTime(runtime);
	
}

public int getEpisodeNumber() {
	return episodeNumber;
}
public void setEpisodeNumber(int episodeNumber) {
	this.episodeNumber = episodeNumber;
}
public int getSeasonsNumber() {
	return seasonsNumber;
}
public void setSeasonsNumber(int seasonsNumber) {
	this.seasonsNumber = seasonsNumber;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getRunTime() {
	return runTime;
}
public void setRunTime(int runTime) {
	this.runTime = runTime;
}



}