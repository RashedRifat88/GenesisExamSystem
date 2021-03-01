package com.egsystem.genesisexamsystem.exam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.airbnb.lottie.LottieAnimationView;
import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.data.database.ExamDbHelper;
import com.egsystem.genesisexamsystem.data.shared_pref.SharedData;
import com.egsystem.genesisexamsystem.exam.adapter.ExamAdapter;
import com.egsystem.genesisexamsystem.model.DoctorExamListModel;
import com.egsystem.genesisexamsystem.model.ErrorResponseGeneric;
import com.egsystem.genesisexamsystem.retrofit.Api;
import com.egsystem.genesisexamsystem.retrofit.RetrofitApiClient;
import com.google.gson.Gson;


import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ExamActivity extends AppCompatActivity {

    ExamDbHelper dbHelper;

    private List<String> topDoctorList;
    private List<String> topDoctorInfo2List;
    private List<String> divisionWiseDoctorList;
    private List<String> divisionWiseDoctorList2;
    private List<String> doctorList3;
    private static ExamAdapter examAdapter;
    private RecyclerView recyclerView;
    private LottieAnimationView animationView;

    String course_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        course_id = getIntent().getStringExtra("course_id");

        initToolbar();
        initComponents();

        loadListData();
        loadRecyclerView();


        dbHelper = ExamDbHelper.getInstance(this);
        deleteAnswerData();

        examsApiCall(course_id);
    }

    private void deleteAnswerData() {
        dbHelper.deleteAllAnswers();
    }



    private void initToolbar() {
        ActionBar ab = ((AppCompatActivity) this).getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        String title = this.getResources().getString(R.string.title_exam);
        ab.setTitle(title);

    }

    private void loadListData() {
        topDoctorList = Arrays.asList(getResources().getStringArray(R.array.top_doctor_title_array));
        topDoctorInfo2List = Arrays.asList(getResources().getStringArray(R.array.top_doctor_deg_array));
        divisionWiseDoctorList = Arrays.asList(getResources().getStringArray(R.array.division_wise_doctor_array));
        divisionWiseDoctorList2 = Arrays.asList(getResources().getStringArray(R.array.division_wise_doctor_image_array));
        doctorList3 = Arrays.asList(getResources().getStringArray(R.array.division_wise_doctor_title_code_array));

    }

    private void initComponents() {
        recyclerView = findViewById(R.id.recyclerView);
        animationView = findViewById(R.id.animationView);
    }



    private void loadRecyclerView() {
        examAdapter = new ExamAdapter(this);
        recyclerView.setAdapter(examAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
//        examAdapter.setData2(divisionWiseDoctorList, divisionWiseDoctorList2, doctorList3);
        examAdapter.notifyDataSetChanged();
    }




    @SuppressLint("CheckResult")
    public void examsApiCall(String course_id1) {

        String token = SharedData.getTOKEN(this);
        String authorization = "Bearer" + " " + token;
        String accept = "application/json";
        String url = Api.BASE_URL + Api.doctor_exam_list + course_id1;

        Log.d("tag11111", "url : "+ url);

        RetrofitApiClient.getApiInterface().getExamList(url, authorization, accept)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            Log.d("tag11111", " response.code(): " + response.code());
//                            progressDialog.dismiss();

                            if (response.code() == 403) {
                                Log.d("tag11111", " 403 is defined ");
                            }


                            if (response.isSuccessful()) {

                                animationView.setVisibility(View.GONE);

                                DoctorExamListModel examModel = response.body();
                                Log.d("tag11111", " examModel: " + examModel);
//                                String loginMessage = examModel.get;
//                                Log.d("tag11111", " loginMessage: " + loginMessage);

                                response.body();
                                Log.d("tag11111", " response.body(): " + response.body());

                                List<DoctorExamListModel.Exam> examList = examModel.getExams();

                                examAdapter.setData(examList);
                                examAdapter.notifyDataSetChanged();
                                Log.d("tag11111", " examList: " + examList);

                            } else {
//                                response.errorBody().string(); // do something with that
                                Gson gson = new Gson();
                                ErrorResponseGeneric errorResponse = gson.fromJson(
                                        response.errorBody().string(),
                                        ErrorResponseGeneric.class);

//                                List<String> errorList = errorResponse.getValidationError();
                                ErrorResponseGeneric.Errors error = errorResponse.getErrors();
                                List<String> bmdc_error_list = error.getBmdcNo();
                                List<String> passwd_error_list = error.getPassword();

//                                String errorNames = "1. ";
//                                for (int i = 0; i < errorList.size(); i++) {
//                                    int j = i + 2;
//                                    errorNames = errorNames
//                                            + errorList.get(i);
//                                    if (i != errorList.size() - 1) {
//                                        errorNames = errorNames + "\n\n" + j + ". ";
//                                    }
//
//                                }

                                String errorNames = "";
                                for (int i = 0; i < bmdc_error_list.size(); i++) {
                                    int j = i + 2;
                                    errorNames = errorNames
                                            + bmdc_error_list.get(i);
                                    if (i != bmdc_error_list.size() - 1) {
                                        errorNames = errorNames + "\n\n" + j + ". ";
                                    }

                                }

//                                String bmdc_error = bmdc_error_list;
//                                String passwd_error = ;
                                String totalErrors = passwd_error_list + "\n" + passwd_error_list;

                                Log.d("tag11111", " totalErrors: " + totalErrors);


                                new MaterialDialog.Builder(ExamActivity.this)
                                        .title("Login Status")
                                        .content(totalErrors)
                                        .positiveText("")
                                        .negativeText("Ok")
                                        .show();


                            }

                        },
                        error -> {

                            Log.d("tag11111", " error: " + error.getMessage());
                        },
                        () -> {
                            Log.d("tag11111", " response.code(): " );
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
                    onBackPressed();
                }
//                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}