/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 30, 2017
* Time: 12:07:44 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model
* File: LevelTest
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
public class LevelTest {

    public LevelTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of updateLevel method, of class Level.
     */
    @Test
    public void testUpdateLevel() {
        System.out.println("updateLevelEasy");
        Level currentlvl = Level.EASY;
        Level expResult = Level.MEDIUM;
        Level result = Level.updateLevel(currentlvl);
        assertEquals(expResult, result);

        System.out.println("updateLevelMedium");
        currentlvl = Level.MEDIUM;
        expResult = Level.HARD;
        result = Level.updateLevel(currentlvl);
        assertEquals(expResult, result);

        System.out.println("updateLevelHard");
        currentlvl = Level.HARD;
        expResult = Level.HARD;
        result = Level.updateLevel(currentlvl);
        assertEquals(expResult, result);

    }

}
