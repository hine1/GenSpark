package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Main {
    private static ArrayList<Character> correctLetters = new ArrayList<>();
    private static ArrayList<Character> incorrectLetters = new ArrayList<>();

    private static String[] guessWords = new String[]{"whale", "shark", "dolphin", "alpaca", "seal", "penguin", "panda"};
    private static String guessWord = guessWords[(int) Math.floor(Math.random() * 7)];

    private static boolean exitGame = false;
    private static boolean restartGame = false;

    private static String playerName = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        playerName = scanner.next();

        while (!exitGame) {
            if (restartGame)
                resetGameData();

            displayProgressAndPromptForAGuess();

            processGuessedLetter();
        }

    }

    // When a new game starts, generate a new guess word and clear previous correct and incorrect guesses
    public static void resetGameData(){
        Scanner scanner = new Scanner(System.in);

        restartGame = false;
        guessWord = guessWords[(int) Math.floor(Math.random() * 7)];
        correctLetters.clear();
        incorrectLetters.clear();
        System.out.println("Enter your name: ");
        playerName = scanner.next();
        System.out.println("H A N G M A N\n");
    }

    public static void displayProgressAndPromptForAGuess(){
        try {
            displayHangman();
            System.out.println("Missed letters:"+incorrectLetters);
            // Display correct guesses
            guessWord.chars().map(Main::displayCorrectGuesses).toArray();
            System.out.println("\nGuess a letter:");
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    // Display the guessed letters and the other letters will be shown as "_"
    public static char displayCorrectGuesses(int ch){
        if (correctLetters.contains((char)ch))
            System.out.print((char)ch);
        else
            System.out.print("_");
        return (char)ch;
    }

    public static void processGuessedLetter(){
        Scanner getInput = new Scanner(System.in);

        // Listen to next guess
        char guess = getInput.next().charAt(0);

        // If the guess is already made, prompt the player and the guess is not counted
        if (correctLetters.contains(guess) || incorrectLetters.contains(guess)) {
            System.out.println("You have already guessed that letter. Choose again.");
        }

        // Check if the guess is correct. If not, add the guess to the incorrect list
        else if (!checkMatches(guess)) {
            if (Character.isAlphabetic(guess))
                incorrectLetters.add(guess);
        }
        try {
            // If the guess is over
            if (checkIfGameOver()) {
                checkIfBestRecord();
                savePlayerRecord();
                if (!checkForGameRestart())
                    exitGame = true;
            }
        }catch (Exception e) {
                System.out.println(e.getMessage());
        }
        System.out.println("\n");
    }
    // Wait for the player's decision whether to restart the game or not
    public static boolean checkForGameRestart(){
        Scanner getInput = new Scanner(System.in);
        String restart;
        System.out.println("Do you want to play again? (yes or no)");
        restart = getInput.nextLine();
        if (restart.equals("yes")) {
            restartGame = true;
            return true;
        }else if (restart.equals("no"))
            return false;
        else
            return checkForGameRestart();
    }
    public static void savePlayerRecord() throws IOException{
        String record = "Player " + playerName + " guessed the word with a total of " + incorrectLetters.size() + " incorrect guesses\n";
        Files.write(Paths.get("records.log"), record.getBytes(), StandardOpenOption.APPEND);
    }

    public static void checkIfBestRecord() throws IOException{
        List<String> str = Files.readAllLines(Paths.get("records.log"));
        List<Integer> records = new ArrayList<>();
        str.forEach(s -> {s = s.replaceAll("[^0-9]", ""); records.add(Integer.valueOf(s));});

        Optional<Integer> bestRecord = records.stream().reduce(Math::min);

        if ((bestRecord.isPresent() && incorrectLetters.size()<=bestRecord.get()) || !bestRecord.isPresent()){
            System.out.println("You have the best record of only " + incorrectLetters.size() + " incorrect guesses");
        }

    }
    // Check if the game is over
    public static boolean checkIfGameOver(){
        try{
            // If 6 incorrect guesses are made
            if (incorrectLetters.size()==6){
                displayHangman();
                System.out.println("\nNOOOOOOO!! You have failed to save the hangman!");
                return true;
            // If the guess word is completed
            }else if (guessWord.length()==correctLetters.size()){
                System.out.println("\nYes! The secret word is \"" + guessWord + "\"! You have won!");
                return true;
            }
        }catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    // Check if the guessed letter is correct
    public static boolean checkMatches(char guess){
        // Count how many times the letter appears in the guessWord
        int count = (int) guessWord.chars().filter(ch -> ch == guess).count();
        // Return false when the letter is not in the guess word
        if (count==0)
            return false;

        // Add the letter into correctLetters ArrayList every time it appears in guess word
        guessWord.chars().filter(ch -> ch == guess).forEach(s -> correctLetters.add((char)s));

        return true;
    }


    // Print out the hangman situation
    public static void displayHangman() throws IOException {
        List<String> hangman = Files.readAllLines(Paths.get("hangman.txt"));
        String str = hangman.get(incorrectLetters.size());
        str = str.replace("\\n", System.lineSeparator());
        System.out.println("+---+");
        System.out.println(str);
        System.out.println("====");
    }
}
