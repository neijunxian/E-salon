package com.example.assigment.Modal;

public class Detail
{
    private String whom,picture,price,time,pid ;
    public Detail()
    {

    }

    public Detail(String whom, String picture, String price, String time, String pid) {
        this.whom = whom;
        this.picture = picture;
        this.price = price;
        this.time = time;
        this.pid = pid;
    }

    public String getWhom() {
        return whom;
    }

    public void setWhom(String whom) {
        this.whom = whom;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
