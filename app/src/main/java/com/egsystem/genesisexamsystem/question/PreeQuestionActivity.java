package com.egsystem.genesisexamsystem.question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
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
                Intent intent = new Intent(PreeQuestionActivity.this, QuestionActivity.class);
                intent.putExtra("from_where", "MAIN");
                startActivity(intent);
            }
        });


    }

}