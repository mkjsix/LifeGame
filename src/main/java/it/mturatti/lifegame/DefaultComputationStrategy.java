/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mturatti.lifegame;

/**
 *
 * @author mturatti
 */
public class DefaultComputationStrategy implements ComputationStrategy {

    @Override
    public void execute(final Grid grid, final Cell cell, int aliveNeighbours, int i, int j) {
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
