package com.example.onlab1.boardgame.player;

import com.example.onlab1.boardgame.model.Move;
import com.example.onlab1.boardgame.model.PlayerData;

import java.util.List;

public abstract class Player {
    protected PlayerData playerData;

    public Player(PlayerData playerData) {
        this.playerData = playerData;
    }

    public abstract void makeMove(List<Move> availableMoves);
}
