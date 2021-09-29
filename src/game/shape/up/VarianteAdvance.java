package game.shape.up;

import player.shape.up.Player;
import view.shape.up.Game;

import java.util.ArrayList;

/**
 * The class implement methods to initialize the players and set their victory cards at the end of the game
 * 
 * @author Daniel 
 */

public class VarianteAdvance implements Variante{
    ArrayList<Player> players = Game.getUniqueGame().getPlayers();
    int nombreJoueur = Game.getUniqueGame().getPlayerNumber();

    /**
     * That method initialize the player so that at the beginning of the game each of them have 3 cards in hand
     */
    @Override
    public void initPlayer() {
        int nombreJoueur = Game.getUniqueGame().getPlayerNumber();
        ArrayList<Player> players = Game.getUniqueGame().getPlayers();
        for (int i = 0; i < nombreJoueur; i++) {
            for (int j = 0; j < 3; j++) {
                players.get(i).pickCard();
            }
        }
    }

    /**
     * Assign the victory card to the player depending on the last card he have in hand
     */
    @Override
    public void victoryCardFind(){
        int nombreJoueur = Game.getUniqueGame().getPlayerNumber();
        ArrayList<Player> players = Game.getUniqueGame().getPlayers();
        for (int i = 0; i < nombreJoueur; i++) {
            if(players.get(i).getHand().size() <= 1){
                players.get(i).setVictoryCard(players.get(i).getHand().get(0));
                players.get(i).getHand().remove(0);
            }
        }
    }
}
