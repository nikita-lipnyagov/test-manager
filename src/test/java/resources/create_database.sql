create TABLE users
(
	id_user MEDIUMINT(8) UNSIGNED NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(64),
	password VARCHAR(64),
    role VARCHAR(64),
	status VARCHAR(64) DEFAULT 'ACTIVE',
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
	level VARCHAR(64),
	questions_number TINYINT UNSIGNED DEFAULT 0,
	time_limit MEDIUMINT(8) UNSIGNED,
	russian_name VARCHAR(64),
	language VARCHAR(64)
);

create TABLE questions
(
	number_of_question MEDIUMINT(8) UNSIGNED NOT NULL,
	text_value VARCHAR(255),
	russian_text_value VARCHAR(255),
	right_answer TINYINT UNSIGNED,
	language VARCHAR(64),
	id_test MEDIUMINT(8) UNSIGNED NOT NULL
);

create TABLE answers
(
	number_of_answer MEDIUMINT(8) UNSIGNED NOT NULL,
	text_value VARCHAR(255),
	language VARCHAR(64),
	id_question MEDIUMINT(8) UNSIGNED NOT NULL
);



