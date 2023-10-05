package com.lisau.myabsensi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Users {

    @SerializedName("absensi")
    private ArrayList<User> userArrayList;

    public Users() {

    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }
}