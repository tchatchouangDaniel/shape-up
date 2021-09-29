package gaem.area.shape.up;

import calcul.score.shape.up.CalcScoreVisitor;
import calcul.score.shape.up.GamePlateform;
import card.and.deck.shapeup.*;
import game.shape.up.Positioning;
import model.shape.up.RoundsManager;
import observer.shape.up.Observable;
import observer.shape.up.Observer;
import player.shape.up.Player;
import view.shape.up.Game;

import java.util.ArrayList;

/**
 * That class implements all the method to ensure that our game area is conform to shape up game rules.
 * Therefore we have a method to check if the adjacency rule is respected when a player want to move or play a card.
 * We have method that check boundaries depending on the game field form choose by the player in the option.
 * Those are the main methods. But of course we can find a method to add a card on field and to remove a card form
 * the field.
 *
 * It implements GamePlateform interface to make sure that brings a method to calculate the score throught the visitor design pattern.
 * (see {@link GamePlateform})
 *
 * @author Daniel
 */
public class GameArea extends Observable implements GamePlateform, Observer {
    protected final Deck deck = Deck.getUniqueDeck();
    protected final Card nullCard = new Card(Color.NULL, Fill.NULL, Shape.NULL);
    
    protected ArrayList<ArrayList<Card>> field;
    protected ArrayList<Positioning> positionPossible;
    protected int cardNumberOn;
    protected int maxLength;
    protected int maxHeight;
    protected int relativeLength;
    protected int relativeHeigth;
    protected int initialLine;
    protected int initialCol;
    protected boolean empty;
    protected CarpetShapeStrategy carpetShapeStrategy;
    protected CarpetShape carpetShape;

    /**
     * Update method that contains actions to perform when a subject change state
     * @param s the subject we observe
     * @param o the property we observe on that objsect
     */
    @Override
    public void update(Observable s, Object o) {
        if(s instanceof RoundsManager)
            printField();
    }

    /**
     * Constructor of the GameArea class
     */
    protected GameArea() {
        resetArray();
    }

    /**
     * That class help to implement the singleton design pattern so that we have only one game area
     */
    public static class SingletonHelper{
        private static final GameArea uniqueGameArea = new GameArea();
    }

    /**
     * Send back a reference to the unique instanec of gameArea.
     * @return uniqueGameArea
     */
    public static GameArea getUniqueGameArea(){
        return SingletonHelper.uniqueGameArea;
    }

    /**
     * return the reference to the visit method defined in calcScoreVisitorInterface
     * @param visitor is the class that contain a method that need some property of the current class to work
     * @return int[] score a table of integer values
     */
    @Override
    public int[] accept(CalcScoreVisitor visitor){
        return visitor.visit(this);
    }

    /**
     * As it name indicate it helps initialise and reset the gameArea each time we start a new game
     */
    public void resetArray(){
        this.field = new ArrayList<>();
        this.positionPossible = new ArrayList<>();
        this.cardNumberOn = 0;
        this.maxLength = 5;
        this.maxHeight = 5;
        this.relativeLength = 0;
        this.relativeHeigth = 0;
        this.initialLine = 5;
        this.initialCol = 5;
        this.empty = true;
        this.carpetShape = CarpetShape.RECTANGLE;
        this.carpetShapeStrategy = new RectangleBoundary();
    }


    /**
     * fill the field with null cards that aren't actually card with null value assigned
     * but null card created from enum class with NULL attribute. It help us solve a problem with indexOutOfBound exception
     * we encounter while using the arrayList for the field
     *
     */
    private void nullArrayComplation(ArrayList<ArrayList<Card>> arr){
        for (int i = 0; i < initialLine; i++) {
            for (int j = 0; j < initialCol; j++) {
                arr.get(i).add(nullCard);
            }
        }
    }

    /**
     * initialize the field with null card by call nullArrayComplation method
     *
     */
    public void initField(){
        for (int i = 0; i < initialLine; i++) {
            ArrayList<Card> row = new ArrayList<>();
            field.add(row);
        }
        nullArrayComplation(field);
    }

