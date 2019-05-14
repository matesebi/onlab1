package com.example.onlab1.boardgame;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class Phase {

    private String name;

    private Map<String, Round> rounds;

    public Phase(String name) {
        this.name = name;
    }
}
