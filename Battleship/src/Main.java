import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nBattleship multiplayer\nEnter Player 1 name: ");
        String name = scanner.next();
        Board board1 = new Board(name);
        System.out.print("Enter Player 1 name: ");
        name = scanner.next();
        Board board2 = new Board(name);

        getShipPlacements(board1);
        getShipPlacements(board2);
//        while (!gameOver){
//
//        }
    }


    public static void placeShip(Board board, Ship ship){
        Scanner scanner = new Scanner(System.in);
        Point point = new Point();
        String[] coordinates;
        while (true){
            String str = scanner.next();
            str = str.replaceAll("[^0-9|^,]","");
            coordinates = str.split(",");
            point.x = Integer.valueOf(coordinates[0]);
            point.y = Integer.valueOf(coordinates[1]);
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
        System.out.print("\nEnter the coordinates for the carrier: ");
    }

    //Need to include enum for coordinate values and directions
    public static void getShipPlacements(Board board){
        Scanner scanner = new Scanner(System.in);
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
