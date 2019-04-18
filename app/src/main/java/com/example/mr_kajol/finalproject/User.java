package com.example.mr_kajol.finalproject;

import java.util.HashMap;
import java.util.Map;

public class User {
    public String Name, Email, Phone,Password, Height, Weight,Sex,Age,PAL;

    public User(){

    }

    public User(String name, String email, String phone, String password, String height, String weight, String sex, String age,String PAL) {
        this.Name = name;
        this.Email = email;
       this.Phone = phone;
        this.Password = password;
        this.Height = height;
        this.Weight = weight;
       this.Sex = sex;
        this.Age = age;
        this.PAL = PAL;
    }
}
