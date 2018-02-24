package com.dsp.dou.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class TimeSheet {

    public TimeSheet() {
    }

    public TimeSheet(String username, String content) {
        this.username = username;
        this.content = content;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String content;
}
