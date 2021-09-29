package player.shape.up;


import card.and.deck.shapeup.Card;
import model.shape.up.OptionData;

/**
 * That class defines a RealPlayer.
 * It extend from the Player class.
 * (see {@link Player})
 *
 * @author Daniel
 */
public class RealPlayer extends Player{

    public RealPlayer(String name) {
        super(name);
    }


    /**
     * Is used to see the card in the hand of a player
     */
    public void viewCard(){
        for (Card card : hand) {
            System.out.println("||" + card + "(" + card.getId() + ")" + "||");
        }
        if(hand.size() == 0){
            System.out.println("Empty Hand");
        }
    }

    /**
     * Is used to choose the card in hand
     * @param id of the card to choose
     * @return true or false if the card is present or not
     */
    public boolean chooseCardInHand(String id){ // Si faux on va redemenader un id au joueur
                                                //Si vrai on affecte la carte correspondante dans la carte à jouer
        boolean check = false;
        if(findCardById(id).getId().equals("---")){
            return false;
        }else{
            return true;
        }

    }

    /**
     * Is used to play a card
     * @param id of the card to play
     * @param posI x position where to play the card
     * @param posJ y position where to play the card
     */
    @Override
    public boolean playCard(String id,int posI,int posJ){//variante avancé le joueur choisi les carte qu'il joue
        //play la card en appellant la method addcard dependant de l'etat du terrain de jeu(empty or not);
        Card card = findCardById(id);
        boolean executed = false;
        if(card.getId().equals("---")){
            System.out.println("Carte pas trouvé");
            return false;
        }else{
                executed = gameArea.addCard(findCardById(id),posI,posJ);
                if(executed){
                    hand.remove(shCardIdxHand(hand,id));
                }
        }
        //remove card de la main du joueur
        return executed;
    }


    /**
     * Basic variant
     * @param posI x position where to play
     * @param posJ y position where to play the card
     */
    @Override
    public boolean playCard(int posI, int posJ){// variante simple aucun choix
        boolean executed = false;

            executed = gameArea.addCard(hand.get(0),posI,posJ);
            if(executed){
                hand.remove(0);
            }

        return executed;
    }

    /**
     * Is used so that the player can move a card on the board
     * @return true or false
     */
    @Override
    public boolean moveCard(String id, int x, int y){
        return gameArea.moveCard(id, x, y);
    }

    /**
     * method that print the name of a player and his victory card
     * @return a String with the characteristics of the Real player
     */
    public String toString(){
        if(OptionData.getVariant() == 2) return "Name : " + name;
        return "Name : " + name + "\nvictoryCard : " + victoryCard ;
    }
}
