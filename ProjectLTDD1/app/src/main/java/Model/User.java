package Model;

import Orther.HashPassword;

public class User {
    int id;
    String userName,passWord,phone,name,avatar;

    public User() {
    }

    public User(int id, String userName, String passWord,String name,  String phone, String avatar) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.name = name;
        this.avatar = avatar;
    }

    public User(String userName, String passWord, String name, String phone, String avatar) {
        this.userName = userName;
        this.passWord = HashPassword.hashPassword(passWord);
        this.phone = phone;
        this.name = name;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}