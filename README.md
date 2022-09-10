# WalletManagementSystem

Wallet Management System
1.Login

![image](https://user-images.githubusercontent.com/95590391/189502814-e3ce1c08-64ad-4d7a-b1d2-7c57fbb0f290.png)

 

2.User Dashbord contain add money,withdraw money,view transaction history  and balance operations
 
![image](https://user-images.githubusercontent.com/95590391/189502823-0bd10d70-779a-4c6e-a4fe-6dd216f0eef5.png)



3.User can add money

![image](https://user-images.githubusercontent.com/95590391/189502836-c3b5ad45-4900-4361-b6b2-634d9b20fa79.png)


 
4. Message will seen  amount added
 
 ![image](https://user-images.githubusercontent.com/95590391/189502846-8b386449-b493-483e-9d73-8ec33f2b6d64.png)



5.User can withdraw money
 
 ![image](https://user-images.githubusercontent.com/95590391/189502854-56088557-5baf-4c07-a9e9-de994bd30798.png)



6. Message will seen  amount debitted 
 
 ![image](https://user-images.githubusercontent.com/95590391/189502859-03a079f1-35bc-4f6b-8688-d25a0c7a77ee.png)



7.Transaction History

![image](https://user-images.githubusercontent.com/95590391/189502865-f6d63b79-01f9-44c2-8432-249cf9721fdc.png)

 

1. Invalid  amount(0) not will not be added to wallet

![image](https://user-images.githubusercontent.com/95590391/189502892-62b58667-5da6-43d7-8aab-0d8ec6f84d52.png)


 
2. Invalid  amount(0) not will not be withdraw from wallet
 
 ![image](https://user-images.githubusercontent.com/95590391/189502898-339c3f74-ae66-41fd-bb3c-e5f218828894.png)



3. amount less than balance will not be withdraw.

 ![image](https://user-images.githubusercontent.com/95590391/189502917-92e19503-cdc2-4bed-b23e-538be885283d.png)





Username : password
user            :user
user2          :user2
user3          :user3
admin         :admin



#Database

CREATE SCHEMA `wallet_schema` ;

CREATE TABLE `wallet_schema`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `enabled` TINYINT NULL DEFAULT 1,
  PRIMARY KEY (`user_id`));

ALTER TABLE `wallet_schema`.`users` 
CHANGE COLUMN `password` `password` CHAR(100) NULL DEFAULT NULL ;

CREATE TABLE `wallet_schema`.`roles` (
  `role_id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`role_id`));

CREATE TABLE `wallet_schema`.`users_roles` (
  `user_id` INT NOT NULL,
  `role_id` INT NULL,
  PRIMARY KEY (`user_id`));

CREATE TABLE `wallet_schema`.`transactions` (
  `transactions_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `date_of_trans` DATE NULL,
  `balence` DOUBLE NULL,
  `debit` DOUBLE NULL,
  `credit` DOUBLE NULL,
  PRIMARY KEY (`transactions_id`));
;

INSERT INTO `wallet_schema`.`roles` (`role_id`, `name`) VALUES ('101', 'ADMIN');
INSERT INTO `wallet_schema`.`roles` (`role_id`, `name`) VALUES ('102', 'USER');

INSERT INTO `wallet_schema`.`users` (`user_id`, `username`, `password`, `enabled`) VALUES ('1', 'user', '$2a$10$lufJyjUvAAR3kgknfOlcZOZQTZwgKFUFARgfn9nzqcjTgwd8jL/Qi', '1');1234
INSERT INTO `wallet_schema`.`users` (`user_id`, `username`, `password`, `enabled`) VALUES ('2', 'admin', '$2a$10$AUHjNLwtAg9jt0WKPyMh.u/EevDzm9BQUrr7HaN5N04hRUhiQn5a.', '1');admin

INSERT INTO `wallet_schema`.`users_roles` (`user_id`, `role_id`) VALUES ('1', '102');
INSERT INTO `wallet_schema`.`users_roles` (`user_id`, `role_id`) VALUES ('2', '101');
