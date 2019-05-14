package com.example.onlab1.boardgame;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Player {

    private String name;

    private Map<String, List<Token>> tokens;

    public Player(String name) {
        this.name = name;
    }
}
