package com.autoscript.springproject.domain;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_ID")
    private long id;
    @Column(name="USER_NAME")
    private String name;
    @Column(name="USER_PASSWORD")
    private String password;
    @Column(name = "NICK_NAME")
    private String nickname;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
