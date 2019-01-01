package com.autoscript.springproject.domain;

import javax.persistence.*;
import java.util.Calendar;

import static javax.swing.text.html.parser.DTDConstants.PARAMETER;

@Entity
public class Script {

    public static final String PASS = "PASS";
    public static final String FAILED = "FAILED";
    public static final String CHECK = "CHECK";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SCRIPT_ID")
    private long id;
    @Column(name = "SCRIPT_NAME")
    private String name;
    @Column(name = "PARAMETER")
    private int parameter;
    @Column(name = "LANGUAGE")
    private String language;
    @Column(name = "FLAG")
    private String flag;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PATH")
    private String path;
    @Column(name = "TIME")
    private Calendar time;

    public Script(String name, int PARAMETER, String LANGUAGE, String FLAG, String description, String PATH) {
        this.name = name;
        this.parameter = PARAMETER;
        this.language = LANGUAGE;
        this.flag = FLAG;
        this.description = description;
        this.path = PATH;
        this.time = Calendar.getInstance();
    }

    public Script() {
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

    public int getParameter() {
        return parameter;
    }

    public void setParameter(int parameter) {
        this.parameter = parameter;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }
}
