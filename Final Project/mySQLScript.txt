CREATE SCHEMA  kcelectricdb ;


CREATE TABLE  kcelectricdb . users  (
   username  VARCHAR(45) NOT NULL,
   password  VARCHAR(45) NOT NULL,
  PRIMARY KEY ( username ,  password ));


CREATE TABLE  kcelectricdb . customer  (
   name  VARCHAR(45) NOT NULL,
   phonenumber  VARCHAR(45) NOT NULL,
   address  VARCHAR(45) NOT NULL,
   state  VARCHAR(45) NOT NULL,
   City  VARCHAR(45) NOT NULL,
   zip  VARCHAR(45) NOT NULL,
   id  INT NOT NULL AUTO_INCREMENT,
   email VARCHAR(45) NOT NULL,
  PRIMARY KEY ( id ));

CREATE TABLE  kcelectricdb . electricaccount  (
   name  VARCHAR(45) NOT NULL,
   id  INT NOT NULL,
   energyTarrif  DECIMAL(4) NOT NULL,
   meterType  VARCHAR(45) NOT NULL,
   kwhPrice  DECIMAL(4) NOT NULL,
   lastMonthMeter  DECIMAL(4) NOT NULL,
   currentMeter  DECIMAL(4) NOT NULL,
   payed  INT NOT NULL,
  PRIMARY KEY ( id ));


ALTER TABLE  kcelectricdb . electricaccount  
CHANGE COLUMN  energyTarrif   energyTarrif  DECIMAL(4,4) NOT NULL ,
CHANGE COLUMN  kwhPrice   kwhPrice  DECIMAL(4,4) NOT NULL ,
CHANGE COLUMN  lastMonthMeter   lastMonthMeter  DECIMAL(10,4) NOT NULL ,
CHANGE COLUMN  currentMeter   currentMeter  DECIMAL(10,4) NOT NULL ;

ALTER TABLE  kcelectricdb . electricaccount  
ADD COLUMN  lastPayment  VARCHAR(45) NULL AFTER  payed ;

insert into kcelectricdb.users (username, password) values ("admin","password");

