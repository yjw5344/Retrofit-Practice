package com.example.shindongkyu.loginsignupcomplete.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("ink")
    @Expose
    public Integer ink;

}