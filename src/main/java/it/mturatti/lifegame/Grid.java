/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mturatti.lifegame;

import java.util.Random;

/**
 *
 * @author mturatti
 */
public class Grid {

    public final int rows;
    public final int cols;
    private final Cell matrixOfCells[][];
    private final Random random = new Random();
    private int totalAlive = 0;

    private Grid(int rows, int cols) {
        if (rows < 3 || cols < 3) {
            throw new IllegalArgumentException("Smallest grid must be 3x3.");
        }
        this.rows = rows;
        this.cols = cols;
        this.matrixOfCells = new Cell[rows][cols];
    }

    public void putCell(Cell newCell) {
        final Cell presentCell = matrixOfCells[newCell.row][newCell.col];
        if (presentCell == null) {
            if (newCell.isAlive) {
                this.totalAlive++;
            }
        } else if (presentCell.isAlive && !newCell.isAlive) {
            this.totalAlive--;
        }
        matrixOfCells[newCell.row][newCell.col] = newCell;
    }

    public static Grid createRandomGrid(int rows, int cols) {
        final Grid grid = new Grid(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid.putCell(new Cell(i, j, grid.nextRandomBoolean()));
            }
        }
        return grid;
    }

    public void computeNextGeneration() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                final Cell cell = matrixOfCells[i][j];
                if (cell.isAlive) {
                    int aliveNeighbours = countAliveNeighbours(cell);
                    if (aliveNeighbours < 3 || aliveNeighbours > 4) {
                        this.putCell(new Cell(i, j, false));
                    }
                }
            }
        }
    }

    public int cellAliveAsInt(int row, int col) {
        return matrixOfCells[row][col].isAlive ? 1 : 0;
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

    private boolean nextRandomBoolean() {
        return this.random.nextBoolean();
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

    /**
     * @return the totalAlive
     */
    public int getTotalAlive() {
        return totalAlive;
    }
}
