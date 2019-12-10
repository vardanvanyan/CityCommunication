package com.example.problem.Model;

public class ProblemsModel {

    private String userimg;
    private String name;
    private String problemimg;
    private String problemDescription;
    private String address;
    private String id;

    public ProblemsModel() {
    }

    public ProblemsModel(String problemDescription, String address) {
        this.problemDescription = problemDescription;
        this.address = address;
    }

    public String getUserImg() {
        return userimg;
    }

    public void setUserImg(int userImg) {
        userImg = userImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getProblemImg() {
        return problemimg;
    }

    public void setProblemImg(String problemImg) {
        this.problemimg = problemImg;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
