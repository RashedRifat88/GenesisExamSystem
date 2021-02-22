package com.egsystem.genesisexamsystem.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.egsystem.genesisexamsystem.MainActivity;
import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.course.CourseActivity;
import com.egsystem.genesisexamsystem.data.shared_pref.SharedData;

public class HomeFragment extends Fragment {

    LinearLayout linear_exam;
    TextView tv_more,tv_name,tv_bmdc_no ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        initView(root);

        return root;
    }

    private void initView(View view) {
        tv_name = view.findViewById(R.id.tv_name);
        tv_bmdc_no = view.findViewById(R.id.tv_bmdc_no);

        tv_name.setText(SharedData.getUSER_NAME(getContext()));
        tv_bmdc_no.setText("BMDC NO: " + SharedData.getBMDC_NO(getContext()));

        linear_exam = view.findViewById(R.id.linear_exam);
        tv_more = view.findViewById(R.id.tv_more);

        linear_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , CourseActivity.class);
                intent.putExtra("from_where", "MAIN");
                startActivity(intent);
            }
        });

        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_moreFragment);
            }
        });
    }
}