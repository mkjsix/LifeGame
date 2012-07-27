/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mturatti.lifegame;

/**
 *
 * @author mturatti
 */
public class Cell {

    public final int row;
    public final int col;
    public final boolean isAlive;

    public Cell(int row, int col, boolean isAlive) {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException("Cell coordinates can't be negative.");
        }
        this.row = row;
        this.col = col;
        this.isAlive = isAlive;
    }
    
    
}
