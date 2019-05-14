package com.example.onlab1.boardgame;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TurnMove {
    private int count;
    private Move move;

    public TurnMove(int count, Move move) {
        this.count = count;
        this.move = move;
    }
}
