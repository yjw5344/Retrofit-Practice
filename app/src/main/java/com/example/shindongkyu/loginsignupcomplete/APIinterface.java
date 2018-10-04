package com.example.shindongkyu.loginsignupcomplete;

import com.example.shindongkyu.loginsignupcomplete.Model.Example;
import com.example.shindongkyu.loginsignupcomplete.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIinterface {

    @POST("login/signin")
    Call<Example> login(@Body User user);

    @POST("login/signup")
    Call<Example> join(@Body User user2);





}
