create database TV_DB;
CREATE USER 'testuser' IDENTIFIED BY '123';
USE TV_DB;

CREATE TABLE shows(
showID int auto_increment,
showName varchar(255),
episodes int,
runTime int,
primary key (showID)
);

CREATE TABLE episodes(
episodesID int auto_increment,
episodeName varchar(255),
runTime int,
primary key (spisodesID),
foreign key (seasonsID) references seasons(seasonsID)
);

CREATE TABLE seasons(
seasonsID int auto_increment,
runTime int,
amountOfEpisodes int,
primary key (seasonsID),
foreign key (showID) references shows(showID)
);

CREATE TABLE users(
usersID int auto_increment,
userName varchar(250),
pass varchar(250),
primary key (usersID)
);

CREATE TABLE userProgress(
userProgressID int auto_increment,
foreign key (episodesID) references episodes(episodesID),
foreign key (usersID) references users(usersID)
);

CREATE TABLE planToWatch(
planID  int auto_increment,
foreign key (usersID) references users(userID),
foreign key (showID) references shows(showID)
);