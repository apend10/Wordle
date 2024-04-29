package com.example.finalproject;

public class Guess {
    private String guess;
    private int size;

    public Guess() {
        guess = "";
        size = 0;
    }

    //pressed a letter on the screen
    public boolean addLetter(char letter) {
        if(size < 5) {
            guess += letter;
            size += 1;
            return true;
        }
        return false;
    }

    //click the delete button on the screen
    public boolean removeLetter() {
        if(guess.length() > 0) {
            guess = guess.substring(0, guess.length() - 1);
            size -= 1;
            return true;
        }
        return false;
    }

    //Accessors
    public int getSize() {return size;}
    public String getGuess() {return guess;}



}
