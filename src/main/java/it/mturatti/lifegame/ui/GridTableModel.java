/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mturatti.lifegame.ui;

import it.mturatti.lifegame.Grid;
import java.awt.Color;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mturatti
 */
public class GridTableModel extends AbstractTableModel {

    private final Grid grid;
    private final int rowCount;
    private final int colCount;

    public GridTableModel(Grid grid) {
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
    public Object getValueAt(int row, int col) {
        return this.grid.isCellAlive(row, col) ? '█' : 0;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
