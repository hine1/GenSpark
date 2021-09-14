package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String premise = "\nYou are in a land full of dragons. In front of you,\n" +
                "you see two caves. In one cave, the dragon is friendly\n" +
                "and will share his treasure with you. The other dragon\n" +
                "is greedy and hungry and will eat you on sight.\n" +
                "Which cave will you go into? (1 or 2)\n";
        String[] scenarios = new String[2];
        // The player encounters a dangerous dragon
        scenarios[0] = "\nYou approach the cave...\n" +
                "It is dark and spooky...\n" +
                "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                "Gobbles you down in one bite!\n";
        // The player encounters a friendly dragon
        scenarios[1] = "\nYou approach the cave...\n" +
                "It is dark and spooky...\n" +
                "A large dragon jumps out in front of you! He spreads his wings widely and...\n" +
                "Gives you the fuzziest hug!\n";
	    System.out.println(premise);

        int decision;
        int randomResult = (int)Math.round(Math.random());
        // Waits till a correct input is made (1 or 2)
        while (true) {
            decision = getDecision();
            if (decision == 1 || decision == 2)
                break;
            else
                System.out.println(premise);
        }

        System.out.println(scenarios[randomResult]);
    }

    public static int getDecision(){
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        }catch (Exception e){
            System.out.println("The answer has to be either 1 or 2");
        }
        return 0;
    }
}
