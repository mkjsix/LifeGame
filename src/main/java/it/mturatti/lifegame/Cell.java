/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mturatti.lifegame;

/**
 *
 * @author mturatti
 */
final public class Cell {

    public final int row;
    public final int col;
    public final boolean isAlive;
    public static final boolean DEAD = false;
    public static final boolean ALIVE = true;

    private Cell(int row, int col, boolean alive) {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException("Cell coordinates can't be negative.");
        }
        this.row = row;
        this.col = col;
        this.isAlive = alive;
    }
    
    public static Cell newDeadInstance(int row, int col) {
        return new Cell(row, col, DEAD);
    }
    
    public static Cell newAliveInstance(int row, int col) {
        return new Cell(row, col, ALIVE);
    }

}
