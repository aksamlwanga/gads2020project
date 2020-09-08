package com.example.leaderboad.api;



import com.example.leaderboad.retrofit.Service;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ApiClient {
    private static Retrofit getRetrofit(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return retrofit;
    }
    public static Service getRequestData(){
        Service service=getRetrofit().create(Service.class);
        return service;
    }
}
