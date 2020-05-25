import java.util.Arrays;
import java.util.Random;

public class Grid {

    private Cell[][] grid;

    private int numBombs;

    public Grid(int size, int numBombs){
        grid = new Cell[size][size];

        // initialize grid cells
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                grid[i][j] = new Cell();
            }
        }

        this.numBombs = numBombs;

        // create bombs
        while(numBombs > 0){
            Random rand = new Random();
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);
            if(grid[row][col].getType() != Cell.CellType.BOMB){
                grid[row][col].setType(Cell.CellType.BOMB);
                incrementSurroundingBombCounts(row, col);
                numBombs--;
            }
        }

        // mark other cells as blank or numbered
        for(Cell[] cellArr : grid){
            for(Cell cell : cellArr){
                if(cell.getType() != Cell.CellType.BOMB){
                    if(cell.getNumber() == 0){
                        cell.setType(Cell.CellType.BLANK);
                    } else{
                        cell.setType(Cell.CellType.NUMBER);
                    }
                }
            }
        }
    }

    // increases the bomb count of all cells surrounding a cell with coordinates row,col
    private void incrementSurroundingBombCounts(int row, int col){
        int[][] surroundings = new int[][]{{-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}};
        for(int[] surrounding : surroundings){
            int nextRow = row + surrounding[0];
            int nextCol = col + surrounding[1];
            if(nextRow >= 0 && nextRow < grid.length){
                if (nextCol >= 0 && nextCol < grid[nextRow].length){
                    grid[nextRow][nextCol].incrementNumber();
                }
            }
        }
    }

    public void printGrid(){
        for (Cell[] cellArr : grid)
            System.out.println(Arrays.toString(cellArr));
    }

    public void revealAndPrintGrid(){
        for(Cell[] cellArr : grid) {
            for (Cell cell : cellArr) {
                cell.reveal();
            }
        }
        for (Cell[] cellArr : grid)
            System.out.println(Arrays.toString(cellArr));
    }
}
