package it.mturatti.lifegame;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class GridTest {

    @Test(expected = IllegalArgumentException.class)
    public void gridTooSmall() {
        Grid.newRandomInstance(2, 2);
    }

    @Test
    public void testNextGeneration() {

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
            System.out.println("---------------------------------------\n");
            System.out.format("[previousAlive: %d] [Alive: %d] [stableGenerations: %d]\n",
                    previousAlive, totalAlive, stableGenerations);
            System.out.println("---------------------------------------\n");
        }
        Assert.assertTrue(stableGenerations >= 4);
    }
}
