import java.util.Scanner;

public class Game {
    /*
    Constraints:

    NxN grid with B bombs
    All cells in grid show number of bombs adjacent
    Needs a user able to choose a square to expose
    If bomb hit, game lose, if all squares but bombs hit, game win
    If number hit, number shown, if blank hit, all connected blanks and numbers adjacent to those blanks are revealed
     */

    /*
    Requirements:

    Grid class
    Game class
    Cell class
     */

    public static void play(){
        System.out.println("Welcome to Minesweeper");
        int size = getSizeFromUser();
        int numBombs = getNumBombsFromUser();

        Grid grid = new Grid(size, numBombs);
        while (true){
            grid.printGrid();
            System.out.println("Enter the row number of the cell to reveal:");
            int row = getCellNumFromUser(size);
            System.out.println("Enter the column number of the cell to reveal:");
            int col = getCellNumFromUser(size);
            while(grid.isRevealed(row, col)){
                System.out.println("That cell was already revealed, please select another");
                System.out.println("Enter the row number of the cell to reveal:");
                row = getCellNumFromUser(size);
                System.out.println("Enter the column number of the cell to reveal:");
                col = getCellNumFromUser(size);
            }


            boolean stillPlaying = grid.revealAtIndex(row, col);
            if(!stillPlaying){
                grid.printGrid();
                System.out.println("You hit a bomb, you lose.");
                break;
            }

            if(grid.hasWon()){
                grid.printGrid();
                System.out.println("You have revealed all non-bombs, you win!.");
                break;
            }
        }
    }

    private static int getSizeFromUser() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the board size you would like:");
        int size = scan.nextInt();
        while (size < 2) {
            System.out.println("Please enter a valid size:");
            size = scan.nextInt();
        }
        return size;
    }

    private static int getNumBombsFromUser(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of bombs you would like:");
        int numBombs = scan.nextInt();
        while(numBombs < 1){
            System.out.println("Please enter more than 0 bombs:");
            numBombs = scan.nextInt();
        }
        return numBombs;
    }

    private static int getCellNumFromUser(int size){
        Scanner scan = new Scanner(System.in);

        int cellNum = scan.nextInt();
        while(cellNum < 0 || cellNum >= size){
            System.out.println("Please enter a valid coordinate:");
            cellNum = scan.nextInt();
        }
        return cellNum;
    }

    public static void main(String[] args) {
        play();
    }
}
