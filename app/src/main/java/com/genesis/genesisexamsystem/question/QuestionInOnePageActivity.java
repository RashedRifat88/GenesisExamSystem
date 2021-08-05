package com.genesis.genesisexamsystem.question;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.genesis.genesisexamsystem.R;
import com.genesis.genesisexamsystem.Result.ResultActivity;
import com.genesis.genesisexamsystem.data.database.ExamDbHelper;
import com.genesis.genesisexamsystem.data.model.Answer;
import com.genesis.genesisexamsystem.data.model.Question;
import com.genesis.genesisexamsystem.data.shared_pref.SharedData;
import com.genesis.genesisexamsystem.model.SubmitAnswerModel;
import com.genesis.genesisexamsystem.question.adapter.QuestionInOnePageAdapter2;
import com.genesis.genesisexamsystem.question.adapter.UnAnsweredAnsAdapter;
import com.genesis.genesisexamsystem.retrofit.Api;
import com.genesis.genesisexamsystem.retrofit.RetrofitApiClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
    private ImageView iv_back;
    private static TextView tv_number_of_unanswered_ques;
    private LinearLayout linear_unanswered_ques;
    static AlertDialog alertDialogQuestion;
    static RecyclerView.LayoutManager mLayoutManager;
    static RecyclerView.SmoothScroller smoothScroller;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private TextView text_view_countdown;
    private ColorStateList textColorDefaultCd;
    //    private static final long COUNTDOWN_IN_MILLIS = 400000;
    private static long COUNTDOWN_IN_MILLIS = 0;

    JSONObject finalobject = null;
    private ArrayList<Answer> answerList;
    String doctor_course_id, exam_id;

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

        String examDuration = SharedData.getEXAM_DURATION(this);
        COUNTDOWN_IN_MILLIS = Long.parseLong(examDuration) * 1000;
        Log.d("tagResponse3333", " COUNTDOWN_IN_MILLIS: " + COUNTDOWN_IN_MILLIS);

        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();

        doctor_course_id = getIntent().getStringExtra("doctor_course_id");
        exam_id = getIntent().getStringExtra("exam_id");

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


                if (unansweredAnswerList != null) {
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
                } else {
                    Toast.makeText(QuestionInOnePageActivity.this, "All the questions are unanswered!", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public static void loadAnswerList() {
        unansweredAnswerList = dbHelper.getUnansweredAnswers();

        int number_of_unanswered_ans = unansweredAnswerList.size();
        tv_number_of_unanswered_ques.setText(String.valueOf(number_of_unanswered_ans));
    }


    public static void scrollSpecificPosition(int position, int size) {

        final int[] positionM = {position - 1};

        alertDialogQuestion.dismiss();

        recyclerView.smoothScrollToPosition(position - 1);

//        recyclerView.smoothScrollToPosition(position-1);
//        recyclerView.requestFocus();
        Log.d("tagResponse3333", " position-1: " + (position - 1));

//        mLayoutManager.scrollToPosition(position-1);
//        recyclerView.requestFocus();

//        mLayoutManager.scrollToPositionWithOffset(index, top);
    }


    public static void scrollNextPosition(int position) {

        recyclerView.smoothScrollToPosition(position);
        Log.d("tagResponse3333", " position-1: " + (position - 1));
    }


    private void loadListData() {

    }

    private void initComponents() {

        recyclerView = findViewById(R.id.recyclerView);
        text_view_countdown = findViewById(R.id.text_view_countdown);
        textColorDefaultCd = text_view_countdown.getTextColors();
        tv_submit = findViewById(R.id.tv_submit);
        iv_back = findViewById(R.id.iv_back);


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(QuestionInOnePageActivity.this, ResultActivity.class);
//                startActivity(intent);
//                finish();


//                try {
//                    makeJsonData();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

//                submitAnswerApiCall(doctor_course_id, exam_id);


                if (unansweredAnswerList != null) {

                    if (unansweredAnswerList.size() > 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(QuestionInOnePageActivity.this);
                        LayoutInflater inflater = (LayoutInflater) QuestionInOnePageActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View dialogView = inflater.inflate(R.layout.exam_end_confirmation, null);
                        builder.setView(dialogView);

                        TextView tv_msg = dialogView.findViewById(R.id.tv_msg);
                        TextView tv_no = dialogView.findViewById(R.id.tv_no);
                        TextView tv_yes = dialogView.findViewById(R.id.tv_yes);

                        tv_msg.setText(unansweredAnswerList.size() + " questions remain unanswered! Do you want to finish the exam");
                        tv_yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                submitAnswerApiCall(doctor_course_id, exam_id);
                            }
                        });
                        tv_no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialogQuestion.dismiss();
                            }
                        });


                        alertDialogQuestion = builder.create();
                        alertDialogQuestion.show();
                        alertDialogQuestion.setCancelable(true);
                        alertDialogQuestion.setCanceledOnTouchOutside(true);
                    }


                } else {
                    Toast.makeText(QuestionInOnePageActivity.this, "All the questions are unanswered!", Toast.LENGTH_LONG).show();

                    AlertDialog.Builder builder = new AlertDialog.Builder(QuestionInOnePageActivity.this);
                    LayoutInflater inflater = (LayoutInflater) QuestionInOnePageActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View dialogView = inflater.inflate(R.layout.exam_end_confirmation, null);
                    builder.setView(dialogView);

                    TextView tv_msg = dialogView.findViewById(R.id.tv_msg);
                    TextView tv_no = dialogView.findViewById(R.id.tv_no);
                    TextView tv_yes = dialogView.findViewById(R.id.tv_yes);

                    tv_msg.setText("All the questions are unanswered! Do you want to finish the exam");
                    tv_yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            submitAnswerApiCall(doctor_course_id, exam_id);
                        }
                    });
                    tv_no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialogQuestion.dismiss();
                        }
                    });

                    alertDialogQuestion = builder.create();
                    alertDialogQuestion.show();
                    alertDialogQuestion.setCancelable(true);
                    alertDialogQuestion.setCanceledOnTouchOutside(true);
                }


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


    public JSONObject makeJsonData()
            throws JSONException {

        answerList = dbHelper.getAllGivenAnswers();

        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < answerList.size(); i++) {

            JSONObject obj1 = new JSONObject();
            obj1.put("question_id", answerList.get(i).getQuestionId());
            obj1.put("exam_question_id", answerList.get(i).getExam_question_id());

            if (answerList.get(i).getQuestionType().equalsIgnoreCase("1")) {

                String multipleChoiceList = "";

                if (answerList.get(i).getCorrect_ans_a().isEmpty()) {
                    multipleChoiceList = multipleChoiceList + ".";
                } else {
                    multipleChoiceList = multipleChoiceList + answerList.get(i).getCorrect_ans_a();
                }

                if (answerList.get(i).getCorrect_ans_b().isEmpty()) {
                    multipleChoiceList = multipleChoiceList + ".";
                } else {
                    multipleChoiceList = multipleChoiceList + answerList.get(i).getCorrect_ans_b();
                }

                if (answerList.get(i).getCorrect_ans_c().isEmpty()) {
                    multipleChoiceList = multipleChoiceList + ".";
                } else {
                    multipleChoiceList = multipleChoiceList + answerList.get(i).getCorrect_ans_c();
                }

                if (answerList.get(i).getCorrect_ans_d().isEmpty()) {
                    multipleChoiceList = multipleChoiceList + ".";
                } else {
                    multipleChoiceList = multipleChoiceList + answerList.get(i).getCorrect_ans_d();
                }


                if (answerList.get(i).getCorrect_ans_e().isEmpty()) {
                    multipleChoiceList = multipleChoiceList + ".";
                } else {
                    multipleChoiceList = multipleChoiceList + answerList.get(i).getCorrect_ans_e();
                }


                obj1.put("answer", multipleChoiceList);

            } else if (answerList.get(i).getQuestionType().equalsIgnoreCase("2")) {
                obj1.put("answer", answerList.get(i).getCorrect_ans_sba());
            }


            obj1.put("question_type", answerList.get(i).getQuestionType());

            jsonArray.put(obj1);

        }


        JSONObject finalobject = new JSONObject();
        finalobject.put("question_answers", jsonArray);
