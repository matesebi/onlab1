package com.example.onlab1.console;

import com.example.onlab1.boardgame.Game;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleController {

    Game game;

    public ConsoleController(Game game) {
        this.game = game;
    }

    public void startPlay(){
      log.info("ConsoleController.startPlay with game: " + game);
        //TODO
    }
}
