public class Potion {
    int healingPower;
    int x_location;
    int y_location;

    Potion(){
        this.healingPower = (int)(Math.random()*10 + 10);
        x_location = (int)Math.floor(Math.random()*10);
        y_location = (int)Math.floor(Math.random()*10);
    }

    @Override
    public String toString(){
        return "*";
    }
}
