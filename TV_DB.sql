create database TV_DB;
CREATE USER 'testuser' IDENTIFIED BY '123';
USE TV_DB;

CREATE TABLE shows(
showID int auto_increment,
showName varchar(250),
primary key (showID)
);

CREATE TABLE episodes(
episodesID int auto_increment,
episodeName varchar(250),
runTime int,
showID int,
primary key (episodesID),
foreign key (showID) references shows(showID)
);

CREATE TABLE users(
usersID int auto_increment,
userName varchar(250),
pass varchar(250),
primary key (usersID)
);

CREATE TABLE inProgress(
showID int,
usersID int,
foreign key (showID) references shows(showID),
foreign key (usersID) references users(usersID)
);

CREATE TABLE completed(
showID int,
usersID int,
foreign key (showID) references shows(showID),
foreign key (usersID) references users(usersID)
);

CREATE TABLE planToWatch(
showID int,
usersID int,
foreign key (showID) references shows(showID),
foreign key (usersID) references users(usersID)
);

insert into shows(showName) 
values ("Naruto"),("She-Hulk"),("Andor"),("The Rings of Power"),("House of the Dragon"),("Stranger Things"),("Cyberpunk Edgerunners"),("Spongebob Squarepants"),("Rick and Morty"),("The Boys"),("Avatar the Last Airbender");

alter table episodes drop foreign key episodes_ibfk_1;
alter table episodes add showID int;
ALTER TABLE episodes ADD FOREIGN KEY (showID) REFERENCES shows(showID) ON DELETE SET NULL;
alter table episodes drop seasonsID;
alter table userProgress rename to inProgress;
drop table seasons;
alter table shows drop episodes;
alter table shows drop runTime;
alter table shows modify showName varchar(250);

alter table inprogress add showID int;
alter table inprogress drop foreign key inprogress_ibfk_1;
alter table inprogress add foreign key (showID) references shows(showID) on delete set null;
alter table inprogress drop episodesID;

insert into users(userName,pass) values ("Chris","root");

insert into planToWatch(showID,UsersID) values(2,2);
select * from shows where showID = (Select showID from planToWatch where usersID = 1);
select * from plantowatch left Join shows on plantowatch.showID=shows.showID; 
 
select * from users;
select * from shows;
select * from plantowatch;