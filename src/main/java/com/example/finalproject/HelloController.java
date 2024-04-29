package com.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.lang.*;
import java.util.Dictionary;
import java.util.Hashtable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

import static java.lang.Character.isLetter;


public class HelloController {

    //GridPane 5 cols, 6 rows
    @FXML private Label guess11;
    @FXML private Label guess12;
    @FXML private Label guess13;
    @FXML private Label guess14;
    @FXML private Label guess15;

    @FXML private Label guess21;
    @FXML private Label guess22;
    @FXML private Label guess23;
    @FXML private Label guess24;
    @FXML private Label guess25;

    @FXML private Label guess31;
    @FXML private Label guess32;
    @FXML private Label guess33;
    @FXML private Label guess34;
    @FXML private Label guess35;

    @FXML private Label guess41;
    @FXML private Label guess42;
    @FXML private Label guess43;
    @FXML private Label guess44;
    @FXML private Label guess45;

    @FXML private Label guess51;
    @FXML private Label guess52;
    @FXML private Label guess53;
    @FXML private Label guess54;
    @FXML private Label guess55;

    @FXML private Label guess61;
    @FXML private Label guess62;
    @FXML private Label guess63;
    @FXML private Label guess64;
    @FXML private Label guess65;

    //Buttons
    @FXML private Button A;
    @FXML private Button B;
    @FXML private Button C;
    @FXML private Button D;
    @FXML private Button E;
    @FXML private Button F;
    @FXML private Button G;
    @FXML private Button H;
    @FXML private Button I;
    @FXML private Button J;
    @FXML private Button K;
    @FXML private Button L;
    @FXML private Button M;
    @FXML private Button N;
    @FXML private Button O;
    @FXML private Button P;
    @FXML private Button Q;
    @FXML private Button R;
    @FXML private Button S;
    @FXML private Button T;
    @FXML private Button U;
    @FXML private Button V;
    @FXML private Button W;
    @FXML private Button X;
    @FXML private Button Y;
    @FXML private Button Z;

    @FXML private Button reset;
    @FXML private Button delete;
    @FXML private Button enter;
    @FXML private Button stat;

    //Dialogue Pane
    @FXML private Label dialogue;

    //Game Variables
    private Label[][] grid;
    private Dictionary<Character, Button> letterButtonMapping;

    private int[] stats;
    //0-5: Finished in 1-6 Guesses

    //Style Variables
    private final String gridStyle = "-fx-border-radius: 5; -fx-border-color: BLACK; -fx-background-radius: 5;";
    private final String buttonStyle = "-fx-background-radius: 5; -fx-border-color: BLACK; -fx-border-radius: 5;";

    Game game;

    @FXML
    public void initialize() {
        Label[] guess1 = {guess11, guess12, guess13, guess14, guess15};
        Label[] guess2 = {guess21, guess22, guess23, guess24, guess25};
        Label[] guess3 = {guess31, guess32, guess33, guess34, guess35};
        Label[] guess4 = {guess41, guess42, guess43, guess44, guess45};
        Label[] guess5 = {guess51, guess52, guess53, guess54, guess55};
        Label[] guess6 = {guess61, guess62, guess63, guess64, guess65};

        grid = new Label[6][5];

        grid[0] = guess1;
        grid[1] = guess2;
        grid[2] = guess3;
        grid[3] = guess4;
        grid[4] = guess5;
        grid[5] = guess6;

        stats = new int[6];

        letterButtonMapping = new Hashtable<>();
        letterButtonMapping.put('A', A);
        letterButtonMapping.put('B', B);
        letterButtonMapping.put('C', C);
        letterButtonMapping.put('D', D);
        letterButtonMapping.put('E', E);
        letterButtonMapping.put('F', F);
        letterButtonMapping.put('G', G);
        letterButtonMapping.put('H', H);
        letterButtonMapping.put('I', I);
        letterButtonMapping.put('J', J);
        letterButtonMapping.put('K', K);
        letterButtonMapping.put('L', L);
        letterButtonMapping.put('M', M);
        letterButtonMapping.put('N', N);
        letterButtonMapping.put('O', O);
        letterButtonMapping.put('P', P);
        letterButtonMapping.put('Q', Q);
        letterButtonMapping.put('R', R);
        letterButtonMapping.put('S', S);
        letterButtonMapping.put('T', T);
        letterButtonMapping.put('U', U);
        letterButtonMapping.put('V', V);
        letterButtonMapping.put('W', W);
        letterButtonMapping.put('X', X);
        letterButtonMapping.put('Y', Y);
        letterButtonMapping.put('Z', Z);

        game = new Game();
        System.out.println(game.word);
    }

