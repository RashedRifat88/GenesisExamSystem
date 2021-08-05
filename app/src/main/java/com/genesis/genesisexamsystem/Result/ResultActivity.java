package com.genesis.genesisexamsystem.Result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.genesis.genesisexamsystem.R;
import com.genesis.genesisexamsystem.answer.CorrectAnswerActivity;
import com.genesis.genesisexamsystem.data.database.ExamDbHelper;
import com.genesis.genesisexamsystem.data.model.Answer;
import com.genesis.genesisexamsystem.data.model.Question;
import com.genesis.genesisexamsystem.data.shared_pref.SharedData;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {


    Double mcq_number_d = 0.0;
    Double mcq_mark_d = 0.0;
    Double single_mcq_mark_d = 0.0;
    Double mcq_negative_mark_d = 0.0;
    Double sba_number_d = 0.0;
    Double sba_mark_d = 0.0;
    Double sba_negative_mark_d = 0.0;

    String mcq_number = "";
    String mcq_mark = "";
    String mcq_negative_mark = "";
    String sba_number = "";
    String sba_mark = "";
    String sba_negative_mark = "";

    private ArrayList<Answer> answerList;
    private ArrayList<Question> questionList;
    private Answer currentAnswer;
    private Question currentQuestion;
    private int questionCountTotal;
    private int ansCountTotal;
    private double totalMark;
    private double totalCorrectMark;
    private double totalNegativeMark;
    private double totalObtainedMark;

    int negative_ans_count = 0;


    private LinearLayout linear_result_ans;
    private TextView tv_correct_mark, tv_negative_mark, tv_obtained_mark, tv_exam_name;
    private TextView tv_wrong_ans;
    private String examName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setDataFromSharedPref();

        totalCorrectMark = 0.0;
        totalNegativeMark = 0.0;
        totalObtainedMark = 0.0;

        ExamDbHelper dbHelper = ExamDbHelper.getInstance(this);
        answerList = dbHelper.getAllGivenAnswers();
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        ansCountTotal = answerList.size();
        Log.d("answerList", "answerList: " + answerList);
        Log.d("answerList", "questionList: " + questionList);
        Log.d("answerList", "questionCountTotal: " + questionCountTotal);
        Log.d("answerList", "ansCountTotal: " + ansCountTotal);

        initView();
        compareAnswers();
        calculateRerult();
    }

    private void setDataFromSharedPref() {
        mcq_number = SharedData.getMCQ_NUMBER(this);
        mcq_mark = SharedData.getMCQ_MARK(this);
        mcq_negative_mark = SharedData.getMCQ_NEGATIVE_MARK(this);
        sba_number = SharedData.getSBA_NUMBER(this);
        sba_mark = SharedData.getSBA_MARK(this);
        sba_negative_mark = SharedData.getSBA_NEGATIVE_MARK(this);

        mcq_number_d = Double.valueOf(mcq_number);
        mcq_mark_d = Double.valueOf(mcq_mark);
        mcq_negative_mark_d = Double.valueOf(mcq_negative_mark);
        sba_number_d = Double.valueOf(sba_number);
        sba_mark_d = Double.valueOf(sba_mark);
        sba_negative_mark_d = Double.valueOf(sba_negative_mark);

        single_mcq_mark_d = mcq_mark_d/5;


        Log.d("tag_shared_pref", "mcq_number: " + mcq_number);
        Log.d("tag_shared_pref", "mcq_mark: " + mcq_mark);
        Log.d("tag_shared_pref", "mcq_negative_mark: " + mcq_negative_mark);
        Log.d("tag_shared_pref", "sba_number: " + sba_number);
        Log.d("tag_shared_pref", "sba_mark: " + sba_mark);
        Log.d("tag_shared_pref", "sba_negative_mark: " + sba_negative_mark);
    }

    private void calculateRerult() {
        totalObtainedMark = totalCorrectMark - totalNegativeMark;

        tv_correct_mark.setText(String.valueOf(new DecimalFormat("##.##").format(totalCorrectMark)));
        tv_negative_mark.setText(String.valueOf(new DecimalFormat("##.##").format(totalNegativeMark)));
        tv_obtained_mark.setText(String.valueOf(new DecimalFormat("##.##").format(totalObtainedMark)));
        tv_wrong_ans.setText(String.valueOf(new DecimalFormat("##.##").format(negative_ans_count)));
    }

    private void compareAnswers() {
        for (int i = 0; i < questionCountTotal; i++) {
            currentQuestion = questionList.get(i);
            currentAnswer = answerList.get(i);

            Log.d("tagResult", "currentQuestion.getCorrect_ans_sba(): " + currentQuestion.getCorrect_ans_sba() + "  currentAnswer.getCorrect_ans_sba(): " + currentAnswer.getCorrect_ans_sba());

            Log.d("tagResult", "currentQuestion.getCorrect_ans_a(): " + currentQuestion.getCorrect_ans_a() + "  currentAnswer.getCorrect_ans_a(): " + currentAnswer.getCorrect_ans_a());
            Log.d("tagResult", "currentQuestion.getCorrect_ans_b(): " + currentQuestion.getCorrect_ans_b() + "  currentAnswer.getCorrect_ans_b(): " + currentAnswer.getCorrect_ans_b());
            Log.d("tagResult", "currentQuestion.getCorrect_ans_c(): " + currentQuestion.getCorrect_ans_c() + "  currentAnswer.getCorrect_ans_c(): " + currentAnswer.getCorrect_ans_c());
            Log.d("tagResult", "currentQuestion.getCorrect_ans_d(): " + currentQuestion.getCorrect_ans_d() + "  currentAnswer.getCorrect_ans_d(): " + currentAnswer.getCorrect_ans_d());
            Log.d("tagResult", "currentQuestion.getCorrect_ans_e(): " + currentQuestion.getCorrect_ans_e() + "  currentAnswer.getCorrect_ans_e(): " + currentAnswer.getCorrect_ans_e());

//
//            if (currentQuestion.getQuestionType().equalsIgnoreCase("1")) {
//
//                if (currentQuestion.getCorrect_ans_a().equalsIgnoreCase(currentAnswer.getCorrect_ans_a())) {
//                    totalCorrectMark = totalCorrectMark + 0.2;
//                } else {
//                    if (currentAnswer.getCorrect_ans_a().equalsIgnoreCase("")) {
//
//                    } else {
//                        totalNegativeMark = totalNegativeMark + 0.1;
//                    }
//                }
//
//                if (currentQuestion.getCorrect_ans_b().equalsIgnoreCase(currentAnswer.getCorrect_ans_b())) {
//                    totalCorrectMark = totalCorrectMark + 0.2;
//                } else {
//                    if (currentAnswer.getCorrect_ans_b().equalsIgnoreCase("")) {
//
//                    } else {
//                        totalNegativeMark = totalNegativeMark + 0.1;
//                    }
//                }
//
//                if (currentQuestion.getCorrect_ans_c().equalsIgnoreCase(currentAnswer.getCorrect_ans_c())) {
//                    totalCorrectMark = totalCorrectMark + 0.2;
//                } else {
//                    if (currentAnswer.getCorrect_ans_c().equalsIgnoreCase("")) {
//
//                    } else {
//                        totalNegativeMark = totalNegativeMark + 0.1;
//                    }
//                }
//
//                if (currentQuestion.getCorrect_ans_d().equalsIgnoreCase(currentAnswer.getCorrect_ans_d())) {
//                    totalCorrectMark = totalCorrectMark + 0.2;
//                } else {
//                    if (currentAnswer.getCorrect_ans_d().equalsIgnoreCase("")) {
//
//                    } else {
//                        totalNegativeMark = totalNegativeMark + 0.1;
//                    }
//                }
//
//                if (currentQuestion.getCorrect_ans_e().equalsIgnoreCase(currentAnswer.getCorrect_ans_e())) {
//                    totalCorrectMark = totalCorrectMark + 0.2;
//                } else {
//                    if (currentAnswer.getCorrect_ans_e().equalsIgnoreCase("")) {
//
//                    } else {
//                        totalNegativeMark = totalNegativeMark + 0.1;
//                    }
//                }
//
//            } else if (currentQuestion.getQuestionType().equalsIgnoreCase("2")) {
//
//                if (currentQuestion.getCorrect_ans_sba().equalsIgnoreCase(currentAnswer.getCorrect_ans_sba())) {
//                    totalCorrectMark = totalCorrectMark + 1.0;
//                } else {
//                    if (currentAnswer.getCorrect_ans_sba().equalsIgnoreCase("")) {
//
//                    } else {
//                        totalNegativeMark = totalNegativeMark + 0.5;
//                    }
//                }
//
//            }


            ///
            if (currentQuestion.getQuestionType().equalsIgnoreCase("1")) {

                if (currentQuestion.getCorrect_ans_a().equalsIgnoreCase(currentAnswer.getCorrect_ans_a())) {
                    totalCorrectMark = totalCorrectMark + single_mcq_mark_d;
                } else {
                    if (currentAnswer.getCorrect_ans_a().equalsIgnoreCase("")) {

                    } else {
                        totalNegativeMark = totalNegativeMark + mcq_negative_mark_d;
                        negative_ans_count++;
                    }
                }

                if (currentQuestion.getCorrect_ans_b().equalsIgnoreCase(currentAnswer.getCorrect_ans_b())) {
                    totalCorrectMark = totalCorrectMark + single_mcq_mark_d;
                } else {
                    if (currentAnswer.getCorrect_ans_b().equalsIgnoreCase("")) {

                    } else {
                        totalNegativeMark = totalNegativeMark + mcq_negative_mark_d;
                        negative_ans_count++;
                    }
                }

                if (currentQuestion.getCorrect_ans_c().equalsIgnoreCase(currentAnswer.getCorrect_ans_c())) {
                    totalCorrectMark = totalCorrectMark + single_mcq_mark_d;
                } else {
                    if (currentAnswer.getCorrect_ans_c().equalsIgnoreCase("")) {

                    } else {
                        totalNegativeMark = totalNegativeMark + mcq_negative_mark_d;
                        negative_ans_count++;
                    }
                }

                if (currentQuestion.getCorrect_ans_d().equalsIgnoreCase(currentAnswer.getCorrect_ans_d())) {
                    totalCorrectMark = totalCorrectMark + single_mcq_mark_d;
                } else {
                    if (currentAnswer.getCorrect_ans_d().equalsIgnoreCase("")) {

                    } else {
                        totalNegativeMark = totalNegativeMark + mcq_negative_mark_d;
                        negative_ans_count++;
                    }
                }

                if (currentQuestion.getCorrect_ans_e().equalsIgnoreCase(currentAnswer.getCorrect_ans_e())) {
                    totalCorrectMark = totalCorrectMark + single_mcq_mark_d;
                } else {
                    if (currentAnswer.getCorrect_ans_e().equalsIgnoreCase("")) {

                    } else {
                        totalNegativeMark = totalNegativeMark + mcq_negative_mark_d;
                        negative_ans_count++;
                    }
                }

            } else if (currentQuestion.getQuestionType().equalsIgnoreCase("2")) {

                if (currentQuestion.getCorrect_ans_sba().equalsIgnoreCase(currentAnswer.getCorrect_ans_sba())) {
                    totalCorrectMark = totalCorrectMark + sba_mark_d;
                } else {
                    if (currentAnswer.getCorrect_ans_sba().equalsIgnoreCase("")) {

                    } else {
                        totalNegativeMark = totalNegativeMark + sba_negative_mark_d;
                        negative_ans_count++;
                    }
                }

            }
            ///




        }
    }

    private void initView() {
        tv_correct_mark = findViewById(R.id.tv_correct_mark);
        tv_negative_mark = findViewById(R.id.tv_negative_mark);
        tv_obtained_mark = findViewById(R.id.tv_obtained_mark);
        tv_wrong_ans = findViewById(R.id.tv_wrong_ans);
        tv_exam_name = findViewById(R.id.tv_exam_name);

        linear_result_ans = findViewById(R.id.linear_result_ans);

        examName = SharedData.getEXAM_NAME(this);
        tv_exam_name.setText(examName);

        linear_result_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, CorrectAnswerActivity.class);
                startActivity(intent);
            }
        });
    }


//    currentAnswer = questionList.get(questionCounter);
//            tv_question.setText(currentAnswer.getAnswer());
//
//            tv_option1.setText(currentAnswer.getOption1());


}