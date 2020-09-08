package com.example.leaderboad.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leaderboad.api.ApiClient;
import com.example.leaderboad.R;
import com.example.leaderboad.models.RequestData;
import com.example.leaderboad.ui.fragments.LeaderBoadDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.webkit.URLUtil.isValidUrl;

public class SubmitActivity extends AppCompatActivity {
   private static EditText fname;
    private static EditText lname;
    private static EditText email;
    private static EditText projectLink;

    LeaderBoadDialogFragment newFragment = LeaderBoadDialogFragment.newInstance();
    private static ConstraintLayout container;
    private static ConstraintLayout main_frame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        fname = findViewById(R.id.firstName);
        lname = findViewById(R.id.lastName);
        email = findViewById(R.id.emailAddress);
        projectLink = findViewById(R.id.link);
        container=findViewById(R.id.body);
       main_frame= findViewById(R.id.wrapper);


    }

    public void prepareSubmission(View view) {
        String fName = fname.getText().toString().trim();
        String lName = lname.getText().toString().trim();
        String emailAddress = email.getText().toString().trim();
        String gitHubLink = projectLink.getText().toString().trim();
        String message;
        if (fName.isEmpty() || lName.isEmpty() || emailAddress.isEmpty() || gitHubLink.isEmpty()) {
             message =getString(R.string.fields);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }else if(!isValidEmail(emailAddress)){
            message=getString(R.string.valide_email);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

        }
        else if(!isValidUrl(gitHubLink)){
          message ="Enter a valid URL.";
          Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }else showDialog() ;


        }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
   public void showDialog() {

       FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        newFragment.show(ft, "dialog");
       container.setVisibility(View.INVISIBLE);
       main_frame.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.tintcolor)));



    }

    public RequestData createRequest(){
        RequestData requestData=new RequestData();
       requestData.setFname(fname.getText().toString().trim());
        requestData.setLname(lname.getText().toString().trim());
        requestData.setEmail(email.getText().toString().trim()) ;
        requestData.setLink(projectLink.getText().toString().trim());
        return requestData;

    }

    public  void  submitDataRequest(RequestData requestData) {
        Call<Void> responseCall = ApiClient.getRequestData().submitData(requestData.getEmail(),
                requestData.getFname(), requestData.getLname(), requestData.getLink());
        responseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    LeaderBoadDialogFragment failFragment = LeaderBoadDialogFragment.newInstance();
                    Bundle bundle = new Bundle();
                    bundle.putString("confirm", "confirm");
                    failFragment.setArguments(bundle);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    failFragment.show(ft, "dialog");
                    container.setVisibility(View.INVISIBLE);
                    main_frame.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.tintcolor)));

                    ;
                }else{
                    LeaderBoadDialogFragment failFragment = LeaderBoadDialogFragment.newInstance();
                    Bundle bundle = new Bundle();
                    bundle.putString("fail", "fail");
                    failFragment.setArguments(bundle);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    failFragment.show(ft, "dialog");
                    container.setVisibility(View.INVISIBLE);
                    main_frame.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.tintcolor)));


                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                LeaderBoadDialogFragment failFragment = LeaderBoadDialogFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("fail", "fail");
                failFragment.setArguments(bundle);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                failFragment.show(ft, "dialog");
                container.setVisibility(View.INVISIBLE);
                main_frame.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.tintcolor)));



            }
        });
    }


    public void confirmCancel(View view) {
        if(R.id.confirm_btn==view.getId()){

           submitDataRequest(createRequest());
            newFragment.dismiss();
            container.setVisibility(View.VISIBLE);
        }else if(R.id.cancel_btn==view.getId()){
            newFragment.dismiss();
            container.setVisibility(View.VISIBLE);
            main_frame.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.untintcolor)));


        }

    }
    public static void setMainVisibility(){
        container.setVisibility(View.VISIBLE);
        main_frame.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
    }


    public void onBackPressed(View view) {
        onBackPressed();
    }
}