    @FXML
    public void onEnter(ActionEvent e) {
        enter();
    }

    public void enter() {
        dialogue.setText(" ");

        boolean guess = game.performGuess();
        if(guess) {
            colorGuess(game.getColors());
        }
        else {
            dialogue.setText(game.error);
        }

        if(game.isFinished() || game.getGuessNumber() == 6) {
            lock();
            intermission();
        }

    }

    @FXML
    public void intermission() {
        //CORRECT WORD IS GUESSED
        if(game.isFinished()) {
            dialogue.setText("YOU WON");
            stats[game.getGuessNumber()-1] += 1;
        }

        //MAX GUESSES ARE REACHED
        else if(game.getGuessNumber() == 6) {
            dialogue.setText("YOU LOST");
        }

        reset.setText("NEXT");
        reset.setStyle("-fx-background-color: LIGHTGREEN; -fx-background-radius: 5; -fx-border-color: BLACK; -fx-border-radius: 5;");
    }

    @FXML
    public void onDelete(ActionEvent e) {
        removeLetter();
    }

    public void removeLetter() {
        if(game.removeLetter()) {
            int col = game.getCurrentGuessSize();
            int guessNumber = game.getGuessNumber();
            grid[guessNumber][col].setText(" ");
        }
    }

    public void addLetter(char c) {
        if((!game.locked.contains(c) || (game.locked.contains(c) && (game.green.contains(c) || game.yellow.contains(c)))) && game.addLetter(c)) {
            int col = game.getCurrentGuessSize() - 1;
            int guessNumber = game.getGuessNumber();
            grid[guessNumber][col].setText(c + "");
        }
    }

    private void colorGuess(String[] colors) {
        String green = "-fx-background-color: LIGHTGREEN;";
        String yellow = "-fx-background-color: LIGHTYELLOW;";
        String black = "-fx-background-color: DARKGRAY;";

        int guessNumber = game.getGuessNumber() - 1;
        for(int i = 0; i < 5; ++i) {
            String color = colors[i];
            Button b = letterButtonMapping.get(grid[guessNumber][i].getText().charAt(0));

            if(color.equals("black")) {
                grid[guessNumber][i].setStyle(gridStyle + black);
                b.setStyle(buttonStyle + black);
            }

            if(color.equals("yellow")) {
                grid[guessNumber][i].setStyle(gridStyle + yellow);
                b.setStyle(buttonStyle + yellow);
            }

            if(color.equals("green")) {
                grid[guessNumber][i].setStyle(gridStyle + green);
                b.setStyle(buttonStyle + green);
            }
        }
        game.resetColors();
    }

    @FXML
    public void onLetterPress(ActionEvent e) {
        Button source = (Button)e.getSource();
        String letter = source.getId();

        addLetter(letter.charAt(0));
    }

