package com.example.shindongkyu.loginsignupcomplete.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example{

    @SerializedName("stat")
    @Expose
    public String stat;
    @SerializedName("data")
    @Expose
    public Data data;



}