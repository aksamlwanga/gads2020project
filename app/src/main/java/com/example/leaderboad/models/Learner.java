package com.example.leaderboad.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Learner {
    public String names;
    public String score;
    public String country;
    public String imgURl;

    public Learner(String names, String score, String country, String imgURl) {
        this.names = names;
        this.score = score;
        this.country = country;
        this.imgURl = imgURl;

    }
    public static ArrayList<Learner> getskillLearnersfromJson(String json) {
        ArrayList<Learner> learners=new ArrayList<>();
        try {
            JSONArray jsonArray=new JSONArray(json);
            int noOfLearners=jsonArray.length();
            for(int i=0;i<noOfLearners;i++){
                JSONObject learnerJson=jsonArray.getJSONObject(i);
                String nameJson=learnerJson.getString("name");
                String scoreJson=learnerJson.getString("score");
                String countryJson=learnerJson.getString("country");
                String imgJson=learnerJson.getString("badgeUrl");
                Learner learner=new Learner(
                        nameJson,scoreJson,countryJson,imgJson

                );

              learners.add(learner);
            }
        } catch (JSONException e) {
            Log.d("Error",e.toString());
//            e.printStackTrace();
        }

        return learners;
    }
    public static ArrayList<Learner> getLearnersfromJson(String json) {
        ArrayList<Learner> learners=new ArrayList<>();

        try {
            JSONArray jsonArray=new JSONArray(json);
            int noOfLearners=jsonArray.length();
            for(int i=0;i<noOfLearners;i++){
                JSONObject learnerJson=jsonArray.getJSONObject(i);
                String nameJson=learnerJson.getString("name");
                String scoreJson=learnerJson.getString("hours");
                String countryJson=learnerJson.getString("country");
                String imgJson=learnerJson.getString("badgeUrl");
                Learner learner=new Learner(
                        nameJson,scoreJson,countryJson,imgJson
                );

                learners.add(learner);
            }
        } catch (JSONException e) {
            Log.d("Error",e.toString());
//            e.printStackTrace();
        }

        return learners;
    }


}

