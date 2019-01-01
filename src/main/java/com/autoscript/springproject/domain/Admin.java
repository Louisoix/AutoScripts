package com.autoscript.springproject.domain;
import javax.persistence.*;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADMIN_ID")
    private long id;
    @Column(name = "ADMIN_NAME")
    private String name;
    @Column(name = "ADMIN_PASSWORD")
    private String password;
    @Column(name = "NICK_NAME")
    private String nickname;

    public Admin(String ADMIN_NAME, String password) {
        this.name = ADMIN_NAME;
        this.password = password;
    }

    public Admin() {
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
