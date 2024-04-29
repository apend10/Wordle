***Wordle Project***  
-
___
- Abhi Pendela
- CSCE 314 Spring 2024
- Video Link: https://youtu.be/7Uxa8vh1hr4
___
1) **Guess.java**

*Variables*
- String Guess
- int size

*Methods*
- Guess()  
- addLetter(char letter)
- removeLetter()  
- getSize()  
- getGuess()  
___
2) **Game.java**  

*Variables*
- Guess[] guesses
- String word
- int guessNumber
- boolean finished
- String[] colors
- ArrayList<Character> locked
- ArrayList<Character> yellow
- ArrayList<Character> green
  
*Methods*
- Game()
- reset()
- selectWord()
- getColors()
- resetColors()
- getGuessNumber()
- getCurrentGuessSize()
- addLetter()
- removeLetter()
- checkGuess()
- performGuess()
- validateGuess()
- isFinised()
---
3) **Hello Controller**  
   *Variables*
- all grid elements as labels
- all buttons  
- Label[][] grid
- Dictionary<Character, Button> letterButtonMapping
- int[] stats
- final String gridStyle
- final String buttonStyle
- Game game

*Methods*
- initialize
- onEnter(ActionEvent e)
- enter()
- intermission()
- onDelete(ActionEvent e)
- removeLetter()
- addLetter(char c)
- colorGuess(String[] colors)
- onLetterPress(ActionEvent e)
- onStatPressed(ActionEvent e)
- resetPressed(ActionEvent e)
- lock()
- onKeyPressed(KeyEvent k)
___
**AGGIE HONOR CODE:**
"An Aggie does not lie, cheat or steal or tolerate those who do."
