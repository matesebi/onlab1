package com.example.onlab1.boardgame.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class Board {

    private Map<String, Field> fields;
}
