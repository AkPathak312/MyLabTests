package com.example.mylabtests;

public class AppointmentModel {


    public String type,transid,name,gender,age,mobile,email,city,dept,doa,status;

    public AppointmentModel(String type, String transid, String name, String gender, String age, String mobile, String email, String city, String dept, String doa, String status) {
        this.type = type;
        this.transid = transid;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.mobile = mobile;
        this.email = email;
        this.city = city;
        this.dept = dept;
        this.doa = doa;
        this.status = status;
    }
}
