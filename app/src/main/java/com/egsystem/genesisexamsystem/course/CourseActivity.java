package com.egsystem.genesisexamsystem.course;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.airbnb.lottie.LottieAnimationView;
import com.egsystem.genesisexamsystem.HomeActivity;
import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.course.adapter.CourseAdapter;
import com.egsystem.genesisexamsystem.data.database.ExamDbHelper;
import com.egsystem.genesisexamsystem.data.shared_pref.SharedData;
import com.egsystem.genesisexamsystem.model.ErrorResponseGeneric;
import com.egsystem.genesisexamsystem.model.DoctorCourseModel;
import com.egsystem.genesisexamsystem.retrofit.RetrofitApiClient;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CourseActivity extends AppCompatActivity {


    ExamDbHelper dbHelper;

    private List<String> topDoctorList;
    private List<String> topDoctorInfo2List;
    private List<String> divisionWiseDoctorList;
    private List<String> divisionWiseDoctorList2;
    private List<String> doctorList3;
    private static CourseAdapter courseAdapterAdapter;
    private RecyclerView  recyclerView;
    private LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        dbHelper = ExamDbHelper.getInstance(this);

        initToolbar();
        initComponents();

        loadListData();
        loadRecyclerView();

        deleteAnswerData();

        coursesApiCall();

    }

    private void deleteAnswerData() {
        dbHelper.deleteAllAnswers();
    }


    private void initToolbar() {
        ActionBar ab = ((AppCompatActivity) this).getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        String title = this.getResources().getString(R.string.title_course);
        ab.setTitle(title);

//        SpannableString spannableString = new SpannableString(title);
//        Typeface typeface = Typeface.createFromAsset(this.getAssets(),"fonts/hindsiliguri-regular.ttf");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            spannableString.setSpan(
//                    new TypefaceSpan(typeface),
//                    0, spannableString.length(),
//                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        }
//
//        ab.setTitle(spannableString);

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
        courseAdapterAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapterAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
//        courseAdapterAdapter.setData2(divisionWiseDoctorList, divisionWiseDoctorList2, doctorList3);
        courseAdapterAdapter.notifyDataSetChanged();
    }





    @SuppressLint("CheckResult")
    public void coursesApiCall() {

        String token = SharedData.getTOKEN(this);
        String authorization = "Bearer" + " " + token;
//        String authorization = "Bearer" + " " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxMCIsImp0aSI6ImQ2OGZlN2M3YjNkZWRmOTM5OTkxOGZlNjc1MmE5ZTdjZGQxNGE3MmNmZjJlYWMzZTAyY2ZkNjc0YmJjZDU1YTAyOTlkMDYxNWJmZGM3NzJlIiwiaWF0IjoxNjEyOTQ1NjI1LCJuYmYiOjE2MTI5NDU2MjUsImV4cCI6MTY0NDQ4MTYyNSwic3ViIjoiMTU5MzUiLCJzY29wZXMiOltdfQ.RGtfgHd6HtyNszzlIcae-V-Tnbw4k52gl9xXVqLaRE7UeTly49R51mGd__BQC61AmKBZThyLSzbZjz3qt8xJxZKvYYpeuALLDu14c2f6ib5HTBpgtdZ6vNKY4oc21GhcYWbfPffR7RD00fKUzFEnirTSCJMJsJAEM8gjbzCQMYGQUKpgwimWPGre2pZkYshZPj1zWzMNG33kw2IiKktW2OxvWvRfiJwogJLuGiLTxa74MK8Aqa0wijXhUhnTdXJ0XwhBuZ0Fwf9KqlCC9Xgp2L4SgeFgkWJVhNWoZrCyzvQY9_IkaUa7Zg_t-F6OIaRFjhmul6H_RR12i_1H5Z9M5PLWi_Lsd_GZjcDBUqryqe85NzsmfP9V3dzcqfymmkcTlTNOI0C2Y6iA3GcbMrX0m-TyovQcqrHQcoE20cfGphC1pD6Pil9ahh2lnY8IT9ovVxdcuvZfaKYVe0V6L3wK8d_lEfQAXUkQJahM7ahPf2Zp_e2-EYgxczsmkzepBpEKJ3eazZJJiJboWYNmTDn5aAMrBPw0pMnkIa7dQRakd8a7HA4zJfopRZd8Rx6gpWd2e_zTulOlyi8FXugtwDPLvC9o0VuJKjvajAK1L8XW8rwHeLI2heFF01R4eIx9_a2BU4DdrGV7L6lO5SHboPSzSmtOEOiMYLN8nSSJ3XE0EKs";
        String accept = "application/json";

        RetrofitApiClient.getApiInterface().getDoctorCourses(authorization, accept)
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

                                DoctorCourseModel courseodel = response.body();
                                Log.d("tag11111", " courseodel: " + courseodel);
//                                String loginMessage = courseodel.get;
//                                Log.d("tag11111", " loginMessage: " + loginMessage);

                                response.body();
                                Log.d("tag11111", " response.body(): " + response.body());

                                List<DoctorCourseModel.DoctorCourse> courseList = courseodel.getDoctorCourses();

                                courseAdapterAdapter.setData(courseList);
                                courseAdapterAdapter.notifyDataSetChanged();
                                Log.d("tag11111", " courseList: " + courseList);

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


                                new MaterialDialog.Builder(CourseActivity.this)
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