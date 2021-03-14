  package com.egsystem.genesisexamsystem.question;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.Result.ResultActivity;
import com.egsystem.genesisexamsystem.data.database.ExamDbHelper;
import com.egsystem.genesisexamsystem.data.model.Answer;
import com.egsystem.genesisexamsystem.data.model.Question;
import com.egsystem.genesisexamsystem.data.shared_pref.SharedData;

import java.util.ArrayList;
import java.util.Locale;


public class SingleQuestionActivity extends AppCompatActivity {

    private static final long COUNTDOWN_IN_MILLIS = 400000;
    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";
    private LinearLayout linear_ques_form;
    private TextView tv_view_message;
    private TextView tv_question;
    private TextView tv_option1;
    private TextView tv_option2;
    private TextView tv_option3;
    private TextView tv_option4;
    private TextView tv_option5;
    private TextView textViewScore;
    private TextView tv_questionCount;
    private TextView textViewCategory;
    private TextView textViewDifficulty;
    private TextView textViewCountDown;
    private RadioGroup radio_group_multiple_choice;
    private RadioGroup radio_group_sba;
    private RadioButton radio_a_1;
    private RadioButton radio_a_2;
    private RadioButton radio_b_1;
    private RadioButton radio_b_2;
    private RadioButton radio_c_1;
    private RadioButton radio_c_2;
    private RadioButton radio_d_1;
    private RadioButton radio_d_2;
    private RadioButton radio_e_1;
    private RadioButton radio_e_2;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;
    private RadioButton radio4;
    private RadioButton radio5;

    private TextView tv_next, tv_skip;
    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private ArrayList<Question> questionList;
    private ArrayList<Question> skippedeQuestionList;
    private ArrayList<String> questionIdList;
    private ArrayList<Answer> skippedAnswerList;
    private int questionCounter;
    private int questionCounterExtra = 0;
    private int questionCounterSkipped;
    private int questionCountTotal;
    private int questionCountTotalSkipped;
    private Question currentQuestion;
    private Question currentQuestionSkipped;
    private int score;
    private boolean answered;
    private long backPressedTime;

    private LinearLayout linear_multiple_choice_a, linear_multiple_choice_b, linear_multiple_choice_c, linear_multiple_choice_d, linear_multiple_choice_e, linear_sba;
    private RadioGroup radio_group_multiple_choice1, radio_group_multiple_choice2, radio_group_multiple_choice3, radio_group_multiple_choice4, radio_group_multiple_choice5;
    private View view5;

    ExamDbHelper dbHelper;

    private String radio1_given_ans = "";
    private String radio2_given_ans = "";
    private String radio3_given_ans = "";
    private String radio4_given_ans = "";
    private String radio5_given_ans = "";
    private String radio_a_1_given_ans = "";
    private String radio_a_2_given_ans = "";
    private String radio_b_1_given_ans = "";
    private String radio_b_2_given_ans = "";
    private String radio_c_1_given_ans = "";
    private String radio_c_2_given_ans = "";
    private String radio_d_1_given_ans = "";
    private String radio_d_2_given_ans = "";
    private String radio_e_1_given_ans = "";
    private String radio_e_2_given_ans = "";

    private int stepCount = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_question);

        dbHelper = ExamDbHelper.getInstance(this);

        initialiseSkippedQuesList();

        initToolbar();
        initView(savedInstanceState);
        initData();
        startCountDown();

