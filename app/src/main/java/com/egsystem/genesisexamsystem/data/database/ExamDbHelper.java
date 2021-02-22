package com.egsystem.genesisexamsystem.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.egsystem.genesisexamsystem.data.database.Constrants.*;
import com.egsystem.genesisexamsystem.data.model.Answer;
import com.egsystem.genesisexamsystem.data.model.Question;

import java.util.ArrayList;
import java.util.List;


public class ExamDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "exam_database5.db";
    private static final int DATABASE_VERSION = 3;
    private static ExamDbHelper instance;
    private SQLiteDatabase db;

    private ExamDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized ExamDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new ExamDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

//        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
//                CategoriesTable.TABLE_CATEGORIES + "( " +
//                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                CategoriesTable.COLUMN_NAME + " TEXT " +
//                ")";

//        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
//                QuestionsTable.TABLE_NAME + " ( " +
//                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
//                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
//                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
//                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
//                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
//                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
//                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
//                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
//                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
//                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_QUESTIONS + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION_ID + " TEXT, " +
                QuestionsTable.COLUMN_QUESTION_TYPE + " TEXT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION5 + " TEXT, " +
                QuestionsTable.CORRECT_ANS_SBA + " TEXT, " +
                QuestionsTable.CORRECT_ANS_A + " TEXT," +
                QuestionsTable.CORRECT_ANS_B + " TEXT, " +
                QuestionsTable.CORRECT_ANS_C + " TEXT, " +
                QuestionsTable.CORRECT_ANS_D + " TEXT, " +
                QuestionsTable.CORRECT_ANS_E + " TEXT " +
                ")";


        final String SQL_CREATE_ANSWERS_TABLE = "CREATE TABLE " +
                AnswersTable.TABLE_ANSWERS + " ( " +
                AnswersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AnswersTable.COLUMN_QUESTION_ID + " TEXT, " +
                AnswersTable.COLUMN_QUESTION_SL + " TEXT, " +
                AnswersTable.COLUMN_QUESTION_TYPE + " TEXT, " +
                AnswersTable.COLUMN_QUESTION + " TEXT, " +
                AnswersTable.CORRECT_ANS_SBA + " TEXT, " +
                AnswersTable.CORRECT_ANS_A + " TEXT," +
                AnswersTable.CORRECT_ANS_B + " TEXT, " +
                AnswersTable.CORRECT_ANS_C + " TEXT, " +
                AnswersTable.CORRECT_ANS_D + " TEXT, " +
                AnswersTable.CORRECT_ANS_E + " TEXT, " +
                AnswersTable.SKIPPED + " TEXT, " +
                AnswersTable.NOT_ANSWERED + " TEXT " +
                ")";


//        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        db.execSQL(SQL_CREATE_ANSWERS_TABLE);

//        fillCategoriesTable();
//        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + AnswersTable.TABLE_ANSWERS);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }


    public ArrayList<String> tables_of_db() {
        ArrayList<String> arrTblNames = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                arrTblNames.add(c.getString(c.getColumnIndex("name")));
                c.moveToNext();
            }
        }

        return arrTblNames;
    }


//    private void fillCategoriesTable() {
//        Category c1 = new Category("Programming");
//        insertCategory(c1);
//        Category c2 = new Category("Geography");
//        insertCategory(c2);
//        Category c3 = new Category("Math");
//        insertCategory(c3);
//    }
//
//    public void addCategory(Category category) {
//        db = getWritableDatabase();
//        insertCategory(category);
//    }
//
//    public void addCategories(List<Category> categories) {
//        db = getWritableDatabase();
//        for (Category category : categories) {
//            insertCategory(category);
//        }
//    }
//
//    private void insertCategory(Category category) {
//        ContentValues cv = new ContentValues();
//        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
//        db.insert(CategoriesTable.TABLE_NAME, null, cv);
//    }
//
//
//    public List<Category> getAllCategories() {
//        List<Category> categoryList = new ArrayList<>();
//        db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);
//        if (c.moveToFirst()) {
//            do {
//                Category category = new Category();
//                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
//                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
//                categoryList.add(category);
//            } while (c.moveToNext());
//        }
//        c.close();
//        return categoryList;
//    }


