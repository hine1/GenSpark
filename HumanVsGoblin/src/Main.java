import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Human human = new Human();
    static Goblin goblin = new Goblin();
    static ArrayList<Potion> potions = new ArrayList<>();
    static Land land = new Land(human, goblin, potions);

    static boolean gameOver = false;

    public static void main(String[] args){
        System.out.println("\n\nWelcome to the game of Human Vs Goblin\nYou are Human 'O'\nThe Goblin is strong and will chase after you\n" +
                "Don't worry, you can still fight it back with the help of healing potion '*'\n" +
                "Move to '*' to collect potions\n" +
                "Move next to 'X' to initiate a fight\n" +
                "Now, go fight the Goblin!\n");
        while (!gameOver){
            if ((int)Math.round(Math.random()*3) == 1) {
                potions.add(new Potion());
                System.out.println("\nNEW POTION DROP!\n");
            }
            System.out.println(land);
            // Wait till human makes a move

            while (!moveHuman()){

            }

            if (!checkCollision()) {
                goblin.move(human);
            }
        }
    }
    public static boolean checkCollision(){
        int distanceX = goblin.x_location - human.x_location;
        int distanceY = goblin.y_location - human.y_location;
        if (Math.abs(distanceX) == 1 && Math.abs(distanceY) == 0) {
            fight();
            return true;
        }else if (Math.abs(distanceX) == 0 && Math.abs(distanceY) == 1) {
            fight();
            return true;
        }
        else return false;
    }

    public static void fight(){
        int goblinDamage = (int) (Math.random()*10) + human.minAttackingPower;
        int humanDamage = (int) (Math.random()*10) + goblin.minAttackingPower;
        goblin.health -= goblinDamage;
        human.health -= humanDamage;
        if (goblin.health <=0){
            System.out.println("Human dealt " +goblinDamage +" damages to goblin. The goblin is dead! GAME OVER!");
            gameOver = true;
        }else if (human.health <=0){
            System.out.println("Goblin dealt " + humanDamage +" damages to human. The human is dead! GAME OVER!");
            gameOver = true;
        }
        else{
            System.out.println("\nHuman attack: " + goblinDamage + " damages! Goblin's health: " + goblin.health);
            System.out.println("Goblin attack: " + humanDamage + " damages! Human's health: " + human.health +"\n");
        }
    }
    public static boolean moveHuman (){
        System.out.println("Make a move. \nEnter ('n'/'s'/'e'/'w') to move. \n" +
                "Enter 'f' to continue drawing attack. \nEnter 'p' to consume potion:");
        Scanner scanner = new Scanner(System.in);
        char action = scanner.next().charAt(0);
        switch (action){
            case 'n':
                if (goblin.y_location - human.y_location == -1 && goblin.x_location == human.x_location)
                    return false;
                human.move(human.x_location, human.y_location - 1);
                isFoundPotion();
                return true;
            case 's':
                if (goblin.y_location - human.y_location == 1 && goblin.x_location == human.x_location)
                    return false;
                human.move(human.x_location, human.y_location + 1);
                isFoundPotion();
                return true;
            case 'e':
                if (goblin.x_location - human.x_location == 1 && goblin.y_location == human.y_location)
                    return false;
                human.move(human.x_location + 1, human.y_location);
                isFoundPotion();
                return true;
            case 'w':
                if (goblin.x_location - human.x_location == -1 && goblin.y_location == human.y_location)
                    return false;
                human.move(human.x_location - 1, human.y_location);
                isFoundPotion();
                return true;
            case 'p':
                human.consumePotion();
                return false;
            case 'f':
                return true;
        }
        return false;
    }
    public static void isFoundPotion(){
        for (int i=0; i<potions.size(); i++){
            if (human.x_location == potions.get(i).x_location &&
                    human.y_location == potions.get(i).y_location) {
                human.collectPotion(potions.remove(i));
                break;
            }
        }
    }
}
