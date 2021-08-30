import java.util.ArrayList;

public class Human {
    int health;
    int minAttackingPower;
    int x_location;
    int y_location;
    ArrayList<Potion> potions = new ArrayList<>();

    Human(){
        health = 100;
        minAttackingPower = 15;
        x_location = (int)Math.floor(Math.random()*10);
        y_location = (int)Math.floor(Math.random()*8);
    }

    public void move(int x_location, int y_location){
        this.x_location = x_location;
        this.y_location = y_location;
    }

    public void collectPotion(Potion potion){
        potions.add(potion);
        System.out.println("Collect new potion! \nPotion inventory count is " + potions.size() +"\n");
    }

    public boolean consumePotion(){
        if (potions.size()>0) {
            health += potions.get(0).healingPower;
            System.out.println("Human health increases by " + potions.get(0).healingPower +
                    "\nHuman health: " + this.health + "\n");
            potions.remove(0);
            return true;
        }else {
            System.out.println("There is no potions available");
            return false;
        }
    }

    @Override
    public String toString(){
        return "O";
    }
}
