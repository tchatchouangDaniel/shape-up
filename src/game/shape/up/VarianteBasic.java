package game.shape.up;

import player.shape.up.Player;
import view.shape.up.Game;

import java.util.ArrayList;

/**
 * The class implement methods to initialize the players and set their victory cards at the beginning.
 *
 * @author Daniel
 */
public class VarianteBasic implements Variante {
    /**
     * That method deal victory cards to players
     */
    @Override
    public void initPlayer() {
        int nombreJoueur = Game.getUniqueGame().getPlayerNumber();
        ArrayList<Player> players = Game.getUniqueGame().getPlayers();
        for (int i = 0; i < nombreJoueur; i++) {
            players.get(i).setVictoryCard();
        }
    }

    @Override
    public void victoryCardFind() {
        System.out.print("");
    }
}
