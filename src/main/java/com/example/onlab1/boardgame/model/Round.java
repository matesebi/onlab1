package com.example.onlab1.boardgame.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class Round {

    private String name;

    private RoundOrder order;
    private Map<String, Turn> turns;

    public Round(String name) {
        this.name = name;
    }
}
