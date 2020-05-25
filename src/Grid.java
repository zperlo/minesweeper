import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Grid {

    private Cell[][] grid;

    public Grid(int size, int numBombs){
        grid = new Cell[size][size];

        // initialize grid cells
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                grid[i][j] = new Cell();
            }
        }

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
        String[] cols = new String[grid.length];
        for(int i = 0; i < cols.length; i++){
            cols[i] = Integer.toString(i);
        }
        System.out.println(" " + Arrays.toString(cols));
        for (int j = 0; j < grid.length; j++)
            System.out.println(Integer.toString(j) + Arrays.toString(grid[j]));
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

    public boolean isRevealed(int row, int col){
        return grid[row][col].isRevealed();
    }

    public boolean hasWon(){
        for(Cell[] cellArr : grid) {
            for (Cell cell : cellArr) {
                if(cell.getType() != Cell.CellType.BOMB){
                    if(!cell.isRevealed()){
                        return false;
                    }
                } else {
                    if(cell.isRevealed()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // returns true if fine, false if bomb is revealed
    public boolean revealAtIndex(int row, int col){
        grid[row][col].reveal();
        switch (grid[row][col].getType()) {
            case BOMB:
                return false;
            case BLANK:
                revealBlanks(row, col);
                break;
        }
        return true;
    }

    private void revealBlanks(int r, int c){
        LinkedList<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[] {r, c});

        int[][] surroundings = new int[][]{{-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}};
        while(!queue.isEmpty()) {
            int[] currCell = queue.pop();
            int row = currCell[0];
            int col = currCell[1];
            for (int[] surrounding : surroundings) {
                int nextRow = row + surrounding[0];
                int nextCol = col + surrounding[1];
                if (nextRow >= 0 && nextRow < grid.length) {
                    if (nextCol >= 0 && nextCol < grid[nextRow].length) {
                        if(grid[nextRow][nextCol].getType() == Cell.CellType.BLANK && !grid[nextRow][nextCol].isRevealed()){
                            queue.add(new int[] {nextRow, nextCol});
                        }
                        grid[nextRow][nextCol].reveal();
                    }
                }
            }
        }
    }
}
