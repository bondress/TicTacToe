package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        /*
        Create two-dimensional array to contain the symbols that make up the 
        Game Board
         */
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '}};

        //Getting input from the user and printing it onto the Game Board
        //Create a while loop to ensure continuous play
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter your placement (1-9): ");
            int playerPosition = scan.nextInt();
            
            while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)){
                System.out.println("Position taken! Enter a correct position!");
                playerPosition = scan.nextInt();
            }
            //Call the method to place a piece on the Game Board for the player
            placePiece(gameBoard, playerPosition, "player");
            
            String result = checkWinner();
            //Stop the game once the player has won
            if(result.length()>0){
                printGameBoard(gameBoard);
                System.out.println(result);
                break;                
            }

            //Call the method to place a piece on the Game Board for the cpu
            Random rand = new Random();
            int cpuPosition = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)){
                System.out.println("Position taken! Enter a correct position!");
                cpuPosition = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPosition, "cpu");
            //Stop the game once the cpu has won
            if(result.length()>0){
                printGameBoard(gameBoard);
                System.out.println(result);
                break;                
            }

            //Call the method that prints out the Game Board
            printGameBoard(gameBoard);
        }

    }

    /*
    Create a method to print out the two-dimensional array which contains
    the symbols for the Game Board
     */
    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    /**
     *
     * @param gameBoard represents the Game Board
     * @param position represents the position the player chooses
     * @param user represents the current user of the application (i.e player or
     * cpu)
     */
    public static void placePiece(char[][] gameBoard, int position,
            String user) {
        //a variable to store the symbol depending on whose turn it is to play
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(position);
        }
        
        //a switch statement to place an X or an O on the board based on the player
        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    //Create a method to check who won the game
    public static String checkWinner() {
        //Create an a read-only List to store all possible winning outcomes
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftColumn = Arrays.asList(1, 4, 7);
        List middleColumn = Arrays.asList(2, 5, 8);
        List rightColumn = Arrays.asList(3, 6, 9);
        List leftDiagonal = Arrays.asList(1, 5, 9);
        List rightDiagonal = Arrays.asList(3, 5, 7);

        //Create another list to the previously created lists
        List<List> winningConditions = new ArrayList<>();
        winningConditions.add(topRow);
        winningConditions.add(middleRow);
        winningConditions.add(bottomRow);
        winningConditions.add(leftColumn);
        winningConditions.add(middleColumn);
        winningConditions.add(rightColumn);
        winningConditions.add(leftDiagonal);
        winningConditions.add(rightDiagonal);

        //Loop through the List of Lists to check who gets the winning combination first
        for (List winningList : winningConditions) {
            if (playerPositions.containsAll(winningList)) {
                return "Congratulations! You Won!!!";
            } else if (cpuPositions.containsAll(winningList)) {
                return "CPU Wins! Sorry :(";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "CAT"; //This means there's a tie between the player and cpu
            }
        }
        return "";
    }
}
