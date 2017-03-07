package com.company.controller.query.parameter;

/**
 * Created by yulia on 05.03.17.
 */
public class ConnectParameters extends Parameters {
    private String database;
    private String userName;
    private String password;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
