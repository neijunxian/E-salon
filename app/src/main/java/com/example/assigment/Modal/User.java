package com.example.assigment.Modal;

public class User {
    private String id;
    private String username;
    private String fullname;
    private String gender;
    private String brith;
    private String imageURL;

    public User(String id, String username, String fullname, String gender, String brith, String imageURL) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.gender = gender;
        this.brith = brith;
        this.imageURL = imageURL;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBrith() {
        return brith;
    }

    public void setBrith(String brith) {
        this.brith = brith;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