//        tables_of_db1();
    }


    private void deleteAnswerData() {
        dbHelper.deleteAllAnswers();
    }


    private void tables_of_db1() {
        ArrayList<String> tables_of_db1 = dbHelper.tables_of_db();

        Toast.makeText(this, "Table Names=> " + tables_of_db1, Toast.LENGTH_LONG).show();
    }

    private void initialiseSkippedQuesList() {
        skippedeQuestionList = new ArrayList<>();
//        skippedeQuestionList.clear();
    }


    private void skippedQuesListFromDb() {
        skippedAnswerList = dbHelper.getAllSkippedQuestionsAnswers();
        Log.d("skippedAnswerList", "skippedAnswerList: " + skippedAnswerList);
        Log.d("skippedAnswerList", "skippedAnswerList size: " + skippedAnswerList.size());
    }


    private void initData() {
        Intent intent = getIntent();
    }


    private void initView(Bundle savedInstanceState) {

        linear_ques_form = findViewById(R.id.linear_ques_form);

        tv_view_message = findViewById(R.id.tv_view_message);

        tv_question = findViewById(R.id.tv_question);
        tv_option1 = findViewById(R.id.tv_option1);
        tv_option2 = findViewById(R.id.tv_option2);
        tv_option3 = findViewById(R.id.tv_option3);
        tv_option4 = findViewById(R.id.tv_option4);
        tv_option5 = findViewById(R.id.tv_option5);

        tv_questionCount = findViewById(R.id.tv_question_no);
//        textViewCategory = findViewById(R.id.text_view_category);
        textViewCountDown = findViewById(R.id.text_view_countdown);

        radio_group_multiple_choice1 = findViewById(R.id.radio_group_multiple_choice1);
        radio_group_multiple_choice2 = findViewById(R.id.radio_group_multiple_choice2);
        radio_group_multiple_choice3 = findViewById(R.id.radio_group_multiple_choice3);
        radio_group_multiple_choice4 = findViewById(R.id.radio_group_multiple_choice4);
        radio_group_multiple_choice5 = findViewById(R.id.radio_group_multiple_choice5);
        radio_group_sba = findViewById(R.id.radio_group_sba);

        radio_a_1 = findViewById(R.id.radio_a_1);
        radio_a_2 = findViewById(R.id.radio_a_2);
        radio_b_1 = findViewById(R.id.radio_b_1);
        radio_b_2 = findViewById(R.id.radio_b_2);
        radio_c_1 = findViewById(R.id.radio_c_1);
        radio_c_2 = findViewById(R.id.radio_c_2);
        radio_d_1 = findViewById(R.id.radio_d_1);
        radio_d_2 = findViewById(R.id.radio_d_2);
        radio_e_1 = findViewById(R.id.radio_e_1);
        radio_e_2 = findViewById(R.id.radio_e_2);

        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);
        radio5 = findViewById(R.id.radio5);

        linear_multiple_choice_a = findViewById(R.id.linear_multiple_choice_a);
        linear_multiple_choice_b = findViewById(R.id.linear_multiple_choice_b);
        linear_multiple_choice_c = findViewById(R.id.linear_multiple_choice_c);
        linear_multiple_choice_d = findViewById(R.id.linear_multiple_choice_d);
        linear_multiple_choice_e = findViewById(R.id.linear_multiple_choice_e);

        linear_sba = findViewById(R.id.linear_sba);
        view5 = findViewById(R.id.view5);

        tv_next = findViewById(R.id.tv_next);
        tv_skip = findViewById(R.id.tv_skip);

        textColorDefaultRb = radio1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();

        if (savedInstanceState == null) {

            ExamDbHelper dbHelper = ExamDbHelper.getInstance(this);
//            questionList = dbHelper.getQuestions(categoryID, difficulty);
            questionList = dbHelper.getAllQuestions();
//            currentQuestion = questionList.get(0);
            questionCountTotal = questionList.size();
//            Collections.shuffle(questionList);
            Log.d("questionList", "questionList: " + questionList);
            showNextQuestion("initial");
//            showInitialQuestion();

//            questionCounter = 1;
            for (int i = 0; i < questionList.size(); i++) {
                currentQuestionSkipped = questionList.get(questionCounter);
//                questionIdList.add();
            }

        } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);
            if (!answered) {
//                startCountDown();
                updateCountDownText();
            } else {
                updateCountDownText();
//                showSolution();
            }
        }

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer();

                if (!answered) {
//                    Toast.makeText(SingleQuestionActivity.this, "Please select an answer or press skip to avoid", Toast.LENGTH_SHORT).show();

                    answered = true;

                    if (questionCounter < questionCountTotal) {
                        tv_next.setText("Next");
                    } else {
                        tv_next.setText("Next");
                    }

//                    addAnswerList();
                    showNextQuestion("answered");


                } else {
                    answered = true;

                    if (questionCounter < questionCountTotal) {
                        tv_next.setText("Next");
                    } else {
//                            tv_next.setText("Next");
                        if (questionCounterSkipped < questionCountTotalSkipped) {
                            tv_next.setText("Next");
                        } else {
                            tv_next.setText("Next");
                        }
                    }


//                    addAnswerList();
                    showNextQuestion("answered");
                }
            }
        });


        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                answered = false;
//                addSkippedQues();
                showNextQuestion("skipped");

                if (answered) {
                    Toast.makeText(SingleQuestionActivity.this, "Can not be skipped, you have already given answer.", Toast.LENGTH_SHORT).show();
//                    answered = true;

                }

//                if (answered) {
//                    Toast.makeText(SingleQuestionActivity.this, "Can not be skipped, you have already given answer.", Toast.LENGTH_SHORT).show();
////                    answered = true;
//
//                } else {
//                    answered = false;
//                    addSkippedQues();
//                    showNextQuestion("skipped");
//                }
            }
        });


    }


    private void addAnswerList() {

    }

    private void addSkippedQues() {
        currentQuestion = questionList.get(questionCounter);
        skippedeQuestionList.add(currentQuestion);

        questionCountTotalSkipped = skippedeQuestionList.size();
    }

