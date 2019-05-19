package com.example.onlab1.console;

import com.example.onlab1.boardgame.model.Move;
import com.example.onlab1.boardgame.model.PlayerData;
import com.example.onlab1.boardgame.player.Player;

import java.util.List;
import java.util.Scanner;

public class ConsolePlayer extends Player {

    public ConsolePlayer(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void makeMove(List<Move> availableMoves) {
        System.out.println(this.playerData.getName() + " takes a move");
        Move moveToPerform = getMoveFromConsole(availableMoves);
        performMove(moveToPerform);
    }

    private void performMove(Move moveToPerform) {
        System.out.println("You chose: " + moveToPerform.getName());

    }

    private Move getMoveFromConsole(List<Move> availableMoves) {
        printAvailableMoves(availableMoves);
        int choice = getChoiceFromConsole(availableMoves.size());
        return availableMoves.get(choice);
    }

    private int getChoiceFromConsole(int max) {
        int choice = max;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                System.out.println("Chose an available move to make [0-" + max + "]");
                String line = scanner.nextLine();
                try {
                    choice = Integer.parseInt(line);
                }catch (NumberFormatException e){
                    System.out.println("Could not parse choice");
                }
            } while (choice >= max);
        }
        return 0;
    }

    private void printAvailableMoves(List<Move> availableMoves) {
        System.out.println("Available moves:");
        for (int i = 0; i < availableMoves.size(); i++) {
            System.out.println(i + ": " + availableMoves.get(i).getName());
        }
    }
}
