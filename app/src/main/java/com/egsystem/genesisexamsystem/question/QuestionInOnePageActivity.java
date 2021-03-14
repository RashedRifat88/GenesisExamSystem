package com.egsystem.genesisexamsystem.question;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.Result.ResultActivity;
import com.egsystem.genesisexamsystem.data.database.ExamDbHelper;
import com.egsystem.genesisexamsystem.data.model.Answer;
import com.egsystem.genesisexamsystem.data.model.Question;
import com.egsystem.genesisexamsystem.question.adapter.QuestionInOnePageAdapter2;
import com.egsystem.genesisexamsystem.question.adapter.UnAnsweredAnsAdapter;
import com.egsystem.genesisexamsystem.utils.LinearLayoutManagerWithSmoothScroller;

import java.util.ArrayList;
import java.util.Locale;

public class QuestionInOnePageActivity extends AppCompatActivity {

    static ExamDbHelper dbHelper;
    private static ArrayList<Answer> unansweredAnswerList;
    private ArrayList<Question> questionList;
    private Answer currentAnswer;
    private Question currentQuestion;
    private static QuestionInOnePageAdapter2 questionInOnePageAdapter2;
    private static UnAnsweredAnsAdapter unAnsweredAnsAdapter;
    private static RecyclerView recyclerView;
    private TextView tv_submit;
    private static TextView tv_number_of_unanswered_ques;
    private LinearLayout linear_unanswered_ques;
    static AlertDialog alertDialogQuestion;
    static RecyclerView.LayoutManager  mLayoutManager;
    static RecyclerView.SmoothScroller smoothScroller;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private TextView text_view_countdown;
    private ColorStateList textColorDefaultCd;
    private static final long COUNTDOWN_IN_MILLIS = 400000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_in_one_page);

        dbHelper = ExamDbHelper.getInstance(this);
//        unansweredAnswerList = dbHelper.getAllGivenAnswers();
        questionList = dbHelper.getAllQuestions();

        initToolbar();
        initComponents();

        loadListData();
        loadRecyclerView();

        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
    }


    private void initToolbar() {

        View decor = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite, this.getTheme()));

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }


        linear_unanswered_ques = findViewById(R.id.linear_unanswered_ques);
        tv_number_of_unanswered_ques = findViewById(R.id.tv_number_of_unanswered_ques);

//        loadAnswerList();
        int number_of_ques = questionList.size();
        tv_number_of_unanswered_ques.setText(String.valueOf(number_of_ques));

        linear_unanswered_ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wrapInScrollView = true;

                AlertDialog.Builder builder = new AlertDialog.Builder(QuestionInOnePageActivity.this);
//                LayoutInflater inflater = ((Activity)QuestionInOnePageActivity.this)).getLayoutInflater();
                LayoutInflater inflater = (LayoutInflater) QuestionInOnePageActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.unanswered_ans_list, null);
                builder.setCancelable(false);
                builder.setView(dialogView);

//                TextView tv_cancel = dialogView.findViewById(R.id.tv_cancel);
//                TextView tv_ok = dialogView.findViewById(R.id.tv_ok);

                RecyclerView recyclerView_unanswered = dialogView.findViewById(R.id.recyclerView_unanswered);

                unAnsweredAnsAdapter = new UnAnsweredAnsAdapter(QuestionInOnePageActivity.this);
                recyclerView_unanswered.setAdapter(unAnsweredAnsAdapter);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(QuestionInOnePageActivity.this, 3);
                recyclerView_unanswered.setLayoutManager(mLayoutManager);
                unAnsweredAnsAdapter.setData2(unansweredAnswerList);
                unAnsweredAnsAdapter.notifyDataSetChanged();

                alertDialogQuestion = builder.create();
                alertDialogQuestion.show();
                alertDialogQuestion.setCancelable(true);
                alertDialogQuestion.setCanceledOnTouchOutside(true);
            }
        });


    }

    public static void loadAnswerList() {
        unansweredAnswerList = dbHelper.getUnansweredAnswers();

        int number_of_unanswered_ans = unansweredAnswerList.size();
        tv_number_of_unanswered_ques.setText(String.valueOf(number_of_unanswered_ans));
    }


    public static void scrollSpecificPosition(int position, int size) {

        final int[] positionM = {position-1};

        alertDialogQuestion.dismiss();

        recyclerView.smoothScrollToPosition(position-1);

//        recyclerView.smoothScrollToPosition(position-1);
//        recyclerView.requestFocus();
        Log.d("tagResponse3333", " position-1: " + (position-1));

//        mLayoutManager.scrollToPosition(position-1);
//        recyclerView.requestFocus();

//        mLayoutManager.scrollToPositionWithOffset(index, top);
    }


    public static void scrollNextPosition(int position) {

        recyclerView.smoothScrollToPosition(position);
        Log.d("tagResponse3333", " position-1: " + (position-1));
    }



    private void loadListData() {

    }

    private void initComponents() {

        recyclerView = findViewById(R.id.recyclerView);
        text_view_countdown = findViewById(R.id.text_view_countdown);
        textColorDefaultCd = text_view_countdown.getTextColors();
        tv_submit = findViewById(R.id.tv_submit);

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionInOnePageActivity.this, ResultActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void loadRecyclerView() {
        questionInOnePageAdapter2 = new QuestionInOnePageAdapter2(this);
        recyclerView.setAdapter(questionInOnePageAdapter2);
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        mLayoutManager = new GridLayoutManager(this, 1);
//        mLayoutManager = new SnappingLinearLayoutManager(this, 1, false);
//        recyclerView.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(this));
        recyclerView.setLayoutManager(mLayoutManager);
        questionInOnePageAdapter2.setData2(questionList);
        questionInOnePageAdapter2.notifyDataSetChanged();

//        recyclerView.smoothScrollToPosition(3);


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
        text_view_countdown.setText(timeFormatted);
        if (timeLeftInMillis < 10000) {
            text_view_countdown.setTextColor(Color.RED);
        } else {
            text_view_countdown.setTextColor(textColorDefaultCd);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    dbHelper.deleteAllAnswers();
                    onBackPressed();
                }
//                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}