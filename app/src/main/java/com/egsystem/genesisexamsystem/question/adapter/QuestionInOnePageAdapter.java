package com.egsystem.genesisexamsystem.question.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.data.model.Answer;
import com.egsystem.genesisexamsystem.data.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionInOnePageAdapter extends RecyclerView.Adapter<QuestionInOnePageAdapter.CorrectAnswerViewHolder> {

    private List<String> dataSet = new ArrayList<>();
    private List<Question> questionList = new ArrayList<>();
    private List<Answer> answerList = new ArrayList<>();

    Context context;

    Cursor dataCursor;

    private List<String> mProductList;
    HashMap<Object, String> cartWithTitleName = new HashMap<Object, String>();

    String title;
    String category_id;
    private List<String> memberListFiltered = new ArrayList<>();

    public QuestionInOnePageAdapter(Context context) {
        this.context = context;
    }


    public QuestionInOnePageAdapter(Context context, Cursor cursor) {
        dataCursor = cursor;
        this.context = context;
        Log.d("tag1wwwww", " adapter: ");
    }

    public void setTitle(String title, String categoryId) {
        this.title = title;
        this.category_id = categoryId;
    }

//    questionList, answerList

    public void setData2(List<Question> questionList) {
        this.questionList = questionList;
//        this.answerList = answerList;

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
    public CorrectAnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_question_list, parent, false);
        CorrectAnswerViewHolder myViewHolder = new CorrectAnswerViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listener.onItemClick(v, myViewHolder.getPosition());
            }
        });
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final QuestionInOnePageAdapter.CorrectAnswerViewHolder holder, int position) {
        TextView tv_sl_no = holder.tv_sl_no;
        TextView tv_question_no = holder.tv_question_no;
//        TextView tv_question = holder.tv_question;
//        TextView tv_option1 = holder.tv_option1;
//        TextView tv_option2 = holder.tv_option2;
//        TextView tv_option3 = holder.tv_option3;
//        TextView tv_option4 = holder.tv_option4;
//        TextView tv_option5 = holder.tv_option5;
//        LinearLayout linear_result_ans = holder.linear_result_ans;
//        LinearLayout linear_start_exam = holder.linear_start_exam;
//        LinearLayout linear_sba = holder.linear_sba;
////        LinearLayout linear_ans_text = holder.linear_ans_text;


//        ImageView imageView = holder.imageView;
        CardView cardView1 = holder.cardView1;


        ImageView iv_ticked = holder.iv_ticked;

//        imageView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
//        cardView1.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fall_down_animation));

        Question question = questionList.get(position);
//        Answer answer = answerList.get(position);


        tv_question_no.setText(String.valueOf(position + 1));



        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean wrapInScrollView = true;

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                LayoutInflater inflater = ((Activity)context)).getLayoutInflater();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.single_item_question_for_dialog, null);
                builder.setCancelable(false);
                builder.setView(dialogView);

//                ImageView imageViewADPPCamera = dialogView.findViewById(R.id.imageViewADPPCamera);
//                ImageView imageViewADPPGallery = dialogView.findViewById(R.id.imageViewADPPGallery);
                TextView tv_cancel = dialogView.findViewById(R.id.tv_cancel);
                TextView tv_ok = dialogView.findViewById(R.id.tv_ok);


                LinearLayout linear_sba = dialogView.findViewById(R.id.linear_sba);
                LinearLayout linear_multiple_choice_a = dialogView.findViewById(R.id.linear_multiple_choice_a);
                LinearLayout linear_multiple_choice_b = dialogView.findViewById(R.id.linear_multiple_choice_b);
                LinearLayout linear_multiple_choice_c = dialogView.findViewById(R.id.linear_multiple_choice_c);
                LinearLayout linear_multiple_choice_d = dialogView.findViewById(R.id.linear_multiple_choice_d);
                LinearLayout linear_multiple_choice_e = dialogView.findViewById(R.id.linear_multiple_choice_e);
                View view5 = dialogView.findViewById(R.id.view5);
