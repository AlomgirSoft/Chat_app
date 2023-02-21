package com.bdcit.mymasssenger;

public class User_Model
{
    String userNmae,userEmail,userPhone,userId,profileImage;

    public User_Model(String userNmae, String userEmail, String userPhone, String userId, String profileImage) {
        this.userNmae = userNmae;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userId = userId;
        this.profileImage = profileImage;
    }

    public User_Model() {

    }

    public String getUserNmae() {
        return userNmae;
    }

    public void setUserNmae(String userNmae) {
        this.userNmae = userNmae;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
