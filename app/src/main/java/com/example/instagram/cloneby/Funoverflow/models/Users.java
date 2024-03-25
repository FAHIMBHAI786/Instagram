package com.example.instagram.cloneby.Funoverflow.models;

import com.google.firebase.Timestamp;

public class Users {
   private String profilePic, userName,mail,password,userId,lastMessage,phoneNumber;
   private Timestamp timeStamp;

    public Users(String profilePic, String userName, String mail, String password, String userId, String lastMessage) {
        this.profilePic = profilePic;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.lastMessage = lastMessage;
    }

    public Users(){}

    //SignUp constructor
    public Users(String userName, String mail, String password, String userId) {

        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.userId = userId;

    }

    public Users(String userName, String phoneNumber, Timestamp timeStamp) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.timeStamp = timeStamp;
    }

    public Users(String userName, Timestamp timeStamp) {
        this.userName = userName;
        this.timeStamp = timeStamp;
    }

    public Users(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
