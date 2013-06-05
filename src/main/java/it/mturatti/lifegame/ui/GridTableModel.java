/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mturatti.lifegame.ui;

import javax.swing.table.AbstractTableModel;

import it.mturatti.lifegame.Grid;

/**
 * @author  mturatti
 */
public class GridTableModel extends AbstractTableModel {

    private final Grid grid;
    private final int rowCount;
    private final int colCount;

    public GridTableModel(final Grid grid) {
        this.grid = grid;
        this.rowCount = grid.rows;
        this.colCount = grid.cols;
    }

    @Override
    public int getColumnCount() {
        return this.colCount;
    }

    @Override
    public int getRowCount() {
        return this.rowCount;
    }

    @Override
    public Object getValueAt(final int row, final int col) {
        return this.grid.isCellAlive(row, col) ? '█' : 0;
    }

    @Override
    public boolean isCellEditable(final int row, final int col) {
        return false;
    }
}
