package com.example.onlab1.boardgame.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Phase {

    private String name;

    private Map<String, Round> rounds;

    private List<String> roundOrder;

    public Phase(String name) {
        this.name = name;
    }
}
