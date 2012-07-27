package it.mturatti.lifegame;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class GridTest {

//    @Test
//    public void testPrint() {
//        Grid myGrid = Grid.createRandomGrid(6, 10);
//        System.out.print(myGrid.toString());
//        assertTrue(true);
//    }
    @Test(expected = IllegalArgumentException.class)
    public void gridTooSmall() {
        Grid.createRandomGrid(2, 2);
    }

    @Test
    public void testNextGeneration() {

        Grid myGrid = Grid.createRandomGrid(16, 20);
        GridStringRender render = new GridStringRender(myGrid);
        int previousAlive = 0;

        while (myGrid.getTotalAlive() >= 0) {
            System.out.println("---------------------------------------\n");
            System.out.println(render.asString());

            previousAlive = myGrid.getTotalAlive();
            myGrid.computeNextGeneration();
            if (previousAlive - myGrid.getTotalAlive() == 0) {
                System.out.println("---------------- END --------------------\n");
                System.out.println(render.asString());
                break;
            }
        }
    }
}
