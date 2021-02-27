package com.egsystem.genesisexamsystem.question.adapter;


import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.data.database.ExamDbHelper;
import com.egsystem.genesisexamsystem.data.model.Answer;
import com.egsystem.genesisexamsystem.data.model.Question;
import com.egsystem.genesisexamsystem.question.QuestionInOnePageActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionInOnePageAdapter2 extends RecyclerView.Adapter<QuestionInOnePageAdapter2.CorrectAnswerViewHolder> {

    private List<String> dataSet = new ArrayList<>();
    private List<Question> questionList = new ArrayList<>();
    private List<Answer> answerList = new ArrayList<>();

    Context context;
    ExamDbHelper dbHelper;


    private String radio1_given_ans = "";
    private String radio2_given_ans = "";
    private String radio3_given_ans = "";
    private String radio4_given_ans = "";
    private String radio5_given_ans = "";
    private String radio_a_1_given_ans = "";
    private String radio_a_2_given_ans = "";
    private String radio_b_1_given_ans = "";
    private String radio_b_2_given_ans = "";
    private String radio_c_1_given_ans = "";
    private String radio_c_2_given_ans = "";
    private String radio_d_1_given_ans = "";
    private String radio_d_2_given_ans = "";
    private String radio_e_1_given_ans = "";
    private String radio_e_2_given_ans = "";

    private List<String> memberListFiltered = new ArrayList<>();

    public QuestionInOnePageAdapter2(Context context) {
        this.context = context;
        dbHelper = ExamDbHelper.getInstance(context);
    }


    public void setData2(List<Question> questionList) {
        this.questionList = questionList;
//        this.answerList = answerList;

        setInitialAnswer();

        Log.d("tagResponse", " questionList: " + questionList);
    }


    @Override
    public int getItemCount() {
        return questionList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public void onViewAttachedToWindow(@NonNull CorrectAnswerViewHolder holder) {
        super.onViewAttachedToWindow(holder);

//        recyclerView.requestFocus();
    }

    @Override
    public CorrectAnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_question_in_one_page, parent, false);
        CorrectAnswerViewHolder myViewHolder = new CorrectAnswerViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listener.onItemClick(v, myViewHolder.getPosition());
            }
        });
        return myViewHolder;
    }

    private void setInitialAnswer() {

        for (int i = 0; i < questionList.size(); i++) {

            String questionId = questionList.get(i).getQuestionId();
            String questionSl = String.valueOf(i + 1);
            String questionType = questionList.get(i).getQuestionType();
            String correct_ans_sba = "";
            String correct_ans_a = "";
            String correct_ans_b = "";
            String correct_ans_c = "";
            String correct_ans_d = "";
            String correct_ans_e = "";
            String skipped = "";
            String not_answered = "";
            Answer ans1 = new Answer(questionId, questionSl, questionType, correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e, skipped, not_answered);
            dbHelper.addAnswer(ans1);
        }

    }


    @Override
    public void onBindViewHolder(final QuestionInOnePageAdapter2.CorrectAnswerViewHolder holder, int position) {
        TextView tv_sl_no = holder.tv_sl_no;
        TextView tv_question_no = holder.tv_question_no;
        TextView tv_save = holder.tv_save;
        TextView tv_edit = holder.tv_edit;
        TextView tv_question = holder.tv_question;
        TextView tv_option1 = holder.tv_option1;
        TextView tv_option2 = holder.tv_option2;
        TextView tv_option3 = holder.tv_option3;
        TextView tv_option4 = holder.tv_option4;
        TextView tv_option5 = holder.tv_option5;
        LinearLayout linear_result_ans = holder.linear_result_ans;
        LinearLayout linear_start_exam = holder.linear_start_exam;
        LinearLayout linear_sba = holder.linear_sba;
        LinearLayout linear_ans_text = holder.linear_ans_text;

        LinearLayout linear_multiple_choice_a = holder.linear_multiple_choice_a;
        LinearLayout linear_multiple_choice_b = holder.linear_multiple_choice_b;
        LinearLayout linear_multiple_choice_c = holder.linear_multiple_choice_c;
        LinearLayout linear_multiple_choice_d = holder.linear_multiple_choice_d;
        LinearLayout linear_multiple_choice_e = holder.linear_multiple_choice_e;

        RadioGroup radio_group_multiple_choice1 = holder.radio_group_multiple_choice1;
        RadioGroup radio_group_multiple_choice2 = holder.radio_group_multiple_choice2;
        RadioGroup radio_group_multiple_choice3 = holder.radio_group_multiple_choice3;
        RadioGroup radio_group_multiple_choice4 = holder.radio_group_multiple_choice4;
        RadioGroup radio_group_multiple_choice5 = holder.radio_group_multiple_choice5;
        RadioGroup radio_group_sba = holder.radio_group_sba;

        RadioButton radio_a_1 = holder.radio_a_1;
        RadioButton radio_a_2 = holder.radio_a_2;
        RadioButton radio_b_1 = holder.radio_b_1;
        RadioButton radio_b_2 = holder.radio_b_2;
        RadioButton radio_c_1 = holder.radio_c_1;
        RadioButton radio_c_2 = holder.radio_c_2;
        RadioButton radio_d_1 = holder.radio_d_1;
        RadioButton radio_d_2 = holder.radio_d_2;
        RadioButton radio_e_1 = holder.radio_e_1;
        RadioButton radio_e_2 = holder.radio_e_2;
        RadioButton radio1 = holder.radio1;
        RadioButton radio2 = holder.radio2;
        RadioButton radio3 = holder.radio3;
        RadioButton radio4 = holder.radio4;
        RadioButton radio5 = holder.radio5;


//        ImageView imageView = holder.imageView;
        CardView cardview1 = holder.cardview1;
        View view5 = holder.view5;

//        imageView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
//        cardview1.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fall_down_animation));

        Question question = questionList.get(position);
//        Answer answer = answerList.get(position);


        tv_question_no.setText("Question(" + (position + 1) + " of " + questionList.size() + ")");


        tv_question.setText(question.getQuestion());

        tv_option1.setText(question.getOption1());
        tv_option2.setText(question.getOption2());
        tv_option3.setText(question.getOption3());
        tv_option4.setText(question.getOption4());
        tv_option5.setText(question.getOption5());

//        Log.d("tag676767","answer.getCorrect_ans_sba(): "+ answer.getCorrect_ans_sba());

        ///
        if (question.getQuestionType().equalsIgnoreCase("1")) {

            linear_multiple_choice_a.setVisibility(View.VISIBLE);
            linear_multiple_choice_b.setVisibility(View.VISIBLE);
            linear_multiple_choice_c.setVisibility(View.VISIBLE);
            linear_multiple_choice_d.setVisibility(View.VISIBLE);
            linear_multiple_choice_e.setVisibility(View.VISIBLE);

            linear_sba.setVisibility(View.GONE);
            view5.setVisibility(View.GONE);
        }

        if (question.getQuestionType().equalsIgnoreCase("2")) {

            linear_sba.setVisibility(View.VISIBLE);
            view5.setVisibility(View.VISIBLE);

            linear_multiple_choice_a.setVisibility(View.GONE);
            linear_multiple_choice_b.setVisibility(View.GONE);
            linear_multiple_choice_c.setVisibility(View.GONE);
            linear_multiple_choice_d.setVisibility(View.GONE);
            linear_multiple_choice_e.setVisibility(View.GONE);

        }


        radio_group_sba.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio1:
                        radio1_given_ans = "A";
                        radio2_given_ans = "";
                        radio3_given_ans = "";
                        radio4_given_ans = "";
                        radio5_given_ans = "";
                        break;
                    case R.id.radio2:
                        radio1_given_ans = "";
                        radio2_given_ans = "B";
                        radio3_given_ans = "";
                        radio4_given_ans = "";
                        radio5_given_ans = "";
                        break;
                    case R.id.radio3:
                        radio1_given_ans = "";
                        radio2_given_ans = "";
                        radio3_given_ans = "C";
                        radio4_given_ans = "";
                        radio5_given_ans = "";
                        break;
                    case R.id.radio4:
                        radio1_given_ans = "";
                        radio2_given_ans = "";
                        radio3_given_ans = "";
                        radio4_given_ans = "D";
                        radio5_given_ans = "";
                        break;
                    case R.id.radio5:
                        radio1_given_ans = "";
                        radio2_given_ans = "";
                        radio3_given_ans = "";
                        radio4_given_ans = "";
                        radio5_given_ans = "E";
                        break;
                }
            }
        });
        //


        //
        radio_group_multiple_choice1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_a_1:
                        radio_a_1_given_ans = "T";
                        radio_a_2_given_ans = "F";
                        break;
                    case R.id.radio_a_2:
                        radio_a_1_given_ans = "F";
                        radio_a_2_given_ans = "T";
                        break;
                }
            }
        });
        //

        //
        radio_group_multiple_choice2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_b_1:
                        radio_b_1_given_ans = "T";
                        radio_b_2_given_ans = "F";
                        break;
                    case R.id.radio_b_2:
                        radio_b_1_given_ans = "F";
                        radio_b_2_given_ans = "T";
                        break;
                }
            }
        });
        //

        //
        radio_group_multiple_choice3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_c_1:
                        radio_c_1_given_ans = "T";
                        radio_c_2_given_ans = "F";
                        break;
                    case R.id.radio_c_2:
                        radio_c_1_given_ans = "F";
                        radio_c_2_given_ans = "T";
                        break;
                }
            }
        });
        //

        //
        radio_group_multiple_choice4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_d_1:
                        radio_d_1_given_ans = "T";
                        radio_d_2_given_ans = "F";
                        break;
                    case R.id.radio_d_2:
                        radio_d_1_given_ans = "F";
                        radio_d_2_given_ans = "T";
                        break;
                }
            }
        });
        //

        //
        radio_group_multiple_choice5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_e_1:
                        radio_e_1_given_ans = "T";
                        radio_e_2_given_ans = "F";
                        break;
                    case R.id.radio_e_2:
                        radio_e_1_given_ans = "F";
                        radio_e_2_given_ans = "T";
                        break;
                }
            }
        });


