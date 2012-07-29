package it.mturatti.lifegame;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class GridTest {

    @Test(expected = IllegalArgumentException.class)
    public void testGridTooSmall() {
        System.out.println("testGridTooSmall");
        
        Grid.newRandomInstance(2, 2);
    }
    
    @Test
    public void testCreateEmptyGrid() {
        System.out.println("testCreateEmptyGrid");
        
        Grid grid = Grid.newEmptyInstance(3, 3);
        Assert.assertEquals("Grid must be empty!", 0, grid.countTotalAlive());
    }
    
    @Test
    public void testIdempotentOperations() {
        System.out.println("testIdempotentOperations");
        
        Grid grid = Grid.newEmptyInstance(3, 3);
        
        grid.putCell(Cell.newAliveInstance(1, 1));
        Assert.assertEquals("Grid alives must be 1", 1, grid.countTotalAlive());
        
        grid.putCell(Cell.newAliveInstance(1, 1));
        Assert.assertEquals("Grid alives still must be 1", 1, grid.countTotalAlive());
        
        grid.putCell(Cell.newDeadInstance(1, 1));
        Assert.assertEquals("Grid must be empty!", 0, grid.countTotalAlive());
        
        grid.putCell(Cell.newDeadInstance(0, 0));
        Assert.assertEquals("Grid must be empty!", 0, grid.countTotalAlive()); 
    }

    //@Test
    public void testNextGeneration() {
        System.out.println("testNextGeneration");
        
        Grid myGrid = Grid.newRandomInstance(16, 20);
        GridStringRender render = new GridStringRender(myGrid);
        int previousAlive = 0;
        int stableGenerations = 0;

        while (stableGenerations < 4) {
            
            System.out.println(render.asString());

            previousAlive = myGrid.countTotalAlive();
            myGrid.computeNextGeneration();
            int totalAlive = myGrid.countTotalAlive();
            if (previousAlive - totalAlive == 0) {
                ++stableGenerations;
            }
            System.out.println("---------------------------------------");
            System.out.format("[stableGenerations: %d]\n", stableGenerations);
            System.out.println("---------------------------------------");
        }
        Assert.assertTrue(stableGenerations >= 4);
    }
}