//                TextView tv_question_no = dialogView.findViewById(R.id.tv_question_no);
                TextView tv_question = dialogView.findViewById(R.id.tv_question);
                TextView tv_option1 = dialogView.findViewById(R.id.tv_option1);
                TextView tv_option2 = dialogView.findViewById(R.id.tv_option2);
                TextView tv_option3 = dialogView.findViewById(R.id.tv_option3);
                TextView tv_option4 = dialogView.findViewById(R.id.tv_option4);
                TextView tv_option5 = dialogView.findViewById(R.id.tv_option5);


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

                tv_question.setText(question.getQuestion());

                tv_option1.setText(question.getOption1());
                tv_option2.setText(question.getOption2());
                tv_option3.setText(question.getOption3());
                tv_option4.setText(question.getOption4());
                tv_option5.setText(question.getOption5());


                final AlertDialog alertDialogQuestion = builder.create();
                alertDialogQuestion.show();
                alertDialogQuestion.setCancelable(true);
                alertDialogQuestion.setCanceledOnTouchOutside(true);

                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialogQuestion.dismiss();
                    }
                });


                tv_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialogQuestion.dismiss();
                        iv_ticked.setVisibility(View.VISIBLE);
                    }
                });


            }
        });


//        Log.d("tag676767","answer.getSkipped(): "+ answer.getSkipped());
//        Log.d("tag676767","answer.getCorrect_ans_sba(): "+ answer.getCorrect_ans_sba());

        ///
        if (question.getQuestionType().equalsIgnoreCase("1")) {

//            linear_ans_text.setVisibility(View.VISIBLE);
//            linear_multiple_choice_a.setVisibility(View.VISIBLE);
//            linear_multiple_choice_b.setVisibility(View.VISIBLE);
//            linear_multiple_choice_c.setVisibility(View.VISIBLE);
//            linear_multiple_choice_d.setVisibility(View.VISIBLE);
//            linear_multiple_choice_e.setVisibility(View.VISIBLE);

//            linear_multiple_choice_a_correct.setVisibility(View.VISIBLE);
//            linear_multiple_choice_b_correct.setVisibility(View.VISIBLE);
//            linear_multiple_choice_c_correct.setVisibility(View.VISIBLE);
//            linear_multiple_choice_d_correct.setVisibility(View.VISIBLE);
//            linear_multiple_choice_e_correct.setVisibility(View.VISIBLE);


//            linear_sba.setVisibility(View.GONE);
//            view5.setVisibility(View.GONE);
        }

        if (question.getQuestionType().equalsIgnoreCase("2")) {

//            linear_sba.setVisibility(View.VISIBLE);
//            view5.setVisibility(View.VISIBLE);

//            linear_ans_text.setVisibility(View.GONE);
//            linear_multiple_choice_a.setVisibility(View.GONE);
//            linear_multiple_choice_b.setVisibility(View.GONE);
//            linear_multiple_choice_c.setVisibility(View.GONE);
//            linear_multiple_choice_d.setVisibility(View.GONE);
//            linear_multiple_choice_e.setVisibility(View.GONE);

//            linear_multiple_choice_a_correct.setVisibility(View.GONE);
//            linear_multiple_choice_b_correct.setVisibility(View.GONE);
//            linear_multiple_choice_c_correct.setVisibility(View.GONE);
//            linear_multiple_choice_d_correct.setVisibility(View.GONE);
//            linear_multiple_choice_e_correct.setVisibility(View.GONE);
        }


