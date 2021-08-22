package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, Character> gameBoard = new HashMap<>();
        int totalMoves = 0;
        int AIMove;
        int playerMove;
        boolean gameOver;

        // Get player's choice of 'X' or 'O'
        char playerMark = getPlayerMark();
        char AIMark;
        if (playerMark == 'X')
            AIMark = 'O';
        else
            AIMark = 'X';
        while (true){
            if (totalMoves == 0){
                gameBoard.clear();
                System.out.println("The computer will go first");
            }

            // AI makes a move
            AIMove = getAIMove(gameBoard);
            gameBoard.put(AIMove, AIMark);
            displayGameBoard(gameBoard);
            totalMoves++;

            gameOver = checkIfGameOver(gameBoard);
            if (gameOver || totalMoves==9){
                if (!gameOver)
                    System.out.println("It's a tie!");
                else
                    System.out.println("The computer has beaten you! You lose.");
                if (checkWhetherToRestartGame()) {
                    totalMoves = 0;
                    continue;
                } else
                    break;
            }

            // Player makes a move
            playerMove = getPlayerMove(gameBoard);
            gameBoard.put(playerMove, playerMark);
            totalMoves++;

            gameOver = checkIfGameOver(gameBoard);
            if (gameOver){
                displayGameBoard(gameBoard);
                System.out.println("You beat the computer! Congrats!");
                if (checkWhetherToRestartGame())
                    totalMoves=0;
                else
                    break;
            }
        }
    }
    public static boolean checkWhetherToRestartGame(){
        System.out.println("Do you want to play again? (yes or no)");
        Scanner getInput = new Scanner(System.in);
        String decision;
        while (true){
            decision = getInput.next();
            if (decision.equals("yes"))
                return true;
            else if (decision.equals("no"))
                return false;
        }
    }
    public static boolean checkIfGameOver(HashMap<Integer, Character> gameBoard){
        char serialChar;
        // Check for Tic Tac Toe horizontally
        for (int i=1; i<10; i++){
            if (i%3==1 && gameBoard.get(i)!=null  && gameBoard.get(i+1)!=null && gameBoard.get(i+2)!=null){
                serialChar = gameBoard.get(i);
                if (serialChar == gameBoard.get(i+1) && serialChar == gameBoard.get(i+2) )
                    return true;
                else i+=3;
            }
        }
        // Check for Tic Tac Toe vertically
        for (int i=1; i<4; i++){
            if ( gameBoard.get(i)!=null && gameBoard.get(i + 3)!= null && gameBoard.get(i + 6)!=null) {
                serialChar = gameBoard.get(i);
                if (serialChar == gameBoard.get(i + 3) && serialChar == gameBoard.get(i + 6))
                    return true;
            }
        }
        // Check for Tic Tac Toe diagonally
        if ( gameBoard.get(1)!=null && gameBoard.get(5)!=null && gameBoard.get(9)!=null){
            serialChar = gameBoard.get(1);
            if (serialChar == gameBoard.get(5) && serialChar == gameBoard.get(9) )
                return true;
        }
        if (gameBoard.get(3)!=null && gameBoard.get(5)!=null && gameBoard.get(7)!=null){
            serialChar = gameBoard.get(3);
            if (serialChar == gameBoard.get(5) && serialChar == gameBoard.get(7) )
                return true;
        }
        return false;
    }
    public static int getAIMove(HashMap<Integer, Character> gameBoard){
        int move;
        while (true){
            move = (int)Math.round(Math.random()*9);
            if (!gameBoard.containsKey(move))
                return move;
        }
    }
    public static int getPlayerMove(HashMap<Integer, Character> gameBoard){
        System.out.println("What is your next move? (1-9)");
        Scanner getInput = new Scanner(System.in);
        int playerMove = getInput.nextInt();
        while (true){
            if (playerMove>9)
                System.out.println("Please enter a move (1-9)");
            else if (gameBoard.containsKey(playerMove))
                System.out.println(playerMove + " is taken. Please enter a move (1-9)");
            else
                return playerMove;
        }
    }
    public static char getPlayerMark(){
        System.out.println("Welcome to Tic-Tac-Toe!\nDo you want to be X or O?");
        Scanner getInput = new Scanner(System.in);
        char playerMark = getInput.next().charAt(0);
        while (true){
            if (playerMark == 'X' || playerMark == 'x')
                return 'X';
            else if (playerMark == 'O' || playerMark == 'o')
                return 'O';
        }
    }
    public static void displayGameBoard(HashMap<Integer, Character> gameBoard){
        for (int i =1; i<10; i++){
            if (gameBoard.get(i)!=null)
                System.out.print(gameBoard.get(i));
            else
                System.out.print(" ");
            if (i%3==0)
                System.out.println();
            else
                System.out.print("|");
        }
        System.out.println();
    }
}
