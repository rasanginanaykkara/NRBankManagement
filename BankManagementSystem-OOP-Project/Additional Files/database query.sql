DROP DATABASE IF EXISTS NRB;
CREATE DATABASE NRB;

USE NRB;

CREATE TABLE "user" (
	nic VARCHAR(15) UNIQUE NOT NULL,
	email VARCHAR(200) NOT NULL,
	ful_name VARCHAR(200) NOT NULL,
	address VARCHAR (300) NOT NULL,
	image_url VARCHAR(300) NOT NULL,
	password VARCHAR(30) NOT NULL,
	is_activated INT DEFAULT 0 NOT NULL,
	CONSTRAINT USER_PK PRIMARY KEY (nic)
);
CREATE TABLE account_type(
	type_name VARCHAR(50) NOT NULL UNIQUE,
	interest_rate DECIMAL(4,2) NOT NULL,
	CONSTRAINT ACCOUNT_TABLE_PK PRIMARY KEY (type_name)
);
CREATE TABLE account(
	account_number INT UNIQUE NOT NULL IDENTITY(1005675,1),
	user_id VARCHAR(15) NOT NULL,
	balance DECIMAL(12,2) NOT NULL,
	is_activated INT NOT NULL DEFAULT 0
	CONSTRAINT ACCOUNT_PK PRIMARY KEY(account_number),
	CONSTRAINT ACCOUNT_FK1 FOREIGN KEY (user_id) REFERENCES "user"(nic)
);
CREATE TABLE savings_account(
	account_number INT UNIQUE NOT NULL,
	type_name VARCHAR(50) NOT NULL,
	CONSTRAINT SAVINGS_ACCOUNT_PK PRIMARY KEY (account_number),
	CONSTRAINT SAVINGS_ACCOUNT_FK1 FOREIGN KEY (account_number) REFERENCES account(account_number),
	CONSTRAINT SAVINGS_ACCOUNT_FK2 FOREIGN KEY (type_name) REFERENCES account_type(type_name)
);
CREATE TABLE duration_type (
	num_of_months INT UNIQUE NOT NULL,
	interest_rate DECIMAL(4,2) NOT NULL,
	CONSTRAINT DURATION_TYPE_PK PRIMARY KEY (num_of_months)
);
CREATE TABLE fixed_deposit(
	account_number INT UNIQUE NOT NULL,
	num_of_months INT NOT NULL,
	CONSTRAINT FIXED_DEPOSIT_PK PRIMARY KEY (account_number),
	CONSTRAINT FIXED_DEPOSIT_FK1 FOREIGN KEY (account_number) REFERENCES account(account_number),
	CONSTRAINT FIXED_DEPOSIT_FK2 FOREIGN KEY (num_of_months) REFERENCES duration_type(num_of_months)
);
CREATE TABLE "transaction"(	
	transaction_id INT UNIQUE NOT NULL IDENTITY(1,1),
	host_account INT NOT NULL,
	amount DECIMAL(10,2) NOT NULL,
	CONSTRAINT TRANSACTION_PK PRIMARY KEY (transaction_id),
	CONSTRAINT TRANSACTION_FK1 FOREIGN KEY (host_account) REFERENCES account(account_number)

);

CREATE TABLE to_account_trans(
	tansaction_id INT UNIQUE NOT NULL,
	payee_account INT NOT NULL,
	CONSTRAINT TO_ACCOUNT_TRANS_PK PRIMARY KEY (tansaction_id),
	CONSTRAINT TO_ACCOUNT_TRANS_FK1 FOREIGN KEY (tansaction_id) REFERENCES "transaction"(transaction_id),
	CONSTRAINT TO_ACCOUNT_TRANS_FK2 FOREIGN KEY (payee_account) REFERENCES account(account_number)
);

CREATE TABLE loan (
	loan_number INT UNIQUE NOT NULL IDENTITY(140300,1),
	loan_owner VARCHAR(15) NOT NULL,
	loan_guarantor VARCHAR(15) NOT NULL,
	installment DECIMAL(10,2) NOT NULL,
	total DECIMAL(10,2) NOT NULL,
	paid_amount DECIMAL(10,2) NOT NULL,
	is_activated INT NOT NULL DEFAULT 0,
	CONSTRAINT LOAN_PK PRIMARY KEY (loan_number),
	CONSTRAINT LOAN_FK1 FOREIGN KEY (loan_owner) REFERENCES "user"(nic),
	CONSTRAINT LOAN_FK2 FOREIGN KEY (loan_guarantor) REFERENCES "user"(nic)
);
CREATE TABLE installment (
	loan_number INT NOT NULL,
	installment_number INT NOT NULL,
	is_payed INT NOT NULL DEFAULT 0,
	due_date VARCHAR(15),
	CONSTRAINT INSTALLMENT_PK PRIMARY KEY (loan_number,installment_number),
	CONSTRAINT INSTALLMENT_FK1 FOREIGN KEY (loan_number) REFERENCES loan(loan_number)
);
CREATE TABLE to_loan_trans(
	transaction_id INT UNIQUE NOT NULL,
	loan_number INT NOT NULL,
	CONSTRAINT TO_LOAN_TRANS_PK PRIMARY KEY (transaction_id),
	CONSTRAINT TO_LOAN_TRANS_FK1 FOREIGN KEY (transaction_id) REFERENCES "transaction"(transaction_id),
	CONSTRAINT TO_LOAN_TRANS_FK2 FOREIGN KEY (loan_number) REFERENCES loan(loan_number)
);
CREATE TABLE card(
	card_number INT UNIQUE NOT NULL,
	pin INT NOT NULL,
	type VARCHAR(20) NOT NULL,
	cvv INT NOT NULL,
	is_active INT NOT NULL DEFAULT 0,
	account_number INT NOT NULL,
	CONSTRAINT CARD_PK PRIMARY KEY (card_number),
	CONSTRAINT CARD_FK1 FOREIGN KEY (account_number) REFERENCES account(account_number)
);