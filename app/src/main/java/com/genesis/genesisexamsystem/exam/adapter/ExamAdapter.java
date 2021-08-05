package com.genesis.genesisexamsystem.exam.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.genesis.genesisexamsystem.R;
import com.genesis.genesisexamsystem.data.database.ExamDbHelper;
import com.genesis.genesisexamsystem.data.model.Question;
import com.genesis.genesisexamsystem.data.shared_pref.SharedData;
import com.genesis.genesisexamsystem.model.DoctorExamListModel;
import com.genesis.genesisexamsystem.model.QuestionFileModel;
import com.genesis.genesisexamsystem.question.QuestionInOnePageActivity;
import com.genesis.genesisexamsystem.retrofit.Api;
import com.genesis.genesisexamsystem.retrofit.RetrofitApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {

    private List<DoctorExamListModel.Exam> dataSet = new ArrayList<>();
    private List<String> dataSet2 = new ArrayList<>();
    private List<String> dataSet3 = new ArrayList<>();
    private List<String> title_code_list = new ArrayList<>();
    Context context;
    ExamDbHelper dbHelper;

    Cursor dataCursor;

    private List<String> mProductList;
    HashMap<Object, String> caWithTitleName = new HashMap<Object, String>();

    String title;
    String category_id;
    private List<String> memberListFiltered = new ArrayList<>();


    public ExamAdapter(Context context) {
        this.context = context;
        dbHelper = ExamDbHelper.getInstance(context);
    }


    public ExamAdapter(Context context, Cursor cursor) {
        dataCursor = cursor;
        this.context = context;
        Log.d("tag1wwwww", " adapter: ");
    }

    public void setTitle(String title, String categoryId) {
        this.title = title;
        this.category_id = categoryId;
    }


    public void setData(List<DoctorExamListModel.Exam> dataSet) {
        this.dataSet = dataSet;
        Log.d("tagResponse", " dataSet2: " + dataSet2);
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public ExamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_exam_list, parent, false);
        ExamViewHolder myViewHolder = new ExamViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listener.onItemClick(v, myViewHolder.getPosition());
            }
        });
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final ExamAdapter.ExamViewHolder holder, int position) {
        TextView tv_sl_no = holder.tv_sl_no;
        TextView tv_name1 = holder.tv_name1;
        TextView tv_exam_type = holder.tv_exam_type;
        TextView tv_duration = holder.tv_duration;
        TextView tv_total_mark = holder.tv_total_mark;
        TextView tv_mcq_mark = holder.tv_mcq_mark;
        TextView tv_sba_mark = holder.tv_sba_mark;
        TextView tv_neg_mark_mcq = holder.tv_neg_mark_mcq;
        TextView tv_neg_mark_sba = holder.tv_neg_mark_sba;
        LinearLayout linear_result_ans = holder.linear_result_ans;
        LinearLayout linear_start_exam = holder.linear_start_exam;
//        ImageView imageView = holder.imageView;
        CardView cardview1 = holder.cardview1;

//        imageView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
//        cardview1.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fall_down_animation));

        DoctorExamListModel.Exam doctor_exam = dataSet.get(position);
        Log.d("tag333", " doctor_exam...: " + doctor_exam);

        int aPosition = position + 1;

        if (aPosition % 4 == 1) {
            cardview1.setBackgroundResource(R.drawable.ic_exam_bg_1_green);

//            linear_result_ans.setVisibility(View.GONE);
//            linear_start_exam.setVisibility(View.VISIBLE);

        } else if (aPosition % 4 == 2) {
            cardview1.setBackgroundResource(R.drawable.ic_exam_bg_4_purple);

//            linear_start_exam.setVisibility(View.GONE);
//            linear_result_ans.setVisibility(View.VISIBLE);

        } else if (aPosition % 4 == 3) {
            cardview1.setBackgroundResource(R.drawable.ic_exam_bg_3_blue);

//            linear_result_ans.setVisibility(View.GONE);
//            linear_start_exam.setVisibility(View.VISIBLE);

        } else if (aPosition % 4 == 0) {
            cardview1.setBackgroundResource(R.drawable.ic_exam_bg_2_orange);

//            linear_start_exam.setVisibility(View.GONE);
//            linear_result_ans.setVisibility(View.VISIBLE);

        }


        if (doctor_exam.getDoctorExamStatus().toString().equalsIgnoreCase("Completed")){
            linear_start_exam.setVisibility(View.GONE);
            linear_result_ans.setVisibility(View.VISIBLE);
        }else {
            linear_result_ans.setVisibility(View.GONE);
            linear_start_exam.setVisibility(View.VISIBLE);
        }


        Log.d("tag333", " position...: " + position);
        Log.d("tag333", " mProductList...: " + mProductList);

        tv_sl_no.setText(String.valueOf(aPosition));

//        tv_exam_type.setText(doctor_exam);

        int duration_exam = doctor_exam.getDuration();
        int duration_exam_in_minutes = duration_exam / 60;

        tv_name1.setText(doctor_exam.getName());
        tv_duration.setText(String.valueOf(duration_exam_in_minutes));
        tv_total_mark.setText(doctor_exam.getFullMark().toString());
        tv_mcq_mark.setText(doctor_exam.getMcqMark().toString());
        tv_sba_mark.setText(doctor_exam.getSbaMark().toString());
        tv_neg_mark_mcq.setText(doctor_exam.getMcqNegativeMark().toString());
        tv_neg_mark_sba.setText(doctor_exam.getSbaNegativeMark().toString());

//        Glide.with(context).load(doctor_exam3).into(imageView);

        linear_start_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(context, PreeQuestionActivity.class);
//                intent.putExtra("title", doctor_exam);
                SharedData.saveEXAM_NAME(context, doctor_exam.getName().toString());
                SharedData.saveEXAM_DURATION(context, doctor_exam.getDuration().toString());
                SharedData.saveMCQ_NUMBER(context, doctor_exam.getMcqNumber().toString());
                SharedData.saveMCQ_MARK(context, doctor_exam.getMcqMark().toString());
                SharedData.saveMCQ_NEGATIVE_MARK(context, doctor_exam.getMcqNegativeMark().toString());
                SharedData.saveSBA_NUMBER(context, doctor_exam.getSbaNumber().toString());
                SharedData.saveSBA_MARK(context, doctor_exam.getSbaMark().toString());
                SharedData.saveSBA_NEGATIVE_MARK(context, doctor_exam.getSbaNegativeMark().toString());

//                context.startActivity(intent);


                getQuestionFileApiCall(doctor_exam.getExamId().toString(), doctor_exam.getResult().getDoctorCourseId().toString());

            }
        });


