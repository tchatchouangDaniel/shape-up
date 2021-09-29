package player.shape.up;
import card.and.deck.shapeup.Card;
import gaem.area.shape.up.GameArea;
import game.shape.up.Positioning;
import observer.shape.up.Observable;
import view.shape.up.BoardView;

import java.util.ArrayList;

/**
 * The class implements methods so that a virtual player can play randomLy.
 * implements virtualPlayerStrategy interface
 * (see {@link VPlayerStrategy})
 *
 * @author Daniel
 */
public class RandomStrategy extends Observable implements VPlayerStrategy {
    /**
     * The constructor of the random strategy
     */
    public RandomStrategy(){
        this.addObserver(BoardView.getUniqueInstance());
    }

    /**
     * choose a place on the gameArea for the virtual player to play. Only valid positons are returned
     * @return Positioning place
     */
    private Positioning chooseWhereToPlay(){
        GameArea gameArea = GameArea.getUniqueGameArea();
        if(gameArea.getCardNumberOn() != 0){
            for (int i = 0; i < gameArea.getField().size(); i++) {
                for (int j = 0; j < gameArea.getField().get(i).size(); j++) {
                    Positioning place = new Positioning(i,j);
                    if(gameArea.isPositionValid(place) && gameArea.isBoundaryRespected(place)){
                        return place;
                    }
                }
            }
        }
        return new Positioning(0,0);
    }

    //Renvoi les Id des differentes cartes présentent sur le terrain

    /**
     * a method to get an arrayList of the cards on the field
     * @return ArrayList of the ids of cards on field
     */
    private ArrayList<String> getCardOnField(){
        ArrayList<String> cardIDs = new ArrayList<>();
        ArrayList<ArrayList<Card>> field = GameArea.getUniqueGameArea().getField();
        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.get(i).size(); j++) {
                if(!field.get(i).get(j).getId().equals("---"))
                    cardIDs.add(field.get(i).get(j).getId());
            }
        }

        return cardIDs;

    }

    /**
     * a method that help the virtual player to choose what card to move on field
     * @return the id of the card to move
     */
    private String ChooseWhatToMove(){
        ArrayList<String> idsOfCardOnField = getCardOnField();
        int randomCardId = (int) (Math.random()*idsOfCardOnField.size());
        return idsOfCardOnField.get(randomCardId);
    }

    /**
     * a method that help the virtual player to choose where to move or play a card
     * @return a position where the virtual player will play or move a card
     */
    private Positioning chooseWhereToMove(){
        return chooseWhereToPlay();
    }

    /**
     * the methods that is used for a virtual player to play a card
     * @param c the card to play
     * @return true or false if the card is played or not
     */
    @Override
    public boolean playCard(Card c) {
        Positioning place = chooseWhereToPlay();
        setChanged();
        notifyObservers(place);
        GameArea.getUniqueGameArea().addCard(c, place.getX(), place.getY());
        setChanged();
        notifyObservers(c);
        return true;
    }

    /**
     * The method that is used by the vitual player to move a card
     * @return true or false if the card is moved or not
     */
    @Override
    public boolean moveCard() {
        Positioning place = chooseWhereToMove();
        setChanged();
        notifyObservers(place);
        String cardId = ChooseWhatToMove();
        GameArea.getUniqueGameArea().moveCard(cardId, place.getX(), place.getY());
        setChanged();
        notifyObservers(cardId);
        return false;
    }



}
