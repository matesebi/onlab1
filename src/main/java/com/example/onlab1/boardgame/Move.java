package com.example.onlab1.boardgame;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Move {

    private String name;

    private Action before;
    private Action action;
    private Action after;

    public Move(String name) {
        this.name = name;
    }
}