//
//    private void checkAnswer() {
//        answered = true;
//        countDownTimer.cancel();
//        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
//        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;
//        if (answerNr == currentQuestion.getAnswerNr()) {
//            score++;
//            textViewScore.setText("Score: " + score);
//        }
//        showSolution();
//    }
//


    private void checkAnswer() {

        //
        radio_group_sba.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio1:
                        answered = true;
                        radio1_given_ans = "A";
                        radio2_given_ans = "";
                        radio3_given_ans = "";
                        radio4_given_ans = "";
                        radio5_given_ans = "";
                        break;
                    case R.id.radio2:
                        answered = true;
                        radio1_given_ans = "";
                        radio2_given_ans = "B";
                        radio3_given_ans = "";
                        radio4_given_ans = "";
                        radio5_given_ans = "";
                        break;
                    case R.id.radio3:
                        answered = true;
                        radio1_given_ans = "";
                        radio2_given_ans = "";
                        radio3_given_ans = "C";
                        radio4_given_ans = "";
                        radio5_given_ans = "";
                        break;
                    case R.id.radio4:
                        answered = true;
                        radio1_given_ans = "";
                        radio2_given_ans = "";
                        radio3_given_ans = "";
                        radio4_given_ans = "D";
                        radio5_given_ans = "";
                        break;
                    case R.id.radio5:
                        answered = true;
                        radio1_given_ans = "";
                        radio2_given_ans = "";
                        radio3_given_ans = "";
                        radio4_given_ans = "";
                        radio5_given_ans = "E";
                        break;
                }
            }
        });
        //

        //
        radio_group_multiple_choice1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_a_1:
                        answered = true;
                        radio_a_1_given_ans = "T";
                        radio_a_2_given_ans = "F";
                        break;
                    case R.id.radio_a_2:
                        answered = true;
                        radio_a_1_given_ans = "F";
                        radio_a_2_given_ans = "T";
                        break;
                }
            }
        });
        //

        //
        radio_group_multiple_choice2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_b_1:
                        answered = true;
                        radio_b_1_given_ans = "T";
                        radio_b_2_given_ans = "F";
                        break;
                    case R.id.radio_b_2:
                        answered = true;
                        radio_b_1_given_ans = "F";
                        radio_b_2_given_ans = "T";
                        break;
                }
            }
        });
        //

        //
        radio_group_multiple_choice3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_c_1:
                        answered = true;
                        radio_c_1_given_ans = "T";
                        radio_c_2_given_ans = "F";
                        break;
                    case R.id.radio_c_2:
                        answered = true;
                        radio_c_1_given_ans = "F";
                        radio_c_2_given_ans = "T";
                        break;
                }
            }
        });
        //

        //
        radio_group_multiple_choice4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_d_1:
                        answered = true;
                        radio_d_1_given_ans = "T";
                        radio_d_2_given_ans = "F";
                        break;
                    case R.id.radio_d_2:
                        answered = true;
                        radio_d_1_given_ans = "F";
                        radio_d_2_given_ans = "T";
                        break;
                }
            }
        });
        //

        //
        radio_group_multiple_choice5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_e_1:
                        answered = true;
                        radio_e_1_given_ans = "T";
                        radio_e_2_given_ans = "F";
                        break;
                    case R.id.radio_e_2:
                        answered = true;
                        radio_e_1_given_ans = "F";
                        radio_e_2_given_ans = "T";
                        break;
                }
            }
        });

    }


    private void showSolution() {

        if (questionCounter < questionCountTotal) {
            tv_next.setText("Next");
        } else {
            tv_next.setText("Finish");
        }

    }


    private void initToolbar() {
        ActionBar ab = ((AppCompatActivity) this).getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        String title = this.getResources().getString(R.string.title_question);
        ab.setTitle(title);
    }


    ////


    private void showInitialQuestion() {

        currentQuestion = questionList.get(0);
        tv_question.setText(currentQuestion.getQuestion());

        Log.d("tagResult", "currentQuestion.getQuestionId() p: " + currentQuestion.getQuestionId());
        Log.d("tagResult", "questionCounter p: " + questionCounter);


        tv_option1.setText(currentQuestion.getOption1());
        tv_option2.setText(currentQuestion.getOption2());
        tv_option3.setText(currentQuestion.getOption3());
        tv_option4.setText(currentQuestion.getOption4());
        tv_option5.setText(currentQuestion.getOption5());

        ///
        if (currentQuestion.getQuestionType().equalsIgnoreCase("1")) {

            linear_multiple_choice_a.setVisibility(View.VISIBLE);
            linear_multiple_choice_b.setVisibility(View.VISIBLE);
            linear_multiple_choice_c.setVisibility(View.VISIBLE);
            linear_multiple_choice_d.setVisibility(View.VISIBLE);
            linear_multiple_choice_e.setVisibility(View.VISIBLE);

            linear_sba.setVisibility(View.GONE);
            view5.setVisibility(View.GONE);
        }

        if (currentQuestion.getQuestionType().equalsIgnoreCase("2")) {
            linear_sba.setVisibility(View.VISIBLE);
            view5.setVisibility(View.VISIBLE);

            linear_multiple_choice_a.setVisibility(View.GONE);
            linear_multiple_choice_b.setVisibility(View.GONE);
            linear_multiple_choice_c.setVisibility(View.GONE);
            linear_multiple_choice_d.setVisibility(View.GONE);
            linear_multiple_choice_e.setVisibility(View.GONE);
        }
        ///
    }


    ////


    private void showNextQuestion(String calling_status) {
        Log.d("currentQuestion", "skippedeQuestionList: " + skippedeQuestionList);
//        radio1.setTextColor(textColorDefaultRb);

//        questionCounter = 1;


        radio_group_multiple_choice1.clearCheck();
        radio_group_multiple_choice2.clearCheck();
        radio_group_multiple_choice3.clearCheck();
        radio_group_multiple_choice4.clearCheck();
        radio_group_multiple_choice5.clearCheck();
        radio_group_sba.clearCheck();


        Log.d("tagResult", "questionCounter p: " + questionCounter);
        if (questionCounter < questionCountTotal) {

//            if (questionCounterExtra == -1){
//                currentQuestion = questionList.get(questionCounterExtra+1);
//            }else {
//                currentQuestion = questionList.get(questionCounterExtra);
//            }

//            currentQuestion = questionList.get(questionCounterExtra);
            currentQuestion = questionList.get(questionCounter);

            tv_question.setText(currentQuestion.getQuestion());

            Log.d("tagResult", "currentQuestion.getQuestionId() p: " + currentQuestion.getQuestionId());
            Log.d("tagResult", "questionCounter p1: " + questionCounter);
            Log.d("tagResult", "questionCounterExtra p1: " + questionCounterExtra);


//            radio1.setText(currentQuestion.getOption1());

            tv_option1.setText(currentQuestion.getOption1());
            tv_option2.setText(currentQuestion.getOption2());
            tv_option3.setText(currentQuestion.getOption3());
            tv_option4.setText(currentQuestion.getOption4());
            tv_option5.setText(currentQuestion.getOption5());

            ///
            if (currentQuestion.getQuestionType().equalsIgnoreCase("1")) {

                linear_multiple_choice_a.setVisibility(View.VISIBLE);
                linear_multiple_choice_b.setVisibility(View.VISIBLE);
                linear_multiple_choice_c.setVisibility(View.VISIBLE);
                linear_multiple_choice_d.setVisibility(View.VISIBLE);
                linear_multiple_choice_e.setVisibility(View.VISIBLE);

                linear_sba.setVisibility(View.GONE);
                view5.setVisibility(View.GONE);
            }


            if (currentQuestion.getQuestionType().equalsIgnoreCase("2")) {
                linear_sba.setVisibility(View.VISIBLE);
                view5.setVisibility(View.VISIBLE);

                linear_multiple_choice_a.setVisibility(View.GONE);
                linear_multiple_choice_b.setVisibility(View.GONE);
                linear_multiple_choice_c.setVisibility(View.GONE);
                linear_multiple_choice_d.setVisibility(View.GONE);
                linear_multiple_choice_e.setVisibility(View.GONE);
            }
            ///


            //insert user ans into bd

            currentQuestion = questionList.get(questionCounterExtra);
            String questionId = currentQuestion.getQuestionId();
            String questionSl = String.valueOf(questionCounter);
            String questionType = currentQuestion.getQuestionType();

            String correct_ans_sba = "";

            String correct_ans_a = "";
            String correct_ans_b = "";
            String correct_ans_c = "";
            String correct_ans_d = "";
            String correct_ans_e = "";


            if (radio1_given_ans.equalsIgnoreCase("A")) {
                correct_ans_sba = "A";
            } else if (radio2_given_ans.equalsIgnoreCase("B")) {
                correct_ans_sba = "B";
            } else if (radio3_given_ans.equalsIgnoreCase("C")) {
                correct_ans_sba = "C";
            } else if (radio4_given_ans.equalsIgnoreCase("D")) {
                correct_ans_sba = "D";
            } else if (radio5_given_ans.equalsIgnoreCase("E")) {
                correct_ans_sba = "E";
            }


            if (radio_a_1_given_ans.equalsIgnoreCase("T")) {
                correct_ans_a = "T";
            } else if (radio_a_2_given_ans.equalsIgnoreCase("T")) {
                correct_ans_a = "F";
            }

            if (radio_b_1_given_ans.equalsIgnoreCase("T")) {
                correct_ans_b = "T";
            } else if (radio_b_2_given_ans.equalsIgnoreCase("T")) {
                correct_ans_b = "F";
            }

            if (radio_c_1_given_ans.equalsIgnoreCase("T")) {
                correct_ans_c = "T";
            } else if (radio_c_2_given_ans.equalsIgnoreCase("T")) {
                correct_ans_c = "F";
            }

            if (radio_d_1_given_ans.equalsIgnoreCase("T")) {
                correct_ans_d = "T";
            } else if (radio_d_2_given_ans.equalsIgnoreCase("T")) {
                correct_ans_d = "F";
            }

            if (radio_e_1_given_ans.equalsIgnoreCase("T")) {
                correct_ans_e = "T";
            } else if (radio_e_2_given_ans.equalsIgnoreCase("T")) {
                correct_ans_e = "F";
            }


            Log.d("tagResult", "questionId:" + questionId);
            Log.d("tagResult", "questionSl:" + questionSl);
            Log.d("tagResult", "questionType:" + questionType);
            Log.d("tagResult", "correct_ans_sba:" + correct_ans_sba);
            Log.d("tagResult", "correct_ans_a:" + correct_ans_a);
            Log.d("tagResult", "correct_ans_b:" + correct_ans_b);
            Log.d("tagResult", "correct_ans_c:" + correct_ans_c);
            Log.d("tagResult", "correct_ans_d:" + correct_ans_d);
            Log.d("tagResult", "correct_ans_e:" + correct_ans_e);

            if (calling_status.equalsIgnoreCase("initial")) {

            } else if (calling_status.equalsIgnoreCase("skipped")) {
                String skipped = "yes";
                String not_answered = "";
                Answer ans1 = new Answer(questionId, questionSl, questionType, correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e, skipped, not_answered);
                dbHelper.addAnswer(ans1);

                Log.d("tag676767", "skipped is called, skipped = " + skipped);

            } else {
                String skipped = "no";
                String not_answered = "";
                Answer ans1 = new Answer(questionId, questionSl, questionType, correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e, skipped, not_answered);
                dbHelper.addAnswer(ans1);
            }

            correct_ans_sba = "";

            correct_ans_a = "";
            correct_ans_b = "";
            correct_ans_c = "";
            correct_ans_d = "";
            correct_ans_e = "";

            radio1_given_ans = "";
            radio2_given_ans = "";
            radio3_given_ans = "";
            radio4_given_ans = "";
            radio5_given_ans = "";
            radio_a_1_given_ans = "";
            radio_a_2_given_ans = "";
            radio_b_1_given_ans = "";
            radio_b_2_given_ans = "";
            radio_c_1_given_ans = "";
            radio_c_2_given_ans = "";
            radio_d_1_given_ans = "";
            radio_d_2_given_ans = "";
            radio_e_1_given_ans = "";
            radio_e_2_given_ans = "";


            //


            Log.d("tagResult", "ansCountTotal p: " + dbHelper.getAllGivenAnswers().size());
            Log.d("answerList", "ansCountTotal p: " + dbHelper.getAllGivenAnswers().size());


            if (calling_status.equalsIgnoreCase("initial")) {
                questionCounter++;
                tv_questionCount.setText("Question: " + (questionCounter) + "/" + questionCountTotal);
            }else {
                questionCounter++;
                questionCounterExtra++;

                tv_questionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            }



            answered = false;
            tv_next.setText("Next");
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
//            startCountDown();


        } else if (questionCounter == questionCountTotal) {

            Log.d("tag0909", "questionCounter: " + questionCounter + " questionCountTotal: " + questionCountTotal);


            String questionId = "";

            currentQuestion = questionList.get(questionCounterExtra);

            if (currentQuestion.getQuestionId() != null) {
                questionId = currentQuestion.getQuestionId();
            }
            String questionType = "";
            if (currentQuestion.getQuestionType() != null) {
                questionType = currentQuestion.getQuestionType();
            }

            String questionSl = String.valueOf(questionCounter);


            String correct_ans_sba = "";

            String correct_ans_a = "";
            String correct_ans_b = "";
            String correct_ans_c = "";
            String correct_ans_d = "";
            String correct_ans_e = "";


            if (radio1_given_ans.equalsIgnoreCase("A")) {
                correct_ans_sba = radio1_given_ans;
            } else if (radio2_given_ans.equalsIgnoreCase("B")) {
                correct_ans_sba = radio2_given_ans;
            } else if (radio3_given_ans.equalsIgnoreCase("C")) {
                correct_ans_sba = radio3_given_ans;
            } else if (radio4_given_ans.equalsIgnoreCase("D")) {
                correct_ans_sba = radio4_given_ans;
            } else if (radio5_given_ans.equalsIgnoreCase("E")) {
                correct_ans_sba = radio5_given_ans;
            }


            if (radio_a_1_given_ans.equalsIgnoreCase("T")) {
                correct_ans_a = "T";
            } else if (radio_a_2_given_ans.equalsIgnoreCase("T")) {
                correct_ans_a = "F";
            }

            if (radio_b_1_given_ans.equalsIgnoreCase("T")) {
                correct_ans_b = "T";
            } else if (radio_b_2_given_ans.equalsIgnoreCase("T")) {
                correct_ans_b = "F";
            }

            if (radio_c_1_given_ans.equalsIgnoreCase("T")) {
                correct_ans_c = "T";
            } else if (radio_c_2_given_ans.equalsIgnoreCase("T")) {
                correct_ans_c = "F";
            }

            if (radio_d_1_given_ans.equalsIgnoreCase("T")) {
                correct_ans_d = "T";
            } else if (radio_d_2_given_ans.equalsIgnoreCase("T")) {
                correct_ans_d = "F";
            }

            if (radio_e_1_given_ans.equalsIgnoreCase("T")) {
                correct_ans_e = "T";
            } else if (radio_e_2_given_ans.equalsIgnoreCase("T")) {
                correct_ans_e = "F";
            }


            Log.d("tagResult", "questionId:" + questionId);
            Log.d("tagResult", "questionSl:" + questionSl);
            Log.d("tagResult", "questionType:" + questionType);
            Log.d("tagResult", "correct_ans_sba:" + correct_ans_sba);
            Log.d("tagResult", "correct_ans_a:" + correct_ans_a);
            Log.d("tagResult", "correct_ans_b:" + correct_ans_b);
            Log.d("tagResult", "correct_ans_c:" + correct_ans_c);
            Log.d("tagResult", "correct_ans_d:" + correct_ans_d);
            Log.d("tagResult", "correct_ans_e:" + correct_ans_e);


            if (calling_status.equalsIgnoreCase("skipped")) {

                String skipped = "yes";
                String not_answered = "";
                Answer ans1 = new Answer(questionId, questionSl, questionType, correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e, skipped, not_answered);
                dbHelper.addAnswer(ans1);

            } else {

                String skipped = "no";
                String not_answered = "";
                Answer ans1 = new Answer(questionId, questionSl, questionType, correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e, skipped, not_answered);
                dbHelper.addAnswer(ans1);

            }
//            Answer ans1 = new Answer(questionId, questionSl, questionType, correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e, skipped, not_answered);
//            dbHelper.addAnswer(ans1);

            correct_ans_sba = "";

            correct_ans_a = "";
            correct_ans_b = "";
            correct_ans_c = "";
            correct_ans_d = "";
            correct_ans_e = "";

            radio1_given_ans = "";
            radio2_given_ans = "";
            radio3_given_ans = "";
            radio4_given_ans = "";
            radio5_given_ans = "";
            radio_a_1_given_ans = "";
            radio_a_2_given_ans = "";
            radio_b_1_given_ans = "";
            radio_b_2_given_ans = "";
            radio_c_1_given_ans = "";
            radio_c_2_given_ans = "";
            radio_d_1_given_ans = "";
            radio_d_2_given_ans = "";
            radio_e_1_given_ans = "";
            radio_e_2_given_ans = "";

            questionCounter++;
            questionCounterExtra++;
            answered = false;
            tv_next.setText("Next");
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;


            linear_ques_form.setVisibility(View.GONE);
            tv_view_message.setVisibility(View.VISIBLE);
            skippedQuesListFromDb();
            Log.d("tag0909", "skippedAnswerList.size(): " + skippedAnswerList.size());

            if (skippedAnswerList.size() < 1) {
                tv_skip.setVisibility(View.GONE);
                tv_view_message.setText("Click next for answers");
            } else {
                tv_skip.setVisibility(View.GONE);
                tv_view_message.setText("Click next for skipped questions");
            }

        } else {

//            stepCount = 50;

            if (calling_status.equalsIgnoreCase("skipped")) {
                checkSkippedQuestion("skipped");
            } else {
                checkSkippedQuestion("answered");
            }

        }
    }

