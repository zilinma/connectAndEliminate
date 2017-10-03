/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 10, 2017
* Time: 1:44:34 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model
* File: Level
* Description:
*
* ****************************************
 */
package csci205_final_project.Model;

import java.io.Serializable;

/**
 * level class for the level of game
 *
 * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
 */
public enum Level implements Serializable {

    /**
     * Easy level for the game which is a 6 by 6 square board
     */
    EASY(6, 6),
    /**
     * Medium level for the game which is a 8 by 8 square board
     */
    MEDIUM(8, 8),
    /**
     * Hard level for the game which is a 10 by 10 square board
     */
    HARD(10, 10);

    Level(int height, int width) {
        this.height = height;
        this.width = width;
    }
    private int height;
    private int width;

    /**
     * Get the width of the board
     *
     * @return this.width
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Get the height of the board
     *
     * @return this.height
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Level up
     *
     * @param currentlvl
     * @return
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    public static Level updateLevel(Level currentlvl) {
        if (currentlvl == Level.EASY) {
            currentlvl = Level.MEDIUM;
        }
        else if (currentlvl == Level.MEDIUM) {
            currentlvl = Level.HARD;
        }
        return currentlvl;
    }
}
