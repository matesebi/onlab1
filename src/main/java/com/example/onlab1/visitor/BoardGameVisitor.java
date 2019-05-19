package com.example.onlab1.visitor;

import com.example.onlab1.boardgame.model.*;
import com.example.onlab1.grammar.BoardGame;
import com.example.onlab1.grammar.BoardGameBaseVisitor;
import org.antlr.v4.runtime.RuleContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardGameVisitor extends BoardGameBaseVisitor<Game> {

    private Game game = new Game();

    @Override
    public Game visitBoardGame(BoardGame.BoardGameContext ctx) {
        game = new Game();

        ctx.gameName().accept(this);
        ctx.board().accept(this);
        ctx.phases().accept(this);
        ctx.rounds().accept(this);
        ctx.turns().accept(this);
        ctx.moves().accept(this);
        ctx.winConditions().accept(this);
        ctx.players().accept(this);

        return game;
    }

    @Override
    public Game visitGameName(BoardGame.GameNameContext ctx) {
        String name = ctx.identifier().stream().map(RuleContext::getText).collect(Collectors.joining(" "));
        game.setName(name);

        return game;
    }

    @Override
    public Game visitBoard(BoardGame.BoardContext ctx) {
        game.setBoard(new Board());


        ctx.boardFields().accept(this);
        ctx.field().forEach(line -> line.accept(this));

        return game;
    }

    @Override
    public Game visitPhases(BoardGame.PhasesContext ctx) {

        ctx.phasesDefinition().accept(this);
        game.setPhaseOrder(new ArrayList<>());
        ctx.phase().forEach(
                phase -> {
                    game.getPhaseOrder().add(phase.identifier().getText());
                    phase.accept(this);
                }
        );
        return game;
    }

    @Override
    public Game visitRounds(BoardGame.RoundsContext ctx) {
        ctx.round().forEach(roundContext -> roundContext.accept(this));

        return game;
    }

    @Override
    public Game visitTurns(BoardGame.TurnsContext ctx) {
        ctx.turn().forEach(turnContext -> turnContext.accept(this));

        return game;
    }

    @Override
    public Game visitMoves(BoardGame.MovesContext ctx) {
        ctx.move().forEach(moveContext -> moveContext.accept(this));

        return game;
    }

    @Override
    public Game visitWinConditions(BoardGame.WinConditionsContext ctx) {
        game.setWinConditions(new ArrayList<>());

        ctx.draw().forEach(drawContext -> drawContext.accept(this));
        ctx.win().forEach(winContext -> winContext.accept(this));

        return game;
    }

    @Override
    public Game visitPlayers(BoardGame.PlayersContext ctx) {
        ctx.playersDefinition().accept(this);
        ctx.player().forEach(playerContext -> playerContext.accept(this));

        return game;
    }

    @Override
    public Game visitBoardFields(BoardGame.BoardFieldsContext ctx) {
        Map<String, Field> fields = new HashMap<>();

        ctx.idList().identifier().forEach(id -> fields.put(id.getText(), new Field(id.getText())));
        game.getBoard().setFields(fields);

        return game;
    }

    @Override
    public Game visitField(BoardGame.FieldContext ctx) {
        String fieldName = ctx.identifier().getText();
        Field field = game.getBoard().getFields().get(fieldName);
        Map<String, Field> neighbours = new HashMap<>();

        ctx.idList().identifier().forEach(
                neighbour -> {
                    String neighbourName = neighbour.getText();
                    Field neighbourField = game.getBoard().getFields().get(neighbourName);
                    neighbours.put(neighbourName, neighbourField);
                }
        );

        field.setNeighbours(neighbours);

        return game;
    }

    @Override
    public Game visitPhasesDefinition(BoardGame.PhasesDefinitionContext ctx) {
        HashMap<String, Phase> phases = new HashMap<>();

        ctx.idList().identifier().forEach(
                id -> phases.put(id.getText(), new Phase(id.getText()))
        );

        game.setPhases(phases);

        return game;
    }

    @Override
    public Game visitPhase(BoardGame.PhaseContext ctx) {
        String phaseName = ctx.identifier().getText();
        Phase phase = game.getPhases().get(phaseName);

        HashMap<String, Round> rounds = new HashMap<>();
        List<String> roundOrder = new ArrayList<>();

        ctx.idList().identifier().forEach(
                id -> {
                    roundOrder.add(id.getText());
                    rounds.put(id.getText(), new Round(id.getText()));
                }
        );
        phase.setRoundOrder(roundOrder);
        phase.setRounds(rounds);

        return game;
    }

    @Override
    public Game visitRound(BoardGame.RoundContext ctx) {
        String roundName = ctx.identifier().getText();
        Round round = new Round(roundName);
        Map<String, Turn> turns = new HashMap<>();

        ctx.turnList().identifier().forEach(
                id -> turns.put(id.getText(), new Turn(id.getText()))
        );
        round.setTurns(turns);

        round.setOrder(getRoundOrderFromRoundOrderContext(ctx.roundOrder()));

        updateRoundInPhases(round);

        return game;
    }

    @Override
    public Game visitTurn(BoardGame.TurnContext ctx) {
        String turnName = ctx.identifier().getText();
        Turn turn = new Turn(turnName);
        List<TurnMove> moves = new ArrayList<>();

        ctx.turnMoves().turnMove().forEach(
                turnMoveContext -> {
                    int count = Integer.parseInt(turnMoveContext.number().getText());
                    Move move = new Move(turnMoveContext.identifier().getText());
                    moves.add(new TurnMove(count, move));
                }
        );

        turn.setMoves(moves);

        updateTurnInRounds(turn);

        return game;
    }

    @Override
    public Game visitMove(BoardGame.MoveContext ctx) {
        String moveName = ctx.identifier().getText();
        Move move = new Move(moveName);

        if (ctx.moveBefore() != null) {
            move.setBefore(getActionFromBuiltinAction(ctx.moveBefore().builtinAction()));
        }

        move.setAction(getActionFromBuiltinAction(ctx.moveAction().builtinAction()));

        if (ctx.moveAfter() != null) {
            move.setAfter(getActionFromBuiltinAction(ctx.moveAfter().builtinAction()));
        }

        updateMoveInTurns(move);

        return game;
    }

    @Override
    public Game visitWin(BoardGame.WinContext ctx) {
        //TODO separate win from draw
        game.getWinConditions().add(getWinConditionFromBuiltinWinCondition(ctx.builtinWinCondition()));

        return game;
    }

    @Override
    public Game visitDraw(BoardGame.DrawContext ctx) {
        //TODO separate win from draw
        game.getWinConditions().add(getWinConditionFromBuiltinWinCondition(ctx.builtinWinCondition()));

        return game;
    }

    @Override
    public Game visitPlayersDefinition(BoardGame.PlayersDefinitionContext ctx) {
        Map<String, PlayerData> players = new HashMap<>();

        ctx.idList().identifier().forEach(id -> players.put(id.getText(), new PlayerData(id.getText())));

        game.setPlayers(players);
        return game;
    }

    @Override
    public Game visitPlayer(BoardGame.PlayerContext ctx) {
        String playerName = ctx.identifier().getText();
        PlayerData playerData = game.getPlayers().get(playerName);

        Map<String, List<Token>> tokens = new HashMap<>();

        ctx.playerTokens().playerToken().forEach(
                token -> {
                    int count = Integer.parseInt(token.number().getText());
                    String name = token.identifier().getText();
                    List<Token> tokenList = Stream.generate(() -> new Token(name)).limit(count).collect(Collectors.toList());
                    tokens.put(name, tokenList);
                }
        );

        playerData.setTokens(tokens);

        return game;
    }

    private RoundOrder getRoundOrderFromRoundOrderContext(BoardGame.RoundOrderContext roundOrderContext) {
        if (roundOrderContext.ROUND_ROBIN() != null) {
            return RoundOrder.roundRobin;
        } else if (roundOrderContext.RANDOM_ORDER() != null) {
            return RoundOrder.randomOrder;
        }
        return RoundOrder.roundRobin;
    }

    private void updateRoundInPhases(Round round) {
        game.getPhases().values().stream()
                .flatMap(phase -> phase.getRounds().entrySet().stream())
                .filter(entry -> entry.getKey().equals(round.getName()))
                .forEach(entry -> entry.setValue(round));

    }

    private void updateTurnInRounds(Turn turn) {
        game.getPhases().values().stream()
                .flatMap(phase -> phase.getRounds().values().stream())
                .flatMap(round -> round.getTurns().entrySet().stream())
                .filter(entry -> entry.getKey().equals(turn.getName()))
                .forEach(entry -> entry.setValue(turn));
    }

    private Action getActionFromBuiltinAction(BoardGame.BuiltinActionContext builtinAction) {
        if (builtinAction.PLACE_TOKEN_ON_EMPTY_FIELD() != null) {
            return Action.placeTokenOnEmptyField;
        }

        return Action.noAction;
    }

    private void updateMoveInTurns(Move move) {
        game.getPhases().values().stream()
                .flatMap(phase -> phase.getRounds().values().stream())
                .flatMap(round -> round.getTurns().values().stream())
                .flatMap(turn -> turn.getMoves().stream())
                .filter(turnMove -> turnMove.getMove().getName().equals(move.getName()))
                .forEach(turnMove -> turnMove.setMove(move));
    }

    private WinCondition getWinConditionFromBuiltinWinCondition(BoardGame.BuiltinWinConditionContext builtinWinCondition) {
        if (builtinWinCondition.NO_EMPTY_FIELD() != null) {
            return WinCondition.noEmptyField;
        } else if (builtinWinCondition.sameTokenOn() != null) {
            //TODO implement this
        }

        return null;
    }
}
