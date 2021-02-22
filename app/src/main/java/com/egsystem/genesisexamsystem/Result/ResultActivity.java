package com.egsystem.genesisexamsystem.Result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.answer.CorrectAnswerActivity;
import com.egsystem.genesisexamsystem.data.database.ExamDbHelper;
import com.egsystem.genesisexamsystem.data.model.Answer;
import com.egsystem.genesisexamsystem.data.model.Question;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

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


    private LinearLayout linear_result_ans;
    private TextView tv_correct_mark, tv_negative_mark, tv_obtained_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

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

    private void calculateRerult() {
        totalObtainedMark = totalCorrectMark - totalNegativeMark;

        tv_correct_mark.setText(String.valueOf(new DecimalFormat("##.##").format(totalCorrectMark)));
        tv_negative_mark.setText(String.valueOf(new DecimalFormat("##.##").format(totalNegativeMark)));
        tv_obtained_mark.setText(String.valueOf(new DecimalFormat("##.##").format(totalObtainedMark)));
    }

    private void compareAnswers() {
        for (int i = 0; i < questionCountTotal; i++) {
            currentQuestion = questionList.get(i);
            currentAnswer = answerList.get(i);

            Log.d("tagResult", "currentQuestion.getCorrect_ans_sba(): "+currentQuestion.getCorrect_ans_sba() +"  currentAnswer.getCorrect_ans_sba(): "+currentAnswer.getCorrect_ans_sba());

            Log.d("tagResult", "currentQuestion.getCorrect_ans_a(): "+currentQuestion.getCorrect_ans_a() +"  currentAnswer.getCorrect_ans_a(): "+currentAnswer.getCorrect_ans_a());
            Log.d("tagResult", "currentQuestion.getCorrect_ans_b(): "+currentQuestion.getCorrect_ans_b() +"  currentAnswer.getCorrect_ans_b(): "+currentAnswer.getCorrect_ans_b());
            Log.d("tagResult", "currentQuestion.getCorrect_ans_c(): "+currentQuestion.getCorrect_ans_c() +"  currentAnswer.getCorrect_ans_c(): "+currentAnswer.getCorrect_ans_c());
            Log.d("tagResult", "currentQuestion.getCorrect_ans_d(): "+currentQuestion.getCorrect_ans_d() +"  currentAnswer.getCorrect_ans_d(): "+currentAnswer.getCorrect_ans_d());
            Log.d("tagResult", "currentQuestion.getCorrect_ans_e(): "+currentQuestion.getCorrect_ans_e() +"  currentAnswer.getCorrect_ans_e(): "+currentAnswer.getCorrect_ans_e());

            if (currentQuestion.getQuestionType().equalsIgnoreCase("1")) {

                if (currentQuestion.getCorrect_ans_a().equalsIgnoreCase(currentAnswer.getCorrect_ans_a())) {
                    totalCorrectMark = totalCorrectMark + 0.2;
                } else {
                    if (currentAnswer.getCorrect_ans_a().equalsIgnoreCase("")){

                    }else {
                        totalNegativeMark = totalNegativeMark + 0.1;
                    }
                }

                if (currentQuestion.getCorrect_ans_b().equalsIgnoreCase(currentAnswer.getCorrect_ans_b())) {
                    totalCorrectMark = totalCorrectMark + 0.2;
                } else {
                    if (currentAnswer.getCorrect_ans_b().equalsIgnoreCase("")){

                    }else {
                        totalNegativeMark = totalNegativeMark + 0.1;
                    }
                }

                if (currentQuestion.getCorrect_ans_c().equalsIgnoreCase(currentAnswer.getCorrect_ans_c())) {
                    totalCorrectMark = totalCorrectMark + 0.2;
                } else {
                    if (currentAnswer.getCorrect_ans_c().equalsIgnoreCase("")){

                    }else {
                        totalNegativeMark = totalNegativeMark + 0.1;
                    }
                }

                if (currentQuestion.getCorrect_ans_d().equalsIgnoreCase(currentAnswer.getCorrect_ans_d())) {
                    totalCorrectMark = totalCorrectMark + 0.2;
                } else {
                    if (currentAnswer.getCorrect_ans_d().equalsIgnoreCase("")){

                    }else {
                        totalNegativeMark = totalNegativeMark + 0.1;
                    }
                }

                if (currentQuestion.getCorrect_ans_e().equalsIgnoreCase(currentAnswer.getCorrect_ans_e())) {
                    totalCorrectMark = totalCorrectMark + 0.2;
                } else {
                    if (currentAnswer.getCorrect_ans_e().equalsIgnoreCase("")){

                    }else {
                        totalNegativeMark = totalNegativeMark + 0.1;
                    }
                }

            } else if (currentQuestion.getQuestionType().equalsIgnoreCase("2")) {

                if (currentQuestion.getCorrect_ans_sba().equalsIgnoreCase(currentAnswer.getCorrect_ans_sba())) {
                    totalCorrectMark = totalCorrectMark + 1.0;
                } else {
                    if (currentAnswer.getCorrect_ans_sba().equalsIgnoreCase("")){

                    }else {
                        totalNegativeMark = totalNegativeMark + 0.5;
                    }
                }

            }
        }
    }

    private void initView() {
        tv_correct_mark = findViewById(R.id.tv_correct_mark);
        tv_negative_mark = findViewById(R.id.tv_negative_mark);
        tv_obtained_mark = findViewById(R.id.tv_obtained_mark);

        linear_result_ans = findViewById(R.id.linear_result_ans);

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