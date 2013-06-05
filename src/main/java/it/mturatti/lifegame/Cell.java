/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mturatti.lifegame;

/**
 * @author  mturatti
 */
public final class Cell {

    public final int row;
    public final int col;
    public final boolean isAlive;
    public static final boolean DEAD = false;
    public static final boolean ALIVE = true;

    private Cell(final int row, final int col, final boolean alive) {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException("Cell coordinates can't be negative.");
        }

        this.row = row;
        this.col = col;
        this.isAlive = alive;
    }

    public static Cell newDeadInstance(final int row, final int col) {
        return new Cell(row, col, DEAD);
    }

    public static Cell newAliveInstance(final int row, final int col) {
        return new Cell(row, col, ALIVE);
    }

}
