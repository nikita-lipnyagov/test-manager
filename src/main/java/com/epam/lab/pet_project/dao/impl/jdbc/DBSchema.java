package com.epam.lab.pet_project.dao.impl.jdbc;

public class DBSchema {
    public static abstract class USERS {
        public final static String ID = "id_user";
        public final static String USER_NAME = "user_name";
        public final static String PASSWORD = "password";
        public final static String ROLE = "role";
        public final static String STATUS = "status";
        public final static String IS_ACTIVE = "is_active";
        public final static String MARK_TEST = "mark_test";

        public static String name() {
            return "users";
        }
    }

    public static abstract class TESTS {
        public final static String NUMBER = "number_of_test";
        public final static String TEST_NAME = "test_name";
        public final static String SUBJECT = "subject";
        public final static String LEVEL = "level";
        public final static String TIME_LIMIT = "time_limit";
        public final static String LANGUAGE = "language";
        public final static String NUMBER_OD_QUESTIONS = "questions_number";
        public final static String RUSSIAN_NAME = "russian_name";

        public static String name() {
            return "tests";
        }
    }

    public static abstract class QUESTIONS {
        public final static String NUMBER = "number_of_question";
        public final static String QUESTION = "text_value";
        public final static String RIGHT_ANSWER = "right_answer";
        public final static String LANGUAGE = "language";
        public final static String TEST_ID = "id_test";
        public final static String RUSSIAN_TEXT = "russian_text_value";


        public static String name() {
            return "questions";
        }
    }

    public static abstract class ANSWERS {
        public final static String NUMBER = "number_of_answer";
        public final static String ANSWER = "text_value";
        public final static String LANGUAGE = "language";
        public final static String QUESTION_ID = "id_question";

        public static String name() {
            return "answers";
        }
    }


}
