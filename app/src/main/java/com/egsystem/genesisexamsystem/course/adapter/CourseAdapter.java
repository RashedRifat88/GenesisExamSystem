package com.egsystem.genesisexamsystem.course.adapter;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.exam.ExamActivity;
import com.egsystem.genesisexamsystem.model.DoctorCourseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<DoctorCourseModel.DoctorCourse> dataSet = new ArrayList<>();
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

    public CourseAdapter(Context context) {
        this.context = context;
    }



    public CourseAdapter(Context context, Cursor cursor) {
        dataCursor = cursor;
        this.context = context;
        Log.d("tag1wwwww", " adapter: ");
    }

    public void setTitle(String title, String categoryId) {
        this.title = title;
        this.category_id = categoryId;
    }

//    public void setData2(List<String> dataSet2, List<String> dataSet3, List<String> title_code_list) {
//        this.dataSet2 = dataSet2;
//        this.dataSet3 = dataSet3;
//        this.title_code_list = title_code_list;
//        Log.d("tagResponse", " dataSet2: " + dataSet2);
//    }



    public void setData(List<DoctorCourseModel.DoctorCourse> dataSet) {
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
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_course_list, parent, false);
        CourseViewHolder myViewHolder = new CourseViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listener.onItemClick(v, myViewHolder.getPosition());
            }
        });
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(final CourseAdapter.CourseViewHolder holder, int position) {
//        TextView txtSlNo = holder.txtSlNo;
        TextView tv_title1 = holder.tv_title1;
        TextView tv_title2 = holder.tv_title2;
        TextView tv_session = holder.tv_session;
        TextView tv_course = holder.tv_course;
        TextView tv_discipline = holder.tv_discipline;
        TextView tv_reg_num = holder.tv_reg_num;
        TextView tv_year = holder.tv_year;

//        LinearLayout linear1 = holder.linear1;
//        ImageView imageView = holder.imageView;
        CardView cardview1 = holder.cardview1;

//        imageView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
//        cardview1.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fall_down_animation));

//        String doctorInfo = dataSet2.get(position);
//        String doctorInfo3 = dataSet3.get(position);
//        String title_code = title_code_list.get(position);

        DoctorCourseModel.DoctorCourse courseModel = dataSet.get(position);
        Log.d("tagResponse", " courseModel: " + courseModel);



        int aPosition = position + 1;

            if (aPosition % 3 == 1){
                cardview1.setBackgroundResource(R.drawable.ic_course_purple_bg);
            }else if (aPosition % 3 == 2){
                cardview1.setBackgroundResource(R.drawable.ic_course_green_bg);
            }else if (aPosition % 3 == 0){
                cardview1.setBackgroundResource(R.drawable.ic_course_blue_bg);
            }

        Log.d("tag333", " dataSet2...: " + dataSet2);

        Log.d("tag333", " position...: " + position);
        Log.d("tag333", " mProductList...: " + mProductList);

//        tv_title1.setText(doctorInfo);
        tv_title2.setText(courseModel.getCourseName());
        tv_session.setText(courseModel.getSession());
        tv_course.setText(courseModel.getCourseName());
        tv_discipline.setText(courseModel.getBatchName());
        tv_reg_num.setText(courseModel.getRegNo());
        tv_year.setText(String.valueOf(courseModel.getYear()));

        String course_id = String.valueOf(courseModel.getId());

//        Glide.with(context).load(doctorInfo3).into(imageView);

        cardview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExamActivity.class);
                intent.putExtra("course_id", course_id);
                context.startActivity(intent);
            }
        });


    }




    class CourseViewHolder extends RecyclerView.ViewHolder {

        TextView txtSlNo;
        TextView tv_title1;
        TextView tv_title2;
        TextView tv_session;
        TextView tv_course;
        TextView tv_discipline;
        TextView tv_reg_num;
        TextView tv_year;
        LinearLayout linear1;

        ImageView imageView;
        CardView cardview1;

        public CourseViewHolder(View itemView) {
            super(itemView);
            tv_title1 = itemView.findViewById(R.id.tv_title1);
            tv_title2 = itemView.findViewById(R.id.tv_title2);
            tv_session = itemView.findViewById(R.id.tv_session);
            tv_course = itemView.findViewById(R.id.tv_course);
            tv_discipline = itemView.findViewById(R.id.tv_discipline);
            tv_reg_num = itemView.findViewById(R.id.tv_reg_num);
            tv_year = itemView.findViewById(R.id.tv_year);

//            imageView = itemView.findViewById(R.id.imageView);
            cardview1 = itemView.findViewById(R.id.cardview1);

        }
    }






}

