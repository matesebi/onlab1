package com.example.onlab1.console;

import com.example.onlab1.boardgame.model.Game;
import com.example.onlab1.boardgame.model.Phase;
import com.example.onlab1.boardgame.model.Round;
import com.example.onlab1.boardgame.model.TurnMove;
import com.example.onlab1.boardgame.player.Player;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ConsoleController {

    private Game game;
    private List<Player> players;

    public ConsoleController(Game game) {
        this.game = game;
    }

    public void startPlay() {

//        log.info("ConsoleController.startPlay with game: " + game);
        players = game.getPlayers().values().stream().map(ConsolePlayer::new).collect(Collectors.toList());
        printStart();
        playPhase(game.getPhaseOrder().get(0));
    }

    private void playPhase(String phaseName) {
        System.out.println("Phase " + phaseName + " is started");

        Phase phase = game.getPhases().get(phaseName);
        playRoundInPhase(phase, phase.getRoundOrder().get(0));
    }

    private void playRoundInPhase(Phase phase, String roundName) {
        System.out.println("Round " + roundName + " is started");
        Round round = phase.getRounds().get(roundName);

        Player player = getFirstPlayer(round);
        round.getTurns().values().stream().findFirst().ifPresent(
                turn -> {
                    player.makeMove(turn.getMoves().stream().map(TurnMove::getMove).collect(Collectors.toUnmodifiableList()));
                }
        );
    }

    private Player getFirstPlayer(Round round) {
        return players.get(0);
    }

    private void printStart() {
        System.out.println("A game of " + game.getName() + " is started");
    }
}
