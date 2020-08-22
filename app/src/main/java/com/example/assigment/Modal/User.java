package com.example.assigment.Modal;

public class User {
    private String id;
    private String username;
    private String fullname;
    private String gender;
    private String birth;
    private String imageURL;
    private String phone;
    private String email;
    private String password;

    public User(String id, String username, String fullname, String gender, String birth, String imageURL, String phone, String email, String password) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.gender = gender;
        this.birth = birth;
        this.imageURL = imageURL;
        this.phone = phone;
        this.email = email;
        this.password = password;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