//        ////
//        if (answer.getCorrect_ans_sba().equalsIgnoreCase(question.getCorrect_ans_sba())) {
//
//            if (answer.getCorrect_ans_sba().equalsIgnoreCase("A")) {
//                radio1.setBackground(context.getDrawable(R.drawable.ic_bg_radio_your_correct));
//                radio1.setText("a");
//                radio1.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_sba().equalsIgnoreCase("B")) {
//                radio2.setBackground(context.getDrawable(R.drawable.ic_bg_radio_your_correct));
//                radio2.setText("b");
//                radio2.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_sba().equalsIgnoreCase("C")) {
//                radio3.setBackground(context.getDrawable(R.drawable.ic_bg_radio_your_correct));
//                radio3.setText("c");
//                radio3.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_sba().equalsIgnoreCase("D")) {
//                radio4.setBackground(context.getDrawable(R.drawable.ic_bg_radio_your_correct));
//                radio4.setText("d");
//                radio4.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_sba().equalsIgnoreCase("E")) {
//                radio5.setBackground(context.getDrawable(R.drawable.ic_bg_radio_your_correct));
//                radio5.setText("e");
//                radio5.setTextColor(context.getResources().getColor(R.color.white));
//            }
//
//        } else {
//
//            if (answer.getCorrect_ans_sba().equalsIgnoreCase("A")) {
//                radio1.setBackground(context.getDrawable(R.drawable.ic_radio_option1selected));
//                radio1.setText("a");
//                radio1.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_sba().equalsIgnoreCase("B")) {
//                radio2.setBackground(context.getDrawable(R.drawable.ic_radio_option1selected));
//                radio2.setText("b");
//                radio2.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_sba().equalsIgnoreCase("C")) {
//                radio3.setBackground(context.getDrawable(R.drawable.ic_radio_option1selected));
//                radio3.setText("c");
//                radio3.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_sba().equalsIgnoreCase("D")) {
//                radio4.setBackground(context.getDrawable(R.drawable.ic_radio_option1selected));
//                radio4.setText("d");
//                radio4.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_sba().equalsIgnoreCase("E")) {
//                radio5.setBackground(context.getDrawable(R.drawable.ic_radio_option1selected));
//                radio5.setText("e");
//                radio5.setTextColor(context.getResources().getColor(R.color.white));
//            }
//
//        }
//        ////
//
//        /////
//        if (answer.getCorrect_ans_a().equalsIgnoreCase(question.getCorrect_ans_a())) {
//            if (answer.getCorrect_ans_a().equalsIgnoreCase("T")) {
//                radio_a_1.setBackground(context.getDrawable(R.drawable.ic_bg_radio_your_correct));
//                radio_a_1.setText("T");
//                radio_a_1.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_a().equalsIgnoreCase("F")) {
//                radio_a_2.setBackground(context.getDrawable(R.drawable.ic_bg_radio_your_correct));
//                radio_a_2.setText("F");
//                radio_a_2.setTextColor(context.getResources().getColor(R.color.white));
//            }
//        } else {
//            if (answer.getCorrect_ans_a().equalsIgnoreCase("T")) {
//                radio_a_1.setBackground(context.getDrawable(R.drawable.ic_radio_option1selected));
//                radio_a_1.setText("T");
//                radio_a_1.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_a().equalsIgnoreCase("F")) {
//                radio_a_2.setBackground(context.getDrawable(R.drawable.ic_radio_option1selected));
//                radio_a_2.setText("F");
//                radio_a_2.setTextColor(context.getResources().getColor(R.color.white));
//            }
//        }
//
//
//        if (answer.getCorrect_ans_b().equalsIgnoreCase(question.getCorrect_ans_b())) {
//            if (answer.getCorrect_ans_b().equalsIgnoreCase("T")) {
//                radio_b_1.setBackground(context.getDrawable(R.drawable.ic_bg_radio_your_correct));
//                radio_b_1.setText("T");
//                radio_b_1.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_b().equalsIgnoreCase("F")) {
//                radio_b_2.setBackground(context.getDrawable(R.drawable.ic_bg_radio_your_correct));
//                radio_b_2.setText("F");
//                radio_b_2.setTextColor(context.getResources().getColor(R.color.white));
//            }
//        } else {
//            if (answer.getCorrect_ans_b().equalsIgnoreCase("T")) {
//                radio_b_1.setBackground(context.getDrawable(R.drawable.ic_radio_option1selected));
//                radio_b_1.setText("T");
//                radio_b_1.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_b().equalsIgnoreCase("F")) {
//                radio_b_2.setBackground(context.getDrawable(R.drawable.ic_radio_option1selected));
//                radio_b_2.setText("F");
//                radio_b_2.setTextColor(context.getResources().getColor(R.color.white));
//            }
//        }
//
//
//        if (answer.getCorrect_ans_c().equalsIgnoreCase(question.getCorrect_ans_c())) {
//            if (answer.getCorrect_ans_c().equalsIgnoreCase("T")) {
//                radio_c_1.setBackground(context.getDrawable(R.drawable.ic_bg_radio_your_correct));
//                radio_c_1.setText("T");
//                radio_c_1.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_c().equalsIgnoreCase("F")) {
//                radio_c_2.setBackground(context.getDrawable(R.drawable.ic_bg_radio_your_correct));
//                radio_c_2.setText("F");
//                radio_c_2.setTextColor(context.getResources().getColor(R.color.white));
//            }
//        } else {
//            if (answer.getCorrect_ans_c().equalsIgnoreCase("T")) {
//                radio_c_1.setBackground(context.getDrawable(R.drawable.ic_radio_option1selected));
//                radio_c_1.setText("T");
//                radio_c_1.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_c().equalsIgnoreCase("F")) {
//                radio_c_2.setBackground(context.getDrawable(R.drawable.ic_radio_option1selected));
//                radio_c_2.setText("F");
//                radio_c_2.setTextColor(context.getResources().getColor(R.color.white));
//            }
//        }
//
//
//        if (answer.getCorrect_ans_d().equalsIgnoreCase(question.getCorrect_ans_d())) {
//            if (answer.getCorrect_ans_d().equalsIgnoreCase("T")) {
//                radio_d_1.setBackground(context.getDrawable(R.drawable.ic_bg_radio_your_correct));
//                radio_d_1.setText("T");
//                radio_d_1.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_d().equalsIgnoreCase("F")) {
//                radio_d_2.setBackground(context.getDrawable(R.drawable.ic_bg_radio_your_correct));
//                radio_d_2.setText("F");
//                radio_d_2.setTextColor(context.getResources().getColor(R.color.white));
//            }
//        } else {
//            if (answer.getCorrect_ans_d().equalsIgnoreCase("T")) {
//                radio_d_1.setBackground(context.getDrawable(R.drawable.ic_radio_option1selected));
//                radio_d_1.setText("T");
//                radio_d_1.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_d().equalsIgnoreCase("F")) {
//                radio_d_2.setBackground(context.getDrawable(R.drawable.ic_radio_option1selected));
//                radio_d_2.setText("F");
//                radio_d_2.setTextColor(context.getResources().getColor(R.color.white));
//            }
//        }
//
//
//        if (answer.getCorrect_ans_e().equalsIgnoreCase(question.getCorrect_ans_e())) {
//            if (answer.getCorrect_ans_e().equalsIgnoreCase("T")) {
//                radio_e_1.setBackground(context.getDrawable(R.drawable.ic_bg_radio_your_correct));
//                radio_e_1.setText("T");
//                radio_e_1.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_e().equalsIgnoreCase("F")) {
//                radio_e_2.setBackground(context.getDrawable(R.drawable.ic_bg_radio_your_correct));
//                radio_e_2.setText("F");
//                radio_e_2.setTextColor(context.getResources().getColor(R.color.white));
//            }
//        } else {
//            if (answer.getCorrect_ans_e().equalsIgnoreCase("T")) {
//                radio_e_1.setBackground(context.getDrawable(R.drawable.ic_radio_option1selected));
//                radio_e_1.setText("T");
//                radio_e_1.setTextColor(context.getResources().getColor(R.color.white));
//            } else if (answer.getCorrect_ans_e().equalsIgnoreCase("F")) {
//                radio_e_2.setBackground(context.getDrawable(R.drawable.ic_radio_option1selected));
//                radio_e_2.setText("F");
//                radio_e_2.setTextColor(context.getResources().getColor(R.color.white));
//            }
//        }

        /////


