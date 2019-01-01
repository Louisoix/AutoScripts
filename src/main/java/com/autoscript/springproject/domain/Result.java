package com.autoscript.springproject.domain;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RESULT_ID")
    private long id;
    @Column(name = "RESULT_PATH")
    private String resultPath;
    @Column(name = "RESULT_TIME")
    private Calendar resultTime;
    @Column(name = "SCRIPT_ID")
    private long scriptid;

    public Result(String resultPath,long scriptid) {
        this.resultPath = resultPath;
        this.resultTime = Calendar.getInstance();
        this.scriptid = scriptid;
    }

    public Result() {
    }

    public long getScriptid() {
        return scriptid;
    }

    public void setScriptid(long scriptid) {
        this.scriptid = scriptid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResultPath() {
        return resultPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

    public Calendar getResultTime() {
        return resultTime;
    }

    public void setResultTime(Calendar resultTime) {
        this.resultTime = resultTime;
    }
}
