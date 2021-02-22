package com.egsystem.genesisexamsystem.course;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.course.adapter.CourseAdapter;
import com.egsystem.genesisexamsystem.data.database.ExamDbHelper;

import java.util.Arrays;
import java.util.List;

public class CourseActivity extends AppCompatActivity {


    ExamDbHelper dbHelper;

    private List<String> topDoctorList;
    private List<String> topDoctorInfo2List;
    private List<String> divisionWiseDoctorList;
    private List<String> divisionWiseDoctorList2;
    private List<String> doctorList3;
    private static CourseAdapter courseAdapterAdapter;
    private RecyclerView  recyclerView;

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
    }



    private void loadRecyclerView() {
        courseAdapterAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapterAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        courseAdapterAdapter.setData2(divisionWiseDoctorList, divisionWiseDoctorList2, doctorList3);
        courseAdapterAdapter.notifyDataSetChanged();
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