//        ////
//        if (question.getCorrect_ans_sba().equalsIgnoreCase("A")) {
//            radio1_correct.setBackground(context.getDrawable(R.drawable.ic_bg_radio_correct));
//            radio1_correct.setText("a");
//            radio1_correct.setTextColor(context.getResources().getColor(R.color.white));
//        } else if (question.getCorrect_ans_sba().equalsIgnoreCase("B")) {
//            radio2_correct.setBackground(context.getDrawable(R.drawable.ic_bg_radio_correct));
//            radio2_correct.setText("b");
//            radio2_correct.setTextColor(context.getResources().getColor(R.color.white));
//        } else if (question.getCorrect_ans_sba().equalsIgnoreCase("C")) {
//            radio3_correct.setBackground(context.getDrawable(R.drawable.ic_bg_radio_correct));
//            radio3_correct.setText("c");
//            radio3_correct.setTextColor(context.getResources().getColor(R.color.white));
//        } else if (question.getCorrect_ans_sba().equalsIgnoreCase("D")) {
//            radio4_correct.setBackground(context.getDrawable(R.drawable.ic_bg_radio_correct));
//            radio4_correct.setText("d");
//            radio4_correct.setTextColor(context.getResources().getColor(R.color.white));
//        } else if (question.getCorrect_ans_sba().equalsIgnoreCase("E")) {
//            radio5_correct.setBackground(context.getDrawable(R.drawable.ic_bg_radio_correct));
//            radio5_correct.setText("e");
//            radio5_correct.setTextColor(context.getResources().getColor(R.color.white));
//        }
//        ////
//
//        /////
//        if (question.getCorrect_ans_a().equalsIgnoreCase("T")) {
//            radio_a_1_correct.setBackground(context.getDrawable(R.drawable.ic_bg_radio_correct));
//            radio_a_1_correct.setText("T");
//            radio_a_1_correct.setTextColor(context.getResources().getColor(R.color.white));
//        } else if (question.getCorrect_ans_a().equalsIgnoreCase("F")) {
//            radio_a_2_correct.setBackground(context.getDrawable(R.drawable.ic_bg_radio_correct));
//            radio_a_2_correct.setText("F");
//            radio_a_2_correct.setTextColor(context.getResources().getColor(R.color.white));
//        }
//
//        if (question.getCorrect_ans_b().equalsIgnoreCase("T")) {
//            radio_b_1_correct.setBackground(context.getDrawable(R.drawable.ic_bg_radio_correct));
//            radio_b_1_correct.setText("T");
//            radio_b_1_correct.setTextColor(context.getResources().getColor(R.color.white));
//        } else if (question.getCorrect_ans_b().equalsIgnoreCase("F")) {
//            radio_b_2_correct.setBackground(context.getDrawable(R.drawable.ic_bg_radio_correct));
//            radio_b_2_correct.setText("F");
//            radio_b_2_correct.setTextColor(context.getResources().getColor(R.color.white));
//        }
//
//        if (question.getCorrect_ans_c().equalsIgnoreCase("T")) {
//            radio_c_1_correct.setBackground(context.getDrawable(R.drawable.ic_bg_radio_correct));
//            radio_c_1_correct.setText("T");
//            radio_c_1_correct.setTextColor(context.getResources().getColor(R.color.white));
//        } else if (question.getCorrect_ans_c().equalsIgnoreCase("F")) {
//            radio_c_2_correct.setBackground(context.getDrawable(R.drawable.ic_bg_radio_correct));
//            radio_c_2_correct.setText("F");
//            radio_c_2_correct.setTextColor(context.getResources().getColor(R.color.white));
//        }
//
//        if (question.getCorrect_ans_d().equalsIgnoreCase("T")) {
//            radio_d_1_correct.setBackground(context.getDrawable(R.drawable.ic_bg_radio_correct));
//            radio_d_1_correct.setText("T");
//            radio_d_1_correct.setTextColor(context.getResources().getColor(R.color.white));
//        } else if (question.getCorrect_ans_d().equalsIgnoreCase("F")) {
//            radio_d_2_correct.setBackground(context.getDrawable(R.drawable.ic_bg_radio_correct));
//            radio_d_2_correct.setText("F");
//            radio_d_2_correct.setTextColor(context.getResources().getColor(R.color.white));
//        }
//
//        if (question.getCorrect_ans_e().equalsIgnoreCase("T")) {
//            radio_e_1_correct.setBackground(context.getDrawable(R.drawable.ic_bg_radio_correct));
//            radio_e_1_correct.setText("T");
//            radio_e_1_correct.setTextColor(context.getResources().getColor(R.color.white));
//        } else if (question.getCorrect_ans_e().equalsIgnoreCase("F")) {
//            radio_e_2_correct.setBackground(context.getDrawable(R.drawable.ic_bg_radio_correct));
//            radio_e_2_correct.setText("F");
//            radio_e_2_correct.setTextColor(context.getResources().getColor(R.color.white));
//        }
//
//        /////


