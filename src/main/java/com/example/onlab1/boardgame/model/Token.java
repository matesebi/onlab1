package com.example.onlab1.boardgame.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Token {

    private String name;

    public Token(String name) {
        this.name = name;
    }
}
