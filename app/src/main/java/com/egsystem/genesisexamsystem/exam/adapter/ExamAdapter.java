package com.egsystem.genesisexamsystem.exam.adapter;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.Result.ResultActivity;
import com.egsystem.genesisexamsystem.exam.ExamActivity;
import com.egsystem.genesisexamsystem.model.DoctorExamListModel;
import com.egsystem.genesisexamsystem.question.PreeQuestionActivity;
import com.egsystem.genesisexamsystem.question.QuestionActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {

    private List<DoctorExamListModel.Exam> dataSet = new ArrayList<>();
    private List<String> dataSet2 = new ArrayList<>();
    private List<String> dataSet3 = new ArrayList<>();
    private List<String> title_code_list = new ArrayList<>();
    Context context;

    Cursor dataCursor;

    private List<String> mProductList;
    HashMap<Object, String> cartWithTitleName = new HashMap<Object, String>();

    String title;
    String category_id;
    private List<String> memberListFiltered = new ArrayList<>();

    public ExamAdapter(Context context) {
        this.context = context;
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

            if (aPosition % 4 == 1){
                cardview1.setBackgroundResource(R.drawable.ic_exam_bg_1_green);

                linear_result_ans.setVisibility(View.GONE);
                linear_start_exam.setVisibility(View.VISIBLE);

            }else if (aPosition % 4 == 2){
                cardview1.setBackgroundResource(R.drawable.ic_exam_bg_4_purple);

                linear_start_exam.setVisibility(View.GONE);
                linear_result_ans.setVisibility(View.VISIBLE);

            }else if (aPosition % 4 == 3){
                cardview1.setBackgroundResource(R.drawable.ic_exam_bg_3_blue);

                linear_result_ans.setVisibility(View.GONE);
                linear_start_exam.setVisibility(View.VISIBLE);

            }else if (aPosition % 4 == 0){
                cardview1.setBackgroundResource(R.drawable.ic_exam_bg_2_orange);

                linear_start_exam.setVisibility(View.GONE);
                linear_result_ans.setVisibility(View.VISIBLE);

            }


        Log.d("tag333", " position...: " + position);
        Log.d("tag333", " mProductList...: " + mProductList);

        tv_sl_no.setText(String.valueOf(aPosition));

//        tv_exam_type.setText(doctor_exam);

        int duration_exam = doctor_exam.getDuration();
        int duration_exam_in_minutes = duration_exam/60;

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
                Intent intent = new Intent(context, PreeQuestionActivity.class);
//                intent.putExtra("title", doctor_exam);
                context.startActivity(intent);
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




    class ExamViewHolder extends RecyclerView.ViewHolder {

        TextView tv_sl_no;
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

