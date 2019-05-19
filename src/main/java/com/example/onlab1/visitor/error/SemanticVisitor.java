package com.example.onlab1.visitor.error;

import com.example.onlab1.grammar.BoardGame;
import com.example.onlab1.grammar.BoardGameBaseVisitor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class SemanticVisitor extends BoardGameBaseVisitor<Void> {

    private List<SemanticWarning> warnings = new ArrayList<>();

    public String getPrintableWarnings() {

        return warnings.stream().map(SemanticWarning::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public Void visitBoardGame(BoardGame.BoardGameContext ctx) {

//        if (ctx.gameName().identifier().getText().equals("")) {
//            throw new RuntimeException("Game name not defined");
//        }

//        ctx.boardSpec().accept(this);
//        ctx.phasesSpec().accept(this);

        return super.visitBoardGame(ctx);
    }

//    @Override
//    public Void visitBoardSpec(BoardGame.BoardSpecContext ctx) {
//        ctx.boardSpecLine().forEach(
//                line -> line.accept(this)
//        );
//        return super.visitBoardSpec(ctx);
//    }
//
//    @Override
//    public Void visitBoardSpecLine(BoardGame.BoardSpecLineContext ctx) {
//
//        if (Integer.parseInt(ctx.number().getText()) != ctx.idList().identifier().size()) {
//            String message = "Number of fields does not equal the number of supplied ids";
//            int line = ctx.start.getLine();
//            int pos = ctx.start.getCharPositionInLine();
//            warnings.add(new SemanticWarning(message, line, pos));
//        }
//
//        checkForDuplicates(ctx.idList());
//
//        return super.visitBoardSpecLine(ctx);
//    }
//
//    @Override
//    public Void visitPhasesSpec(BoardGame.PhasesSpecContext ctx) {
//        ctx.phasesDefinition().accept(this);
//
//        return super.visitPhasesSpec(ctx);
//    }

    @Override
    public Void visitPhasesDefinition(BoardGame.PhasesDefinitionContext ctx) {

        if (Integer.parseInt(ctx.number().getText()) != ctx.idList().identifier().size()) {
            String message = "Number of phases does not equal the number of supplied ids";
            int line = ctx.start.getLine();
            int pos = ctx.start.getCharPositionInLine();
            warnings.add(new SemanticWarning(message, line, pos));
        }

        checkForDuplicates(ctx.idList());

        return super.visitPhasesDefinition(ctx);
    }

    private void checkForDuplicates(BoardGame.IdListContext idListContext) {
        Set<String> ids = new HashSet<>();

        idListContext.identifier().forEach(
                id -> {
                    if (!ids.add(id.getText())) {
                        String message = "Duplicate id: " + id.getText();
                        int line = id.start.getLine();
                        int pos = id.start.getCharPositionInLine();
                        warnings.add(new SemanticWarning(message, line, pos));
                    }
                }
        );
    }
}
