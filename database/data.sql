USE test_manager;
INSERT INTO users
	(user_name, password, role, status)
    VALUES
  ('user1',   'Qwerty1234',   'USER', 'ACTIVE'),
	('user2',   'starWars1',    'USER',   'ACTIVE'),
	('user3',   'starWars2',    'USER',   'BANNED'),
	('admin',   'adminADMIN01', 'ADMIN',  'ACTIVE');
INSERT INTO tests
	(number_of_test, test_name ,subject, level, questions_number, time_limit, russian_name, language)
    VALUES
  (1, 'Числовые ряды',   'Логика', 'MEDIUM', 5, 300, 'Числовые ряды', 'RUS'),
	(1, 'Numerical series','Logic',    'MEDIUM', 5, 300, 'Числовые ряды', 'ENG'),
	(2, 'Арифметика', 'Математика',  'EASY',   4, 100, 'Арифметика','RUS'),
	(2, 'Arithmetics','Math', 		   'EASY',   4, 100, 'Арифметика','ENG'),
	(3, 'Прикладная математика', 'Математика',  'MEDIUM',   4, 600,'Прикладная математика','RUS'),
	(3, 'Applied Math',			      'Math', 		'MEDIUM',   4, 600,'Прикладная математика','ENG');
    
INSERT INTO questions
	(number_of_question, text_value, russian_text_value, right_answer, language, id_test)
    VALUES
	(1, 'Enter numerical series: 24 21	19	18	15	13	–	–	7', 'Заполните числовой ряд: 24 21	19	18	15	13	–	–	7',  1, 'ENG', 1), -- TEST 1
  (1, 'Заполните числовой ряд: 24 21	19	18	15	13	–	–	7', 'Заполните числовой ряд: 24 21	19	18	15	13	–	–	7',  1, 'RUS', 1),
	(2, 'Enter numerical series: 1	4 9	16	–	–	49	64	81	100','Заполните числовой ряд: 1	4 9	16	–	–	49	64	81	100',  3, 'ENG', 1),
	(2, 'Заполните числовой ряд: 1	4 9	16	–	–	49	64	81	100','Заполните числовой ряд: 1	4 9	16	–	–	49	64	81	100',  3, 'RUS', 1),
	(3, 'Enter numerical series: 16 17	15	18	14	19	–	–','Заполните числовой ряд: 16 17	15	18	14	19	–	–',  2, 'ENG', 1),
	(3, 'Заполните числовой ряд: 16 17	15	18	14	19	–	–','Заполните числовой ряд: 16 17	15	18	14	19	–	–',  2, 'RUS', 1),
	(4, 'Enter numerical series: 1	3 6	8	16	18	–	–	76	78', 'Заполните числовой ряд: 1	3 6	8	16	18	–	–	76	78',  1, 'ENG', 1),
	(4, 'Заполните числовой ряд: 1	3 6	8	16	18	–	–	76	78', 'Заполните числовой ряд: 1	3 6	8	16	18	–	–	76	78',  1, 'RUS', 1),
	(5, 'Enter numerical series: 7	16	9	5	21	16	9	–	4', 'Заполните числовой ряд: 7	16	9	5	21	16	9	–	4',  2, 'ENG', 1),
	(5, 'Заполните числовой ряд: 7	16	9	5	21	16	9	–	4', 'Заполните числовой ряд: 7	16	9	5	21	16	9	–	4',  2, 'RUS', 1),
	(6, 'How much will be 12*12 ?', 'Сколько будет 12*12 ?   ',3, 'ENG', 2),							 -- TEST 2
  (6, 'Сколько будет 12*12 ?   ', 'Сколько будет 12*12 ?   ',3, 'RUS', 2),
	(7, 'How much will be 15 - 12 * 0.5 ?','Сколько будет 15 - 12 * 0.5 ? ',   3, 'ENG', 2),
	(7, 'Сколько будет 15 - 12 * 0.5 ?   ','Сколько будет 15 - 12 * 0.5 ? ',  3, 'RUS', 2),
	(8, 'How much will be 13 * 16 ?','Сколько будет 13 * 16 ?   ', 2, 'ENG', 2),
	(8, 'Сколько будет 13 * 16 ?   ','Сколько будет 13 * 16 ?   ', 2, 'RUS', 2),
	(9, 'How much will be  22/4 * 10?','Сколько будет 22/4 * 10 ?   ',  1, 'ENG', 2),
	(9, 'Сколько будет 22/4 * 10 ?   ','Сколько будет 22/4 * 10 ?   ',  1, 'RUS', 2),
	(10, 'Five students put their sandwiches in paper bags. Then everyone took one package at random.<br> What is the probability that only 4 pupils will get their sandwiches?',    'Пять учеников положили свои бутерброды в бумажные пакеты. Затем каждый взял наугад один пакет.<br> Какова вероятность того, что только 4 ученика получат свои бутерброды?', 1, 'ENG', 3),	 -- TEST 3
  (10, 'Пять учеников положили свои бутерброды в бумажные пакеты. Затем каждый взял наугад один пакет.<br> Какова вероятность того, что только 4 ученика получат свои бутерброды?','Пять учеников положили свои бутерброды в бумажные пакеты. Затем каждый взял наугад один пакет.<br> Какова вероятность того, что только 4 ученика получат свои бутерброды?', 1, 'RUS', 3),
	(11, 'Four different varieties of apples are mixed in a large box. I pull out the apples without looking. <br> How many apples need to be pulled out so that at least three of them were of one grade?', 'Четыре различных сорта яблок перемешаны в большой коробке. Я вытаскиваю яблоки не глядя. <br> Сколько яблок нужно вытащить чтобы, по крайней мере, три из них были одного сорта?',  2, 'ENG', 3),
	(11, 'Четыре различных сорта яблок перемешаны в большой коробке. Я вытаскиваю яблоки не глядя. <br> Сколько яблок нужно вытащить чтобы, по крайней мере, три из них были одного сорта?', 	               'Четыре различных сорта яблок перемешаны в большой коробке. Я вытаскиваю яблоки не глядя. <br> Сколько яблок нужно вытащить чтобы, по крайней мере, три из них были одного сорта?',  2, 'RUS', 3),
	(12, 'I divided the clock face by two lines, so the sums of the numbers in the formed areas are the same. <br> Which expression is true?', 'Я разделил циферблат часов двумя линиями, так что суммы чисел в образовавшихся областях одинаковы. <бр> Какое выражение верно?',2, 'ENG', 3),
	(12, 'Я разделил циферблат часов двумя линиями, так что суммы чисел в образовавшихся областях одинаковы. <бр> Какое выражение верно?',     'Я разделил циферблат часов двумя линиями, так что суммы чисел в образовавшихся областях одинаковы. <бр> Какое выражение верно?',2, 'RUS', 3),
	(13, 'Five people are sitting at a round table. <br> What is the probability that they are sitting in an age-related order? <br> The order can be ascending or descending, clockwise or counterclockwise.',       'Пять человек сидят за круглым столом. <br> Какова вероятность того, что они сидят в возрастном порядке? <br> Порядок может быть восходящим или нисходящим, по часовой стрелке или против часовой стрелки.', 1, 'ENG', 3),
	(13, 'Пять человек сидят за круглым столом. <br> Какова вероятность того, что они сидят в возрастном порядке? <br> Порядок может быть восходящим или нисходящим, по часовой стрелке или против часовой стрелки.', 'Пять человек сидят за круглым столом. <br> Какова вероятность того, что они сидят в возрастном порядке? <br> Порядок может быть восходящим или нисходящим, по часовой стрелке или против часовой стрелки.', 1, 'RUS', 3);
	

