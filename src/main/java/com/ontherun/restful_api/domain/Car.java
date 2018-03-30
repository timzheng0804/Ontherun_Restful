package com.ontherun.restful_api.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String color;
    @OneToOne
    private UserInfo user;

    // default constructor, JPA use
    private Car() {}

    public Car(UserInfo user, String type, String color) {
        super();
        this.user = user;
        this.type = type;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public UserInfo getUser() { return user; }
}
