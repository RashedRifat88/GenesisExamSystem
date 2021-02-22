package com.egsystem.genesisexamsystem.question;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.Result.ResultActivity;
import com.egsystem.genesisexamsystem.answer.CorrectAnswerAdapter;
import com.egsystem.genesisexamsystem.data.database.ExamDbHelper;
import com.egsystem.genesisexamsystem.data.model.Answer;
import com.egsystem.genesisexamsystem.data.model.Question;
import com.egsystem.genesisexamsystem.question.adapter.QuestionInOnePageAdapter;
import com.egsystem.genesisexamsystem.question.adapter.QuestionInOnePageAdapter2;

import java.util.ArrayList;

public class QuestionInOnePageActivity extends AppCompatActivity {

    ExamDbHelper dbHelper;
    private ArrayList<Answer> answerList;
    private ArrayList<Question> questionList;
    private Answer currentAnswer;
    private Question currentQuestion;
    private static QuestionInOnePageAdapter2 questionInOnePageAdapter2;
    private RecyclerView recyclerView;
    private TextView tv_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_in_one_page);

        dbHelper = ExamDbHelper.getInstance(this);
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
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        questionInOnePageAdapter2.setData2(questionList);
        questionInOnePageAdapter2.notifyDataSetChanged();
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