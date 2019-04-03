DROP DATABASE IF EXISTS wedding;
DROP USER IF EXISTS 'wedding'@'localhost';
CREATE DATABASE wedding;
CREATE USER 'wedding'@'localhost' IDENTIFIED BY 'WeddingPassword';
GRANT ALL PRIVILEGES ON wedding.* TO 'wedding'@'localhost';
USE wedding;

CREATE TABLE `users` (
	user_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	username varchar(255) not null,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    email varchar(255) not null,
	password varchar(255) NOT NULL,
	enabled tinyint(1) default 1,
	token_expired tinyint(1) default 1,
    last_password_reset TIMESTAMP default now(),
	unique key(username)
) Engine=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `roles` (
	role_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	role_name varchar(255) not null,
	unique key(role_name)
) Engine=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `privileges` (
	privilege_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	privilege_name varchar(255) not null,
	unique key(privilege_name)
) Engine=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users_roles` (
	user_id INT NOT NULL,
	role_id INT NOT NULL,
	foreign key (user_id) references users(user_id),
	foreign key (role_id) references roles(role_id),
    PRIMARY KEY (user_id, role_id)
) Engine=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE roles_privileges (
	role_id INT NOT NULL,
	privilege_id INT NOT NULL,
	foreign key (role_id) references roles(role_id),
	foreign key (privilege_id) references `privileges`(privilege_id),
    PRIMARY KEY (role_id, privilege_id)
) Engine=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `events` (
	event_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    uuid varchar(36) NOT NULL UNIQUE,
    `name` varchar(255) NOT NULL    
) Engine=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users_events` (
	user_id INT NOT NULL,
	event_id INT NOT NULL,
	foreign key (user_id) references users(user_id),
	foreign key (event_id) references `events`(event_id),
    PRIMARY KEY (user_id, event_id)
) Engine=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `files` (
	content_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    sha256 varchar(64) NOT NULL UNIQUE,
    `name` varchar(255) NOT NULL,
    `comment` varchar(255),
    `extension` varchar(255) NOT NULL,
    event_id INT NOT NULL,
    user_id INT NOT NULL,
    created_on TIMESTAMP NOT NULL DEFAULT now(),
    
    foreign key (user_id) references `users`(user_id),
    foreign key (event_id) references `events`(event_id)
) Engine=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO roles(role_id, role_name) VALUES(1, "ROLE_ADMIN");
INSERT INTO roles(role_id, role_name) VALUES(2, "ROLE_USER");

INSERT INTO `privileges`(privilege_id, privilege_name) VALUES(1, "READ_PRIVILEGE");
INSERT INTO `privileges`(privilege_id, privilege_name) VALUES(2, "WRITE_PRIVILEGE");

INSERT INTO roles_privileges(role_id, privilege_id) VALUES(1, 1);
INSERT INTO roles_privileges(role_id, privilege_id) VALUES(1, 2);
INSERT INTO roles_privileges(role_id, privilege_id) VALUES(2, 1);

INSERT INTO users(user_id, username, firstname, lastname, email, password) VALUES(1, "TESTUSER", "TEST", "USER", "TEST@EMAIL.com", "PASSWORD");
INSERT INTO users(user_id, username, firstname, lastname, email, password) VALUES(2, "TESTUSER2", "TEST", "USER", "TEST2@EMAIL.com", "PASSWORD");
INSERT INTO users(user_id, username, firstname, lastname, email, password) VALUES(3, "TESTUSER3", "TEST", "USER", "TEST3@EMAIL.com", "PASSWORD");
INSERT INTO users(user_id, username, firstname, lastname, email, password) VALUES(4, "TESTUSER4", "TEST", "USER", "TEST4@EMAIL.com", "PASSWORD");
INSERT INTO users(user_id, username, firstname, lastname, email, password) VALUES(5, "TESTUSER5", "TEST", "USER", "TEST5@EMAIL.com", "PASSWORD");

INSERT INTO `events`(event_id, uuid, name) VALUES(1, "a944ca71-86d3-4c32-82f9-67115946d5e1", "Wedding");

INSERT INTO `users_events`(event_id, user_id) VALUES(1, 1);
INSERT INTO `users_events`(event_id, user_id) VALUES(1, 2);
INSERT INTO `users_events`(event_id, user_id) VALUES(1, 3);
INSERT INTO `users_events`(event_id, user_id) VALUES(1, 4);
INSERT INTO `users_events`(event_id, user_id) VALUES(1, 5);
