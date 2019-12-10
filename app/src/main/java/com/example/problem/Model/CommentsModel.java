package com.example.problem.Model;

public class CommentsModel {

    private String comments;
    private String userName;
    private String userImg;

    public CommentsModel() {
    }

    public CommentsModel(String commImg, String commName, String commDescription) {
        this.userImg = commImg;
        this.userName = commName;
        this.comments = commDescription;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
