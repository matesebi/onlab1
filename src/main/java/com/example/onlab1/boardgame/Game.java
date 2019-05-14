package com.example.onlab1.boardgame;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Game {

    private String name;
    private Board board;
    private Map<String, Phase> phases;
    private Map<String, Player> players;
    private List<WinCondition> winConditions;
}
