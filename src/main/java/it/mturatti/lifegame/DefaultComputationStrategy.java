/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mturatti.lifegame;

/**
 * @author  mturatti
 */
public class DefaultComputationStrategy implements ComputationStrategy {

    @Override
    public void execute(final Grid grid, final Cell cell, final int aliveNeighbours, final int i, final int j) {
        if (cell.isAlive) {
            if (aliveNeighbours < 2 || aliveNeighbours > 3) {
                grid.putCell(Cell.newDeadInstance(i, j));
            }
        } else {
            if (aliveNeighbours == 3) {
                grid.putCell(Cell.newAliveInstance(i, j));
            }
        }
    }
}