INSERT INTO answers
	(number_of_answer, text_value, language, id_question)
	VALUES
	(1, '12 and 9', 'ENG', 1),	-- TEST 1
	(1, '12  и  9', 'RUS', 1),
	(2, '15 and 8', 'ENG', 1),
	(2, '15  и  8', 'RUS', 1),
	(3, '11 and 6', 'ENG', 1),
	(3, '11  и  6', 'RUS', 1),
	(4, '20 and 40','ENG', 2),
	(4, '20  и  40','RUS', 2),
	(5, '26 and 36','ENG', 2),
	(5, '26  и  36','RUS', 2),
	(6, '25 and 36','ENG', 2),
	(6, '25  и  36','RUS', 2),
	(7, '20 and 40','ENG', 3),
	(7, '20  и  40','RUS', 3),
	(8, '26 and 36','ENG', 3),
	(8, '26  и  36','RUS', 3),
	(9, '25 and 36','ENG', 3),
	(9, '25  и  36','RUS', 3),
	(10,'15 and 20','ENG', 4),
	(10,'15  и  20','RUS', 4),
	(11,'13 and 20','ENG', 4),
	(11,'13  и  20','RUS', 4),
	(12,'13 and 17','ENG', 4),
	(12,'13  и  17','RUS', 4),
	(13,'Number 13','ENG', 5),
	(13,'Число  13','RUS', 5),
	(14,'Number 15','ENG', 5),
	(14,'Число  15','RUS', 5),
	(15,'Number 17','ENG', 5),
	(15,'Число  17','RUS', 5),
	(16,'Number 140','ENG', 6), -- TEST 2
	(16,'Число  140','RUS', 6),
	(17,'Number 148','ENG', 6),
	(17,'Число  148','RUS', 6),
	(18,'Number 144','ENG', 6),
	(18,'Число  144','RUS', 6),
	(19,'Number 1.5','ENG', 7),
	(19,'Число  1.5','RUS', 7),
	(20,'Number 1',  'ENG', 7),
	(20,'Число  1',  'RUS', 7),
	(21,'Number 9',  'ENG', 7),
	(21,'Число  9',  'RUS', 7),
	(22,'Number 204','ENG', 8),
	(22,'Число  204','RUS', 8),
	(23,'Number 208','ENG', 8),
	(23,'Число  208','RUS', 8),
	(24,'Number 212','ENG', 8),
	(24,'Число  212','RUS', 8),
	(25,'Number 55' ,'ENG', 9),
	(25,'Число  55' ,'RUS', 9),
	(26,'Number 52' ,'ENG', 9),
	(26,'Число  52' ,'RUS', 9),
	(27,'Number 64' ,'ENG', 9),
	(27,'Число  64' ,'RUS', 9),
	(28,'0 %',  'ENG', 10),		-- TEST3
	(28,'0 %', 'RUS',  10),
	(29,'80 %','ENG',  10),
	(29,'80 %','RUS',  10),
	(30,'10 apples','ENG', 11),
	(30,'10  яблок','RUS', 11),
	(31,'9 apples' ,'ENG', 11),
	(31,'9  яблок' ,'RUS', 11),
	(32,'They are perpendicular','ENG', 12),
	(32,'Они перпендикулярны'   ,'RUS', 12),
	(33,'They are parralel'     ,'ENG', 12),
	(33,'Они паралельны'        ,'RUS', 12),
	(34,'1/6', 'ENG', 13),
	(34,'1/6', 'RUS', 13),
	(35,'1/10','ENG', 13),
	(35,'1/10','RUS', 13);
	