package com.genesis.genesisexamsystem.data.database;

import android.provider.BaseColumns;

public final class Constrants {

    private Constrants() {
    }

    public static class CategoriesTable implements BaseColumns {
        public static final String TABLE_CATEGORIES = "table_categories";
        public static final String COLUMN_NAME = "name";
    }


    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_QUESTIONS = "table_questions";
        public static final String COLUMN_QUESTION_ID = "question_id";
        public static final String COLUMN_EXAM_QUESTION_ID = "exam_question_id";
        public static final String COLUMN_QUESTION_TYPE = "question_type";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_OPTION5 = "option5";
        public static final String CORRECT_ANS_SBA = "correct_ans_sba";
        public static final String CORRECT_ANS_A = "correct_ans_a";
        public static final String CORRECT_ANS_B = "correct_ans_b";
        public static final String CORRECT_ANS_C = "correct_ans_c";
        public static final String CORRECT_ANS_D = "correct_ans_d";
        public static final String CORRECT_ANS_E = "correct_ans_e";
    }



    public static class AnswersTable implements BaseColumns {
        public static final String TABLE_ANSWERS = "table_answers";
        public static final String COLUMN_QUESTION_ID = "question_id";
        public static final String COLUMN_EXAM_QUESTION_ID = "exam_question_id";
        public static final String COLUMN_QUESTION_SL = "question_sl";
        public static final String COLUMN_QUESTION_TYPE = "question_type";
        public static final String COLUMN_QUESTION = "question";
        public static final String CORRECT_ANS_SBA = "correct_ans_sba";
        public static final String CORRECT_ANS_A = "correct_ans_a";
        public static final String CORRECT_ANS_B = "correct_ans_b";
        public static final String CORRECT_ANS_C = "correct_ans_c";
        public static final String CORRECT_ANS_D = "correct_ans_d";
        public static final String CORRECT_ANS_E = "correct_ans_e";
        public static final String SKIPPED = "skipped";
        public static final String NOT_ANSWERED = "not_answered";
    }





}
