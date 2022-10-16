create database TV_DB;

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

insert into users(userName,pass) values ("Chris","root"),("Disha","123"),("Patterson","321"),("Parker","pass");

insert into planToWatch(showID,UsersID) values(1,1),(6,1),(3,2),(4,2),(7,3),(8,3),(9,4),(2,4);
insert into inProgress(showID,UsersID) values(3,1),(7,2),(3,3),(1,4);
insert into completed(showID,UsersID) values(4,1),(2,2),(1,3),(4,4);