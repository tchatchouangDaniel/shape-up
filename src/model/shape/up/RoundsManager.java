package model.shape.up;

import calcul.score.shape.up.CalcScoreVisitor;
import calcul.score.shape.up.CalcScoreVisitorImpl;
import card.and.deck.shapeup.Deck;
import gaem.area.shape.up.CarpetShape;
import gaem.area.shape.up.GameArea;
import game.shape.up.Positioning;
import game.shape.up.Variante;
import game.shape.up.VarianteAdvance;
import game.shape.up.VarianteBasic;
import observer.shape.up.Observable;
import player.shape.up.Level;
import player.shape.up.Player;
import player.shape.up.RealPlayer;
import player.shape.up.VirtualPlayer;
import view.shape.up.*;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * this class manage the rounds of the game by definig methods to implement the different sequence of the gameplay.
 *
 * @author Daniel
 */
public class RoundsManager extends Observable {
    private final ConsoleTextOutput outputCon = ConsoleTextOutput.getUniqueTextInputInstance();
    private final GameArea gameArea = GameArea.getUniqueGameArea();
    private final Deck deck = Deck.getUniqueDeck();
    private ArrayList<Player> players;

    protected int roundNb = 0;
    private boolean turnOver;
    private ArrayList<String> winners;
    private String state;
    private Variante variante;
    private CarpetShape carpetShape;
    private VarianteBasic vb;
    private VarianteAdvance va;

    private int currentPlayerInd;
    protected int[] score;
    protected boolean firstPlay;
    private boolean gameEnd = false;

    /**
     * constructor of gameManager
     */
    private RoundsManager() {
        this.roundNb = 4;
        this.players = new ArrayList<>();
        this.turnOver = false;
        this.winners = new ArrayList<>();
        this.currentPlayerInd = randomBeginner();
        this.firstPlay = true;
        score = new int[OptionData.nbPlayer];
        if(OptionData.variant == 1){
            variante = new VarianteBasic();
            vb = (VarianteBasic) variante;
            // do that on first turn vb.initPlayer(); // player with victory card set
        }else{
            variante = new VarianteAdvance();
            va = (VarianteAdvance) variante;
            // do that on second turn va.initPlayer(); // player with hand set
        }

        if(OptionData.carpetShape == 1){
            carpetShape = CarpetShape.RECTANGLE;
        }else{
            carpetShape = CarpetShape.SQUARE;
        }
//        gameArea.initField();                          xxx do that in the first round
//        gameArea.setCarpetShape(carpetShape);
    }

    /**
     * get the current player index
     * @return currentPlayerInd
     */
    public int getCurrentPlayerInd() {
        return currentPlayerInd;
    }

    /**
     * get the players of the game
     * @return players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * an intern class to help creating a singleton of round manager
     */
    private static class singletonHelper{
        private static final RoundsManager roundsManager = new RoundsManager();
    }

    /**
     * get the unique instance of game manager
     * @return singletonHelper.roundsManager
     */
    public static RoundsManager getUniqueRoundManager(){
        return singletonHelper.roundsManager;
    }

    /**
     * Create the players according to the names specified in the OptionData class
     */
    public void createPlayersByName(){
        players.clear();
        for (int i = 0; i < OptionData.nbRealPlayer; i++) {
            players.add(new RealPlayer(OptionData.playersName.get(i)));
        }

        for (int i = 0; i < OptionData.nbVirtualPlayer; i++) {
            VirtualPlayer vPlayer = new VirtualPlayer(OptionData.vPlayerName[i]);
            Level lvl = switch (OptionData.levels[i]) {
                case 1 -> Level.EASY;
                case 2 -> Level.MEDIUM;
                case 3 -> Level.ADVANCED;
                default -> null;
            };
            vPlayer.setLevel(lvl);
            vPlayer.setStrategy();
            players.add(vPlayer);
        }
    }

