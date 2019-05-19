package com.example.onlab1.boardgame.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoundOrder {
    public static final RoundOrder roundRobin = new RoundOrder("roundRobin");
    public static final RoundOrder randomOrder = new RoundOrder("randomOrder");

    private String name;

    public RoundOrder(String name) {
        this.name = name;
    }
}
