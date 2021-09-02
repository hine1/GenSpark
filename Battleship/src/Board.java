import java.util.ArrayList;

public class Board {
    private static char[][] grid = new char[10][10];
    private ArrayList<Ship> ships = new ArrayList<>();
    private String playerName;

    public static char[][] getGrid() {
        return grid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    Board(String playerName){
        this.playerName = playerName;
    }
    public void addShip(Ship ship) {
        this.ships.add(ship);
        markShip(ship);
    }

    public void destroyShip(Ship ship){
        this.ships.remove(ship);
    }

    public boolean markShip(Ship ship){
        int x = ship.getStartPoint().x;
        int y = ship.getStartPoint().y;

        if (grid[x][y] != '\u0000'){
            System.out.println("ERROR. The coordinates are already taken.");
            return false;
        }

        if (checkShipPlacement(ship, false)){
            if (ship.getDirection() == 'v'){
                for (int i=0; i<ship.getLength(); i++){
                    grid[x-i][y] = ship.getMark();
                }
            }else{
                for (int i=0; i<ship.getLength(); i++){
                    grid[x][y-i] = ship.getMark();
                }
            }
            return true;
        }else if (checkShipPlacement(ship, true)){
            if (ship.getDirection() == 'v'){
                for (int i=0; i<ship.getLength(); i++){
                    grid[x+i][y] = ship.getMark();
                }
            }else{
                for (int i=0; i<ship.getLength(); i++){
                    grid[x][y+i] = ship.getMark();
                }
            }
            return true;
        }
        return false;
    }

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
            }
            str += "\n";
        }
        return str;
    }
}
