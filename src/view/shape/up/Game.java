package view.shape.up;

import calcul.score.shape.up.CalcScoreVisitor;
import calcul.score.shape.up.CalcScoreVisitorImpl;
import card.and.deck.shapeup.Deck;
import gaem.area.shape.up.CarpetShape;
import gaem.area.shape.up.GameArea;
import game.shape.up.Variante;
import game.shape.up.VarianteBasic;
import player.shape.up.Player;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that implement the methods to play a round of the game and even calculaete
 *
 * @author Franck
 */
public class Game{

    protected final Deck deck = Deck.getUniqueDeck();
    protected final GameArea gameArea = GameArea.getUniqueGameArea();
    protected final Scanner scanner = new Scanner(System.in);
    protected final int turnNb = 4;

    protected int[] score;
    protected int playerNumber;
    protected int virtualPlayerNb;
    protected int realPlayerNb;
    protected ArrayList<Player> players;
    protected CarpetShape carpetShape;
    protected Variante gameVariants;
    protected boolean youWantVirtual;
    protected boolean gameEnd;
    protected ArrayList<int[]> scores;

    /**
     * The constructor of the game class
     */
    public Game() {
        playerNumber = 0;
        virtualPlayerNb = 0;
        realPlayerNb = 0;
        carpetShape = CarpetShape.RECTANGLE;
        gameVariants = new VarianteBasic();
        this.players = new ArrayList<>();
        this.youWantVirtual = false;
        this.gameEnd = false;
        scores = new ArrayList<>();
    }

    /**
     * classe qui comme son nom l'indique aide à la création du singleton
     */
    protected static class SingletonHelper{
        protected static final Game uniqueGame = new Game();
    }
    /**
     * Renvoi la reference à la seule instance de game créer
     * @return
     */
    public static Game getUniqueGame(){
        return SingletonHelper.uniqueGame;
    }

    public void resetScore(){
        score = new int[playerNumber];
    }

    /**
     * Determine le nombre de joueur réel qu'on veut dans la partie
     */
    protected void realPlayerNbOpt(){
        if(virtualPlayerNb != playerNumber)
            do {
                realPlayerNb = scanner.nextInt();
            }while (realPlayerNb < 0 || realPlayerNb > playerNumber);
        scanner.nextLine();
    }

    /**
     * Determine le nombre de joueur virtuel qu'on veut dans la partie
     */
    protected void virtualPlayerNbOpt(){
        System.out.println("--->NOMBRE DE JOUEURS VIRUTELS");
        if(realPlayerNb != playerNumber)
            do {
                virtualPlayerNb = scanner.nextInt();
            }while(virtualPlayerNb < 0 || virtualPlayerNb > playerNumber);

    }

    /**
     * permet d'afficher les noms de joueurs
     */
    public void printPlayerName(){
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i+1) + ": " + players.get(i).getName());
        }
    }

    public boolean checkTurnEnd(){
        if(deck.isEmpty()){
            int allEmpty = 0;
            for (Player p: players){
                if(p.getHand().isEmpty()){
                    allEmpty++;
                }
            }
            if(allEmpty ==  playerNumber){
                return true;
            }
        }
        return false;
    }


    public void calculateScore(GameArea gameArea){
        CalcScoreVisitor visitor = new CalcScoreVisitorImpl();
        score = gameArea.accept(visitor);
    }

    public CarpetShape getCarpetShape() {
        return carpetShape;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getTurnNb() {
        return turnNb;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<int[]> getScores() {
        return scores;
    }

    public int[] getScore() {
        return score;
    }


}
