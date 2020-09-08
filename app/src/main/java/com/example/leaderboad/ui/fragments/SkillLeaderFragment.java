package com.example.leaderboad.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaderboad.models.Learner;
import com.example.leaderboad.R;
import com.example.leaderboad.adapters.SkillAdapter;
import com.example.leaderboad.models.PageViewModel;


import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class SkillLeaderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    ArrayList<Learner> learners=new ArrayList<Learner>();
    TextView textView;

    private PageViewModel pageViewModel;

    public static SkillLeaderFragment newInstance(int index) {
        SkillLeaderFragment fragment = new SkillLeaderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_skill_iq, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.rv_learner);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                LinearLayoutManager learnersLayoutManager=new LinearLayoutManager
                        (getContext(),
                                LinearLayoutManager.VERTICAL,
                                false);
                recyclerView.setLayoutManager(learnersLayoutManager);
                learners=Learner.getskillLearnersfromJson(s);
                SkillAdapter adapter=new SkillAdapter(learners,getContext());
                recyclerView.setAdapter(adapter);


            }


        });
        return root;
    }


}