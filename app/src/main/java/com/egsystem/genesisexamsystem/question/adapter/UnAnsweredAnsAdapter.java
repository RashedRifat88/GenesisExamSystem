package com.egsystem.genesisexamsystem.question.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.data.database.ExamDbHelper;
import com.egsystem.genesisexamsystem.data.model.Answer;
import com.egsystem.genesisexamsystem.question.QuestionInOnePageActivity;


import java.util.ArrayList;
import java.util.List;

public class UnAnsweredAnsAdapter extends RecyclerView.Adapter<UnAnsweredAnsAdapter.AnswerViewHolder> {

    private List<String> dataSet = new ArrayList<>();
//    private List<Question> answerList = new ArrayList<>();
    private List<Answer> answerList = new ArrayList<>();

    Context context;
    ExamDbHelper dbHelper;

    private List<String> memberListFiltered = new ArrayList<>();

    public UnAnsweredAnsAdapter(Context context) {
        this.context = context;
        dbHelper = ExamDbHelper.getInstance(context);
    }


    public void setData2(List<Answer> answerList) {
        this.answerList = answerList;
        Log.d("tagResponse", " answerList: " + answerList);
    }


    @Override
    public int getItemCount() {
        return answerList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_unanswered_ans, parent, false);
        AnswerViewHolder myViewHolder = new AnswerViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listener.onItemClick(v, myViewHolder.getPosition());
            }
        });
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(final UnAnsweredAnsAdapter.AnswerViewHolder holder, int position) {
        TextView tv_sl_no = holder.tv_sl_no;
        TextView tv_ans_no = holder.tv_ans_no;
        TextView tv_save = holder.tv_save;
        TextView tv_edit = holder.tv_edit;
        TextView tv_answer = holder.tv_answer;

        Answer answer = answerList.get(position);

//        tv_ans_no.setText("(position + 1)");
        tv_ans_no.setText(answer.getQuestionSl());

        tv_ans_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionInOnePageActivity.scrollSpecificPosition(Integer.parseInt(answer.getQuestionSl()), answerList.size());
            }
        });


//        Glide.with(context).load(doctorInfo3).into(imageView);

    }


    class AnswerViewHolder extends RecyclerView.ViewHolder {

        TextView tv_sl_no;
        TextView tv_ans_no;
        TextView tv_edit;
        TextView tv_save;
        TextView tv_answer;

        public AnswerViewHolder(View itemView) {
            super(itemView);
            tv_sl_no = itemView.findViewById(R.id.tv_sl_no);
            tv_ans_no = itemView.findViewById(R.id.tv_ans_no);
            tv_edit = itemView.findViewById(R.id.tv_edit);
            tv_save = itemView.findViewById(R.id.tv_save);

        }
    }



}

