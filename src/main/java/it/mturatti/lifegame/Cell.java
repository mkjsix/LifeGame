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
    public final boolean alive;
    public static final boolean DEAD = false;
    public static final boolean ALIVE = true;

    public Cell(int row, int col, boolean alive) {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException("Cell coordinates can't be negative.");
        }
        this.row = row;
        this.col = col;
        this.alive = alive;
    }

    public boolean isAlive() {
        return this.alive;
    }
}
