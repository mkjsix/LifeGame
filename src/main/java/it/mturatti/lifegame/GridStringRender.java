/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mturatti.lifegame;

/**
 *
 * @author mturatti
 */
public class GridStringRender {
    
    private final Grid grid;
    
    GridStringRender(Grid grid) {
        this.grid = grid;
    }
    
    public String asString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < grid.rows; i++) {
            builder.append("|");
            for (int j = 0; j < grid.cols; j++) {
                if (grid.cellAliveAsInt(i, j) == 1) {
                    builder.append("X|");
                } else {
                    builder.append("_|");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
    
}
