package com.example.onlab1.boardgame.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class PlayerData {

    private String name;

    private Map<String, List<Token>> tokens;

    public PlayerData(String name) {
        this.name = name;
    }
}
