package com.example.onlab1.boardgame.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Action {
    public static final Action placeTokenOnEmptyField = new Action();
    public static final Action moveTokenToEmptyNeighbour = new Action();
    public static final Action noAction = new Action();
}