//        linear_result_ans.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, ResultActivity.class);
////                intent.putExtra("title", doctor_exam);
//                context.startActivity(intent);
//            }
//        });


    }


    @SuppressLint("CheckResult")
    public void getQuestionFileApiCall(String exam_id, String doctor_course_id) {

        String token = SharedData.getTOKEN(context);
        String authorization = "Bearer" + " " + token;
        String accept = "application/json";
        String url = Api.BASE_URL + Api.doctor_exam + exam_id;


        Log.d("tag11111", "url exam: " + url);

        RetrofitApiClient.getApiInterface().getQuestionFileApiCall(url, authorization, accept)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            Log.d("tag11111", " response.code(): " + response.code());
//                            progressDialog.dismiss();

                            if (response.isSuccessful()) {

                                QuestionFileModel examModel = response.body();
                                Log.d("tag11111", " examModel: " + examModel);
//                                String loginMessage = examModel.get;
//                                Log.d("tag11111", " loginMessage: " + loginMessage);

                                response.body();
                                Log.d("tag11111", " response.body(): " + response.body());

                                List<QuestionFileModel.Question> quesList = examModel.getQuestions();
                                Log.d("tag11111", " quesList.size(): " + quesList.size());

//                                JSONArray questions = quesList;
                                loadQuestionData(quesList);

                                Intent intent = new Intent(context, QuestionInOnePageActivity.class);
                                intent.putExtra("from_where", "EXAM_ACTIVITY");
                                intent.putExtra("doctor_course_id", doctor_course_id);
                                intent.putExtra("exam_id", exam_id);
                                context.startActivity(intent);


                            } else {
////                                response.errorBody().string(); // do something with that
//                                Gson gson = new Gson();
//                                ErrorResponseGeneric errorResponse = gson.fromJson(
//                                        response.errorBody().string(),
//                                        ErrorResponseGeneric.class);
//
////                                List<String> errorList = errorResponse.getValidationError();
//                                ErrorResponseGeneric.Errors error = errorResponse.getErrors();
//                                List<String> bmdc_error_list = error.getBmdcNo();
//                                List<String> passwd_error_list = error.getPassword();
//
////                                String errorNames = "1. ";
////                                for (int i = 0; i < errorList.size(); i++) {
////                                    int j = i + 2;
////                                    errorNames = errorNames
////                                            + errorList.get(i);
////                                    if (i != errorList.size() - 1) {
////                                        errorNames = errorNames + "\n\n" + j + ". ";
////                                    }
////
////                                }
//
//                                String errorNames = "";
//                                for (int i = 0; i < bmdc_error_list.size(); i++) {
//                                    int j = i + 2;
//                                    errorNames = errorNames
//                                            + bmdc_error_list.get(i);
//                                    if (i != bmdc_error_list.size() - 1) {
//                                        errorNames = errorNames + "\n\n" + j + ". ";
//                                    }
//
//                                }
//
//                                String totalErrors = passwd_error_list + "\n" + passwd_error_list;
//
//                                Log.d("tag11111", " totalErrors: " + totalErrors);
//
//
//                                new MaterialDialog.Builder(ExamActivity.this)
//                                        .title("Login Status")
//                                        .content(totalErrors)
//                                        .positiveText("")
//                                        .negativeText("Ok")
//                                        .show();


                            }

                        },
                        error -> {

                            Log.d("tag11111", " error: " + error.getMessage());
                        },
                        () -> {
                            Log.d("tag11111", " response.code(): ");
                        }

                );

    }


    private void loadQuestionData(List<QuestionFileModel.Question> quesList) {

        dbHelper.deleteAllQuestions();

        for (int i = 0; i < quesList.size(); i++) {

            String question_id = quesList.get(i).getQuestionId().toString();
            String exam_question_id = quesList.get(i).getExamQuestionId().toString();
            String question_type = quesList.get(i).getQuestionType();
            String question_title = quesList.get(i).getQuestionTitle();
            String correct_ans_sba = quesList.get(i).getCorrectAnsSba();

            String option1 = quesList.get(i).getQuestionOption().get(0).getOptionTitle();
            String option2 = quesList.get(i).getQuestionOption().get(1).getOptionTitle();
            String option3 = quesList.get(i).getQuestionOption().get(2).getOptionTitle();
            String option4 = quesList.get(i).getQuestionOption().get(3).getOptionTitle();
            String option5 = quesList.get(i).getQuestionOption().get(4).getOptionTitle();

            String correct_ans_a = quesList.get(i).getQuestionOption().get(0).getCorrectAns().toString();
            String correct_ans_b = quesList.get(i).getQuestionOption().get(1).getCorrectAns().toString();
            String correct_ans_c = quesList.get(i).getQuestionOption().get(2).getCorrectAns().toString();
            String correct_ans_d = quesList.get(i).getQuestionOption().get(3).getCorrectAns().toString();
            String correct_ans_e = quesList.get(i).getQuestionOption().get(4).getCorrectAns().toString();

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

            Question q1 = new Question(question_id, exam_question_id, question_type, question_title, option1, option2, option3, option4, option5,
                    correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e);
            dbHelper.addQuestion(q1);

        }


    }