    /**
     * Find all the valid possible positions
     */
    public void findPossibleValue(){
        redimensionArrayList();
        positionPossible.clear();
        for(int i = 0; i < field.size(); i++){
            for (int j = 0; j < field.get(i).size(); j++) {
                if(!field.get(i).get(j).getId().equals("---")){
                    if(j-1 >= 0){
                        if(field.get(i).get(j-1).getId().equals("---"))
                            positionPossible.add(new Positioning(i,j-1));
                    }
                    if(j+1 <= (field.get(i).size() -1)){
                        if(field.get(i).get(j+1).getId().equals("---"))
                            positionPossible.add(new Positioning(i, j+1));
                    }
                    if(i-1 >= 0){
                        if(field.get(i-1).get(j).getId().equals("---"))
                            positionPossible.add(new Positioning(i-1,j));
                    }
                    if(i+1 <= (field.size() -1)){
                        if(field.get(i+1).get(j).getId().equals("---"))
                            positionPossible.add(new Positioning(i+1,j));
                    }
                }
            }
        }
    }

    /**
     * Check if a particular position, one entered by the user for exemple, is a valid position
     * @param position position to check
     * @return boolean true or false
     */
    public boolean isPositionValid(Positioning position){
        findPossibleValue();
        for (Positioning positioning : positionPossible) {
            if (position.equals(positioning)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a column contain a card
     * @param i index of the column
     * @return boolean true / false
     */
    public boolean checkColumn(int i){
        for (int j = 0; j < field.size(); j++) {
            if(!field.get(j).get(i).getId().equals("---")){
                return true;
            }
        }
        return false;
    }


    /**
     * Check if a line contain a card or not
     * @param i the index of the line
     * @return true / false
     */
    public boolean checkLine(int i){
        for (int j = 0; j < field.get(i).size(); j++) {
            if(!field.get(i).get(j).getId().equals("---"))
                return true;
        }

        return false;
    }

    /**
     * Check if the position entered respect the boundary of the carpet shape choosed  by the user
     * @param position the position we want to check
     * @return Boolean true or false
     */
    public boolean isBoundaryRespected(Positioning position){
        return carpetShapeStrategy.checkForBoundary(position);
    }

    /**
     * Check if a card is on the field through it id
     * @param cardID id if the card ti search
     * @return Boolean true or false
     */
    public boolean isCardOnField(String cardID){
        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.get(i).size(); j++) {
                if(field.get(i).get(j).getId().equals(cardID)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * add a line or a column when we rich the limit of the gamefield. it is used to scope with
     * IndexOutOfBound exception
     */
    public void updateBoundaries(){
        int length = 0;
        int heigth = 0;

        for (int i = 0; i < field.size(); i++) {
            if(checkLine(i)){
                heigth++;

            }else if(i != 0 && (i!= field.size() - 1) && checkLine(i-1) && checkLine(i+1) ){
                heigth++;
            }
        }

        for (int i = 0; i < field.get(0).size(); i++) {
            if(checkColumn(i)){
                length++;
            }else if(i!=0 && (i!=field.get(0).size() - 1) && checkColumn(i-1) && checkColumn( i+1)){
                length++;
            }
        }
        relativeLength = length;
        relativeHeigth = heigth;
    }

    /**
     * In continuation with the update boundaries. They serve the same purpose but this method simply
     * build the new ArrayList by looking at the position of lines and/or columns that needs to be add
     */
    public void redimensionArrayList(){
        int preLineNbr = initialLine;
        int preColNbr = initialCol;
        boolean line1 = checkLine(0), line2 = checkLine(field.size() - 1), column1 = checkColumn(0), column2 = checkColumn(field.get(0).size() -1);

        if(line1){
            preLineNbr++;
        }

        if(line2){
            preLineNbr++;
        }
        if(column1){
            preColNbr++;
        }
        if(column2){
            preColNbr++;
        }
        initialLine = preLineNbr;
        initialCol = preColNbr;

        if(line1 || line2 || column1 || column2){
            ArrayList<ArrayList<Card>> newArray = new ArrayList<>();
            for (int i = 0; i < initialLine; i++) {
                ArrayList<Card> row = new ArrayList<>();
                newArray.add(row);
            }
            nullArrayComplation(newArray);
            if (line1 && !line2 && !column1 && !column2) {
                for (int i = 1; i < newArray.size(); i++) {
                    for (int j = 0; j < newArray.get(i).size(); j++) {
                        newArray.get(i).set(j,field.get(i-1).get(j));
                    }
                }
                setChanged();
                notifyObservers("top");
            }else if(line1 && line2 && !column1 && !column2) {
                for (int i = 1; i < (newArray.size()-1); i++) {
                    for (int j = 0; j < newArray.get(i).size(); j++) {
                        newArray.get(i).set(j,field.get(i-1).get(j));
                    }
                }
            }else if(line1 && line2 && column1 && !column2){
                for (int i = 1; i < (newArray.size() -1); i++) {
                    for (int j = 1; j < newArray.get(i).size(); j++) {
                        newArray.get(i).set(j,field.get(i-1).get(j-1));
                    }
                }
            }else if(line1 && line2 && column1 && column2){ // implicitly saying that column 2 is true
                for (int i = 1; i < (newArray.size()-1); i++) {
                    for (int j = 1; j < (newArray.get(i).size() - 1); j++) {
                        newArray.get(i).set(j,field.get(i-1).get(j-1));
                    }
                }
            }else if(!line1 && line2 && !column1 && !column2){
                for (int i = 0; i < (newArray.size()-1); i++) {
                    for (int j = 0; j < newArray.get(i).size(); j++) {
                        newArray.get(i).set(j,field.get(i).get(j));
                    }
                }
                setChanged();
                notifyObservers("bottom");
            }else if(!line1 && line2 && column1 && !column2){
                for (int i = 0; i < (newArray.size() -1); i++) {
                    for (int j = 1; j < newArray.get(i).size(); j++) {
                        newArray.get(i).set(j,field.get(i).get(j-1));
                    }
                }
            }else if(!line1 && line2 && column1 && column2){ // implicitly implying that column 2 is true
                for (int i = 0; i < (newArray.size()-1); i++) {
                    for (int j = 1; j < (newArray.get(i).size() -1); j++) {
                        newArray.get(i).set(j,field.get(i).get(j-1));
                    }
                }
            }else if(!line1 && !line2 && column1 && ! column2){
                for (int i = 0; i < newArray.size(); i++) {
                    for (int j = 1; j < newArray.get(i).size(); j++) {
                        newArray.get(i).set(j,field.get(i).get(j-1));
                    }
                }
                setChanged();
                notifyObservers("left");
            }else if(!line1 && !line2 && column1 && column2){
                for (int i = 0; i < newArray.size(); i++) {
                    for (int j = 1; j < (newArray.get(i).size()-1); j++) {
                        newArray.get(i).set(j,field.get(i).get(j-1));
                    }
                }
            }else if(!line1 && !line2 && !column1 && column2){
                for (int i = 0; i < newArray.size(); i++) {
                    for (int j = 0; j < (newArray.get(i).size()-1); j++) {
                        newArray.get(i).set(j,field.get(i).get(j));

                    }
                }
                setChanged();
                notifyObservers("Right");
            }
            field = newArray;
        }


    }


    /**
     * Is used  to add a card on field
     * @param card the card that we want to add
     * @param posI the abscis position of the card on the field
     * @param posJ the ordinal position of the card on the field
     * @return true if card is added false if not
     */
    public boolean addCard(Card card, int posI, int posJ){

        if(empty){
            field.get(2).set(2,card);
            setChanged();
            notifyObservers(new Positioning(2,2));
            setChanged();
            notifyObservers(card);
            empty = false;
            cardNumberOn++;
            return true;
        }else{
            Positioning position = new Positioning(posI,posJ);
            if(isPositionValid(position) && isBoundaryRespected(position)){
                field.get(posI).set(posJ,card);
                cardNumberOn++;
                setChanged();
                notifyObservers(position);
                setChanged();
                notifyObservers(card);
                redimensionArrayList();
                return true;
            }
        }
        return false;
    }

    /**
     * Is used to move the card around the field
     * @param id the id of the card on the field
     * @param posI the abscisse position of the card on field
     * @param posJ the ordinal position of the card ont the field
     * @return Boolean true or false if the card is moved or not
     */
    public boolean moveCard(String id, int posI, int posJ){
        Positioning position = new Positioning(posI,posJ),
                cardPosition = new Positioning(-1,-1);
        Card cardRef = new Card(Color.NULL, Fill.NULL, Shape.NULL);
        boolean found = false;
        boolean proceedToAdd = true;
        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.get(i).size(); j++) {
                if(field.get(i).get(j).getId().equals(id) && !id.equals("---")){
                    cardRef = field.get(i).get(j);
                    found = true;
                    cardPosition.setX(i);
                    cardPosition.setY(j);
                    break;
                }
            }
            if(found)
                break;
        }
        if(!found){
            proceedToAdd = false;
            //System.out.println("Card not on field");
        }else if(posI == cardPosition.getX() && posJ == cardPosition.getY()){
            proceedToAdd = false;
            //System.out.println("Vous ne pouvez pas déplacer une carte pour le même endroit");
        }
        if(proceedToAdd && cardNumberOn >= 2){ // On ne deplace pas quand il ya une seule carte; c'est à partir de 2 cartes
            field.get(cardPosition.getX()).set(cardPosition.getY(), nullCard);
                if(isPositionValid(position) && isBoundaryRespected(position)){
                    field.get(posI).set(posJ, cardRef);
                    setChanged();
                    notifyObservers(true);
                    setChanged();
                    notifyObservers(position);
                    setChanged();
                    notifyObservers(id);
                    redimensionArrayList();
                    return true;
                }else{
                    field.get(cardPosition.getX()).set(cardPosition.getY(), cardRef);
                    //System.out.println("La position choisie n'est pas valide");
                }
        }
        return false;
    }

    /**
     *
     * @return
     */
    public boolean printFhelperPlayer(){
        Game game = Game.getUniqueGame();
        ArrayList<Player> players =  game.getPlayers();
        boolean everyPlayerHasOne = true;

        for (Player p: players){
            if(p.getHand().size() >= 1){
                everyPlayerHasOne = false;
            }
        }

        return everyPlayerHasOne;
    }

    /**
     * print the gameField to the console.
     */
    public void printField(){
        if(!deck.isEmpty() || !printFhelperPlayer()){
            for (int i = 0; i < field.get(0).size(); i++) {
                System.out.print("\t\t" + i);
            }
            System.out.println("\n");
            for (int i = 0; i < field.size(); i++) {
                System.out.print(i + "\t\t");
                for (int j = 0; j < field.get(i).size(); j++) {

                    System.out.print(field.get(i).get(j).getId() + "\t\t");

                }
                System.out.print("\n\n");
            }
        }else{
            for (int i = 0; i < field.size(); i++) {
                for (int j = 0; j < field.get(i).size(); j++) {

                    System.out.print(field.get(i).get(j).getId() + "\t\t");

                }
                System.out.print("\n\n");
            }
        }

        System.out.println("/////////////////");
    }

    /**
     * Remove additional lines and columns from our board layout
     */
    public void removeUselessLines(){
        int numberToRemoveTop = 0;
        int numberToRemoveBottom = 0;
        int numberToRemoveLeft = 0;
        int numberToRemoveRight = 0;
        int lineNumber = field.size();
        int colNumber = field.get(0).size();
        int firstLine = 0;
        int firstCol = 0;

        //top
        for (int i = 0; i < field.size(); i++) {
            if(!checkLine(i) && !checkLine(i+1)){
                numberToRemoveTop++;
            }else if(!checkLine(i) && checkLine(i+1)){
                break;
            }
        }

        //bottom
        for (int i = (field.size() -1); i >-1 ; i--) {
            if(!checkLine(i) && !checkLine(i-1)){
                numberToRemoveBottom++;
            }else if(!checkLine(i) && checkLine(i-1)){
                break;
            }
        }

        //left
        for (int i = 0; i < field.get(0).size(); i++) {
            if(!checkColumn(i) && !checkColumn((i+1))){
                numberToRemoveLeft++;
            }else if(!checkColumn(i) && checkColumn(i+1)){
                break;
            }
        }

        //right
        for (int i = (field.get(0).size()-1); i > -1; i--) {
            if(!checkColumn(i) && !checkColumn(i-1)){
                numberToRemoveRight++;
            }else if(!checkColumn(i) && checkColumn(i-1)){
                break;
            }
        }

        //top Remove
        lineNumber -= (numberToRemoveBottom + numberToRemoveTop);
        colNumber -= (numberToRemoveRight + numberToRemoveLeft);

        ArrayList<ArrayList<Card>> newArray = new ArrayList<>();
        for (int i = 0; i < lineNumber; i++) {
            ArrayList<Card> row = new ArrayList<>(); 
            newArray.add(row);
        }

        for (int i = 0; i < lineNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                newArray.get(i).add(nullCard);
            }
        }

        for (int i = 0; i < field.size(); i++) {
            if(checkLine(i)){
                firstLine = i-1;
                break;
            }
        }

        for (int i = 0; i < field.get(0).size(); i++) {
            if(checkColumn(i)){
                firstCol = i-1;
                break;
            }
        }
        int i2 = 0;
        int j2 = 0;
        for (int i = firstLine; i < field.size()-(numberToRemoveBottom+numberToRemoveTop); i++) {
            for (int j = firstCol; j < field.get(0).size() - (numberToRemoveLeft+numberToRemoveRight); j++) {
                newArray.get(i2).set(j2,field.get(i).get(j));
                j2++;
            }
            i2++;
            j2 = 0;
        }
        field = newArray;
        printField();
        setChanged();
        notifyObservers("reduce");

    }

    /**
     * Get the relative length of the field which is the number of cards that followed each other in a column independantly
     * of the line they are in
     * @return integer value that represent the length
     */
    public int getRelativeLength() {
        return relativeLength;
    }

    /**
     * Get the relative height of the field which is the number of cards that followed each other on line independantly
     * of the columns they are in
     * @return integer value that represent the height
     */
    public int getRelativeHeigth() {
        return relativeHeigth;
    }

    /**
     * get the initial line
     * @return initialLine
     */
    public int getInitialLine() {
        return initialLine;
    }

    /**
     * get the initial column
     * @return initialCol
     */
    public int getInitialCol() {
        return initialCol;
    }

    /**
     * the card board
     * @return field
     */
    public ArrayList<ArrayList<Card>> getField() {
        return field;
    }

    /**
     * get the number of card on
     * @return cardNumberOn
     */
    public int getCardNumberOn() {
        return cardNumberOn;
    }

    /**
     * get the maxLength
     * @return maxLength
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * get the max height
     * @return maxHeight
     */
    public int getMaxHeight() {
        return maxHeight;
    }

    /**
     * is used to check if the field is empty or not
     * @return true or false
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * All the positions possible for the player to play a card
     * @return positionPossible
     */
    public ArrayList<Positioning> getPositionPossible() {
        return positionPossible;
    }

    /**
     *
     * @param field on which we are playing the game
     */
    public void setField(ArrayList<ArrayList<Card>> field) {
        this.field = field;
    }

    /**
     * Max number of card in  a line
     * @param maxLength number of cards
     */
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Max number of card in a column
     * @param maxHeight number of card
     */
    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setRelativeLength(int relativeLength) {
        this.relativeLength = relativeLength;
    }

    public void setRelativeHeigth(int relativeHeigth) {
        this.relativeHeigth = relativeHeigth;
    }

    /**
     * give the first line in which we find a card
     * @param initialLine a line index
     */
    public void setInitialLine(int initialLine) {
        this.initialLine = initialLine;
    }

    public void setInitialCol(int initialCol) {
        this.initialCol = initialCol;
    }

    /**
     * Define the carpet Shape to be use in game by using the attributes of enum class CarpetShape
     * @param carpetShape of the field
     */
    public void setCarpetShape(CarpetShape carpetShape) {
        this.carpetShape = carpetShape;
        if(carpetShape.equals(CarpetShape.RECTANGLE)){
            carpetShapeStrategy = new RectangleBoundary();
        }else if(carpetShape.equals(CarpetShape.SQUARE)){
            carpetShapeStrategy = new SquareBoundary();
        }
    }


}
