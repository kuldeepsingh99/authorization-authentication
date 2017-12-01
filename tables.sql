DROP DATABASE IF EXISTS test;
CREATE DATABASE IF NOT EXISTS test /*!40100 DEFAULT CHARACTER SET utf8 */;
USE test;

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  role varchar(50) NOT NULL,
  password varchar(500) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS usertoken;
CREATE TABLE IF NOT EXISTS usertoken (
  id int(11) NOT NULL AUTO_INCREMENT,
  token varchar(1000) DEFAULT NULL,
  user_type varchar(10) DEFAULT NULL,
  user_id int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_userToken_users (user_id),
  CONSTRAINT FK_userToken_users FOREIGN KEY (user_id) REFERENCES users (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
