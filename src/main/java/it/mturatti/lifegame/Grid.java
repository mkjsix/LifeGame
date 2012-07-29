/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mturatti.lifegame;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mturatti
 */
public class Grid {

    public final int rows;
    public final int cols;
    private final Cell matrixOfCells[][];
    private ComputationStrategy computation = new DefaultComputationStrategy();
    private static final Logger log = LoggerFactory.getLogger(Grid.class);

    private Grid(int rows, int cols) {
        if (rows < 3 || cols < 3) {
            throw new IllegalArgumentException("Smallest grid must be 3x3.");
        }
        this.rows = rows;
        this.cols = cols;
        this.matrixOfCells = new Cell[rows][cols];
        log.info("Grid with [{}] cells created.", rows * cols);
    }
    
    public void setComputationStrategy(ComputationStrategy computation) {
        this.computation = computation;
    }

    public static Grid newRandomInstance(int rows, int cols, int initialAliveRatio) {
        if (initialAliveRatio < 0 || initialAliveRatio > 100) {
            throw new IllegalArgumentException("initialAliveRatio must be an integer in [0, 100]");
        }
        final Random random = new Random();
        final Grid grid = new Grid(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (random.nextInt(100) < initialAliveRatio) {
                    grid.putCell(Cell.newAliveInstance(i, j));
                } else {
                    grid.putCell(Cell.newDeadInstance(i, j));
                }
            }
        }
        log.info("newRandomInstance created.");
        return grid;
    }

    public static Grid newEmptyInstance(int rows, int cols) {
        final Grid grid = new Grid(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid.putCell(Cell.newDeadInstance(i, j));
            }
        }
        log.info("newEmptyInstance created.");
        return grid;
    }

    public void putCell(Cell newCell) {
        matrixOfCells[newCell.row][newCell.col] = newCell;
    }

    public void computeNextGeneration() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                final Cell cell = matrixOfCells[i][j];
                int aliveNeighbours = countAliveNeighbours(cell);
                computation.execute(this, cell, aliveNeighbours, i, j);
            }
        }
        log.info("Next generation computed.");
    }

    public int countAliveNeighbours(Cell cell) {
        int alives = north(cell)
                + northWest(cell)
                + west(cell)
                + southWest(cell)
                + south(cell)
                + southEast(cell)
                + east(cell)
                + northEast(cell);
        return alives;
    }

    public int countTotalAlive() {
        int totalAlive = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                totalAlive += cellAliveAsInt(i, j);
            }
        }
        log.info("Total alive: [{}]", totalAlive);
        return totalAlive;
    }

    public boolean isCellAlive(int row, int col) {
        return matrixOfCells[row][col].isAlive;
    }

    private int west(Cell cell) {
        int col = goWest(cell);
        return cellAliveAsInt(cell.row, col);
    }

    private int east(Cell cell) {
        int col = goEast(cell);
        return cellAliveAsInt(cell.row, col);
    }

    private int north(Cell cell) {
        int row = goNorth(cell);
        return cellAliveAsInt(row, cell.col);
    }

    private int south(Cell cell) {
        int row = goSouth(cell);
        return cellAliveAsInt(row, cell.col);
    }

    private int northWest(Cell cell) {
        int row = goNorth(cell);
        int col = goWest(cell);
        return cellAliveAsInt(row, col);
    }

    private int northEast(Cell cell) {
        int row = goNorth(cell);
        int col = goEast(cell);
        return cellAliveAsInt(row, col);
    }

    private int southWest(Cell cell) {
        int row = goSouth(cell);
        int col = goWest(cell);
        return cellAliveAsInt(row, col);
    }

    private int southEast(Cell cell) {
        int row = goSouth(cell);
        int col = goEast(cell);
        return cellAliveAsInt(row, col);
    }

    private int goWest(Cell cell) {
        int col = cell.col < this.cols - 1 ? cell.col + 1 : 0;
        return col;
    }

    private int goEast(Cell cell) {
        int col = cell.col > 0 ? cell.col - 1 : this.cols - 1;
        return col;
    }

    private int goNorth(Cell cell) {
        int row = cell.row > 0 ? cell.row - 1 : this.rows - 1;
        return row;
    }

    private int goSouth(Cell cell) {
        int row = cell.row < this.rows - 1 ? cell.row + 1 : 0;
        return row;
    }

    private int cellAliveAsInt(int row, int col) {
        return matrixOfCells[row][col].isAlive ? 1 : 0;
    }

}
