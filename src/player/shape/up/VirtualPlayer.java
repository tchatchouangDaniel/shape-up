package player.shape.up;

import card.and.deck.shapeup.Card;
import gaem.area.shape.up.CarpetShape;

/**
 * define the properties and the methods of a virtual player.
 * It extends from Player class
 * (see {@link Player})
 *
 * @author Daniel
 */
public class VirtualPlayer extends Player{
    private VPlayerStrategy strategy;
    private Level level;

    /**
     *The constructor for the virtual player. The default level is EASY
     *
     * @param name of the real player
     */
    public VirtualPlayer(String name) {
        super(name);
        level = Level.EASY;
        setStrategy();
    }

    /**
     * set the strategy of the virtual player
     */
    public void setStrategy() {
        if(level.equals(Level.EASY)){
            strategy = new RandomStrategy();
        }
    }

    /**
     * set the level of the virtual player
     * @param level of the virtual player
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * return the strategy of the virtual player
     * @return strategy
     */
    public VPlayerStrategy getStrategy() {
        return strategy;
    }

    /**
     * return the level of the virtual player
     * @return level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * is used to pick a card in the hand of a player
     * @return card
     */
    public Card pickCardInHand(){
            int randomCard = (int) (Math.random() * hand.size());
            return hand.get(randomCard);
    }

    /**
     * the method used to play a card in the basic variant
     */
    public void playBasic(){ // variante basique
        //int RandomAction = (int) (Math.random()*3);
        if(!deck.isEmpty()){
            if (gameArea.isEmpty() || gameArea.getCardNumberOn() == 1) {
                pickCard();
                strategy.playCard(hand.get(0));
                hand.remove(0);
            }else{
                pickCard();
                strategy.playCard(hand.get(0));
                hand.remove(0);
                if(!deck.isEmpty() || (deck.isEmpty() && game.getPlayerNumber() > 2) || (deck.isEmpty() && game.getCarpetShape().equals(CarpetShape.SQUARE)))
                   strategy.moveCard();
            }
        }
    }

    /**
     * The method used to play a card in advence variant
     */
    public void playAdvance(){ // variante avancée
        if(!deck.isEmpty() || hand.size() > 1){
            if (gameArea.isEmpty() || gameArea.getCardNumberOn() == 1) {
                Card cardToPlay = pickCardInHand();
                strategy.playCard(cardToPlay);

                hand.remove(cardToPlay);
                if(!deck.isEmpty())
                    pickCard();
            }else{

                if(hand.size() > 1) {
                    Card cardToPlay = pickCardInHand();
                    strategy.playCard(cardToPlay);
                    hand.remove(cardToPlay);
                    if(!deck.isEmpty())
                        pickCard();
                    if(!deck.isEmpty() || (deck.isEmpty() && game.getPlayerNumber() > 2) || (deck.isEmpty() && game.getCarpetShape().equals(CarpetShape.SQUARE)))
                        strategy.moveCard();
                }


            }
        }
    }
}
