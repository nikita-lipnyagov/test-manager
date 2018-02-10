DROP DATABASE IF EXISTS test_manager;
CREATE DATABASE test_manager;
USE test_manager;

create TABLE users
(
	id_user MEDIUMINT(8) UNSIGNED NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(64),
	password VARCHAR(64),
    role ENUM ('USER','ADMIN'),
	status ENUM('ACTIVE','BANNED') DEFAULT 'ACTIVE',
	is_active BOOLEAN DEFAULT false,
	mark_test1 DECIMAL UNSIGNED DEFAULT 0,
	mark_test2 DECIMAL UNSIGNED DEFAULT 0,
	mark_test3 DECIMAL UNSIGNED DEFAULT 0,
    PRIMARY KEY(id_user)
);

create TABLE tests
(
	number_of_test MEDIUMINT(8) UNSIGNED NOT NULL,
	test_name VARCHAR(64),
	subject VARCHAR(64),
	level ENUM('EASY','MEDIUM','HARD'),
	questions_number TINYINT UNSIGNED DEFAULT 0,
	time_limit MEDIUMINT(8) UNSIGNED,
	russian_name VARCHAR(64),
	language ENUM('ENG','RUS')
);

create TABLE questions
(
	number_of_question MEDIUMINT(8) UNSIGNED NOT NULL,
	text_value VARCHAR(255),
	russian_text_value VARCHAR(255),
	right_answer TINYINT UNSIGNED,
	language ENUM('ENG','RUS'),
	id_test MEDIUMINT(8) UNSIGNED NOT NULL
);

create TABLE answers
(
	number_of_answer MEDIUMINT(8) UNSIGNED NOT NULL,
	text_value VARCHAR(255),
	language ENUM('ENG','RUS'),
	id_question MEDIUMINT(8) UNSIGNED NOT NULL
);



