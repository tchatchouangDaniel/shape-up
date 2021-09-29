package game.shape.up;

import card.and.deck.shapeup.Deck;
import gaem.area.shape.up.GameArea;
import view.shape.up.Game;

/**
 * Interface that defines the methods all the variants in our game
 * It is part of the strategy design pattern so this interface is common to all concrete variants
 * (see {@link VarianteBasic}, {@link VarianteAdvance})
 *
 * @author Daniel
 */
public interface Variante {
    Game game = Game.getUniqueGame();
    Deck deck = Deck.getUniqueDeck();
    GameArea gameArea = GameArea.getUniqueGameArea();

    /**
     * Initialize the player depending of the variant
     */
    public void initPlayer();

    /**
     * find the victory card depending of the variant
     */
    public void victoryCardFind();

}
