package com.example.shindongkyu.loginsignupcomplete.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("email")
    public String email;
    @SerializedName("pwd")
    public String pwd;

    public User(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }
}
