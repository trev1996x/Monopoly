/**
 * ULID: tmqualt, (Enter ULID), (Enter ULID)
 * Date Created: 12 October 2022
 */
package edu.ilstu;
/**
 * @author Ty Qualters
 * @author Trevor Leamon
 * @author Joel Fangu
 * 
 * A simple implementation of a Point class
 */

public class Point {
    /**
     * Default Point constructor
     * @param coord_x x Position
     * @param coord_y y Position
     */
    Point(int coord_x, int coord_y) {
        setPoint(coord_x, coord_y);
    }

    /**
     * Set the Point values
     * @param x x Position
     * @param y y Position
     */
    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the Point value of x
     * @return x Value
     */
    public int getX() {
        return this.x;
    }

    /**
     * Get the Point value of y
     * @return y Value
     */
    public int getY() {
        return this.y;
    }

    /**
     * Get the Point values of x and y
     * @return [x, y]
     */
    public int[] get() {
        return new int[]{this.x, this.y};
    }

    private int x = 0;
    private int y = 0;
}
