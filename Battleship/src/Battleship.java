import java.awt.*;

public class Battleship extends Ship{
    private static int length = 4;
    private static char mark = 'B';

    Battleship(){
        this.setLength(length);
        this.setMark(mark);
    }

//    Battleship(Point startPoint, char direction){
//        this.setStartPoint(startPoint);
//        this.setDirection(direction);
//        this.setLength(length);
//        this.setMark(mark);
//    }

}
