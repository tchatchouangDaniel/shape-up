package view.shape.up;

import player.shape.up.RealPlayer;

import java.util.Scanner;

/**
 * That class is here to provide a way to get user input while decoupling the methods of the game
 * from inputs in the console.
 *
 * @author Daniel
 */
public class ConsoleUserInput {
    private Scanner entree;

    /**
     * The constructor of our class. We just instanciate a new scanner
     */
    private ConsoleUserInput(){
        entree = new Scanner(System.in);
    }

    /**
     * hat method is used to implement the singleton design pattern. It creates a private static instance of the
     * class we want a single instance of and therefore, ensure that the instance can be accessed only inside
     * this class. The getUniqueInstance() method help to get that instance outside
     */
    private static class SingletonHelper{
        private static ConsoleUserInput consoleUserInput = new ConsoleUserInput();
    }

    public static ConsoleUserInput getUniqueInstance(){
        return SingletonHelper.consoleUserInput;
    }

    /**
     * Check if the value is between two values min and max
     * @param min minimum value
     * @param max maximum value
     * @param input the value to check
     * @param output the output to the console
     * @throws SetupException in the case of any error at the setup level
     */
    public void isCorrectInputBetweenMinMax(int min, int max, int input, ConsoleOutput output) throws SetupException {
        if(input >= min && input <= max){
            ConsoleGameView.display(output);
        }else{
            throw new SetupException("The value is not correct");
        }
    }

    /**
     * Check if the String correspond to a valid card id
     * @param p the player that have the card
     * @param input the input of the user
     * @throws SetupException in the case of any error at the setup level
     */
    public void isCorrectStringInputId(RealPlayer p, String input) throws SetupException {
        if(!p.chooseCardInHand(input)){
            throw new SetupException("The card is not in hand");
        }
    }

    /**
     * return the integer value entered by the user in the console
     * @return integer value
     */
    public int nextInt(){
        return entree.nextInt();
    }

    /**
     * return the String value entered by the user in the console
     * @return String value
     */
    public String nextString(){
        return entree.next();
    }

    /**
     * return the integer value entered by the user in the console. Is used to cope
     * with inputTypeMismatchException in a try and catch close
     * @return String value
     */
    public String next(){
        return entree.next();
    }

}

