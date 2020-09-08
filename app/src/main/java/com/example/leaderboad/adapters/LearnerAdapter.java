package com.example.leaderboad.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaderboad.R;
import com.example.leaderboad.models.Learner;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LearnerAdapter extends RecyclerView.Adapter<LearnerAdapter.SkillViewHolder> {
    private ArrayList<Learner> learner;
    private Context context;


    public LearnerAdapter(ArrayList<Learner> learner, Context context) {
        this.learner = learner;
        this.context = context;
    }
//
//    public SkillAdapter(Context context) {
//        this.context = context;
//    }
//
//    public SkillAdapter(ArrayList<Learner> learner) {
//        this.learner = learner;
//    }

    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.learner_item, parent, false);
        return new SkillViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        Learner learners=learner.get(position);
        holder.bind(learners);
    }

    @Override
    public int getItemCount() {
        return learner.size();
    }

    public class SkillViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView country;
        TextView hours;
        ImageView img;
        TextView role;

        public SkillViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            country = itemView.findViewById(R.id.country);
            hours = itemView.findViewById(R.id.hours);
            img=itemView.findViewById(R.id.image);
            role=itemView.findViewById(R.id.role);



        }

        public void bind(Learner learner) {
            name.setText(learner.names);
            country.setText(learner.country);
            hours.setText(learner.score);

            Picasso.with(context).load(learner.imgURl).into(img);



        }
    }
}