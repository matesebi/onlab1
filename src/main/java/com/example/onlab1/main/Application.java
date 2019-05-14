package com.example.onlab1.main;

import com.example.onlab1.boardgame.Game;
import com.example.onlab1.console.ConsoleController;
import com.example.onlab1.grammar.BoardGame;
import com.example.onlab1.grammar.BoardGameLexer;
import com.example.onlab1.visitor.BoardGameVisitor;
import com.example.onlab1.visitor.error.ThrowingErrorListener;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

@Slf4j
public class Application {
    public static void main(String[] args) throws IOException {
        //TODO move parsing to ConsoleController
        //TODO make parsing dynamic
        BoardGameLexer lexer = new BoardGameLexer(CharStreams.fromFileName("src/main/resources/Tic-Tac-Toe.txt"));
//        BoardGameLexer lexer = new BoardGameLexer(CharStreams.fromFileName("src/main/resources/test/test.txt"));

        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
        BoardGame boardGame = new BoardGame(new CommonTokenStream(lexer));
        boardGame.removeErrorListeners();
        boardGame.addErrorListener(ThrowingErrorListener.INSTANCE);

        BoardGameVisitor visitor = new BoardGameVisitor();
        Game game = visitor.visitBoardGame(boardGame.boardGame());

        new ConsoleController(game).startPlay();
    }
}
