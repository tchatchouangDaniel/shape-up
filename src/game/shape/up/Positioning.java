package game.shape.up;

/**
 * The class for a position on the board following x and y axis
 *
 * @author Daniel
 */

public class Positioning {

    private int x;
    private int y;

    /**
     * Constructor a coordinate / position
     * @param x x coordinate
     * @param y y coordinate
     */
    public Positioning(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * return x value
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * set x value
     * @param x is the new value of x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * return y value
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * set y value
     * @param y is the new value for y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * provide a String with the x and y values of the position
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * redefinition of equal method to compare two objects of the positioning class
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Positioning that = (Positioning) o;
        return x == that.x &&
                y == that.y;
    }
}