//        Glide.with(context).load(doctorInfo3).into(imageView);
//
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String correct_ans_sba = "";

                String correct_ans_a = "";
                String correct_ans_b = "";
                String correct_ans_c = "";
                String correct_ans_d = "";
                String correct_ans_e = "";


                if (radio1_given_ans.equalsIgnoreCase("A")) {
                    correct_ans_sba = "A";
                } else if (radio2_given_ans.equalsIgnoreCase("B")) {
                    correct_ans_sba = "B";
                } else if (radio3_given_ans.equalsIgnoreCase("C")) {
                    correct_ans_sba = "C";
                } else if (radio4_given_ans.equalsIgnoreCase("D")) {
                    correct_ans_sba = "D";
                } else if (radio5_given_ans.equalsIgnoreCase("E")) {
                    correct_ans_sba = "E";
                }


                if (radio_a_1_given_ans.equalsIgnoreCase("T")) {
                    correct_ans_a = "T";
                } else if (radio_a_2_given_ans.equalsIgnoreCase("T")) {
                    correct_ans_a = "F";
                }

                if (radio_b_1_given_ans.equalsIgnoreCase("T")) {
                    correct_ans_b = "T";
                } else if (radio_b_2_given_ans.equalsIgnoreCase("T")) {
                    correct_ans_b = "F";
                }

                if (radio_c_1_given_ans.equalsIgnoreCase("T")) {
                    correct_ans_c = "T";
                } else if (radio_c_2_given_ans.equalsIgnoreCase("T")) {
                    correct_ans_c = "F";
                }

                if (radio_d_1_given_ans.equalsIgnoreCase("T")) {
                    correct_ans_d = "T";
                } else if (radio_d_2_given_ans.equalsIgnoreCase("T")) {
                    correct_ans_d = "F";
                }

                if (radio_e_1_given_ans.equalsIgnoreCase("T")) {
                    correct_ans_e = "T";
                } else if (radio_e_2_given_ans.equalsIgnoreCase("T")) {
                    correct_ans_e = "F";
                }

                String questionId = question.getQuestionId();
                String questionSl = String.valueOf(position + 1);
                String questionType = question.getQuestionType();
                String skipped = "";
                String not_answered = "";

                dbHelper.updateSpecificAnswer(questionId, questionSl, questionType, correct_ans_sba, correct_ans_a, correct_ans_b, correct_ans_c, correct_ans_d, correct_ans_e, skipped, not_answered);


                tv_save.setText("SAVED");
                tv_save.setPadding(50,0,50, 0);

                correct_ans_sba = "";

                correct_ans_a = "";
                correct_ans_b = "";
                correct_ans_c = "";
                correct_ans_d = "";
                correct_ans_e = "";

                radio1_given_ans = "";
                radio2_given_ans = "";
                radio3_given_ans = "";
                radio4_given_ans = "";
                radio5_given_ans = "";
                radio_a_1_given_ans = "";
                radio_a_2_given_ans = "";
                radio_b_1_given_ans = "";
                radio_b_2_given_ans = "";
                radio_c_1_given_ans = "";
                radio_c_2_given_ans = "";
                radio_d_1_given_ans = "";
                radio_d_2_given_ans = "";
                radio_e_1_given_ans = "";
                radio_e_2_given_ans = "";

                radio_a_1.setEnabled(false);
                radio_a_2.setEnabled(false);
                radio_b_1.setEnabled(false);
                radio_b_2.setEnabled(false);
                radio_c_1.setEnabled(false);
                radio_c_2.setEnabled(false);
                radio_d_1.setEnabled(false);
                radio_d_2.setEnabled(false);
                radio_e_1.setEnabled(false);
                radio_e_2.setEnabled(false);
                radio1.setEnabled(false);
                radio2.setEnabled(false);
                radio3.setEnabled(false);
                radio4.setEnabled(false);
                radio5.setEnabled(false);

                tv_save.setBackgroundResource(R.drawable.rounded_corner2);

                QuestionInOnePageActivity.loadAnswerList();

            }
        });

        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_save.setText("Save");

                tv_save.setBackgroundResource(R.drawable.rounded_corner3);
                tv_save.setPadding(50,0,50, 0);


                radio_a_1.setEnabled(true);
                radio_a_2.setEnabled(true);
                radio_b_1.setEnabled(true);
                radio_b_2.setEnabled(true);
                radio_c_1.setEnabled(true);
                radio_c_2.setEnabled(true);
                radio_d_1.setEnabled(true);
                radio_d_2.setEnabled(true);
                radio_e_1.setEnabled(true);
                radio_e_2.setEnabled(true);
                radio1.setEnabled(true);
                radio2.setEnabled(true);
                radio3.setEnabled(true);
                radio4.setEnabled(true);
                radio5.setEnabled(true);

                answerList = dbHelper.getAllGivenAnswers();
                String correct_ans_sba_from_ans = answerList.get(position).getCorrect_ans_sba();
                String correct_ans_a_from_ans = answerList.get(position).getCorrect_ans_a();
                String correct_ans_b_from_ans = answerList.get(position).getCorrect_ans_b();
                String correct_ans_c_from_ans = answerList.get(position).getCorrect_ans_c();
                String correct_ans_d_from_ans = answerList.get(position).getCorrect_ans_d();
                String correct_ans_e_from_ans = answerList.get(position).getCorrect_ans_e();

                Log.d("tag676767","correct_ans_sba_from_ans: "+ correct_ans_sba_from_ans);

                if (correct_ans_sba_from_ans.equalsIgnoreCase("A")) {
                    radio1_given_ans = "A";
                } else if (correct_ans_sba_from_ans.equalsIgnoreCase("B")) {
                    radio2_given_ans = "B";
                } else if (correct_ans_sba_from_ans.equalsIgnoreCase("C")) {
                    radio3_given_ans = "C";
                } else if (correct_ans_sba_from_ans.equalsIgnoreCase("D")) {
                    radio4_given_ans = "D";
                } else if (correct_ans_sba_from_ans.equalsIgnoreCase("E")) {
                    radio5_given_ans = "E";
                }


                if (correct_ans_a_from_ans.equalsIgnoreCase("T")) {
                    radio_a_1_given_ans = "T";
                } else if (correct_ans_a_from_ans.equalsIgnoreCase("F")) {
                    radio_a_2_given_ans = "T";
                }


                if (correct_ans_b_from_ans.equalsIgnoreCase("T")) {
                    radio_b_1_given_ans = "T";
                } else if (correct_ans_b_from_ans.equalsIgnoreCase("F")) {
                    radio_b_2_given_ans = "T";
                }


                if (correct_ans_c_from_ans.equalsIgnoreCase("T")) {
                    radio_c_1_given_ans = "T";
                } else if (correct_ans_c_from_ans.equalsIgnoreCase("F")) {
                    radio_c_2_given_ans = "T";
                }


                if (correct_ans_d_from_ans.equalsIgnoreCase("T")) {
                    radio_d_1_given_ans = "T";
                } else if (correct_ans_d_from_ans.equalsIgnoreCase("F")) {
                    radio_d_2_given_ans = "T";
                }


                if (correct_ans_e_from_ans.equalsIgnoreCase("T")) {
                    radio_e_1_given_ans = "T";
                } else if (correct_ans_e_from_ans.equalsIgnoreCase("F")) {
                    radio_e_2_given_ans = "T";
                }


            }
        });


    }


    class CorrectAnswerViewHolder extends RecyclerView.ViewHolder {

        TextView tv_sl_no;
        TextView tv_question_no;
        TextView tv_edit;
        TextView tv_save;
        TextView tv_question;
        TextView tv_option1;
        TextView tv_option2;
        TextView tv_option3;
        TextView tv_option4;
        TextView tv_option5;

        LinearLayout linear_result_ans;
        LinearLayout linear_start_exam;
        LinearLayout linear_sba;

        LinearLayout linear_multiple_choice_a;
        LinearLayout linear_multiple_choice_b;
        LinearLayout linear_multiple_choice_c;
        LinearLayout linear_multiple_choice_d;
        LinearLayout linear_multiple_choice_e;

        LinearLayout linear_ans_text;

        ImageView imageView;
        CardView cardview1;
        View view5;

        RadioButton radio_a_1;
        RadioButton radio_a_2;
        RadioButton radio_b_1;
        RadioButton radio_b_2;
        RadioButton radio_c_1;
        RadioButton radio_c_2;
        RadioButton radio_d_1;
        RadioButton radio_d_2;
        RadioButton radio_e_1;
        RadioButton radio_e_2;

        RadioButton radio1;
        RadioButton radio2;
        RadioButton radio3;
        RadioButton radio4;
        RadioButton radio5;

        RadioGroup radio_group_multiple_choice1, radio_group_multiple_choice2, radio_group_multiple_choice3,
                radio_group_multiple_choice4, radio_group_multiple_choice5, radio_group_sba;


        public CorrectAnswerViewHolder(View itemView) {
            super(itemView);
            tv_sl_no = itemView.findViewById(R.id.tv_sl_no);
            tv_question_no = itemView.findViewById(R.id.tv_question_no);
            tv_edit = itemView.findViewById(R.id.tv_edit);
            tv_save = itemView.findViewById(R.id.tv_save);
            tv_question = itemView.findViewById(R.id.tv_question);
            tv_option1 = itemView.findViewById(R.id.tv_option1);
            tv_option2 = itemView.findViewById(R.id.tv_option2);
            tv_option3 = itemView.findViewById(R.id.tv_option3);
            tv_option4 = itemView.findViewById(R.id.tv_option4);
            tv_option5 = itemView.findViewById(R.id.tv_option5);

            cardview1 = itemView.findViewById(R.id.cardview1);
            view5 = itemView.findViewById(R.id.view5);

            linear_result_ans = itemView.findViewById(R.id.linear_result_ans);
            linear_start_exam = itemView.findViewById(R.id.linear_start_exam);
            linear_sba = itemView.findViewById(R.id.linear_sba);

            linear_multiple_choice_a = itemView.findViewById(R.id.linear_multiple_choice_a);
            linear_multiple_choice_b = itemView.findViewById(R.id.linear_multiple_choice_b);
            linear_multiple_choice_c = itemView.findViewById(R.id.linear_multiple_choice_c);
            linear_multiple_choice_d = itemView.findViewById(R.id.linear_multiple_choice_d);
            linear_multiple_choice_e = itemView.findViewById(R.id.linear_multiple_choice_e);

            linear_ans_text = itemView.findViewById(R.id.linear_ans_text);

            radio_group_multiple_choice1 = itemView.findViewById(R.id.radio_group_multiple_choice1);
            radio_group_multiple_choice2 = itemView.findViewById(R.id.radio_group_multiple_choice2);
            radio_group_multiple_choice3 = itemView.findViewById(R.id.radio_group_multiple_choice3);
            radio_group_multiple_choice4 = itemView.findViewById(R.id.radio_group_multiple_choice4);
            radio_group_multiple_choice5 = itemView.findViewById(R.id.radio_group_multiple_choice5);
            radio_group_sba = itemView.findViewById(R.id.radio_group_sba);

            radio_a_1 = itemView.findViewById(R.id.radio_a_1);
            radio_a_2 = itemView.findViewById(R.id.radio_a_2);
            radio_b_1 = itemView.findViewById(R.id.radio_b_1);
            radio_b_2 = itemView.findViewById(R.id.radio_b_2);
            radio_c_1 = itemView.findViewById(R.id.radio_c_1);
            radio_c_2 = itemView.findViewById(R.id.radio_c_2);
            radio_d_1 = itemView.findViewById(R.id.radio_d_1);
            radio_d_2 = itemView.findViewById(R.id.radio_d_2);
            radio_e_1 = itemView.findViewById(R.id.radio_e_1);
            radio_e_2 = itemView.findViewById(R.id.radio_e_2);
            radio1 = itemView.findViewById(R.id.radio1);
            radio2 = itemView.findViewById(R.id.radio2);
            radio3 = itemView.findViewById(R.id.radio3);
            radio4 = itemView.findViewById(R.id.radio4);
            radio5 = itemView.findViewById(R.id.radio5);


            //

        }
    }


