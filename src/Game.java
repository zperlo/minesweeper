public class Game {
    /*
    Constraints:

    NxN grid with B bombs
    All cells in grid show number of bombs adjacent
    Needs a user able to choose a square to expose
    If bomb hit, game lose, if all squares but bombs hit, game win
    If number hit, number shown, if blank hit, all connected blanks and numbers adjacent to those blanks are revealed
    User can flag bombs
     */

    /*
    Requirements:

    Grid class
    Game class
    Cell class
     */

    public static void main(String[] args) {
        Grid grid = new Grid(10, 4);
        grid.printGrid();
        grid.revealAndPrintGrid();
    }
}
