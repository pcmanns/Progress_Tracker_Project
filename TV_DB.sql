create database TV_DB;
CREATE USER 'testuser' IDENTIFIED BY '123';
USE TV_DB;

CREATE TABLE shows(
showID int auto_increment,
showName varchar(255),
episodesTotal int,
seasonsTotal int,
runTimeTotal int,
primary key (showID)
);

-- insert into

-- CREATE TABLE seasons(
-- seasonsID int auto_increment,
-- runTime int,
-- amountOfEpisodes int,
-- showID int,
-- primary key (seasonsID),
-- foreign key (showID) references shows(showID)
-- );

CREATE TABLE episodes(
episodesID int auto_increment,
<<<<<<< HEAD
episodeNumber int,
episodeName varchar(250),
seasonNumber int,
=======
episodeName varchar(255),
>>>>>>> 42117d1de22a9753ac402bd4edb02ee0d26517d2
runTime int,
primary key (episodesID),
foreign key (showID) references shows(showID)
);

CREATE TABLE users(
usersID int auto_increment,
userName varchar(250),
pass varchar(250),
primary key (usersID)
);

CREATE TABLE userProgress(
episodesID int,
usersID int,
foreign key (episodesID) references episodes(episodesID),
foreign key (usersID) references users(usersID)
);

CREATE TABLE planToWatch(
showID int,
usersID int,
foreign key (showID) references shows(showID),
foreign key (usersID) references users(usersID)
);