package calcul.score.shape.up;
import card.and.deck.shapeup.Card;
import card.and.deck.shapeup.Color;
import card.and.deck.shapeup.Fill;
import card.and.deck.shapeup.Shape;
import gaem.area.shape.up.GameArea;
import model.shape.up.RoundsManager;
import player.shape.up.Player;
import java.util.ArrayList;

/**
 * This class hold the methods that are used to calculate the score at the end of the game.
 * It is called by using the visitor design pattern.
 *
 * @author Daniel
 */
public class CalcScoreVisitorImpl implements CalcScoreVisitor{
    /**
     * is used to calculate the score by looking at cards disposition on the game area
     * @param gameArea the game field where the cgame is played
     * @return an array containing the scores of the player
     */
    @Override
    public int[] visit(GameArea gameArea) {

        ArrayList<Card> victoryCards = victoryCardsRetriever();
        int[] scores = new int[victoryCards.size()];
        for (int i = 0; i < victoryCards.size(); i++) {
            int scoreCumulation = 0;
            for (int j = 0; j < gameArea.getField().size(); j++) {
                int numberColor = numberOfConsecutiveColorLine(victoryCards.get(i).getColor(),j);
                int scoreColor = correspondingScoreColor(numberColor);
                int numberShape = numberOfConsecutiveShapeLine(victoryCards.get(i).getShape(),j);
                int scoreShape = correspondingScoreShape(numberShape);
                int numberFill = numberOfConsecutiveFillLine(victoryCards.get(i).getFill(),j);
                int scoreFill = correspondingScoreFill(numberFill);
                scoreCumulation += returnMaximumBetween(scoreFill,scoreColor,scoreShape);
            }

            for (int j = 0; j < gameArea.getField().get(0).size(); j++) {
                int numberColor = numberOfConsecutiveColorColumn(victoryCards.get(i).getColor(),j);
                int scoreColor = correspondingScoreColor(numberColor);
                int numberShape = numberOfConsecutiveShapeColumn(victoryCards.get(i).getShape(),j);
                int scoreShape = correspondingScoreShape(numberShape);
                int numberFill = numberOfConsecutiveFillColumn(victoryCards.get(i).getFill(),j);
                int scoreFill = correspondingScoreFill(numberFill);

                scoreCumulation += returnMaximumBetween(scoreColor,scoreFill,scoreShape);
            }
            scores[i] = scoreCumulation;
        }
        return scores;
    }

    /**
     * Store all the victory cards of the different players in an arrayList.
     * This arrayList is used to calculate the score
     * @return ArrayList of victory cards of all players in game
     */
    public ArrayList<Card> victoryCardsRetriever(){
        ArrayList<Player> players = RoundsManager.getUniqueRoundManager().getPlayers();
        ArrayList<Card> victoryCards = new ArrayList<>();
        for (Player player : players) {
            victoryCards.add(player.getVictoryCard());
        }
        return victoryCards;
    }

    /**
     * take the line and color as parameters and return
     * the maximum number of consecutive cases in our field line
     * that has a card containing the color we are searching for.
     * @param color the color we are looking for into the line
     * @param i the line index we're checking
     * @return number of cards that have the color we are searching for
     */
    public int numberOfConsecutiveColorLine(Color color, int i){ // i represents the line we're working on
        int counter = 0;
        int maxCounter = 0;
        ArrayList<ArrayList<Card>> field = GameArea.getUniqueGameArea().getField();
        for (int j = 0; j < field.get(0).size(); j++) {
            if(field.get(i).get(j).getColor() == color){
                counter++;
                if(j != (field.get(i).size()-1)){
                    int k=j+1;
                    do{
                        if(field.get(i).get(k).getColor() == color){
                            counter++;
                        }else{
                            break;
                        }
                        if(k!= (field.get(i).size() -1)){
                            k++;
                        }else{
                            break;
                        }
                    }while(field.get(i).get(k).getColor() == color);

                }
            }
            if(counter >= maxCounter){
                maxCounter = counter;
            }
            counter = 0;
        }

        return maxCounter;
    }

    //SHAPE

