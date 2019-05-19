package com.example.onlab1.boardgame.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WinCondition {
    public static final WinCondition noEmptyField = new WinCondition("noEmptyField");
    public static final WinCondition sameTokenOn = new WinCondition("sameTokenOn");

    private String name;

    public WinCondition(String name) {
        this.name = name;
    }

    public boolean checkForGame(Game game) {
        throw new RuntimeException("checkForGame not implemented");
    }
}
