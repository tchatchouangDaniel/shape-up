package gaem.area.shape.up;

import game.shape.up.Positioning;

/**
 * That class implement the methods so that we have a rectangle carpet shape
 *
 * @author Daniel
 */
public class RectangleBoundary implements CarpetShapeStrategy{
    /**
     * The boundaries are check to make sure that we don't get out of the 3x5 or 5x3 grid of card that define a square like carpet shape
     * @param position where the card is being played
     * @return true or false depending if the boundaries are respected or not
     */
    @Override
    public boolean checkForBoundary(Positioning position) {
            GameArea gameArea = GameArea.getUniqueGameArea();
            boolean conditionLigne = false;
            boolean conditionColonne = false;
            int firstColumnCard = 0;
            int lastColumnCard = 0;
            int firstLineCard = 0;
            int lastLineCard = 0;
            gameArea.updateBoundaries();

            if (gameArea.getRelativeHeigth() >= 4 && gameArea.getRelativeLength() <= 3) {
                gameArea.setMaxHeight(5);
                gameArea.setMaxLength(3);
            } else if (gameArea.getRelativeLength() >= 4 && gameArea.getRelativeHeigth() <= 3) {
                gameArea.setMaxHeight(3);
                gameArea.setMaxLength(5);
            } else {
                gameArea.setMaxHeight(5);
                gameArea.setMaxLength(5);
            }

            for (int i = 0; i < gameArea.getField().get(0).size(); i++) {
                if (gameArea.checkColumn(i)) {
                    firstColumnCard = i;
                    break;
                }
            }
            for (int i = (gameArea.getField().get(0).size() - 1); i >= 0; i--) {
                if (gameArea.checkColumn(i)) {
                    lastColumnCard = i;
                    break;
                }
            }

            if (position.getY() >= firstColumnCard) {
                if ((position.getY() - firstColumnCard + 1) <= gameArea.getMaxLength()) {
                    conditionColonne = true;
                }
            } else if (position.getY() <= firstColumnCard) {
                if ((Math.abs(position.getY() - lastColumnCard) + 1) <= gameArea.getMaxLength()) {
                    conditionColonne = true;
                }
            }

            for (int i = 0; i < gameArea.getField().size(); i++) {
                if (gameArea.checkLine(i)) {
                    firstLineCard = i;
                    break;
                }
            }
            for (int i = (gameArea.getField().size() - 1); i >= 0; i--) {
                if (gameArea.checkLine(i)) {
                    lastLineCard = i;
                    break;
                }
            }
            if (position.getX() >= firstLineCard) {
                if ((position.getX() - firstLineCard + 1) <= gameArea.getMaxHeight()) {
                    conditionLigne = true;
                }
            } else if (position.getX() <= firstLineCard) {
                if ((Math.abs(position.getX() - lastLineCard) + 1) <= gameArea.getMaxHeight()) {
                    conditionLigne = true;
                }
            }

            return conditionLigne && conditionColonne;
        }


}
