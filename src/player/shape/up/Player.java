package player.shape.up;


import card.and.deck.shapeup.*;
import gaem.area.shape.up.GameArea;
import model.shape.up.OptionData;
import observer.shape.up.Observable;
import view.shape.up.Game;
import java.util.ArrayList;


/**
 * The class from which extends both virtual and real players in the game
 *
 * @author Daniel
 */
public class Player extends Observable {
    protected ArrayList<Card> hand;
    protected String name;
    protected Card victoryCard;
    protected Deck deck;
    protected GameArea gameArea;
    protected Game game;

    //Variante de jeu simple: la carte victoire est distribué au joueur au moment de sa création

    // Variante de jeu avancée: la carte victoire sera attribué à la fin de la partie

    /**
     * Build a player object
     * @param name player name
     */
    public Player(String name){
        this.hand = new ArrayList<>();
        this.name = name;
        this.victoryCard = new Card(Color.NULL,Fill.NULL,Shape.NULL);
        this.deck = Deck.getUniqueDeck();
        this.gameArea = GameArea.getUniqueGameArea();
        this.game = Game.getUniqueGame();
    }

    /**
     * pick a card in the deck and add it to the hand
     */
    public void pickCard(){
        hand.add(deck.dealCard());
    }

    /**
     * Set the victory card of a player
     */
    public void setVictoryCard() {
        victoryCard = deck.dealCard();
    }

    /**
     * Show the victory card
     */
    public void showVictoryCard(){
        System.out.println(victoryCard);
    }

    /**
     * return the index of a particular card in hand
     * @param hand arraylist of card in hand
     * @param id of the card to search
     * @return index
     */
    protected int shCardIdxHand(ArrayList<Card> hand, String id){
        for (int i = 0; i < hand.size(); i++) {
            if(hand.get(i).getId().equals(id)){
                return i;
            }
        }

        return -1;
    }

    /**
     * Print the card in hand
     */
    public void printHand(){
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(hand.get(i) + "(" + hand.get(i).getId() +")");
        }
    }

    /**
     * get the hand of the play
     * @return list of card(s) in hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * get the name of a player
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get the victory card of a player
     * @return victoryCard
     */
    public Card getVictoryCard() {
        return victoryCard;
    }

    /**
     * get the deck
     * @return deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * get the gameArea
     * @return gameArea
     */
    public GameArea getGameArea() {
        return gameArea;
    }

    /**
     * set the hand of players
     * @param hand that is a list of card
     */
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    /**
     * set the name of a player
     * @param name of player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set the victory card
     * @param victoryCard of a player
     */
    public void setVictoryCard(Card victoryCard) {
        this.victoryCard = victoryCard;
    }

    /**
     * set the deck of the ongoing game
     * @param deck of the ongoing game
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    /**
     * set the game Area
     * @param gameArea of the ongoing game
     */
    public void setGameArea(GameArea gameArea) {
        this.gameArea = gameArea;
    }

    /**
     * Reset the victory card the player to a null victory card.
     */
    public void resetVictoryCard(){
        this.victoryCard = new Card(Color.NULL,Fill.NULL,Shape.NULL);
    }

    /**
     * Find a card in had by its id
     * @param id of the card wr seatch
     * @return card we searched for
     */
    public Card findCardById(String id){
        Card card = new Card(Color.NULL,Fill.NULL,Shape.NULL);
        for (Card value : hand) {
            if (value.getId().equals(id)) {
                card = value;
                break;
            }
        }
        return card;
    }

    /**
     * method to play a card.
     * this method is redefined in the classes RealPlayer and VirtualPlayer.
     * @param id id of the card to play
     * @param x position x
     * @param y position y
     * @return true or false
     */
    public boolean playCard(String id, int x, int y) {
        return false;
    }

    /**
     * method to play a card.
     * this method is redefined in the classes RealPlayer and VirtualPlayer.
     * @param posI position x
     * @param posJ position y
     * @return true or false
     */
    public boolean playCard(int posI, int posJ){// variante simple aucun choix
        return false;
    }

    /**
     * method to move a card.
     * this method is redefined in the classes RealPlayer and VirtualPlayer.
     * @param x position x
     * @param y position y
     * @return true or false
     */
    public boolean moveCard(String id, int x, int y){
        return false;
    }

    /**
     * return a String with player characteristics
     * @return a String with Player properties
     */
    public String toString(){
        if(OptionData.getVariant() == 2) return "Name : " + name;
        return "Name : " + name + "\nvictoryCard : " + victoryCard ;
    }

}
