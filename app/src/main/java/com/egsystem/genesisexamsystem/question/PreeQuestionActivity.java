package com.egsystem.genesisexamsystem.question;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.data.database.ExamDbHelper;

public class PreeQuestionActivity extends AppCompatActivity {


    CardView cardView1, cardView2;
    ExamDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_question);

        dbHelper = ExamDbHelper.getInstance(this);
        initComponent();
        initToolbar();


    }


    private void initToolbar() {
        ActionBar ab = ((AppCompatActivity) this).getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        String title = this.getResources().getString(R.string.title_question_option);
        ab.setTitle(title);

    }


    private void initComponent() {

        cardView1 = findViewById(R.id.cardView1);
        cardView2 = findViewById(R.id.cardView2);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreeQuestionActivity.this, QuestionInOnePageActivity.class);
                intent.putExtra("from_where", "MAIN");
                startActivity(intent);
            }
        });


        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreeQuestionActivity.this, SingleQuestionActivity.class);
                intent.putExtra("from_where", "MAIN");
                startActivity(intent);
            }
        });


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