//    private void fillQuestionsTable() {
//        Question q1 = new Question("Programming, Easy: A is correct",
//                "A", "B", "C", 1,
//                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
//        insertQuestion(q1);
//        Question q2 = new Question("Geography, Medium: B is correct",
//                "A", "B", "C", 2,
//                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
//        insertQuestion(q2);
//        Question q3 = new Question("Math, Hard: C is correct",
//                "A", "B", "C", 3,
//                Question.DIFFICULTY_HARD, Category.MATH);
//        insertQuestion(q3);
//        Question q4 = new Question("Math, Easy: A is correct",
//                "A", "B", "C", 1,
//                Question.DIFFICULTY_EASY, Category.MATH);
//        insertQuestion(q4);
//        Question q5 = new Question("Non existing, Easy: A is correct",
//                "A", "B", "C", 1,
//                Question.DIFFICULTY_EASY, 4);
//        insertQuestion(q5);
//        Question q6 = new Question("Non existing, Medium: B is correct",
//                "A", "B", "C", 2,
//                Question.DIFFICULTY_MEDIUM, 5);
//        insertQuestion(q6);
//    }

    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addAnswer(Answer answer) {
        db = getWritableDatabase();
        insertAnswer(answer);
    }


//    public void removeAnswer(Answer answer) {
//        db = getWritableDatabase();
//        removeAnswer();
//    }

    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();
        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    private void insertAnswer(Answer answer) {
        ContentValues cv = new ContentValues();
        cv.put(AnswersTable.COLUMN_QUESTION_ID, answer.getQuestionId());
        cv.put(AnswersTable.COLUMN_QUESTION_SL, answer.getQuestionSl());
        cv.put(AnswersTable.COLUMN_QUESTION_TYPE, answer.getQuestionType());
        cv.put(AnswersTable.CORRECT_ANS_SBA, answer.getCorrect_ans_sba());
        cv.put(AnswersTable.CORRECT_ANS_A, answer.getCorrect_ans_a());
        cv.put(AnswersTable.CORRECT_ANS_B, answer.getCorrect_ans_b());
        cv.put(AnswersTable.CORRECT_ANS_C, answer.getCorrect_ans_c());
        cv.put(AnswersTable.CORRECT_ANS_D, answer.getCorrect_ans_d());
        cv.put(AnswersTable.CORRECT_ANS_E, answer.getCorrect_ans_e());
        cv.put(AnswersTable.SKIPPED, answer.getSkipped());
        cv.put(AnswersTable.NOT_ANSWERED, answer.getNot_answered());

        db.insert(AnswersTable.TABLE_ANSWERS, null, cv);
    }

    private void removeAnswer(String question_id1) {
        db = getWritableDatabase();
        db.delete(AnswersTable.TABLE_ANSWERS, "question_id=?", new String[]{question_id1});
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION_ID, question.getQuestionId());
        cv.put(QuestionsTable.COLUMN_QUESTION_TYPE, question.getQuestionType());
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_OPTION5, question.getOption5());
        cv.put(QuestionsTable.CORRECT_ANS_SBA, question.getCorrect_ans_sba());
        cv.put(QuestionsTable.CORRECT_ANS_A, question.getCorrect_ans_a());
        cv.put(QuestionsTable.CORRECT_ANS_B, question.getCorrect_ans_b());
        cv.put(QuestionsTable.CORRECT_ANS_C, question.getCorrect_ans_c());
        cv.put(QuestionsTable.CORRECT_ANS_D, question.getCorrect_ans_d());
        cv.put(QuestionsTable.CORRECT_ANS_E, question.getCorrect_ans_e());
        db.insert(QuestionsTable.TABLE_QUESTIONS, null, cv);
    }


    public void deleteAllQuestions() {
        db = getWritableDatabase();
        db.execSQL("delete from " + QuestionsTable.TABLE_QUESTIONS);
    }


    public void deleteAllAnswers() {
        db = getWritableDatabase();
        db.execSQL("delete from " + AnswersTable.TABLE_ANSWERS);
    }


    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_QUESTIONS, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestionId(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION_ID)));
                question.setQuestionType(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION_TYPE)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setOption5(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION5)));
                question.setCorrect_ans_sba(c.getString(c.getColumnIndex(QuestionsTable.CORRECT_ANS_SBA)));
                question.setCorrect_ans_a(c.getString(c.getColumnIndex(QuestionsTable.CORRECT_ANS_A)));
                question.setCorrect_ans_b(c.getString(c.getColumnIndex(QuestionsTable.CORRECT_ANS_B)));
                question.setCorrect_ans_c(c.getString(c.getColumnIndex(QuestionsTable.CORRECT_ANS_C)));
                question.setCorrect_ans_d(c.getString(c.getColumnIndex(QuestionsTable.CORRECT_ANS_D)));
                question.setCorrect_ans_e(c.getString(c.getColumnIndex(QuestionsTable.CORRECT_ANS_E)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }


    public ArrayList<Answer> getAllGivenAnswers() {
        ArrayList<Answer> answerList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + AnswersTable.TABLE_ANSWERS, null);
        if (c.moveToFirst()) {
            do {
                Answer answer = new Answer();
                answer.setId(c.getInt(c.getColumnIndex(AnswersTable._ID)));
                answer.setQuestionId(c.getString(c.getColumnIndex(AnswersTable.COLUMN_QUESTION_ID)));
                answer.setQuestionSl(c.getString(c.getColumnIndex(AnswersTable.COLUMN_QUESTION_SL)));
                answer.setQuestionType(c.getString(c.getColumnIndex(AnswersTable.COLUMN_QUESTION_TYPE)));
                answer.setCorrect_ans_sba(c.getString(c.getColumnIndex(AnswersTable.CORRECT_ANS_SBA)));
                answer.setCorrect_ans_a(c.getString(c.getColumnIndex(AnswersTable.CORRECT_ANS_A)));
                answer.setCorrect_ans_b(c.getString(c.getColumnIndex(AnswersTable.CORRECT_ANS_B)));
                answer.setCorrect_ans_c(c.getString(c.getColumnIndex(AnswersTable.CORRECT_ANS_C)));
                answer.setCorrect_ans_d(c.getString(c.getColumnIndex(AnswersTable.CORRECT_ANS_D)));
                answer.setCorrect_ans_e(c.getString(c.getColumnIndex(AnswersTable.CORRECT_ANS_E)));
                answer.setSkipped(c.getString(c.getColumnIndex(AnswersTable.SKIPPED)));
                answer.setNot_answered(c.getString(c.getColumnIndex(AnswersTable.NOT_ANSWERED)));
                answerList.add(answer);
            } while (c.moveToNext());
        }
        c.close();
        return answerList;
    }


    public ArrayList<Answer> getAllSkippedQuestionsAnswers() {
        ArrayList<Answer> answerList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + AnswersTable.TABLE_ANSWERS + " WHERE skipped=?", new String[]{"yes"});

        if (c.moveToFirst()) {
            do {
                Answer answer = new Answer();
                answer.setId(c.getInt(c.getColumnIndex(AnswersTable._ID)));
                answer.setQuestionId(c.getString(c.getColumnIndex(AnswersTable.COLUMN_QUESTION_ID)));
                answer.setQuestionSl(c.getString(c.getColumnIndex(AnswersTable.COLUMN_QUESTION_SL)));
                answer.setQuestionType(c.getString(c.getColumnIndex(AnswersTable.COLUMN_QUESTION_TYPE)));
                answer.setCorrect_ans_sba(c.getString(c.getColumnIndex(AnswersTable.CORRECT_ANS_SBA)));
                answer.setCorrect_ans_a(c.getString(c.getColumnIndex(AnswersTable.CORRECT_ANS_A)));
                answer.setCorrect_ans_b(c.getString(c.getColumnIndex(AnswersTable.CORRECT_ANS_B)));
                answer.setCorrect_ans_c(c.getString(c.getColumnIndex(AnswersTable.CORRECT_ANS_C)));
                answer.setCorrect_ans_d(c.getString(c.getColumnIndex(AnswersTable.CORRECT_ANS_D)));
                answer.setCorrect_ans_e(c.getString(c.getColumnIndex(AnswersTable.CORRECT_ANS_E)));
                answer.setSkipped(c.getString(c.getColumnIndex(AnswersTable.SKIPPED)));
                answer.setNot_answered(c.getString(c.getColumnIndex(AnswersTable.NOT_ANSWERED)));
                answerList.add(answer);
            } while (c.moveToNext());
        }
        c.close();
        return answerList;
    }


    public Question getSpecificQueation(String question_id1) {
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_QUESTIONS + " WHERE question_id=?", new String[]{question_id1});
        c.moveToFirst();

        Question question = new Question();
        question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
        question.setQuestionId(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION_ID)));
        question.setQuestionType(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION_TYPE)));
        question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
        question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
        question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
        question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
        question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
        question.setOption5(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION5)));
        question.setCorrect_ans_sba(c.getString(c.getColumnIndex(QuestionsTable.CORRECT_ANS_SBA)));
        question.setCorrect_ans_a(c.getString(c.getColumnIndex(QuestionsTable.CORRECT_ANS_A)));
        question.setCorrect_ans_b(c.getString(c.getColumnIndex(QuestionsTable.CORRECT_ANS_B)));
        question.setCorrect_ans_c(c.getString(c.getColumnIndex(QuestionsTable.CORRECT_ANS_C)));
        question.setCorrect_ans_d(c.getString(c.getColumnIndex(QuestionsTable.CORRECT_ANS_D)));
        question.setCorrect_ans_e(c.getString(c.getColumnIndex(QuestionsTable.CORRECT_ANS_E)));

        c.close();
        return question;
    }


    public boolean updateSpecificAnswer(String question_id, String question_sl, String question_type, String correct_ans_sba, String correct_ans_a, String correct_ans_b,
                                        String correct_ans_c, String correct_ans_d, String correct_ans_e, String skipped, String not_answered) {
        db = getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(AnswersTable.COLUMN_QUESTION_ID, question_id);
        cv.put(AnswersTable.COLUMN_QUESTION_SL, question_sl);
        cv.put(AnswersTable.COLUMN_QUESTION_TYPE, question_type);
        cv.put(AnswersTable.CORRECT_ANS_SBA, correct_ans_sba);
        cv.put(AnswersTable.CORRECT_ANS_A, correct_ans_a);
        cv.put(AnswersTable.CORRECT_ANS_B, correct_ans_b);
        cv.put(AnswersTable.CORRECT_ANS_C, correct_ans_c);
        cv.put(AnswersTable.CORRECT_ANS_D, correct_ans_d);
        cv.put(AnswersTable.CORRECT_ANS_E, correct_ans_e);
        cv.put(AnswersTable.SKIPPED, skipped);
        cv.put(AnswersTable.NOT_ANSWERED, not_answered);

        db.update(AnswersTable.TABLE_ANSWERS, cv, "question_id = ?", new String[]{question_id});

        return true;
    }


    public void removeSkippedSpecificAnswer(String question_id1) {
        db = getWritableDatabase();
        db.execSQL("delete from " + AnswersTable.TABLE_ANSWERS);

        String table = AnswersTable.TABLE_ANSWERS;
        String whereClause = "question_id=?";
        String[] whereArgs = new String[]{question_id1};
        db.delete(table, whereClause, whereArgs);
    }


//    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
//        ArrayList<Question> questionList = new ArrayList<>();
//        db = getReadableDatabase();
//        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
//                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
//        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};
//        Cursor c = db.query(
//                QuestionsTable.TABLE_NAME,
//                null,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                null
//        );
//        if (c.moveToFirst()) {
//            do {
//                Question question = new Question();
//                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
//                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
//                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
//                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
//                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
//                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
//                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
//                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
//                questionList.add(question);
//            } while (c.moveToNext());
//        }
//        c.close();
//        return questionList;
//    }
//


}
