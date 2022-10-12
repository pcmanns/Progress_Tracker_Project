create database TV_DB;
CREATE USER 'testuser' IDENTIFIED BY '123';
USE TV_DB;

CREATE TABLE shows(
ShowID int,
ShowName varchar(255),
Episodes int,
RunTime int,
primary key (showID)
);

CREATE TABLE episodes(
EpisodesID int,
EpisodeName varchar(255),
RunTime int,
primary key (EpisodesID),
foreign key (SeasonsID) references seasons(SeasonsID)
);

CREATE TABLE seasons(
SeasonsID int,
RunTime int,
primary key (SeasonsID),
foreign key (ShowID) references shows(ShowID)
);