    /**
     * Initialize the players in the basic variant
     */
    public void initPlayerBasic(){
        for (int i = 0; i < OptionData.nbPlayer; i++) {
            players.get(i).setVictoryCard();
        }
    }

    /**
     * Initialize the players in the advance variant
     */
    public void initPlayerAdvance(){
        for (int i = 0; i < OptionData.nbPlayer; i++) {
            for (int j = 0; j < 3; j++) {
                players.get(i).pickCard();
            }
        }
    }

    /**
     * Initialize the victory card  for advance variant
     */
    public void victoryCardFind(){
        for (int i = 0; i < OptionData.nbPlayer; i++) {
            players.get(i).setVictoryCard(players.get(i).getHand().get(0));
        }
    }

    /**
     * generate a random number to choose the game beginner
     * @return index of player
     */
    public int randomBeginner(){
        return (int) (Math.random()*OptionData.nbPlayer);
    }

    /**
     * get the variant of the game
     * @return variante
     */
    public Variante getVariante() {
        return variante;
    }

    /**
     * Describe a sequence of actions for the first round
     * In fact during the first round the player are created their victory cards are set depending on the variant and the board is initialised
     * After that a player is choose randomly and the game begin.
     * this method specify all that in the right order.
     */
    public void firstRound() {
        deck.initDeck();
        gameArea.resetArray();
        gameArea.initField();
        gameArea.setCarpetShape(carpetShape);
        deck.shuffle();
        createPlayersByName();
        if(OptionData.variant == 1){
            initPlayerBasic();
        }else if(OptionData.variant == 2){
            initPlayerAdvance();
        }
        //player created
        this.setChanged();
        this.notifyObservers("player created");
        notifyObservers(players);
        this.addObserver(outputCon);
        this.setChanged();
        this.notifyObservers(ConsoleOutput.SHOW_PLAYER);
        this.setChanged();
        this.notifyObservers(ConsoleOutput.PLAYING);

        if(players.get(currentPlayerInd) instanceof VirtualPlayer){
            if(variante instanceof VarianteAdvance) ((VirtualPlayer) players.get(currentPlayerInd)).playAdvance();
            if(variante instanceof VarianteBasic) ((VirtualPlayer) players.get(currentPlayerInd)).playBasic();
            gameArea.printField();
        }
        if(players.get(currentPlayerInd) instanceof RealPlayer){
            if(variante instanceof VarianteAdvance){
                ((RealPlayer) players.get(currentPlayerInd)).viewCard();
                String playCard = chooseAC((RealPlayer) players.get(currentPlayerInd));
                Positioning position = choosePosition();
                players.get(currentPlayerInd).playCard(playCard, position.getX(), position.getY());
                players.get(currentPlayerInd).pickCard();  // The deck is not supposed to be empty at that point. if check would be useless.
                gameArea.printField();
            }

            if(variante instanceof VarianteBasic){
                players.get(currentPlayerInd).showVictoryCard();
                players.get(currentPlayerInd).pickCard();
                this.setChanged();
                this.notifyObservers("card picked");
                ((RealPlayer) players.get(currentPlayerInd)).viewCard();
                Positioning position = choosePosition();
                players.get(currentPlayerInd).playCard(position.getX(), position.getY());
                gameArea.printField();
            }
        }

        if(currentPlayerInd == (OptionData.nbPlayer - 1)){
            currentPlayerInd = 0;
        }else{
            currentPlayerInd++;
        }
        firstPlay = false;
    }