//
//    private void loadQuestionData(JSONArray questions) {
//
////        JSONArray questions = loadJSONArray_question(context);
//
//
//        try {
//            for (int i = 0; i < questions.length(); i++) {
//                JSONObject question = questions.getJSONObject(i);
//
//                String question_id = question.getString("question_id");
//                String question_type = question.getString("question_type");
//                String question_title = question.getString("question_title");
//                String correct_ans_sba = question.getString("correct_ans_sba");
//
//                JSONArray optionsArray = question.getJSONArray("question_option");
//
//                JSONObject optionA = optionsArray.getJSONObject(0);
//                JSONObject optionB = optionsArray.getJSONObject(1);
//                JSONObject optionC = optionsArray.getJSONObject(2);
//                JSONObject optionD = optionsArray.getJSONObject(3);
//                JSONObject optionE = optionsArray.getJSONObject(4);
//
//                String option1 = optionA.getString("option_title");
//                String option2 = optionB.getString("option_title");
//                String option3 = optionC.getString("option_title");
//                String option4 = optionD.getString("option_title");
//                String option5 = optionE.getString("option_title");
//
//                String correct_ans_a = optionA.getString("correct_ans");
//                String correct_ans_b = optionB.getString("correct_ans");
//                String correct_ans_c = optionC.getString("correct_ans");
//                String correct_ans_d = optionD.getString("correct_ans");
//                String correct_ans_e = optionE.getString("correct_ans");
//
//                if (correct_ans_sba.isEmpty()) {
//                    correct_ans_sba = "not sba";
//                }
//                if (correct_ans_a.isEmpty()) {
//                    correct_ans_a = "not mcq";
//                }
//                if (correct_ans_b.isEmpty()) {
//                    correct_ans_b = "not mcq";
//                }
//                if (correct_ans_c.isEmpty()) {
//                    correct_ans_c = "not mcq";
//                }
//                if (correct_ans_d.isEmpty()) {
//                    correct_ans_d = "not mcq";
//                }
//                if (correct_ans_e.isEmpty()) {
//                    correct_ans_e = "not mcq";
//                }
//
//
//                Question q1 = new Question(question_id, question_type, question_title, option1, option2, option3, option4, option5,
//                        correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e);
//                dbHelper.addQuestion(q1);
//
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
//


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


    class ExamViewHolder extends RecyclerView.ViewHolder {

        TextView tv_sl_no;
        TextView tv_name1;
        TextView tv_exam_type;
        TextView tv_duration;
        TextView tv_total_mark;
        TextView tv_mcq_mark;
        TextView tv_sba_mark;
        TextView tv_neg_mark_mcq;
        TextView tv_neg_mark_sba;

        LinearLayout linear_result_ans;
        LinearLayout linear_start_exam;

        ImageView imageView;
        CardView cardview1;

        public ExamViewHolder(View itemView) {
            super(itemView);
            tv_sl_no = itemView.findViewById(R.id.tv_sl_no);
            tv_name1 = itemView.findViewById(R.id.tv_name1);
            tv_exam_type = itemView.findViewById(R.id.tv_exam_type);
            tv_duration = itemView.findViewById(R.id.tv_duration);
            tv_total_mark = itemView.findViewById(R.id.tv_total_mark);
            tv_mcq_mark = itemView.findViewById(R.id.tv_mcq_mark);
            tv_sba_mark = itemView.findViewById(R.id.tv_sba_mark);
            tv_neg_mark_mcq = itemView.findViewById(R.id.tv_neg_mark_mcq);
            tv_neg_mark_sba = itemView.findViewById(R.id.tv_neg_mark_sba);

//            imageView = itemView.findViewById(R.id.imageView);
            cardview1 = itemView.findViewById(R.id.cardview1);

            linear_result_ans = itemView.findViewById(R.id.linear_result_ans);
            linear_start_exam = itemView.findViewById(R.id.linear_start_exam);

        }
    }


}

