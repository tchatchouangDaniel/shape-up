package gaem.area.shape.up;

import game.shape.up.Positioning;

/**
 * Interface which declares a method for the different carpet shape strategy we will be using during the game.
 * It is part of the strategy design pattern.
 * (See {@link RectangleBoundary}, {@link SquareBoundary}).
 *
 * @author Daniel
 */
public interface CarpetShapeStrategy {
    /**
     * The method to check the boundaries depending of the carpet shape the user choose
     * @param position position to check
     * @return true or false
     */
    public boolean checkForBoundary(Positioning position);
}
