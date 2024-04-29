package com.example.finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Game game = new Game();

        System.out.println(game.word);

        game.addLetter('A');
        game.addLetter('D');
        game.addLetter('E');
        game.addLetter('I');
        game.addLetter('U');

        game.performGuess();


        System.out.println(game.guesses[0].getGuess().charAt(0));
        System.out.println(game.guesses[0].getGuess().charAt(1));
        System.out.println(game.guesses[0].getGuess().charAt(2));
        System.out.println(game.guesses[0].getGuess().charAt(3));
        System.out.println(game.guesses[0].getGuess().charAt(4));

    }
}
