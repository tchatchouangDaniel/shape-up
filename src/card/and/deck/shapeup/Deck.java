package card.and.deck.shapeup;

import java.util.ArrayList;
import java.util.Collections;

/**
 * the class where the deck containing the <code>Card</code> for one game is created.
 * It contains all the method needed to interact with the deck.
 *
 * @author Daniel
 */
public class Deck {
    private ArrayList<Card> deck;
    private Deck(){
        deck =  new ArrayList<>();
    }

    /**
     * this class is a helper for the singleton
     */
    private static class SingletonHelper{

        private static final Deck uniqueDeck = new Deck();
    }
    /**
     * Return the unique instance of the deck
     * @return deck
     */
    public static Deck getUniqueDeck(){
        return SingletonHelper.uniqueDeck;
    }
    /**
     * return the deck of cards
     * @return deck
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }
    /**
     * is used to pick the topCard of the deck
     * @return card
     */
    public Card dealCard() {
        if (deck.isEmpty())
            return null;
        else {
            Card ref = deck.get(0);
            deck.remove(0);
            return ref;
        }
    }



    /**
     * Return the victory card
     * @return card
     */
    public Card dealVictoryCard() {
        Card cardToReturn = getDeckTopCard();
        removeCard();
        return cardToReturn;
    }

    /**
     * shuffle the card in deck
     */
    public void shuffle() {
        Collections.shuffle( deck );
    }


    /**
     * Looks for an empty deck.
     * @return <code>true</code> if there are no cards left to be dealt from the deck.
     */
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    /**
     * initialise the deck of card with all the card then remove the useless card an d shuffle it
     */
    public void initDeck(){
        deck = new ArrayList<Card>();
        for(Color c: Color.values()){
            if(!c.equals(Color.NULL))
                for (Fill f: Fill.values()){
                    if(!f.equals(Fill.NULL))
                        for (Shape s: Shape.values()){
                            if(!s.equals(Shape.NULL))
                                deck.add(new Card(c,f,s));
                        }
                }
        }
        setPath();
        shuffle();
        removeCard(); // retire la carte inutile
    }

    /**
     * The path to card images
     */
    public void setPath(){
        for (Card card :  deck){
            switch (card.id){
                case "bhc" :
                    card.imagePath = "pictures/CardsPng/BLUE_HOLLOW_CIRCLE.jpg";
                    break;
                case "bhs" :
                    card.imagePath = "pictures/CardsPng/BLUE_HOLLOW_SQUARE.jpg";
                    break;
                case "bht" :
                    card.imagePath = "pictures/CardsPng/BLUE_HOLLOW_TRIANGLE.jpg";
                    break;
                case "bsc" :
                    card.imagePath = "pictures/CardsPng/BLUE_SOLID_CIRCLE.jpg";
                    break;
                case "bss" :
                    card.imagePath = "pictures/CardsPng/BLUE_SOLID_SQUARE.jpg";
                    break;
                case "bst" :
                    card.imagePath = "pictures/CardsPng/BLUE_SOLID_TRIANGLE.jpg";
                    break;
                case "ghc" :
                    card.imagePath = "pictures/CardsPng/GREEN_HOLLOW_CIRCLE.jpg";
                    break;
                case "ghs" :
                    card.imagePath = "pictures/CardsPng/GREEN_HOLLOW_SQUARE.jpg";
                    break;
                case "ght" :
                    card.imagePath = "pictures/CardsPng/GREEN_HOLLOW_TRIANGLE.jpg";
                    break;
                case "gsc" :
                    card.imagePath = "pictures/CardsPng/GREEN_SOLID_CIRCLE.jpg";
                    break;
                case "gss" :
                    card.imagePath = "pictures/CardsPng/GREEN_SOLID_SQUARE.jpg";
                    break;
                case "gst" :
                    card.imagePath = "pictures/CardsPng/GREEN_SOLID_TRIANGLE.jpg";
                    break;
                case "rhc" :
                    card.imagePath = "pictures/CardsPng/RED_HOLLOW_CIRCLE.jpg";
                    break;
                case "rhs" :
                    card.imagePath = "pictures/CardsPng/RED_HOLLOW_SQUARE.jpg";
                    break;
                case "rht" :
                    card.imagePath = "pictures/CardsPng/RED_HOLLOW_TRIANGLE.jpg";
                    break;
                case "rsc" :
                    card.imagePath = "pictures/CardsPng/RED_SOLID_CIRCLE.jpg";
                    break;
                case "rss" :
                    card.imagePath = "pictures/CardsPng/RED_SOLID_SQUARE.jpg";
                    break;
                case "rst" :
                    card.imagePath = "pictures/CardsPng/RED_SOLID_TRIANGLE.jpg";
                    break;
            }
        }
    }

    /**
     * remove the top card of the deck
     */
    public void removeCard(){
        deck.remove(0);
    }

    /**
     * get the top card of the deck
     * @return card
     */
    public Card getDeckTopCard(){
        return deck.get(0);
    }
}