    /**
     * Describe a sequence of actions for the second round.
     */
    public void secondRound(){
        this.setChanged();
        this.notifyObservers(ConsoleOutput.PLAYING);
        if(players.get(currentPlayerInd) instanceof VirtualPlayer){
            if(variante instanceof VarianteAdvance) ((VirtualPlayer) players.get(currentPlayerInd)).playAdvance();
            if(variante instanceof VarianteBasic) ((VirtualPlayer) players.get(currentPlayerInd)).playBasic();
            gameArea.printField();
        }
        if(players.get(currentPlayerInd) instanceof RealPlayer){
            if(variante instanceof VarianteAdvance){
                ((RealPlayer) players.get(currentPlayerInd)).viewCard();
                String playCard = chooseAC((RealPlayer) players.get(currentPlayerInd));
                Positioning position = choosePosition();
                players.get(currentPlayerInd).playCard(playCard, position.getX(), position.getY());
                players.get(currentPlayerInd).pickCard();  // The deck is not supposed to be empty at that point. if check would be useless.
                gameArea.printField();
            }

            if(variante instanceof VarianteBasic){
                players.get(currentPlayerInd).showVictoryCard();
                players.get(currentPlayerInd).pickCard();
                this.setChanged();
                this.notifyObservers("card picked");
                ((RealPlayer) players.get(currentPlayerInd)).viewCard();
                Positioning position = choosePosition();
                players.get(currentPlayerInd).playCard(position.getX(), position.getY());
                gameArea.printField();
            }
        }

        if(currentPlayerInd == (OptionData.nbPlayer - 1)){
            currentPlayerInd = 0;
        }else{
            currentPlayerInd++;
        }
    }

    //Next rounds that will execute till:
    //1. Basic variant we have 1 card in the deck so that for last round the player pick the card play and we calculate the score
    //2. Advance. //EMPTY DECK //One card in all the players hands except on player for whom we assume he has 2 card in hand and will be the one to play last
    //in both cases we have to

    /**
     * Specify how the other rounds of the game go on.
     * And change the player that is playing at each
     */
    public void nextRounds(){
        do{
            this.setChanged();
            this.notifyObservers(ConsoleOutput.PLAYING);
            if(players.get(currentPlayerInd) instanceof VirtualPlayer){
                if(variante instanceof VarianteBasic) ((VirtualPlayer) players.get(currentPlayerInd)).playBasic();
                if(variante instanceof VarianteAdvance) ((VirtualPlayer) players.get(currentPlayerInd)).playAdvance();
                gameArea.printField();
            }
            if(players.get(currentPlayerInd) instanceof RealPlayer){
                int action = chooseWhatTD();
                if(action == 1){
                    //Play Card
                    if(variante instanceof VarianteBasic){
                        playCardBasic((RealPlayer) players.get(currentPlayerInd));
                    }else if(variante instanceof VarianteAdvance){
                        playCardAdvanced((RealPlayer) players.get(currentPlayerInd));
                    }
                    //Propose to move card
                    boolean wanToMove = wannaMove();
                    if(wanToMove && gameArea.getCardNumberOn() < 15){
                        String id = chooseACField();
                        Positioning position = choosePosition();
                        ((RealPlayer) players.get(currentPlayerInd)).moveCard(id, position.getX(), position.getY());
                        //moveACard(((RealPlayer) players.get(currentPlayerInd)));
                        gameArea.printField();
                    }else if(!wanToMove){
                        this.setChanged();
                        this.notifyObservers(ConsoleOutput.STANDARD);
                    }else{
                        this.setChanged();
                        this.notifyObservers(ConsoleOutput.CANT_DO);
                    }
                }
                if(action == 2){
                    //move Card
                    boolean wanToMove = wannaMove();
                    if(wanToMove){
                        String id = chooseACField();
                        Positioning position = choosePosition();
                        ((RealPlayer) players.get(currentPlayerInd)).moveCard(id, position.getX(), position.getY());
                        gameArea.printField();
                        //moveACard(((RealPlayer) players.get(currentPlayerInd)));
                    }
                    //play Card
                    if(variante instanceof VarianteBasic){
                        playCardBasic((RealPlayer) players.get(currentPlayerInd));
                    }else if(variante instanceof VarianteAdvance){
                        playCardAdvanced((RealPlayer) players.get(currentPlayerInd));
                    }
                }
            }
            if(currentPlayerInd == (OptionData.nbPlayer - 1)){
                currentPlayerInd = 0;
            }else{
                currentPlayerInd++;
            }
        }while(!findCondition(variante));
    }

