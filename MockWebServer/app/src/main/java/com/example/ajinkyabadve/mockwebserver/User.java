package com.example.ajinkyabadve.mockwebserver;

/**
 * Created by ajinkyabadve on 21/11/17.
 */

public class User {
    public String login;
    public int followers;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", followers=" + followers +
                '}';
    }
}
