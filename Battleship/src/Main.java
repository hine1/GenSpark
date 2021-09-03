import java.awt.*;
import java.util.Scanner;

public class Main {
    static Board board1;
    static Board board2;
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nBattleship multiplayer\nEnter Player 1 name: ");
        String name = scanner.next();
        board1 = new Board(name);
        System.out.print("Enter Player 2 name: ");
        name = scanner.next();
        board2 = new Board(name);

        // Get players' ship placements
        getShipPlacements(board1);
        getShipPlacements(board2);

        while (true){
            // Player 1 turn to attack player 2's board
            System.out.println(board2.showOpponentGridView());
            launchAttack(board1.getPlayerName(), board2);
            if (board2.gotAllShipsDestroyed()){
                System.out.println("Congratulation, " + board1.getPlayerName() + "!!! You has won!");
                break;
            }

            // Player 2 turn to attack player 2's board
            System.out.println(board1.showOpponentGridView());
            launchAttack(board2.getPlayerName(), board1);
            if (board1.gotAllShipsDestroyed()){
                System.out.println("Congratulation, " + board2.getPlayerName() + "!!! You has won!");
                break;
            }
        }
    }

    // Launch an attack (attacker, defender)
    // Return true if the attack is made
    public static void launchAttack(String attacker, Board board){
        System.out.println(attacker+ ", enter the coordinates for an attack: ");

        Point point = getCoordinates();
        board.getAttackedAt(point);
    }

    // Get a ship's coordinates from user input
    // Return the coordinates if the input is valid
    public static Point getCoordinates(){
        Scanner scanner = new Scanner(System.in);
        Point point = new Point();
        String[] coordinates;
        while(true){
            try {
                String str = scanner.next();
                str = str.replaceAll("[^0-9|^,]", "");
                coordinates = str.split(",");
                point.x = Integer.valueOf(coordinates[0]);
                point.y = Integer.valueOf(coordinates[1]);
                if (point.x < 1 || point.y < 1 || point.x > 9 || point.x > 9) {
                    throw new Exception();
                } else
                    break;
            }catch(Exception e){
                System.out.print("Please reenter the coordinates. The coordinate values (1-9) must be separated by a ',': ");
            }
        }
        return point;
    }

    // Place a ship on the game board if the placement is not already taken
    public static void placeShip(Board board, Ship ship){
        Scanner scanner = new Scanner(System.in);
        Point point = new Point();

        while (true){
            point = getCoordinates();
            System.out.print("Place horizontally or vertically (h or v)? ");
            ship.setDirection(scanner.next().charAt(0));
            ship.setStartPoint(point);
            if (board.markShip(ship)){
                System.out.println(board.toString());
                break;
            }
            else
                notifyPlacementError(board, ship);
        }
    }

    public static void notifyPlacementError(Board board, Ship ship){
        System.out.println("Cannot place the ship at (" + ship.getStartPoint().x + ", " + ship.getStartPoint().y +
                ") in " + (ship.getDirection()=='v' ? "vertical" : "horizontal") +
                " direction. Please try again");
        System.out.print("\nEnter the coordinates for the " + ship.getClass().getName() + " (length of " + ship.getLength() + "):");
    }

    // Get placements for 5 ships per each player
    public static void getShipPlacements(Board board){
        Scanner scanner = new Scanner(System.in);
        System.out.println(board.toString());
        System.out.println(board.getPlayerName() + ", please enter the coordinates for your ships.");

        System.out.print("\nEnter the coordinates for the carrier (length of 5): ");
        Ship carrier = new Carrier();
        placeShip(board, carrier);

        System.out.print("\nEnter the coordinates for the battleship (length of 4): ");
        Ship battleship = new Battleship();
        placeShip(board, battleship);

        System.out.print("\nEnter the coordinates for the cruiser (length of 3): ");
        Ship cruiser = new Cruiser();
        placeShip(board, cruiser);

        System.out.print("\nEnter the coordinates for the submarine (length of 3): ");
        Ship submarine = new Submarine();
        placeShip(board, submarine);

        System.out.print("\nEnter the coordinates for the destroyer (length of 2): ");
        Ship destroyer = new Destroyer();
        placeShip(board, destroyer);
    }
}
