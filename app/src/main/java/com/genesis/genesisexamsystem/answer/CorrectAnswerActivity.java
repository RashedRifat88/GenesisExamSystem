package com.genesis.genesisexamsystem.answer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.genesis.genesisexamsystem.R;
import com.genesis.genesisexamsystem.data.database.ExamDbHelper;
import com.genesis.genesisexamsystem.data.model.Answer;
import com.genesis.genesisexamsystem.data.model.Question;

import java.util.ArrayList;

public class CorrectAnswerActivity extends AppCompatActivity {

    private ArrayList<Answer> answerList;
    private ArrayList<Question> questionList;
    private Answer currentAnswer;
    private Question currentQuestion;
    private static CorrectAnswerAdapter correctAnswerAdapterAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_answer);

        ExamDbHelper dbHelper = ExamDbHelper.getInstance(this);
        answerList = dbHelper.getAllGivenAnswers();
        questionList = dbHelper.getAllQuestions();

        initToolbar();
        initComponents();

        loadListData();
        loadRecyclerView();
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

    }

    private void initComponents() {
        recyclerView = findViewById(R.id.recyclerView);
    }


    private void loadRecyclerView() {
        correctAnswerAdapterAdapter = new CorrectAnswerAdapter(this);
        recyclerView.setAdapter(correctAnswerAdapterAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        correctAnswerAdapterAdapter.setData2(questionList, answerList);
        correctAnswerAdapterAdapter.notifyDataSetChanged();
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