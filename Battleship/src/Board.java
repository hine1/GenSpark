import java.awt.*;

public class Board {
    private char[][] grid = new char[10][10];
    private char[][] opponentGridView = new char[10][10];
    private String playerName;

    Board(String playerName){
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


    // Get attacked at coordinates
    public void getAttackedAt(Point point){
        if (grid[point.x][point.y] == '\u0000') {
            opponentGridView[point.x][point.y] = 'm';
            showOpponentGridView();
            System.out.println("You missed!\n");
        }else {
            opponentGridView[point.x][point.y] = 'x';
            showOpponentGridView();
            System.out.println("Attacked successfully at (" + point.x + "," + point.y + ")\n");
        }

    }

    // Declare lost if there are 17 successful attacks (total length of all ships)
    public boolean gotAllShipsDestroyed(){
        int destroys = 0;
        // Count number of 'x' marks
        String str = showOpponentGridView().replaceAll("[^x]", "");
        destroys = str.length();
        if (destroys == 17)
            return true;
        else
            return false;
    }

    // Mark ship placement on the board game if the placement is valid
    public boolean markShip(Ship ship){
        int x = ship.getStartPoint().x;
        int y = ship.getStartPoint().y;

        // if the start coordinates is already taken, return false
        if (grid[x][y] != '\u0000'){
            System.out.println("ERROR. The coordinates are already taken.");
            return false;
        }

        try{
            // if the direction input is not correct (v or h)
            if (ship.getDirection() != 'v' && (ship.getDirection() != 'h'))
                throw new Exception("The direction must be either 'v' or 'h'");
            // Vertical
            if (ship.getDirection() == 'v'){
                    // If the ship is able to fit in a forward direction
                    if (x<=ship.getLength() && checkShipPlacement(ship, true)) {
                        for (int i=0; i<ship.getLength(); i++){
                            grid[x+i][y] = ship.getMark();
                        }
                        return true;
                        // If the ship can't fit in the forward direction
                    }else if (x>ship.getLength() && checkShipPlacement(ship, false)) {
                        for (int i = 0; i < ship.getLength(); i++) {
                            grid[x - i][y] = ship.getMark();
                        }
                        return true;
                    }
            // Horizontal
            }else if (ship.getDirection() == 'h'){
                // If the ship is able to fit in a forward direction
                if (y<=ship.getLength() && checkShipPlacement(ship, true)) {
                    for (int i=0; i<ship.getLength(); i++){
                        grid[x][y+i] = ship.getMark();
                    }
                    return true;
                    // If the ship can't fit in the forward direction
                }else if (y>ship.getLength() && checkShipPlacement(ship, false)) {
                    for (int i=0; i<ship.getLength(); i++){
                        grid[x][y-i] = ship.getMark();
                    }
                    return true;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    // Check whether the ship placement is valid or not
    public boolean checkShipPlacement(Ship ship, boolean isForward){
        int x = ship.getStartPoint().x;
        int y = ship.getStartPoint().y;

        if (isForward && ship.getDirection() == 'v'){
            for (int i=0; i<ship.getLength(); i++){
                if (grid[x+i][y] != '\u0000')
                    return false;
            }
        }
        else if (isForward && ship.getDirection() == 'h'){
            for (int i=0; i<ship.getLength(); i++){
                if (grid[x][y+i] != '\u0000')
                    return false;
            }
        }
        else if (!isForward && ship.getDirection() == 'v'){
            for (int i=ship.getLength()-1; i>=0; i--){
                if (grid[i][y] != '\u0000')
                    return false;
            }
        }
        else if (!isForward && ship.getDirection() == 'h'){
            for (int i=ship.getLength()-1; i>=0; i--){
                if (grid[x][i] != '\u0000')
                    return false;
            }
        }
        return true;
    }

    // This is what the opponent see when launching an attack
    public String showOpponentGridView(){
        String str = "";
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                if (i==0){
                    if (j==0)
                        str += " ";
                    else
                        str += j;
                }else if (j==0) {
                    str += i;
                }else{
                    if(opponentGridView[i][j] == '\u0000')
                        str += '~';
                    else
                        str += opponentGridView[i][j];
                }
                str += " ";
            }
            str += "\n";
        }
        return str;
    }

    @Override
    public String toString(){
        String str = "";
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                if (i==0){
                    if (j==0)
                        str += " ";
                    else
                        str += j;
                }else if (j==0) {
                    str += i;
                }else{
                    if(grid[i][j] == '\u0000')
                        str += '~';
                    else
                        str += grid[i][j];
                }
                str += " ";
            }
            str += "\n";
        }
        return str;
    }
}
