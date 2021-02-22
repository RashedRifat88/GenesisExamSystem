package com.egsystem.genesisexamsystem.ui.more;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.course.CourseActivity;

public class MoreFragment extends Fragment {

    LinearLayout linear_exam;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        linear_exam = view.findViewById(R.id.linear_exam);

        linear_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , CourseActivity.class);
                intent.putExtra("from_where", "MAIN");
                startActivity(intent);
            }
        });
    }

}