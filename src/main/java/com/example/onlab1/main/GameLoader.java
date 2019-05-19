package com.example.onlab1.main;

import com.example.onlab1.boardgame.model.Game;
import com.example.onlab1.grammar.BoardGame;
import com.example.onlab1.grammar.BoardGameLexer;
import com.example.onlab1.visitor.BoardGameVisitor;
import com.example.onlab1.visitor.error.ThrowingErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class GameLoader {
    static Game getGameFromDescription(String fileName) throws IOException {
        BoardGameLexer lexer = new BoardGameLexer(CharStreams.fromFileName(fileName));

        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
        BoardGame boardGame = new BoardGame(new CommonTokenStream(lexer));
        boardGame.removeErrorListeners();
        boardGame.addErrorListener(ThrowingErrorListener.INSTANCE);

        BoardGameVisitor visitor = new BoardGameVisitor();
        return visitor.visitBoardGame(boardGame.boardGame());
    }
}