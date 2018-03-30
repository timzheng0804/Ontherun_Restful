package com.ontherun.restful_api.domain;

/**
 * User Login use credentials
 */
public class UserCredentials {
    String phone;
    String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
