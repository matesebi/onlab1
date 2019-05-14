package com.example.onlab1.boardgame;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Turn {

    private String name;

    private List<TurnMove> moves;

    public Turn(String name) {
        this.name = name;
    }
}
