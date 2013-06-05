/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mturatti.lifegame;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author  mturatti
 */
public class Grid {

    public final int rows;
    public final int cols;
    private final Cell[][] matrixOfCells;
    private ComputationStrategy computation = new DefaultComputationStrategy();
    private static final Logger log = LoggerFactory.getLogger(Grid.class);

    private Grid(final int rows, final int cols) {
        if (rows < 3 || cols < 3) {
            throw new IllegalArgumentException("Smallest grid must be 3x3.");
        }

        this.rows = rows;
        this.cols = cols;
        this.matrixOfCells = new Cell[rows][cols];
        log.info("Grid with [{}] cells created.", rows * cols);
    }

    public void setComputationStrategy(final ComputationStrategy computation) {
        this.computation = computation;
    }

    public static Grid newRandomInstance(final int rows, final int cols, final int initialAliveRatio) {
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

    public static Grid newEmptyInstance(final int rows, final int cols) {
        final Grid grid = new Grid(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid.putCell(Cell.newDeadInstance(i, j));
            }
        }

        log.info("newEmptyInstance created.");
        return grid;
    }

    public void putCell(final Cell newCell) {
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

    public int countAliveNeighbours(final Cell cell) {
        int alives = north(cell) + northWest(cell) + west(cell) + southWest(cell) + south(cell) + southEast(cell)
                + east(cell) + northEast(cell);
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

    public boolean isCellAlive(final int row, final int col) {
        return matrixOfCells[row][col].isAlive;
    }

    private int west(final Cell cell) {
        return cellAliveAsInt(cell.row, goWest(cell));
    }

    private int east(final Cell cell) {
        return cellAliveAsInt(cell.row, goEast(cell));
    }

    private int north(final Cell cell) {
        return cellAliveAsInt(goNorth(cell), cell.col);
    }

    private int south(final Cell cell) {
        return cellAliveAsInt(goSouth(cell), cell.col);
    }

    private int northWest(final Cell cell) {
        return cellAliveAsInt(goNorth(cell), goWest(cell));
    }

    private int northEast(final Cell cell) {
        return cellAliveAsInt(goNorth(cell), goEast(cell));
    }

    private int southWest(final Cell cell) {
        return cellAliveAsInt(goSouth(cell), goWest(cell));
    }

    private int southEast(final Cell cell) {
        return cellAliveAsInt(goSouth(cell), goEast(cell));
    }

    private int goWest(final Cell cell) {
        return cell.col < this.cols - 1 ? cell.col + 1 : 0;
    }

    private int goEast(final Cell cell) {
        return cell.col > 0 ? cell.col - 1 : this.cols - 1;
    }

    private int goNorth(final Cell cell) {
        return cell.row > 0 ? cell.row - 1 : this.rows - 1;
    }

    private int goSouth(final Cell cell) {
        return cell.row < this.rows - 1 ? cell.row + 1 : 0;
    }

    private int cellAliveAsInt(final int row, final int col) {
        return matrixOfCells[row][col].isAlive ? 1 : 0;
    }
}