//
//    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
//    public void filter(CharSequence charText) {
//
//        List<String> filteredList = new ArrayList<>();
//        String charString = charText.toString();
//
//        if (charString.length() == 0) {
//////         memberListFiltered = dataSet;
////         filteredList.addAll(dataSet);
//            Log.i("tag", String.valueOf("1:  "+charString.length())+dataSet);
//
//
//        }
//
//        if (charString.isEmpty() || charString.equalsIgnoreCase("")) {
//            memberListFiltered = questionList;
//        } else {
////         List<MemberInfoModel.Result> filteredList = new ArrayList<>();
//            for (String row : questionList) {
//                if (
//                        row.toLowerCase().contains(charString.toLowerCase())
////                                ||
////                                row.getMobile().toLowerCase().contains(charString.toLowerCase())
////                                ||
////                                row.getYear().toLowerCase().contains(charString.toLowerCase())
////                                ||
////                                row.getMember_id_String().toLowerCase().contains(charString.toLowerCase())
////                             ||
////                             row.getTakaAmount().toLowerCase().contains(charString.toLowerCase()) ||
////                             row.getPaymentStatus().toLowerCase().contains(charString.toLowerCase())
//                ) {
//                    filteredList.add(row);
//                }
//            }
//
//            Log.i("tag", "2:  "+String.valueOf(charString.length())+filteredList);
//
//            memberListFiltered = filteredList;
//        }
//
////     Filter.FilterResults filterResults = new Filter.FilterResults();
////     filterResults.values = memberListFiltered;
//        this.setData2(memberListFiltered, memberListFiltered, memberListFiltered);
//        this.notifyDataSetChanged();
//    }


}

