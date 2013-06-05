/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mturatti.lifegame;

/**
 * @author  mturatti
 */
public interface ComputationStrategy {

    void execute(final Grid grid, final Cell cell, int aliveNeighbours, int i, int j);

}
