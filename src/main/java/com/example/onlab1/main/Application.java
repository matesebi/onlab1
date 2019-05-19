package com.example.onlab1.main;

import com.example.onlab1.boardgame.model.Game;
import com.example.onlab1.console.ConsoleController;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Application {
    public static void main(String[] args) throws IOException {
        Game game = GameLoader.getGameFromDescription("src/main/resources/Tic-Tac-Toe.txt");
//        Game game = GameLoader.getGameFromDescription("src/main/resources/Nine-Mans-Morris.txt");
//        Game game = GameLoader.getGameFromDescription("src/main/resources/test/test.txt");

        new ConsoleController(game).startPlay();
    }

}
