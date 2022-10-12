create database TV_DB;
CREATE USER 'testuser' IDENTIFIED BY '123';
USE TV_DB;

CREATE TABLE shows(
showID int,
showName varchar(255),
episodes int,
runTime int,
primary key (showID)
);

CREATE TABLE episodes(
episodesID int,
episodeName varchar(255),
runTime int,
primary key (spisodesID),
foreign key (seasonsID) references seasons(seasonsID)
);

CREATE TABLE seasons(
seasonsID int,
runTime int,
amountOfEpisodes int,
primary key (seasonsID),
foreign key (showID) references shows(showID)
);