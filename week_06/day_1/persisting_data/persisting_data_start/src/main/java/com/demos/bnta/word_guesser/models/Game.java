package com.demos.bnta.word_guesser.models;

public class Game {

    private static int nextId = 1;

    private int id;
    private String word;
    private int guesses;
    private boolean complete;

    public Game(String word) {
        this.id = nextId;
        nextId += 1;
        this.guesses = 0;
        this.complete = false;
        this.word = word;
    }

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getGuesses() {
        return guesses;
    }

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
