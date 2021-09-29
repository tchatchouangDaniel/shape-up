package player.shape.up;
import card.and.deck.shapeup.Card;

/**
 * Interface which declarer both methods a Virtual player will be using during the game.
 * It's part of the strategy design pattern, then this interface is common to all virtual player concrete strategies
 * (see {@link RandomStrategy})
 *
 * @author Daniel
 */
public interface VPlayerStrategy {
    /**
     * Declaration of the method which allows a virtual player to play a card
     * @param c the card to play
     * @return true or false if the card is played or not
     */
    public boolean playCard(Card c);

    /**
     * Declaration of the method which allowe a virtual player to move a card
     * @return true or false if the card is moved
     */
    public boolean moveCard();
}