//        Glide.with(context).load(doctorInfo3).into(imageView);
//
//        cardView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, QuestionActivity.class);
//                intent.putExtra("title", doctorInfo);
//                intent.putExtra("title_code", title_code);
//                context.startActivity(intent);
//            }
//        });


    }


    class CorrectAnswerViewHolder extends RecyclerView.ViewHolder {

        TextView tv_sl_no;
        TextView tv_question_no;
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

        LinearLayout linear_multiple_choice_a_correct;
        LinearLayout linear_multiple_choice_b_correct;
        LinearLayout linear_multiple_choice_c_correct;
        LinearLayout linear_multiple_choice_d_correct;
        LinearLayout linear_multiple_choice_e_correct;
        LinearLayout linear_ans_text;

        ImageView imageView;
        ImageView iv_ticked;
        CardView cardView1;
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

        RadioButton radio_a_1_correct;
        RadioButton radio_a_2_correct;
        RadioButton radio_b_1_correct;
        RadioButton radio_b_2_correct;
        RadioButton radio_c_1_correct;
        RadioButton radio_c_2_correct;
        RadioButton radio_d_1_correct;
        RadioButton radio_d_2_correct;
        RadioButton radio_e_1_correct;
        RadioButton radio_e_2_correct;

        RadioButton radio1_correct;
        RadioButton radio2_correct;
        RadioButton radio3_correct;
        RadioButton radio4_correct;
        RadioButton radio5_correct;


        public CorrectAnswerViewHolder(View itemView) {
            super(itemView);
            tv_sl_no = itemView.findViewById(R.id.tv_sl_no);
            tv_question_no = itemView.findViewById(R.id.tv_question_no);
            tv_question = itemView.findViewById(R.id.tv_question);
            tv_option1 = itemView.findViewById(R.id.tv_option1);
            tv_option2 = itemView.findViewById(R.id.tv_option2);
            tv_option3 = itemView.findViewById(R.id.tv_option3);
            tv_option4 = itemView.findViewById(R.id.tv_option4);
            tv_option5 = itemView.findViewById(R.id.tv_option5);

            cardView1 = itemView.findViewById(R.id.cardView1);
            view5 = itemView.findViewById(R.id.view5);

            iv_ticked = itemView.findViewById(R.id.iv_ticked);

            linear_result_ans = itemView.findViewById(R.id.linear_result_ans);
            linear_start_exam = itemView.findViewById(R.id.linear_start_exam);
            linear_sba = itemView.findViewById(R.id.linear_sba);

            linear_multiple_choice_a = itemView.findViewById(R.id.linear_multiple_choice_a);
            linear_multiple_choice_b = itemView.findViewById(R.id.linear_multiple_choice_b);
            linear_multiple_choice_c = itemView.findViewById(R.id.linear_multiple_choice_c);
            linear_multiple_choice_d = itemView.findViewById(R.id.linear_multiple_choice_d);
            linear_multiple_choice_e = itemView.findViewById(R.id.linear_multiple_choice_e);

            linear_multiple_choice_a_correct = itemView.findViewById(R.id.linear_multiple_choice_a_correct);
            linear_multiple_choice_b_correct = itemView.findViewById(R.id.linear_multiple_choice_b_correct);
            linear_multiple_choice_c_correct = itemView.findViewById(R.id.linear_multiple_choice_c_correct);
            linear_multiple_choice_d_correct = itemView.findViewById(R.id.linear_multiple_choice_d_correct);
            linear_multiple_choice_e_correct = itemView.findViewById(R.id.linear_multiple_choice_e_correct);

            linear_ans_text = itemView.findViewById(R.id.linear_ans_text);

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
            radio_a_1_correct = itemView.findViewById(R.id.radio_a_1_correct);
            radio_a_2_correct = itemView.findViewById(R.id.radio_a_2_correct);
            radio_b_1_correct = itemView.findViewById(R.id.radio_b_1_correct);
            radio_b_2_correct = itemView.findViewById(R.id.radio_b_2_correct);
            radio_c_1_correct = itemView.findViewById(R.id.radio_c_1_correct);
            radio_c_2_correct = itemView.findViewById(R.id.radio_c_2_correct);
            radio_d_1_correct = itemView.findViewById(R.id.radio_d_1_correct);
            radio_d_2_correct = itemView.findViewById(R.id.radio_d_2_correct);
            radio_e_1_correct = itemView.findViewById(R.id.radio_e_1_correct);
            radio_e_2_correct = itemView.findViewById(R.id.radio_e_2_correct);
            radio1_correct = itemView.findViewById(R.id.radio1_correct);
            radio2_correct = itemView.findViewById(R.id.radio2_correct);
            radio3_correct = itemView.findViewById(R.id.radio3_correct);
            radio4_correct = itemView.findViewById(R.id.radio4_correct);
            radio5_correct = itemView.findViewById(R.id.radio5_correct);


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

