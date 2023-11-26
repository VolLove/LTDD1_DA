package Model;

import Orther.HashPassword;

public class User {
    int id;
    String userName, passWord;

    public User() {
    }

    public User(int id, String userName, String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = HashPassword.hashPassword(passWord);
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
        this.passWord = HashPassword.hashPassword(passWord);
    }

    @Override
    public String toString() {
        return "id=" + id + ", userName='" + userName + '\'' + ", passWord='" + passWord;
    }
}
