package com.egsystem.genesisexamsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.egsystem.genesisexamsystem.course.CourseActivity;
import com.egsystem.genesisexamsystem.data.database.ExamDbHelper;
import com.egsystem.genesisexamsystem.data.model.Question;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HomeActivity extends AppCompatActivity {


    ExamDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.noticeFragment, R.id.profileFragment, R.id.moreFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



        View decor = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite, this.getTheme()));

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }



        dbHelper = ExamDbHelper.getInstance(this);
        deleteQuestionData();
        deleteAnswerData();
        loadQuestionData();

        initComponent();

    }


    private void initComponent() {


//        cardView1 = findViewById(R.id.cardView1);
//
//        cardView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, CourseActivity.class);
//                intent.putExtra("from_where", "MAIN");
//                startActivity(intent);
//            }
//        });

    }


    private void deleteQuestionData() {
        dbHelper.deleteAllQuestions();
    }

    private void deleteAnswerData() {
        dbHelper.deleteAllAnswers();
    }



    private void loadQuestionData() {

        JSONArray questions = loadJSONArray_question(this);

        try {
            for (int i = 0; i < questions.length(); i++) {
                JSONObject question = questions.getJSONObject(i);

                String question_id = question.getString("question_id");
                String question_type = question.getString("question_type");
                String question_title = question.getString("question_title");
                String correct_ans_sba = question.getString("correct_ans_sba");

                JSONArray optionsArray = question.getJSONArray("question_option");
//                for (int j = 0; j < optionsArray.length(); j++) {
//                    String option = optionsArray.getString(j);
//                }

//                String option1 = optionsArray.getString(0);
//                String option2 = optionsArray.getString(1);
//                String option3 = optionsArray.getString(2);
//                String option4 = optionsArray.getString(3);
//                String option5 = optionsArray.getString(4);

                JSONObject optionA = optionsArray.getJSONObject(0);
                JSONObject optionB = optionsArray.getJSONObject(1);
                JSONObject optionC = optionsArray.getJSONObject(2);
                JSONObject optionD = optionsArray.getJSONObject(3);
                JSONObject optionE = optionsArray.getJSONObject(4);

                String option1 = optionA.getString("option_title");
                String option2 = optionB.getString("option_title");
                String option3 = optionC.getString("option_title");
                String option4 = optionD.getString("option_title");
                String option5 = optionE.getString("option_title");

                String correct_ans_a = optionA.getString("correct_ans");
                String correct_ans_b = optionB.getString("correct_ans");
                String correct_ans_c = optionC.getString("correct_ans");
                String correct_ans_d = optionD.getString("correct_ans");
                String correct_ans_e = optionE.getString("correct_ans");

                if (correct_ans_sba.isEmpty()) {
                    correct_ans_sba = "not sba";
                }
                if (correct_ans_a.isEmpty()) {
                    correct_ans_a = "not mcq";
                }
                if (correct_ans_b.isEmpty()) {
                    correct_ans_b = "not mcq";
                }
                if (correct_ans_c.isEmpty()) {
                    correct_ans_c = "not mcq";
                }
                if (correct_ans_d.isEmpty()) {
                    correct_ans_d = "not mcq";
                }
                if (correct_ans_e.isEmpty()) {
                    correct_ans_e = "not mcq";
                }


                Question q1 = new Question(question_id, question_type, question_title, option1, option2, option3, option4, option5,
                        correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e);
                dbHelper.addQuestion(q1);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public static JSONArray loadJSONArray_question(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = context.getResources().openRawResource(R.raw.demo_exam_json);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            return jsonObject.getJSONArray("question_list");

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


}