    /**
     * Methods in which score are calculated and winner announced
     */
    public void finalRound(){
        if(variante instanceof VarianteAdvance) {
            victoryCardFind();
            setChanged();
            notifyObservers(ConsoleOutput.SHOW_PLAYER_ADV);
        }
        calculateScore(gameArea);
        gameArea.removeUselessLines();
        winners = findWinner(score);
        setChanged();
        notifyObservers(ConsoleOutput.SCORE);
        setChanged();
        notifyObservers("game end");
    }

    /**
     * Method to return the score
     * @return score
     */
    public int[] getScore() {
        return score;
    }

    /**
     * Method to return the winners
     * @return winners
     */
    public ArrayList<String> getWinners() {
        return winners;
    }

    /**
     * Method to decide either if the player move or place a card
     * @return choice
     */
    public int chooseWhatTD(){
        this.setChanged();
        this.notifyObservers(ConsoleOutput.TO_DO);
        boolean verif = false;
        int choice = -1;
        do{
            try {
                choice = ConsoleUserInput.getUniqueInstance().nextInt();
                ConsoleUserInput.getUniqueInstance().isCorrectInputBetweenMinMax(1,2,choice,ConsoleOutput.STANDARD);
                verif = true;
            }catch(SetupException e){
                System.out.println("1 or 2 please! Retry : ");
            }catch (InputMismatchException e){
                System.out.println("You must enter a number! Retry : ");
            }
        }while (!verif);

        return choice;
    }

    /**
     * Method to choose a card
     * @param p the player who is choosing
     * @return id of the card
     */
    public String chooseAC(RealPlayer p){
        this.setChanged();
        this.notifyObservers(ConsoleOutput.SELECT_CARD);
        String cardId;
        do {
            cardId = ConsoleUserInput.getUniqueInstance().nextString();
            if(!p.chooseCardInHand(cardId)){
                this.setChanged();
                this.notifyObservers(ConsoleOutput.BAD_ENTRY);
            }
        }while(!p.chooseCardInHand(cardId));
        return ConsoleUserInput.getUniqueInstance().nextString();
    }

    /**
     * Method to choose a card on the field
     * @return cardId
     */
    public String chooseACField(){
        this.setChanged();
        this.notifyObservers(ConsoleOutput.SELECT_CARD);
        String cardId;
        do {
            cardId = ConsoleUserInput.getUniqueInstance().nextString();
            if(!gameArea.isCardOnField(cardId)){
                this.setChanged();
                this.notifyObservers(ConsoleOutput.BAD_ENTRY);
            }
        }while(!gameArea.isCardOnField(cardId));
        return cardId;
    }

    /**
     * Method to choose a position on field
     * @return position
     */
    public Positioning choosePosition(){
        this.setChanged();
        this.notifyObservers(ConsoleOutput.MOVE_CARD);
        Positioning position = new Positioning(-1,-1);
        boolean valid = false;
        do{
            this.setChanged();
            notifyObservers(ConsoleOutput.POS_X);
            position.setX(ConsoleUserInput.getUniqueInstance().nextInt());
            this.setChanged();
            notifyObservers(ConsoleOutput.POS_Y);
            position.setY(ConsoleUserInput.getUniqueInstance().nextInt());
            if(checkPosBound(position)  || firstPlay){
                valid = true;
            }else {
                this.setChanged();
                notifyObservers(ConsoleOutput.BAD_POS);
            }
        }while(!valid);
        return position;
    }

    /**
     * Method to check if the position is valid and respect the boundaries of the GameArea.
     * @param position to check
     * @return true or false
     */
    public boolean checkPosBound(Positioning position){
        return gameArea.isPositionValid(position) && gameArea.isBoundaryRespected(position);
    }

