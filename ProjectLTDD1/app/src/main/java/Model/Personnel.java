package Model;

import Orther.HashPassword;

public class Personnel {
    int id_personnel;
    String userName,passWord,phone,name,avatar;

    public Personnel() {
    }

    public Personnel(int id_personnel, String userName, String passWord, String name, String phone, String avatar) {
        this.id_personnel = id_personnel;
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.name = name;
        this.avatar = avatar;
    }

    public Personnel(String userName, String passWord, String name, String phone, String avatar) {
        this.userName = userName;
        this.passWord = HashPassword.hashPassword(passWord);
        this.phone = phone;
        this.name = name;
        this.avatar = avatar;
    }

    public int getId_personnel() {
        return id_personnel;
    }

    public void setId(int id_personnel) {
        this.id_personnel = id_personnel;
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
