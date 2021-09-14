package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner getInput = new Scanner(System.in);
        boolean gameStart = true;
        System.out.println("Hello! What is your name?");
        String name = getInput.nextLine();
        // Runs until the player decides to stop the game
        while (gameStart) {
            int answer = (int) Math.round(Math.random() * 20);
            boolean isWinner = false;
            String[] scenarios = new String[2];
            scenarios[0] = "Your guess is too high.\nTake a guess.";
            scenarios[1] = "Your guess is too low.\nTake a guess.";

            System.out.println("Well, " + name + ", I am thinking of a number between 1 and 20.\n" +
                    "Take a guess.");


            int attempts = 1;
            // The current game ends if the player guesses the number/guesses incorrectly 6 times
            while (attempts < 7) {
                int guess = getNumber();
                if (guess > 0 && guess < 21) {
                    if (guess < answer)
                        System.out.println(scenarios[1]);
                    else if (guess > answer)
                        System.out.println(scenarios[0]);
                    else {
                        isWinner = true;
                        break;
                    }
                    attempts++;
                }else if (guess < 1 || guess > 20){
                    System.out.println("The number has to be between 1 and 20\n");
                }else {
                    attempts--;
                }
            }
            if (isWinner)
                System.out.println("Good job, " + name + "! You guessed my number in " + attempts + " guesses! " +
                        "Would you like to play again? (y or n)");
            else
                System.out.println("Too bad, " + name + "! You didn't guess the number in 6 guesses. " +
                        "Would you like to play again? (y or n)");
            if (!restartGame())
                gameStart=false;
        }
    }

    public static int getNumber(){
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        }catch (Exception e){
            return 0;
        }
    }

    // Wait for the player's decision whether to restart the game
     public static boolean restartGame(){
         Scanner getInput = new Scanner(System.in);
         char restart;
         while (true){
             restart = getInput.next().charAt(0);
             if ((restart == 'y') || (restart == 'n'))
                 break;
             else
                 System.out.println("Would you like to play again? (y or n)");

         }
         if (restart == 'y')
             return true;
         else
             return false;
     }
}