    /**
     * Play a card in the basic variant
     * @param player the player who is playing
     */
    public void playCardBasic(RealPlayer player){
        player.showVictoryCard();
        player.pickCard();
        this.setChanged();
        this.notifyObservers("card picked");
        player.viewCard();
        Positioning position = choosePosition();
        player.playCard(position.getX(), position.getY());
        gameArea.printField();
    }

    /**
     * Play a card in the basic Variant specifically for the graphic view
     * @param position
     */
    public void playCardBasicView(Positioning position){
        RealPlayer player = (RealPlayer) players.get(currentPlayerInd);
        player.showVictoryCard();
        player.pickCard();
        this.setChanged();
        this.notifyObservers("card picked");
        player.viewCard();
        player.playCard(position.getX(), position.getY());
        gameArea.printField();
        this.setChanged();
        this.notifyObservers("card played");
        if(currentPlayerInd == (players.size()-1)){
            currentPlayerInd = 0;
        }else {
            currentPlayerInd++;
        }
    }

    /**
     * Play a card in the advance variant
     * @param player who is playing
     */
    public void playCardAdvanced(RealPlayer player){
        player.viewCard();
        String playCard = chooseAC(player);
        Positioning position = choosePosition();
        player.playCard(playCard, position.getX(), position.getY());
        if(!deck.isEmpty()) player.pickCard(); // only if the deck is not empty
        gameArea.printField();
    }

    /**
     * is used to choose to move or not
     * @return true or false
     */
    public boolean wannaMove(){
        this.setChanged();
        int choice = -1;
        this.notifyObservers(ConsoleOutput.WANNA);
        boolean valid = false;
        do{
            try {
                choice = ConsoleUserInput.getUniqueInstance().nextInt();
                ConsoleUserInput.getUniqueInstance().isCorrectInputBetweenMinMax(1,2,choice,ConsoleOutput.STANDARD);
                valid = true;
            }catch(SetupException e){
                System.out.println("1 or 2 please! Retry : ");
            }catch (InputMismatchException e){
                System.out.println("You must enter a number! Retry : ");
            }
        }while (!valid);

        return choice == 1;
    }


    /**
     * check if the game is ending.
     * @param var the variante of the game
     * @return true or false
     */
    public boolean findCondition(Variante var){
        if(var instanceof VarianteBasic){
            return (deck.getDeck().size() == 0);
        }else if(var instanceof VarianteAdvance){
            int cpt = 0;
            for(Player p : players){
                cpt += p.getHand().size();
            }
            if(OptionData.nbPlayer == 2){
                return cpt == 2;
            }else if(OptionData.nbPlayer == 3){
                return cpt == 3;
            }
        }
        return false;
    }

    /**
     * Find the biggest value in an array
     * @param arr array to find the max in
     * @return max
     */
    public int findMax(int[] arr){
        int max = 0;
        for (int j : arr) {
            if (max < j) {
                max = j;
            }
        }
        return max;
    }

    /**
     * Find the winner of the game
     * @param score the score arrray containing the scores
     * @return winnerName
     */
    public ArrayList<String> findWinner(int[] score){
        int max = findMax(score);
        ArrayList<String> winnersName = new ArrayList<>();
        for (int i = 0; i < score.length; i++) {
            if(max == score[i]){
                winnersName.add(players.get(i).getName());
            }
        }

        return winnersName;
    }

    // Method to calculate the score

    /**
     * Calculate the score of the game by using the visitor design pattern.
     * @param gameArea where the game is played
     */
    public void calculateScore(GameArea gameArea){
        CalcScoreVisitor visitor = new CalcScoreVisitorImpl();
        score = gameArea.accept(visitor);
        gameEnd = true;
    }




    // TODO: 18/12/2020 All the methods bellow

    //add gameArea as observer before doing either add or move of cards so that the methods are lighter

    //For the virtual player make sure that his method is just playing cards during the first two rounds

    //Method to play a card in advanced variant

    //Method to play a card in basic variant

    //Method to move a card in basic Variant

    //Method to move a card in advance Variant
}
