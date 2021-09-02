import java.awt.*;

public class Destroyer extends Ship{
    private static int length = 2;
    private static char mark = 'D';

    Destroyer(){
        this.setLength(length);
        this.setMark(mark);
    }
//    Destroyer(Point startPoint, char direction){
//        this.setStartPoint(startPoint);
//        this.setDirection(direction);
//        this.setLength(length);
//        this.setMark(mark);
//    }
}
