# OIBSIP
This Repository consists of two projects of java development.

## ATM INTERFACE
This is a console based project, in this we have to first log in to an account then a list of operations are displayed to the console, using which you can change the entries in database of the particular account. These operations include:-
    1) To show the Transanction History.
    2) To Withdraw
    3) To Deposit
    4) To Transfer
    5) To Exit
You cannot create a new account using this application, but you can do the operations mentioned above using this in the existing accounts very easily.
You can go thorough these commands to create the accounts and create the transanction table also using MySql.
    
    For creating the accounts table and transanction tables the following commands are used, you have to paste these commands in your MySql command line and press enter the tables will be created and then you can use the application very conviniently::-
###
        drop database AtmDatabase;
         create database AtmDatabase;
         connect oasis_atm;
         CREATE TABLE ACCOUNTSTABLE(ACCNO INT PRIMARY KEY, ACCHOLDERNAME VARCHAR(26), BRANCHNAME VARCHAR(26), TRANSANCTIONID VARCHAR(8), PASSWORD INT);
         INSERT INTO ACCOUNTSTABLE VALUES(111,'AAA', 'DURGAPUR', 'T111', 111);
         INSERT INTO ACCOUNTSTABLE VALUES(222,'BBB', 'UTTAPARA', 'T222', 222);
         INSERT INTO ACCOUNTSTABLE VALUES(333,'CCC', 'HINDMOTOR', 'T333', 333);
         INSERT INTO ACCOUNTSTABLE VALUES(444,'DDD', 'SHRIRAMPUR', 'T444', 444);
         INSERT INTO ACCOUNTSTABLE VALUES(555,'EEE', 'KONNAGAR', 'T555', 555);
         INSERT INTO ACCOUNTSTABLE VALUES(666,'FFF', 'JIRAT', 'T666', 666);
         CREATE TABLE T111(SERIALNO INT PRIMARY KEY,DATEOFTRANSACT DATE,DESCRIPTION VARCHAR(30),CREDIT FLOAT,DEBIT FLOAT,BALANCE FLOAT);
         INSERT INTO T111 VALUES(1,"2005-06-2","ACCOUNT CREATION",500,0,500);
         CREATE TABLE T222(SERIALNO INT PRIMARY KEY,DATEOFTRANSACT DATE,DESCRIPTION VARCHAR(30),CREDIT FLOAT,DEBIT FLOAT,BALANCE FLOAT);
         INSERT INTO T222 VALUES(1,"2006-08-12","ACCOUNT CREATION",3000,0,3000);
         CREATE TABLE T333(SERIALNO INT PRIMARY KEY,DATEOFTRANSACT DATE,DESCRIPTION VARCHAR(30),CREDIT FLOAT,DEBIT FLOAT,BALANCE FLOAT);
         INSERT INTO T333 VALUES(1,"2007-07-22","ACCOUNT CREATION",8000,0,8000);
         CREATE TABLE T444(SERIALNO INT PRIMARY KEY,DATEOFTRANSACT DATE,DESCRIPTION VARCHAR(30),CREDIT FLOAT,DEBIT FLOAT,BALANCE FLOAT);
         INSERT INTO T444 VALUES(1,"2008-03-17","ACCOUNT CREATION",9000,0,9000);
         CREATE TABLE T555(SERIALNO INT PRIMARY KEY,DATEOFTRANSACT DATE,DESCRIPTION VARCHAR(30),CREDIT FLOAT,DEBIT FLOAT,BALANCE FLOAT);
         INSERT INTO T555 VALUES(1,"2009-02-23","ACCOUNT CREATION",5000,0,5000);
         CREATE TABLE T666(SERIALNO INT PRIMARY KEY,DATEOFTRANSACT DATE,DESCRIPTION VARCHAR(30),CREDIT FLOAT,DEBIT FLOAT,BALANCE FLOAT);
         INSERT INTO T666 VALUES(1,"2007-10-28","ACCOUNT CREATION",4000,0,4000);
 ###        
         The Id and passwords of the above demo users are given below::
            USERS       Id      PASSWORDS
            AAA        111          111
            BBB        222          222
            CCC        333          333
            DDD        444          444
            EEE        555          555
            FFF        666          666
    
## NUMBER GUESSING GAME
    This is a server based servlet application, in this project you just need to connect your server and then run the code using the server.
    When you launch the application in your server a page will open in which you have to give some input from 1 to 100 and the application will direct you whether that is the number or the number you have entered is larger of smaller than the number that is required. This is a 40 seconds game you have to guess the number within that time otherwise the game will be over and the session will be expired.