package com.example.onlab1.boardgame;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.Token;

import java.util.Map;

@Getter
@Setter
@ToString
public class Field {

    private String id;

    private Map<String, Token> tokens;

    private Map<String, Field> neighbours;

    public Field(String id) {
        this.id = id;
    }
}
