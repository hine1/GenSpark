package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    String[] guessWords = new String[]{"whale", "shark", "dolphin", "alpaca", "seal", "penguin", "panda"};
        Scanner getInput = new Scanner(System.in);
	    boolean gameStart = true;
        ArrayList<Character> correctLetters = new ArrayList<>();
        ArrayList<Character> incorrectLetters = new ArrayList<>();
        int totalGuesses = 0;
        String guessWord="";

	    while (gameStart){
	        // When a new game starts, generate a new guess word and clear previous correct and incorrect guesses
            if (totalGuesses==0) {
                guessWord = guessWords[(int) Math.floor(Math.random() * 7)];
                correctLetters.clear();
                incorrectLetters.clear();
                System.out.println("H A N G M A N\n");
            }

            // Display current game progress
            displayHangman(incorrectLetters);
            System.out.println("Missed letters:"+incorrectLetters);
            displayProgress(guessWord, correctLetters);
            System.out.println("Guess a letter:");

            // Listen to next guess
            char guess = getInput.next().charAt(0);
            totalGuesses++;

            // If the guess is already made, prompt the player and the guess is not counted
            if (correctLetters.contains(guess) || incorrectLetters.contains(guess)) {
                System.out.println("You have already guessed that letter. Choose again.");
                totalGuesses--;
            }
            // Check if the guess is correct. If not, add the guess to the incorrect list
            else if (!checkMatches(guessWord, guess, correctLetters))
                incorrectLetters.add(guess);
            // If the guess is over
            if (checkIfGameOver(guessWord, correctLetters, incorrectLetters)){
                if (restartGame()){
                    totalGuesses=0;
                    continue;
                }
                else
                    gameStart=false;
            }
            System.out.println("\n");
        }
    }

    // Wait for the player's decision whether to restart the game or not
    public static boolean restartGame(){
        Scanner getInput = new Scanner(System.in);
        String restart;
        while (true){
            System.out.println("Do you want to play again? (yes or no)");
            restart = getInput.nextLine();
            if (restart.equals("yes"))
                return true;
            else if (restart.equals("no"))
                return false;
        }
    }

    // Check if the game is over
    public static boolean checkIfGameOver(String guessWord, ArrayList<Character> correctLetters, ArrayList<Character> incorrectLetters ){
        // If 6 incorrect guesses are made
        if (incorrectLetters.size()==6){
            System.out.println("\nNOOOOOOO!! You have failed to save the hangman!");
            return true;
        // If the guess word is completed
        }else if (guessWord.length()==correctLetters.size()){
            System.out.println("\nYes! The secret word is \"" + guessWord + "\"! You have won!");
            return true;
        }
        return false;
    }
    // Check if the guess is correct
    public static boolean checkMatches(String guessWord, char guess, ArrayList<Character> correctLetters){
        // Count how many times the letter appears in the guessWord
        int count = (int) guessWord.chars().filter(ch -> ch == guess).count();
        if (count==0)
            return false;
        // Add the letter "count" times into the correct list
        // So that in checkIfGameOver(), guessWord.length()==correctLetters.size() works
        // when a letter appears more than once (i.e. alpaca)
        for (int i=0; i<count; i++){
            correctLetters.add(guess);
        }
        return true;
    }
    // Print out the guess word progress
    public static void displayProgress(String guessWord, ArrayList<Character> correctLetters){
        boolean match = false;
        for (int i=0; i<guessWord.length(); i++){
            for (int j=0; j< correctLetters.size(); j++){
                if (guessWord.charAt(i)==correctLetters.get(j))
                    match=true;
            }
            // Only print either the matching letter or "_" for position i in guess word
            if (match)
                System.out.print(guessWord.charAt(i));
            else
                System.out.print("_");
            match = false;
        }
        System.out.println();
    }
    // Print out the hangman situation
    public static void displayHangman(ArrayList<Character> incorrectLetters){
        System.out.println("+---+");
        switch (incorrectLetters.size()){
            case 0:
                System.out.println("\n\n\n");
                break;
            case 1:
                System.out.println(" O \n\n\n");
                break;
            case 2:
                System.out.println(" O \n\\\n\n");
                break;
            case 3:
                System.out.println(" O \n\\|\n\n");
                break;
            case 4:
                System.out.println(" O \n\\|/\n\n");
                break;
            case 5:
                System.out.println(" O \n\\|/\n/\n");
                break;
            case 6:
                System.out.println(" O \n\\|/\n/ \\\n");
                break;
        }
        System.out.println("====");
    }
}
