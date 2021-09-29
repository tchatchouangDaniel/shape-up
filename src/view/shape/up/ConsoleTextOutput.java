package view.shape.up;

import model.shape.up.RoundsManager;
import observer.shape.up.Observable;
import observer.shape.up.Observer;

/**This class look at my game and is getting notified each time a player has to play to move a card;
 * each time a turn end so that we can print the score;
 * each time a player had to play a card so that we can print the card in hand
 * each time the game begin so that we can print the menu
 * each time the state change so that we know that we must print the
 *
 * @author Daniel
 *  */


public class ConsoleTextOutput implements Observer {
    private ConsoleTextOutput() {}

    /**
     * that method is used to implement the singleton design pattern. It creates a private static instance of the
     * class we want a single instance of and therefore, ensure that the instance can be accessed only inside
     * this class. The getUniqueTextInstance() method help to get that instance outside
     */
    private static class SingletonHelper{
        private static final ConsoleTextOutput consoleTextOutput = new ConsoleTextOutput();
    }

    /**
     * return the unique instance of the class. Is used to get the class instance outside of the class scope, since
     * the constructor is private.
     *
     * @return ConsoleTextOutput
     */
    public static ConsoleTextOutput getUniqueTextInputInstance(){
        return SingletonHelper.consoleTextOutput;
    }

    /**
     * This method help to decouple the console log from the method of the game
     *
     * @param s The observable object
     * @param o The particular property of object above the class were observing
     */
    @Override
    public void update(Observable s, Object o) {
        if(s instanceof RoundsManager){
            if(o instanceof ConsoleOutput)
                ConsoleGameView.display(((ConsoleOutput) o));

        }
    }
}
