package com.egsystem.genesisexamsystem.exam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.data.database.ExamDbHelper;
import com.egsystem.genesisexamsystem.exam.adapter.ExamAdapter;


import java.util.Arrays;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

    ExamDbHelper dbHelper;

    private List<String> topDoctorList;
    private List<String> topDoctorInfo2List;
    private List<String> divisionWiseDoctorList;
    private List<String> divisionWiseDoctorList2;
    private List<String> doctorList3;
    private static ExamAdapter examAdapterAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        initToolbar();
        initComponents();

        loadListData();
        loadRecyclerView();


        dbHelper = ExamDbHelper.getInstance(this);
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
        String title = this.getResources().getString(R.string.title_exam);
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
        examAdapterAdapter = new ExamAdapter(this);
        recyclerView.setAdapter(examAdapterAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        examAdapterAdapter.setData2(divisionWiseDoctorList, divisionWiseDoctorList2, doctorList3);
        examAdapterAdapter.notifyDataSetChanged();
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