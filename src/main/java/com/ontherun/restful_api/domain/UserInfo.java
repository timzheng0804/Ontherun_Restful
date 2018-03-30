package com.ontherun.restful_api.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String phone;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToOne(mappedBy = "user")
    Car car;

    // default constructor, JPA use
    private UserInfo() { }

    public UserInfo(String phone, String username, String password) {
        super();
        this.phone = phone;
        this.username = username;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Car getCar() { return car; }
}
