public class Goblin {
    int health;
    int minAttackingPower;
    int x_location;
    int y_location;

    Goblin(){
        health = 100;
        minAttackingPower = 20;
        x_location = (int)Math.floor(Math.random()*10);
        y_location = (int)Math.floor(Math.random()*8);
    }

    // Goblin chases human
    public void move(Human human){
        int distanceX = x_location - human.x_location;
        int distanceY = y_location - human.y_location;
        if (Math.abs(distanceX) == 1 && Math.abs(distanceY) == 1)
            return;
        else if (distanceX > 1)
            x_location--;
        else if (distanceX < -1)
            x_location++;
        else if (distanceY > 1)
            y_location--;
        else if (distanceY < -1)
            x_location++;
    }

    @Override
    public String toString(){
        return "X";
    }
}

