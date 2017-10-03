/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 30, 2017
* Time: 12:35:48 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model
* File: TileTest
* Description:
*
* ****************************************
 */
package csci205_final_project.Model;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jj030
 */
public class TileTest {

    public TileTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isEqualTo method, of class Tile.
     */
    @Test
    public void testIsEqualTo() {
        System.out.println("isEqualTo");
        Object obj = null;
        Tile instance = new Tile(2, 2, "a");
        boolean expResult = false;
        boolean result = instance.isEqualTo(obj);
        assertEquals(expResult, result);

        obj = new Model(Level.EASY, "pokeman");
        result = instance.isEqualTo(obj);
        assertEquals(expResult, result);

        obj = new Tile(2, 3, "b");
        result = instance.isEqualTo(obj);
        assertEquals(expResult, result);

        obj = new Tile(2, 3, "a");
        result = instance.isEqualTo(obj);
        expResult = true;
        assertEquals(expResult, result);
    }

}