//    skippedAnswerList

    private void checkSkippedQuestion(String calling_status2) {

        linear_ques_form.setVisibility(View.VISIBLE);
        tv_view_message.setVisibility(View.GONE);
        tv_skip.setVisibility(View.VISIBLE);

        skippedQuesListFromDb();
        Log.d("tag0909", "skippedAnswerList.size(): " + skippedAnswerList.size());

//        if (skippedeQuestionList.size() < 1) {
        if (skippedAnswerList.size() < 1) {
            finishExam();
        } else {

            String skipped_ques_id_stored = SharedData.getSKIPPED_QUES_ID_STORED(this);

            int size_s = skippedAnswerList.size();
            int size_n = size_s - size_s;
            String skipped_ques_sl = skippedAnswerList.get(size_n).getQuestionSl();
            String skipped_ques_id = skippedAnswerList.get(size_n).getQuestionId();
            SharedData.saveSKIPPED_QUES_ID_STORED(this, skipped_ques_id);

            Log.d("tag0909", "skippedAnswerList.size(): " + skippedAnswerList.size());
            Log.d("tag0909", "skipped_ques_sl: " + skipped_ques_sl);
            Log.d("tag0909", "skipped_ques_id: " + skipped_ques_id);

            currentQuestion = dbHelper.getSpecificQuestion(skipped_ques_id);


            ///
            radio_group_multiple_choice1.clearCheck();
            radio_group_multiple_choice2.clearCheck();
            radio_group_multiple_choice3.clearCheck();
            radio_group_multiple_choice4.clearCheck();
            radio_group_multiple_choice5.clearCheck();
            radio_group_sba.clearCheck();


//            if (questionCounterSkipped < questionCountTotalSkipped) {

//                currentQuestionSkipped = skippedeQuestionList.get(questionCounterSkipped);
            tv_question.setText(currentQuestion.getQuestion());


//            radio1.setText(currentQuestion.getOption1());

            tv_option1.setText(currentQuestion.getOption1());
            tv_option2.setText(currentQuestion.getOption2());
            tv_option3.setText(currentQuestion.getOption3());
            tv_option4.setText(currentQuestion.getOption4());
            tv_option5.setText(currentQuestion.getOption5());

            ///
            if (currentQuestion.getQuestionType().equalsIgnoreCase("1")) {

                linear_multiple_choice_a.setVisibility(View.VISIBLE);
                linear_multiple_choice_b.setVisibility(View.VISIBLE);
                linear_multiple_choice_c.setVisibility(View.VISIBLE);
                linear_multiple_choice_d.setVisibility(View.VISIBLE);
                linear_multiple_choice_e.setVisibility(View.VISIBLE);

                linear_sba.setVisibility(View.GONE);
                view5.setVisibility(View.GONE);
            }

            if (currentQuestion.getQuestionType().equalsIgnoreCase("2")) {
                linear_sba.setVisibility(View.VISIBLE);
                view5.setVisibility(View.VISIBLE);

                linear_multiple_choice_a.setVisibility(View.GONE);
                linear_multiple_choice_b.setVisibility(View.GONE);
                linear_multiple_choice_c.setVisibility(View.GONE);
                linear_multiple_choice_d.setVisibility(View.GONE);
                linear_multiple_choice_e.setVisibility(View.GONE);
            }
            ///



            currentQuestion = dbHelper.getSpecificQuestion(skipped_ques_id_stored);

            //insert user ans into bd
            String questionId = currentQuestion.getQuestionId();
//            String questionSl = String.valueOf(questionCounter);
            String questionSl = skipped_ques_sl;
            String questionType = currentQuestion.getQuestionType();

            String correct_ans_sba = "";

            String correct_ans_a = "";
            String correct_ans_b = "";
            String correct_ans_c = "";
            String correct_ans_d = "";
            String correct_ans_e = "";


            if (radio1_given_ans.equalsIgnoreCase("A")) {
                correct_ans_sba = "A";
            } else if (radio2_given_ans.equalsIgnoreCase("B")) {
                correct_ans_sba = "B";
            } else if (radio3_given_ans.equalsIgnoreCase("C")) {
                correct_ans_sba = "C";
            } else if (radio4_given_ans.equalsIgnoreCase("D")) {
                correct_ans_sba = "D";
            } else if (radio5_given_ans.equalsIgnoreCase("E")) {
                correct_ans_sba = "E";
            }


            if (radio_a_1_given_ans.equalsIgnoreCase("T")) {
                correct_ans_a = "T";
            } else if (radio_a_2_given_ans.equalsIgnoreCase("T")) {
                correct_ans_a = "F";
            }

            if (radio_b_1_given_ans.equalsIgnoreCase("T")) {
                correct_ans_b = "T";
            } else if (radio_b_2_given_ans.equalsIgnoreCase("T")) {
                correct_ans_b = "F";
            }

            if (radio_c_1_given_ans.equalsIgnoreCase("T")) {
                correct_ans_c = "T";
            } else if (radio_c_2_given_ans.equalsIgnoreCase("T")) {
                correct_ans_c = "F";
            }

            if (radio_d_1_given_ans.equalsIgnoreCase("T")) {
                correct_ans_d = "T";
            } else if (radio_d_2_given_ans.equalsIgnoreCase("T")) {
                correct_ans_d = "F";
            }

            if (radio_e_1_given_ans.equalsIgnoreCase("T")) {
                correct_ans_e = "T";
            } else if (radio_e_2_given_ans.equalsIgnoreCase("T")) {
                correct_ans_e = "F";
            }


            Log.d("tagResultSkipped", "questionId:" + questionId);
            Log.d("tagResultSkipped", "questionSl:" + questionSl);
            Log.d("tagResultSkipped", "questionType:" + questionType);
            Log.d("tagResultSkipped", "correct_ans_sba:" + correct_ans_sba);
            Log.d("tagResultSkipped", "correct_ans_a:" + correct_ans_a);
            Log.d("tagResultSkipped", "correct_ans_b:" + correct_ans_b);
            Log.d("tagResultSkipped", "correct_ans_c:" + correct_ans_c);
            Log.d("tagResultSkipped", "correct_ans_d:" + correct_ans_d);
            Log.d("tagResultSkipped", "correct_ans_e:" + correct_ans_e);


//            if (calling_status2.equalsIgnoreCase("skipped")) {
//
////                    removeAnswer(String question_id1);
//                String skipped = "yes";
//                String not_answered = "";
////                    Answer ans1 = new Answer(questionId, questionSl, questionType, correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e, skipped, not_answered);
////                    dbHelper.addAnswer(ans1);
//
//                dbHelper.updateSpecificAnswer(questionId, questionSl, questionType, correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e, skipped, not_answered);
//
//            } else {
//                String skipped = "no";
//                String not_answered = "";
////                    Answer ans1 = new Answer(questionId, questionSl, questionType, correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e, skipped, not_answered);
////                    dbHelper.addAnswer(ans1);
//                dbHelper.updateSpecificAnswer(questionId, questionSl, questionType, correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e, skipped, not_answered);
////                    dbHelper.removeSkippedSpecificAnswer(questionId);
//            }

            if (stepCount > 50) {
                if (calling_status2.equalsIgnoreCase("skipped")) {

//                    removeAnswer(String question_id1);
                    String skipped = "yes";
                    String not_answered = "";
                    dbHelper.updateSpecificAnswer(questionId, questionSl, questionType, correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e, skipped, not_answered);

                } else {
                    String skipped = "no";
                    String not_answered = "";
                    dbHelper.updateSpecificAnswer(questionId, questionSl, questionType, correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e, skipped, not_answered);
//                    dbHelper.removeSkippedSpecificAnswer(questionId);
                }
            }else {
                String skipped = "no";
                String not_answered = "";
                dbHelper.updateSpecificAnswer(questionId, questionSl, questionType, correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e, skipped, not_answered);
            }

            stepCount++;




//            removeSkippedItem,updateAnswerItem


//                skippedeQuestionList.remove(currentQuestionSkipped);
//                skippedAnswerList.remove(0);


            correct_ans_sba = "";

            correct_ans_a = "";
            correct_ans_b = "";
            correct_ans_c = "";
            correct_ans_d = "";
            correct_ans_e = "";

            radio1_given_ans = "";
            radio2_given_ans = "";
            radio3_given_ans = "";
            radio4_given_ans = "";
            radio5_given_ans = "";
            radio_a_1_given_ans = "";
            radio_a_2_given_ans = "";
            radio_b_1_given_ans = "";
            radio_b_2_given_ans = "";
            radio_c_1_given_ans = "";
            radio_c_2_given_ans = "";
            radio_d_1_given_ans = "";
            radio_d_2_given_ans = "";
            radio_e_1_given_ans = "";
            radio_e_2_given_ans = "";

            //


            Log.d("tagResult", "ansCountTotal p: " + dbHelper.getAllGivenAnswers().size());
            Log.d("answerList", "ansCountTotal p: " + dbHelper.getAllGivenAnswers().size());


            questionCounterSkipped++;
            tv_questionCount.setText("Question: " + skipped_ques_sl + "/" + questionCountTotal);
            answered = false;

            tv_next.setText("Next");
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;


//            }
//
//            else if (questionCounterSkipped == questionCountTotalSkipped) {
//
//                //insert user ans into bd
//                String questionId = currentQuestion.getQuestionId();
//                String questionSl = String.valueOf(questionCounter);
//                String questionType = currentQuestion.getQuestionType();
//
//                String correct_ans_sba = "";
//
//                String correct_ans_a = "";
//                String correct_ans_b = "";
//                String correct_ans_c = "";
//                String correct_ans_d = "";
//                String correct_ans_e = "";
//
//
//                if (radio1_given_ans.equalsIgnoreCase("A")) {
//                    correct_ans_sba = radio1_given_ans;
//                } else if (radio2_given_ans.equalsIgnoreCase("B")) {
//                    correct_ans_sba = radio2_given_ans;
//                } else if (radio3_given_ans.equalsIgnoreCase("C")) {
//                    correct_ans_sba = radio3_given_ans;
//                } else if (radio4_given_ans.equalsIgnoreCase("D")) {
//                    correct_ans_sba = radio4_given_ans;
//                } else if (radio5_given_ans.equalsIgnoreCase("E")) {
//                    correct_ans_sba = radio5_given_ans;
//                }
//
//
//                if (radio_a_1_given_ans.equalsIgnoreCase("T")) {
//                    correct_ans_a = "T";
//                } else if (radio_a_2_given_ans.equalsIgnoreCase("T")) {
//                    correct_ans_a = "F";
//                }
//
//                if (radio_b_1_given_ans.equalsIgnoreCase("T")) {
//                    correct_ans_b = "T";
//                } else if (radio_b_2_given_ans.equalsIgnoreCase("T")) {
//                    correct_ans_b = "F";
//                }
//
//                if (radio_c_1_given_ans.equalsIgnoreCase("T")) {
//                    correct_ans_c = "T";
//                } else if (radio_c_2_given_ans.equalsIgnoreCase("T")) {
//                    correct_ans_c = "F";
//                }
//
//                if (radio_d_1_given_ans.equalsIgnoreCase("T")) {
//                    correct_ans_d = "T";
//                } else if (radio_d_2_given_ans.equalsIgnoreCase("T")) {
//                    correct_ans_d = "F";
//                }
//
//                if (radio_e_1_given_ans.equalsIgnoreCase("T")) {
//                    correct_ans_e = "T";
//                } else if (radio_e_2_given_ans.equalsIgnoreCase("T")) {
//                    correct_ans_e = "F";
//                }
//
//
//                Log.d("tagResult", "questionId:" + questionId);
//                Log.d("tagResult", "questionSl:" + questionSl);
//                Log.d("tagResult", "questionType:" + questionType);
//                Log.d("tagResult", "correct_ans_sba:" + correct_ans_sba);
//                Log.d("tagResult", "correct_ans_a:" + correct_ans_a);
//                Log.d("tagResult", "correct_ans_b:" + correct_ans_b);
//                Log.d("tagResult", "correct_ans_c:" + correct_ans_c);
//                Log.d("tagResult", "correct_ans_d:" + correct_ans_d);
//                Log.d("tagResult", "correct_ans_e:" + correct_ans_e);
//
//
//                String skipped = "";
//                String not_answered = "";
//                Answer ans1 = new Answer(questionId, questionSl, questionType, correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e, skipped, not_answered);
//                dbHelper.addAnswer(ans1);
//                skippedeQuestionList.remove(currentQuestionSkipped);
//
//                correct_ans_sba = "";
//
//                correct_ans_a = "";
//                correct_ans_b = "";
//                correct_ans_c = "";
//                correct_ans_d = "";
//                correct_ans_e = "";
//
//                radio1_given_ans = "";
//                radio2_given_ans = "";
//                radio3_given_ans = "";
//                radio4_given_ans = "";
//                radio5_given_ans = "";
//                radio_a_1_given_ans = "";
//                radio_a_2_given_ans = "";
//                radio_b_1_given_ans = "";
//                radio_b_2_given_ans = "";
//                radio_c_1_given_ans = "";
//                radio_c_2_given_ans = "";
//                radio_d_1_given_ans = "";
//                radio_d_2_given_ans = "";
//                radio_e_1_given_ans = "";
//                radio_e_2_given_ans = "";
//
//                questionCounter++;
//
//            } else {
//
//            }


//            checkSkippedQuestion();
        }

    }

    private void startCountDown() {

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
//                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int hours = (int) (timeLeftInMillis / 1000) / 3600;
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
        textViewCountDown.setText(timeFormatted);
        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.YELLOW);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }

    ////

    private void finishExam() {
//        Intent resultIntent = new Intent();
////        resultIntent.putExtra(EXTRA_SCORE, score);
//        setResult(RESULT_OK, resultIntent);
//        finish();

        Intent intent = new Intent(SingleQuestionActivity.this, ResultActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishExam();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
//        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    onBackPressed();
                }
//                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}