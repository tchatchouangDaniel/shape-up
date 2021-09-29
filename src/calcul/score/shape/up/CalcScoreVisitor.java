package calcul.score.shape.up;

import gaem.area.shape.up.*;

/**
 * Interface which declares the method to visit the gameArea at the end of the game to perform score calculation.
 * It is part of the visitor design pattern. And brings to the GameArea class a virtual function to calculate the score.
 * (See {@link calcul.score.shape.up.CalcScoreVisitorImpl} for the methods to calculate score)
 *
 * @author Daniel
 */
public interface CalcScoreVisitor {
    /**
     * The method that take in parameter the GameArea so that the visitor can visit it
     * @param gameArea the gameArea where the score is will be calculated.
     * @return an array containing the score of each player sort by creation order.
     */
    int[] visit(GameArea gameArea);
}
