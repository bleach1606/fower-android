package com.example.myflowerproject.model.dao;

public class ForgotPassword {
    private String code;
    private String password1;
    private String password2;

    public ForgotPassword() {
    }

    public ForgotPassword(String code, String password1, String password2) {
        this.code = code;
        this.password1 = password1;
        this.password2 = password2;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
