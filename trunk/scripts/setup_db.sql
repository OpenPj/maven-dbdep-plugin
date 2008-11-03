create database dbdep;

grant all on dbdep.* to 'dbdep'@'localhost' identified by 'dbdep' with grant option;

grant all on dbdep.* to 'dbdep'@'localhost.localdomain' identified by 'dbdep' with grant option;

CREATE TABLE dependencies (
id INT(10) NOT NULL AUTO_INCREMENT,
environment VARCHAR(30),
project VARCHAR(50) NOT NULL,
name VARCHAR(60) NOT NULL,
version VARCHAR(255) NOT NULL,
PRIMARY KEY (id)
)