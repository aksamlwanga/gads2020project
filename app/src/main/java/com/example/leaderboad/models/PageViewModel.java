package com.example.leaderboad.models;

import android.os.StrictMode;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.leaderboad.api.Api;

import java.io.IOException;
import java.net.URL;

public class PageViewModel extends ViewModel {

    private String LearningLeaders = "/api/hours";
    private String SkillIQLeaders = "/api/skilliq";
    private String result;
    URL url;


    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
            if (input == 0) {
                url = Api.buildUrl(LearningLeaders);
                try {
                    result = Api.getJson(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
            else
                url = Api.buildUrl(SkillIQLeaders);
            try {
                 result=Api.getJson(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }


}