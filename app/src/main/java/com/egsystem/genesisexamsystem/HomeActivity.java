package com.egsystem.genesisexamsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

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

        htmlParse();

    }

    private void htmlParse() {
        String str1 = "<p>a&nbsp;এর চর্তুঘাত থেকে a&nbsp;এর বর্গের বিয়োগফল -1&nbsp;হলে&nbsp;<img alt=\\\"fraction numerator a to the power of 4 over denominator a to the power of 8 minus a to the power of 4 plus 1 end fraction\\\" src=\\\"data:image/svg+xml;charset=utf8,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20xmlns%3Awrs%3D%22http%3A%2F%2Fwww.wiris.com%2Fxml%2Fmathml-extension%22%20height%3D%2249%22%20width%3D%2282%22%20wrs%3Abaseline%3D%2230%22%3E%3C!--MathML%3A%20%3Cmath%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F1998%2FMath%2FMathML%22%3E%3Cmfrac%3E%3Cmsup%3E%3Cmi%3Ea%3C%2Fmi%3E%3Cmn%3E4%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmrow%3E%3Cmsup%3E%3Cmi%3Ea%3C%2Fmi%3E%3Cmn%3E8%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmo%3E-%3C%2Fmo%3E%3Cmsup%3E%3Cmi%3Ea%3C%2Fmi%3E%3Cmn%3E4%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmo%3E%2B%3C%2Fmo%3E%3Cmn%3E1%3C%2Fmn%3E%3C%2Fmrow%3E%3C%2Fmfrac%3E%3C%2Fmath%3E--%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%40font-face%7Bfont-family%3A'ae2ef524fbf3d9fe611d5a8e90fefdc'%3Bsrc%3Aurl(data%3Afont%2Ftruetype%3Bcharset%3Dutf-8%3Bbase64%2CAAEAAAAMAIAAAwBAT1MvMjv%2FLJYAAADMAAAATmNtYXDgWxEdAAABHAAAADRjdnQgAAAABwAAAVAAAAAEZ2x5ZoYrxVAAAAFUAAAA0WhlYWQOdyayAAACKAAAADZoaGVhC0UVwQAAAmAAAAAkaG10eCg8AIUAAAKEAAAACGxvY2EAAAVKAAACjAAAAAxtYXhwBIoEWwAAApgAAAAgbmFtZXSF9ZsAAAK4AAABrXBvc3QDogHPAAAEaAAAACBwcmVwukanGAAABIgAAAANAAAGtAGQAAUAAAgACAAAAAAACAAIAAAAAAAAAQIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAgICAAAAAg8AMGe%2F57AAAHPgGyAAAAAAACAAEAAQAAABQAAwABAAAAFAAEACAAAAAEAAQAAQAAAGH%2F%2FwAAAGH%2F%2F%2F%2BgAAEAAAAAAAAABwACAFUAAAMAA6sAAwAHAAAzESERJSERIVUCq%2F2rAgD%2BAAOr%2FFVVAwAAAwAt%2F3QEAwRZAAsAFwAdADsYAbAdELAD1LADELAU1LAUELAc1LAcELAJ1LAcELAOPLAJELAbPACwBhCwEdSwBhCwANSwABCwF9QwMQEiABEWEjMyEjcQJgYWAwIGIyImNTQ2MwE1BhMjEgIBs%2F7fFvWy07oDhYZwFgxOhVmysoUB7YwEslEEWf7f%2Ft71%2Ft8BM%2BMBp5yyLf6d%2FwBlyJzfsvxZjF0B5%2F1eAAAAAAEAAAABAACav9usXw889QADCAD%2F%2F%2F%2F%2F1a3uPf%2F%2F%2F%2F%2FVre49AAH%2B9QQDBkMAAAAKAAIAAQAAAAAAAQAABz7%2BTgAAF3AAAf%2F8BAMAAQAAAAAAAAAAAAAAAAAAAAIDUgBVBEwALQAAAAAAAAAoAAAA0QABAAAAAgAeAAMAAAAAAAIAgAQAAAAAAAQAADsAAAAAAAAAFQECAAAAAAAAAAEAFgAAAAAAAAAAAAIADgAWAAAAAAAAAAMANAAkAAAAAAAAAAQAFgBYAAAAAAAAAAUAFgBuAAAAAAAAAAYACwCEAAAAAAAAAAgAHACPAAEAAAAAAAEAFgAAAAEAAAAAAAIADgAWAAEAAAAAAAMANAAkAAEAAAAAAAQAFgBYAAEAAAAAAAUAFgBuAAEAAAAAAAYACwCEAAEAAAAAAAgAHACPAAMAAQQJAAEAFgAAAAMAAQQJAAIADgAWAAMAAQQJAAMANAAkAAMAAQQJAAQAFgBYAAMAAQQJAAUAFgBuAAMAAQQJAAYACwCEAAMAAQQJAAgAHACPAE0AYQB0AGgAIABGAG8AbgB0ACAAMgBSAGUAZwB1AGwAYQByAE0AYQB0AGgAcwAgAEYAbwByACAATQBvAHIAZQAgAE0AYQB0AGgAIABGAG8AbgB0ACAAMgBNAGEAdABoACAARgBvAG4AdAAgADIAVgBlAHIAcwBpAG8AbgAgADEALgAwTWF0aF9Gb250XzIATQBhAHQAaABzACAARgBvAHIAIABNAG8AcgBlAAAAAAMAAAAAAAADnwHPAAAAAAAAAAAAAAAAAAAAAAAAAAC5ByIAAI2FGACyAAAAAAAA)format('truetype')%3Bfont-weight%3Anormal%3Bfont-style%3Anormal%3B%7D%40font-face%7Bfont-family%3A'math1122e6b39e850bce62e39ea338f'%3Bsrc%3Aurl(data%3Afont%2Ftruetype%3Bcharset%3Dutf-8%3Bbase64%2CAAEAAAAMAIAAAwBAT1MvMi7iBBMAAADMAAAATmNtYXDEvmKUAAABHAAAADxjdnQgDVUNBwAAAVgAAAA6Z2x5ZoPi2VsAAAGUAAAA62hlYWQQC2qxAAACgAAAADZoaGVhCGsXSAAAArgAAAAkaG10eE2rRkcAAALcAAAADGxvY2EAHTwYAAAC6AAAABBtYXhwBT0FPgAAAvgAAAAgbmFtZaBxlY4AAAMYAAABn3Bvc3QB9wD6AAAEuAAAACBwcmVwa1uragAABNgAAAAUAAADSwGQAAUAAAQABAAAAAAABAAEAAAAAAAAAQEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAgICAAAAAg1UADev96AAAD6ACWAAAAAAACAAEAAQAAABQAAwABAAAAFAAEACgAAAAGAAQAAQACACsiEv%2F%2FAAAAKyIS%2F%2F%2F%2F1t3wAAEAAAAAAAAAAAFUAywAgAEAAFYAKgJYAh4BDgEsAiwAWgGAAoAAoADUAIAAAAAAAAAAKwBVAIAAqwDVAQABKwAHAAAAAgBVAAADAAOrAAMABwAAMxEhESUhESFVAqv9qwIA%2FgADq%2FxVVQMAAAEAgABVAtUCqwALAEkBGLIMAQEUExCxAAP2sQEE9bAKPLEDBfWwCDyxBQT1sAY8sQ0D5gCxAAATELEBBuSxAQETELAFPLEDBOWxCwX1sAc8sQkE5TEwEyERMxEhFSERIxEhgAEAVQEA%2FwBV%2FwABqwEA%2FwBW%2FwABAAABAIABVQLVAasAAwAwGAGwBBCxAAP2sAM8sQIH9bABPLEFA%2BYAsQAAExCxAAblsQABExCwATyxAwX1sAI8EyEVIYACVf2rAatWAAABAAAAAQAA1XjOQV8PPPUAAwQA%2F%2F%2F%2F%2F9Y6E3P%2F%2F%2F%2F%2F1joTcwAA%2FyAEgAOrAAAACgACAAEAAAAAAAEAAAPo%2F2oAABdwAAD%2FtgSAAAEAAAAAAAAAAAAAAAAAAAADA1IAVQNWAIADVgCAAAAAAAAAACgAAAChAAAA6wABAAAAAwBeAAUAAAAAAAIAgAQAAAAAAAQAAN4AAAAAAAAAFQECAAAAAAAAAAEAEgAAAAAAAAAAAAIADgASAAAAAAAAAAMAMAAgAAAAAAAAAAQAEgBQAAAAAAAAAAUAFgBiAAAAAAAAAAYACQB4AAAAAAAAAAgAHACBAAEAAAAAAAEAEgAAAAEAAAAAAAIADgASAAEAAAAAAAMAMAAgAAEAAAAAAAQAEgBQAAEAAAAAAAUAFgBiAAEAAAAAAAYACQB4AAEAAAAAAAgAHACBAAMAAQQJAAEAEgAAAAMAAQQJAAIADgASAAMAAQQJAAMAMAAgAAMAAQQJAAQAEgBQAAMAAQQJAAUAFgBiAAMAAQQJAAYACQB4AAMAAQQJAAgAHACBAE0AYQB0AGgAIABGAG8AbgB0AFIAZQBnAHUAbABhAHIATQBhAHQAaABzACAARgBvAHIAIABNAG8AcgBlACAATQBhAHQAaAAgAEYAbwBuAHQATQBhAHQAaAAgAEYAbwBuAHQAVgBlAHIAcwBpAG8AbgAgADEALgAwTWF0aF9Gb250AE0AYQB0AGgAcwAgAEYAbwByACAATQBvAHIAZQAAAwAAAAAAAAH0APoAAAAAAAAAAAAAAAAAAAAAAAAAALkHEQAAjYUYALIAAAAVFBOxAAE%2F)format('truetype')%3Bfont-weight%3Anormal%3Bfont-style%3Anormal%3B%7D%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cline%20stroke%3D%22%23000000%22%20stroke-linecap%3D%22square%22%20stroke-width%3D%221%22%20x1%3D%222.5%22%20x2%3D%2278.5%22%20y1%3D%2224.5%22%20y2%3D%2224.5%22%2F%3E%3Ctext%20font-family%3D%22ae2ef524fbf3d9fe611d5a8e90fefdc%22%20font-size%3D%2216%22%20font-style%3D%22italic%22%20text-anchor%3D%22middle%22%20x%3D%2236.5%22%20y%3D%2218%22%3Ea%3C%2Ftext%3E%3Ctext%20font-family%3D%22Arial%22%20font-size%3D%2212%22%20text-anchor%3D%22middle%22%20x%3D%2245.5%22%20y%3D%2211%22%3E4%3C%2Ftext%3E%3Ctext%20font-family%3D%22ae2ef524fbf3d9fe611d5a8e90fefdc%22%20font-size%3D%2216%22%20font-style%3D%22italic%22%20text-anchor%3D%22middle%22%20x%3D%228.5%22%20y%3D%2244%22%3Ea%3C%2Ftext%3E%3Ctext%20font-family%3D%22Arial%22%20font-size%3D%2212%22%20text-anchor%3D%22middle%22%20x%3D%2217.5%22%20y%3D%2237%22%3E8%3C%2Ftext%3E%3Ctext%20font-family%3D%22math1122e6b39e850bce62e39ea338f%22%20font-size%3D%2216%22%20text-anchor%3D%22middle%22%20x%3D%2228.5%22%20y%3D%2244%22%3E%26%23x2212%3B%3C%2Ftext%3E%3Ctext%20font-family%3D%22ae2ef524fbf3d9fe611d5a8e90fefdc%22%20font-size%3D%2216%22%20font-style%3D%22italic%22%20text-anchor%3D%22middle%22%20x%3D%2240.5%22%20y%3D%2244%22%3Ea%3C%2Ftext%3E%3Ctext%20font-family%3D%22Arial%22%20font-size%3D%2212%22%20text-anchor%3D%22middle%22%20x%3D%2249.5%22%20y%3D%2237%22%3E4%3C%2Ftext%3E%3Ctext%20font-family%3D%22math1122e6b39e850bce62e39ea338f%22%20font-size%3D%2216%22%20text-anchor%3D%22middle%22%20x%3D%2260.5%22%20y%3D%2244%22%3E%2B%3C%2Ftext%3E%3Ctext%20font-family%3D%22Arial%22%20font-size%3D%2216%22%20text-anchor%3D%22middle%22%20x%3D%2272.5%22%20y%3D%2244%22%3E1%3C%2Ftext%3E%3C%2Fsvg%3E\\\" style=\\\"height:49px; width:82px\\\" />&nbsp;এর মান কত?</p>";
        String str1_parsed = String.valueOf(Html.fromHtml(str1));

        Log.d("riririririri", "str1: "+str1);
        Log.d("riririririri", "str1_parsed: "+str1_parsed);

        WebView webview1 = findViewById(R.id.webview1);
        webview1.loadData(str1, "text/html", "UTF-8");
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