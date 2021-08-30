import java.util.ArrayList;

public class Land {
    int width_units = 10;
    int height_units = 8;
    Human human;
    Goblin goblin;
    ArrayList<Potion> potions;

    Land(Human human, Goblin goblin, ArrayList<Potion> potions){
      this.human = human;
      this.goblin = goblin;
      this.potions = potions;
    }

    @Override
    public String toString(){
        String str = "";
        boolean  isPotion = false;
        for (int i=0; i<height_units; i++){
            for (int j=0; j<width_units; j++){
                if (human.x_location == j && human.y_location == i) {
                    str += human.toString();
                    str += "|";
                }else if (goblin.x_location == j && goblin.y_location == i) {
                    str += goblin.toString();
                    str += "|";
                }
                else {
                    for (int k=0; k< potions.size(); k++){
                        if (potions.get(k).x_location == j && potions.get(k).y_location == i){
                            str += potions.get(k).toString();
                            str += "|";
                            isPotion = true;
                        }
                    }
                    if (isPotion)
                        isPotion = false;
                    else
                        str += "_|";
                }
            }
            str += "\n";
        }
        return str;
    }
}
