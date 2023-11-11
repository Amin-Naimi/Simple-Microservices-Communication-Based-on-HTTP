package com.Mohamed.springService;

public class model {
    private String userName;
    private String password;

    public model(){

    }

    public model(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' ;
    }
}