    /**
     * take the line and shape as parameters and return
     * the maximum number of consecutive cases in our field line
     * that has a card containing the shape we are searching for.
     * @param shape The shape we are looking for in the line
     * @param i the line index we're checking
     * @return number of cards that have the shape we are searching for
     */
     public int numberOfConsecutiveShapeLine(Shape shape, int i){ // i represents the line we're working on
        int counter = 0;
        int maxCounter = 0;
        ArrayList<ArrayList<Card>> field = GameArea.getUniqueGameArea().getField();
        for (int j = 0; j < field.get(0).size(); j++) {
            if(field.get(i).get(j).getShape() == shape){
                counter++;
                if(j != (field.get(i).size()-1)){
                    int k=j+1;
                    do{
                        if(field.get(i).get(k).getShape() == shape){
                            counter++;
                        }else{
                            break;
                        }
                        if(k!= (field.get(i).size() -1)){
                            k++;
                        }else{
                            break;
                        }
                    }while(field.get(i).get(k).getShape() == shape);

                }
            }
            if(counter >= maxCounter){
                maxCounter = counter;
            }
            counter = 0;
        }

        return maxCounter;
    }
    /**
     * take the line and fill as parameters and return
     * the maximum number of consecutive cases in our field line
     * that has a card containing the fill we are searching for.
     * @param fill The fill we are looking for in the line
     * @param i the line index we're checking
     * @return number of cards that have the shape we are searching for
     */
    public int numberOfConsecutiveFillLine(Fill fill, int i){ // i represents the line we're working on
        int counter = 0;
        int maxCounter = 0;
        ArrayList<ArrayList<Card>> field = GameArea.getUniqueGameArea().getField();
        for (int j = 0; j < field.get(0).size(); j++) {
            if(field.get(i).get(j).getFill() == fill){
                counter++;
                if(j != (field.get(i).size()-1)){
                    int k=j+1;
                    do{
                        if(field.get(i).get(k).getFill() == fill){
                            counter++;
                        }else{
                            break;
                        }
                        if(k!= (field.get(i).size() -1)){
                            k++;
                        }else{
                            break;
                        }
                    }while(field.get(i).get(k).getFill() == fill);

                }
            }
            if(counter >= maxCounter){
                maxCounter = counter;
            }
            counter = 0;
        }

        return maxCounter;
    }
    /**
     * take the column and color as parameters and return
     * the maximum number of consecutive cases in our field column
     * that has a card containing the color we are searching for.
     * @param color the color we are looking for into the column
     * @param i the line index we're checking
     * @return number of cards that have the color we are searching for
     */
    public int numberOfConsecutiveColorColumn(Color color, int i){ // i represents the column we're working on
        int counter = 0;
        int maxCounter = 0;
        ArrayList<ArrayList<Card>> field = GameArea.getUniqueGameArea().getField();
        for (int j = 0; j < field.size(); j++) {
            if(field.get(j).get(i).getColor() == color){
                counter++;
                if(j != (field.size() - 1)){
                    int k=j+1;
                    do{
                        if(field.get(k).get(i).getColor() == color){
                            counter++;
                        }else{
                            break;
                        }
                        if(k!=(field.size() -1)){
                            k++;
                        }else{
                            break;
                        }

                    }while(field.get(k).get(i).getColor() == color);

                }
            }
            if(counter >= maxCounter){
                maxCounter = counter;
            }
            counter = 0;
        }

        return maxCounter;
    }
    /**
     * take the column and shape as parameters and return
     * the maximum number of consecutive cases in our field column
     * that has a card containing the shape we are searching for.
     * @param shape The shape we are looking for in the column
     * @param i the line index we're checking
     * @return number of cards that have the shape we are searching for
     */
    public int numberOfConsecutiveShapeColumn(Shape shape, int i){
        int counter = 0;
        int maxCounter = 0;
        ArrayList<ArrayList<Card>> field = GameArea.getUniqueGameArea().getField();
        for (int j = 0; j < field.size(); j++) {
            if(field.get(j).get(i).getShape() == shape){
                counter++;
                if(j != (field.size() - 1)){
                    int k=j+1;
                    do{
                        if(field.get(k).get(i).getShape() == shape){
                            counter++;
                        }else{
                            break;
                        }
                        if(k!=(field.size() -1)){
                            k++;
                        }else{
                            break;
                        }

                    }while(field.get(k).get(i).getShape() == shape);

                }
            }
            if(counter >= maxCounter){
                maxCounter = counter;
            }
            counter = 0;
        }

        return maxCounter;
    }
    /**
     * take the column and fill as parameters and return
     * the maximum number of consecutive cases in our field column
     * that has a card containing the fill we are searching for.
     * @param fill The fill we are looking for in the column
     * @param i the line index we're checking
     * @return number of cards that have the shape we are searching for
     */

    public int numberOfConsecutiveFillColumn(Fill fill, int i){
        int counter = 0;
        int maxCounter = 0;
        ArrayList<ArrayList<Card>> field = GameArea.getUniqueGameArea().getField();
        for (int j = 0; j < field.size(); j++) {
            if(field.get(j).get(i).getFill() == fill){
                counter++;
                if(j != (field.size() - 1)){
                    int k=j+1;
                    do{
                        if(field.get(k).get(i).getFill() == fill){
                            counter++;
                        }else{
                            break;
                        }
                        if(k!=(field.size() -1)){
                            k++;
                        }else{
                            break;
                        }

                    }while(field.get(k).get(i).getFill() == fill);

                }
            }
            if(counter >= maxCounter){
                maxCounter = counter;
            }
            counter = 0;
        }

        return maxCounter;
    }

    /**
     * Simple comparison
     * take three numbers and return the greatest
     * @param a first score
     * @param b second score
     * @param c third score
     * @return the biggest value between the three
     */
    public int returnMaximumBetween(int a, int b, int c){
        int max;
        if(a >= b && a >= c){
            max = a;
        }else if(b >= a && b >= c){
            max = b;
        }else{
            max = c;
        }
        return max;
    }
    /**
     * return the score depending on the length of the consecutive shape card
     * @param length number of successive card with the shape
     * @return the number of successive card with corresponding shape
     */
    public int correspondingScoreShape(int length){
        if(length == 2){
            return 1;
        }else if(length == 3){
            return 2;
        }else if(length == 4){
            return 3;
        }else if(length == 5){
            return 4;
        }else{
            return 0;
        }
    }
    /**
     * return the score depending on the length of the consecutive Fill card
     * @param length number of successive card with the fill
     * @return the number of successive card with corresponding fill
     */
    public int correspondingScoreFill(int length){
        if(length == 3){
            return 3;
        }else if(length == 4){
            return 4;
        }else if(length == 5){
            return 5;
        }else {
            return 0;
        }
    }
    /**
     * return the score depending on the length of the consecutive Color card
     * @param length number of successive card with the Color
     * @return the number of successive card with corresponding Color
     */
    public int correspondingScoreColor(int length){
        if(length == 3){
            return 4;
        }else if(length == 4){
            return 5;
        }else if(length == 5){
            return 6;
        }else{
            return 0;
        }
    }
}