    @FXML
    public void onStatPressed(ActionEvent e) {
        ActionEvent blank = new ActionEvent();

        //stats are NOT currently being shown
        if(!dialogue.getText().equals("WINS DISTRIBUTION BY GUESSES")) {
            resetPressed(blank);
            lock();

            for (int i = 0; i < 6; ++i) {
                for (int j = 0; j < 5; ++j) {
                    if(j != 0 && j != 3) {
                        grid[i][j].setDisable(false);
                        grid[i][j].setText(" ");
                        grid[i][j].setStyle(" ");
                    }
                }
            }

            //showing stats
            guess11.setText("1");
            guess21.setText("2");
            guess31.setText("3");
            guess41.setText("4");
            guess51.setText("5");
            guess61.setText("6");

            Font newFont = new Font("Avenir Book", 16.0);
            guess12.setFont(newFont);
            guess22.setFont(newFont);
            guess32.setFont(newFont);
            guess42.setFont(newFont);
            guess52.setFont(newFont);
            guess62.setFont(newFont);

            guess12.setText("guess");
            guess22.setText("guesses");
            guess32.setText("guesses");
            guess42.setText("guesses");
            guess52.setText("guesses");
            guess62.setText("guesses");

            guess13.setText("=");
            guess23.setText("=");
            guess33.setText("=");
            guess43.setText("=");
            guess53.setText("=");
            guess63.setText("=");

            guess14.setText(stats[0] + "");
            guess24.setText(stats[1] + "");
            guess34.setText(stats[2] + "");
            guess44.setText(stats[3] + "");
            guess54.setText(stats[4] + "");
            guess64.setText(stats[5] + "");

            guess15.setText("wins");
            guess25.setText("wins");
            guess35.setText("wins");
            guess45.setText("wins");
            guess55.setText("wins");
            guess65.setText("wins");

            dialogue.setText("WINS DISTRIBUTION BY GUESSES");
            stat.setStyle("-fx-background-color: LIGHTGREEN; -fx-background-radius: 5; -fx-border-color: BLACK; -fx-border-radius: 5;");

        }

        //stats ARE currently being shown
        else if(dialogue.getText().equals("WINS DISTRIBUTION BY GUESSES")) {
            resetPressed(blank);

            Font newFont = new Font("Avenir Book", 24.0);
            guess12.setFont(newFont);
            guess22.setFont(newFont);
            guess32.setFont(newFont);
            guess42.setFont(newFont);
            guess52.setFont(newFont);
            guess62.setFont(newFont);

            stat.setStyle("-fx-background-color: LIGHTGRAY; -fx-background-radius: 5; -fx-border-color: BLACK; -fx-border-radius: 5;");

        }

    }

    @FXML
    public void resetPressed(ActionEvent e) {
        //intermission state
        if(reset.getText().equals("NEXT")) {
            reset.setText("RESET");
            reset.setStyle("-fx-background-color: LIGHTGRAY; -fx-background-radius: 5; -fx-border-color: BLACK; -fx-border-radius: 5;");
        }

        //resets the current game
        game.reset();

        //resets the grid
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 5; ++j) {
                grid[i][j].setDisable(false);
                grid[i][j].setText(" ");
                grid[i][j].setStyle(gridStyle);
            }
        }

        //resets the dialogue label
        dialogue.setText("");

        //resets the alphabet buttons
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < alphabet.length(); ++i) {
            Button letter = letterButtonMapping.get(alphabet.charAt(i));
            letter.setDisable(false);
            letter.setStyle(buttonStyle + "-fx-background-color: LIGHTGRAY;");
        }
        delete.setDisable(false);
        enter.setDisable(false);

        System.out.println(game.word);
    }


    public void lock() {
        //resets the grid
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 5; ++j) {
                grid[i][j].setDisable(true);
            }
        }
        delete.setDisable(true);
        enter.setDisable(true);

        //resets the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < alphabet.length(); ++i) {
            Button letter = letterButtonMapping.get(alphabet.charAt(i));
            letter.setDisable(true);
        }

        //changes grid
    }
    @FXML
    public void onKeyPressed(KeyEvent k) {
        KeyCode kc = k.getCode();

        //if delete key is pressed
        if(kc == KeyCode.BACK_SPACE) {
            removeLetter();
        }

        if(kc == KeyCode.ENTER) {
            enter();
        }


        else if(kc.getChar().length() == 1 && isLetter(kc.getChar().charAt(0)) && (kc != KeyCode.SPACE)) {
            addLetter(kc.getChar().charAt(0));
        }
    }
}