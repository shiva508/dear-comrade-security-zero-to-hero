package com.comrade.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvalidLoginResponse {
    private String username;
    private String password;
    private String message;

    public InvalidLoginResponse() {
        this.username = "Invalid Username";
        this.password = "Invalid Password";
    }

}