//        finalobject.put("collectionModewise", obj2);

        Log.d("tag11111", " finalobject: " + finalobject);
        return finalobject;
    }


    @SuppressLint("CheckResult")
    public void submitAnswerApiCall(String doctor_course_id, String exam_id) {

        String token = SharedData.getTOKEN(this);
        String authorization = "Bearer" + " " + token;
        String accept = "application/json";
        String url = Api.BASE_URL + Api.submit_result + doctor_course_id + "/" + exam_id;

        JsonObject jsonObject = new JsonObject();

        try {
            finalobject = makeJsonData();
            JsonParser jsonParser = new JsonParser();
            jsonObject = (JsonObject) jsonParser.parse(finalobject.toString());
            Log.d("tag11111", "jsonObject :  " + jsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.d("tag11111", "url exam: " + url);

        RetrofitApiClient.getApiInterface().submitGivenAnswer(url, authorization, accept, jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            Log.d("tag11111", " response.code(): " + response.code());
//                            progressDialog.dismiss();

                            if (response.isSuccessful()) {

                                SubmitAnswerModel examModel = response.body();
                                Log.d("tag11111", " examModel: " + examModel);

                                response.body();
                                Log.d("tag11111", " response.body(): " + response.body());
//
//                                List<SubmitAnswerModel.Question> quesList = examModel.getQuestions();

                                Intent intent = new Intent(QuestionInOnePageActivity.this, ResultActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
////                                response.errorBody().string(); // do something with that
//                                Gson gson = new Gson();
//                                ErrorResponseGeneric errorResponse = gson.fromJson(
//                                        response.errorBody().string(),
//                                        ErrorResponseGeneric.class);
//
////                                List<String> errorList = errorResponse.getValidationError();
//                                ErrorResponseGeneric.Errors error = errorResponse.getErrors();
//                                List<String> bmdc_error_list = error.getBmdcNo();
//                                List<String> passwd_error_list = error.getPassword();
//
////                                String errorNames = "1. ";
////                                for (int i = 0; i < errorList.size(); i++) {
////                                    int j = i + 2;
////                                    errorNames = errorNames
////                                            + errorList.get(i);
////                                    if (i != errorList.size() - 1) {
////                                        errorNames = errorNames + "\n\n" + j + ". ";
////                                    }
////
////                                }
//
//                                String errorNames = "";
//                                for (int i = 0; i < bmdc_error_list.size(); i++) {
//                                    int j = i + 2;
//                                    errorNames = errorNames
//                                            + bmdc_error_list.get(i);
//                                    if (i != bmdc_error_list.size() - 1) {
//                                        errorNames = errorNames + "\n\n" + j + ". ";
//                                    }
//
//                                }
//
//                                String totalErrors = passwd_error_list + "\n" + passwd_error_list;
//
//                                Log.d("tag11111", " totalErrors: " + totalErrors);
//
//
//                                new MaterialDialog.Builder(ExamActivity.this)
//                                        .title("Login Status")
//                                        .content(totalErrors)
//                                        .positiveText("")
//                                        .negativeText("Ok")
//                                        .show();


                            }

                        },
                        error -> {

                            Log.d("tag11111", " error: " + error.getMessage());
                        },
                        () -> {
                            Log.d("tag11111", " response.code(): ");
                        }

                );

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