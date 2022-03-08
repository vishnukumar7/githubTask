package com.app.githubtask;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit=null;

    private static Retrofit getClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://gorest.co.in/")
                    .build();
        }
        return retrofit;
    }

    public static ApiInterface getAPI(){
        return getClient().create(ApiInterface.class);
    }
}
