package com.example.finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public Guess[] guesses;
    public String word;

    private int guessNumber;
    private boolean finished;
    private String[] colors;
    String error;

    //state of letters
    public ArrayList<Character> locked;
    public ArrayList<Character> yellow;
    public ArrayList<Character> green;



    public Game() {
         guesses = new Guess[6];
         guessNumber = 0;
         guesses[0] = new Guess();
         word = selectWord();
         finished = false;
         colors = new String[5];

         locked = new ArrayList<Character>();
         yellow = new ArrayList<Character>();
         green = new ArrayList<Character>();
    }

    public void reset() {
        guesses = new Guess[6];
        guessNumber = 0;
        guesses[0] = new Guess();
        word = selectWord();
        finished = false;
        colors = new String[5];

        locked = new ArrayList<Character>();
        yellow = new ArrayList<Character>();
        green = new ArrayList<Character>();
    }

    private String selectWord() {
        //file information
        int fileLength = 2311;
        String path = "/Users/apendela10/CSCE314/FinalProject/FinalProject/src/main/java/com/example/finalproject/words.txt";

        //generate random info
        Random random = new Random();
        int randomNumber = random.nextInt(fileLength);

        int i = 0;
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                String curr = scanner.next();
                if(i == randomNumber) {
                    return curr;
                }
                i += 1;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String[] getColors() {
        return colors;
    }
    public void resetColors() { colors = new String[5];}
    public int getGuessNumber() {
        return guessNumber;
    }
    public int getCurrentGuessSize() {
        return guesses[guessNumber].getSize();
    }
    public boolean addLetter(char letter) {
        return guesses[guessNumber].addLetter(letter);
    }

    public boolean removeLetter() {
        return guesses[guessNumber].removeLetter();
    }


    //make the guess
    public boolean performGuess() {
        //if the guess is not a valid guess
        if(!validateGuess()) {
            return false;
        }

        //if the guess is valid guess
        else {
            String thisGuess = guesses[guessNumber].getGuess();
            int greenCount = 0;

            //greens
            for(int i = 0; i < 5; ++i) {
                if(word.charAt(i) == thisGuess.toLowerCase().charAt(i)) {
                    green.add(thisGuess.charAt(i));
                    colors[i] = "green";
                    greenCount += 1;
                }
            }

            //if guess is correct
            if(greenCount == 5) {
                finished = true;
                guessNumber += 1;
                return true;
            }

            //yellow
            //USURP - guess
            //COURT - word
            for(int i = 0; i < 5; ++i) {
                char c = thisGuess.toLowerCase().charAt(i);
                //not green and the word contains this char
                if(colors[i] == null && word.contains(c+"")) {
                    //only one of this char in word
                    if(word.indexOf(c) == word.lastIndexOf(c) && !green.contains((c+"").toUpperCase().charAt(0))) {
                        yellow.add(thisGuess.charAt(i));
                        colors[i] = "yellow";
                    }

                    //if two of this char in word
                    if(word.indexOf(c) != word.lastIndexOf(c)) {
                        yellow.add(thisGuess.charAt(i));
                        colors[i] = "yellow";
                    }
                }
            }

            //black
            for(int i = 0; i < 5; ++i) {
                if(colors[i] == null) {
                    locked.add(thisGuess.charAt(i));
                    colors[i] = "black";
                }
            }

            if(guessNumber < 5) {
                guessNumber += 1;
            }
            guesses[guessNumber] = new Guess();
            return true;
        }
    }

    //helper method to ensure that a guess is first valid
    //if length of guess is 5
    //"discovered" green letters are used in correct spot
    //"discovered" yellow letters are used
    //"discovered" locked letters are not used
    //guess is a valid guess
    private boolean validateGuess() {
        boolean validGuess = false;
        error = "";


        //checks length
        if(guesses[guessNumber].getGuess().length() != 5) {
            error = ("The guess must be 5 letters!");
            return false;
        }

        //file information
        int fileLength = 2311;
        String path = "/Users/apendela10/CSCE314/FinalProject/FinalProject/src/main/java/com/example/finalproject/words.txt";

        boolean validWord = false;
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                String curr = scanner.next();
                if(curr.equals(guesses[guessNumber].getGuess().toLowerCase())) {
                    validWord = true;
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(!validWord) {
            error = ("The guess is not a valid word.");
            return false;
        }

        //locked letters aren't able to be used again
        for(char letter : locked) {
            if(guesses[guessNumber].getGuess().contains(letter + "") && !green.contains(letter)) {
                error = ("The guess cannot contain " + letter + "!");
                return false;
            }
        }

        //yellow letters must be in the word
        for(char letter : yellow) {
            if(!guesses[guessNumber].getGuess().contains(letter + "")) {
                error = ("The guess must contain " + letter + "!");
                return false;
            }
        }

        //green letters must be in the same spot as previous guesses
        for(char letter : green) {
            int position = word.toUpperCase().indexOf(letter);
            int positionb = word.toUpperCase().lastIndexOf(letter);
            if(guesses[guessNumber].getGuess().charAt(position) != letter && guesses[guessNumber].getGuess().charAt(positionb) != letter) {
                error = ("The guess must have " + letter + " at the correct spot!");
                return false;
            }

        }
        return true;
    }

    //returns if the word has been guessed or not
    public boolean isFinished() {
        return finished;
    }
}
