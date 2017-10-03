/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 13, 2017
* Time: 8:01:50 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model
* File: ModelTest
* Description:
*
* ****************************************
 */
package csci205_final_project.Model;

import static csci205_final_project.Model.Level.EASY;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jj030
 */
public class ModelTest {

    public ModelTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of cancelTile method, of class Model.
     */
    @Test
    public void testCancelTile() {
        Model md = new Model(EASY, "pokeman");
        ArrayList<ArrayList<Tile>> data = md.getData();
        System.out.println("cancelTileDiffName");
        data.get(2).get(5).setImgName("a");
        data.get(3).get(5).setImgName("b");
        md.setData(data);
        ArrayList<ArrayList<Integer>> result = new ArrayList();
        result = md.findPath(data.get(2).get(5),
                             data.get(3).get(5));
        assertEquals(result, null);

        System.out.println("cancelTileNextToEachOther");
        data.get(3).get(5).setImgName("a");
        md.setData(data);
        result = md.findPath(data.get(2).get(5),
                             data.get(3).get(5));
        ArrayList<ArrayList<Integer>> expect = new ArrayList();
        ArrayList<Integer> pos1 = new ArrayList();
        pos1.add(5);
        pos1.add(2);
        ArrayList<Integer> pos2 = new ArrayList();
        pos2.add(5);
        pos2.add(3);
        expect.add(pos1);
        expect.add(pos2);
        assertEquals(result, expect);
        expect.clear();
        result.clear();
        pos1.clear();
        pos2.clear();

        System.out.println("cancelTileOneApart");
        // data.get(2).set(5, new Tile(5, 2, "a"));
        //data.get(4).get(5).setImgName("a");
        // data.get(3).set(5, new Tile(5, 3, "b"));
        data.get(2).get(5).setImgName("b");
        data.get(3).set(5, null);
        data.get(4).get(5).setImgName("b");
        md.setData(data);
        result = md.findPath(data.get(2).get(5),
                             data.get(4).get(5));
        pos1.add(5);
        pos1.add(2);
        pos2.add(5);
        pos2.add(4);
        expect.add(pos1);
        expect.add(pos2);
        assertEquals(result, expect);
        expect.clear();
        result.clear();
        pos1.clear();
        pos2.clear();

        System.out.println("cancelTileOneTurns");
        data.get(3).set(5, new Tile(5, 3, "b"));
        data.get(4).set(5, null);
        data.get(4).set(4, new Tile(4, 4, "b"));
        md.setData(data);
        result = md.findPath(data.get(3).get(5),
                             data.get(4).get(4));
        ArrayList<Integer> pos3 = new ArrayList();
        pos1.add(5);
        pos1.add(3);
        pos2.add(5);
        pos2.add(4);
        pos3.add(4);
        pos3.add(4);
        expect.add(pos1);
        expect.add(pos2);
        expect.add(pos3);
        assertEquals(result, expect);
        expect.clear();
        result.clear();
        pos1.clear();
        pos2.clear();
        pos3.clear();

        System.out.println("cancelTileTwoTurns");
        data.get(3).set(5, new Tile(5, 3, "b"));
        data.get(2).set(5, new Tile(5, 2, "a"));
        data.get(4).set(5, new Tile(5, 4, "a"));
        data.get(2).set(6, null);
        data.get(3).set(6, null);
        data.get(4).set(6, null);
        md.setData(data);
        result = md.findPath(data.get(2).get(5),
                             data.get(4).get(5));
        ArrayList<Integer> pos4 = new ArrayList();
        pos1.add(5);
        pos1.add(2);
        pos2.add(6);
        pos2.add(2);
        pos3.add(6);
        pos3.add(4);
        pos4.add(5);
        pos4.add(4);
        expect.add(pos1);
        expect.add(pos2);
        expect.add(pos3);
        expect.add(pos4);
        assertEquals(result, expect);
        expect.clear();
        result.clear();
        pos1.clear();
        pos2.clear();
        pos3.clear();
        pos4.clear();

        System.out.println("cancelTileTwoTurnsZ");
        md = new Model(EASY, "pokeman");
        data = md.getData();
        data.get(2).set(5, null);
        data.get(3).set(5, null);
        data.get(4).set(5, null);
        data.get(2).get(4).setImgName("b");
        data.get(4).get(6).setImgName("b");
        md.setData(data);
        result = md.findPath(data.get(2).get(4),
                             data.get(4).get(6));
        pos1.add(4);
        pos1.add(2);
        pos2.add(5);
        pos2.add(2);
        pos3.add(5);
        pos3.add(4);
        pos4.add(6);
        pos4.add(4);
        expect.add(pos1);
        expect.add(pos2);
        expect.add(pos3);
        expect.add(pos4);
        assertEquals(result, expect);

        System.out.println("cancelTileThreeTurns");
        data.get(1).get(4).setImgName("b");
        data.get(5).get(6).setImgName("b");
        md.setData(data);
        result = md.findPath(data.get(1).get(4),
                             data.get(5).get(6));
        assertEquals(result, null);
    }

    /**
     * Test of removeTile method, of class Model.
     */
    @Test
    public void testRemoveTile() {
        System.out.println("removeTile");
        Model md = new Model(EASY, "pokeman");
        ArrayList<ArrayList<Tile>> data = md.getData();
        data.get(1).get(4).setImgName("a");
        data.get(1).get(5).setImgName("a");
        md.removeTile(data.get(1).get(4), data.get(1).get(5));
        assertEquals(data.get(1).get(4), null);
        assertEquals(data.get(1).get(5), null);
    }

}
