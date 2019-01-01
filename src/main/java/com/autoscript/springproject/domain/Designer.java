package com.autoscript.springproject.domain;

import javax.persistence.*;

@Entity
public class Designer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DESIGNER_ID")
    private long id;
    @Column(name = "DESIGNER_NAME")
    private String name;
    @Column(name = "DESIGNER_PASSWORD")
    private String password;
    @Column(name = "NICK_NAME")
    private String nickname;

    public Designer(String DESIGNER_NAME, String DESIGNER_PASSWORD) {
        this.name = DESIGNER_NAME;
        this.password = DESIGNER_PASSWORD;
    }

    public Designer() {
    }

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
