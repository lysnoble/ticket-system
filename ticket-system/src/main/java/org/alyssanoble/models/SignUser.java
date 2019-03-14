package org.alyssanoble.models;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;

public class SignUser {

    @NotEmpty(message="Please enter the Email or Phone")
    private String email;

    @NotBlank(message="Please enter the password")
    private String password;

    public SignUser() {

    }

    public SignUser(@NotNull String email,  @NotNull String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void checkValidation(){


    }
}