package com.example.onlab1.visitor.error;

public class SemanticWarning {
    private String message;
    private int line;
    private int characterPosition;

    public SemanticWarning(String message, int line, int characterPosition) {
        this.message = message;
        this.line = line;
        this.characterPosition = characterPosition;
    }

    @Override
    public String toString() {
        return String.format("Semantic error at %d:%d %s", line, characterPosition , message);
    }
}
