package model.shape.up;

import observer.shape.up.Observable;
import observer.shape.up.Observer;
import view.shape.up.ConsoleGameView;
import view.shape.up.ConsoleOutput;
import view.shape.up.ConsoleUserInput;
import view.shape.up.SetupException;

public class GameManager extends Observable {
    private String gameState;
    private Observer board;  //gameArea
    private GameManager(){}

    private static class SingletonHelper{
        private static final GameManager gm = new GameManager();
    }

    public static GameManager getUniqueGm(){
        return SingletonHelper.gm;
    }

    public String getGameState() {
        return gameState;
    }

    public void play(){
        updateGameState("started");
        System.out.println("Game started and notify");
        RoundsManager currentGame = RoundsManager.getUniqueRoundManager();
        currentGame.firstRound();
        currentGame.secondRound();
        currentGame.nextRounds();
        currentGame.finalRound();
    }

    public void mainMenu() throws SetupException {
        System.out.println("Game launched");
        updateGameState("mainMenu");
        ConsoleGameView.display(ConsoleOutput.MAIN_MENU);
        this.executeUserChoice(ConsoleUserInput.getUniqueInstance().nextInt());
        this.play();
        updateGameState("started");
    }
    public void executeUserChoice(int userChoice) throws SetupException {
        switch (userChoice) {
            case 1:
                updateGameState("options");
                GameOptions.setup();
                break;
            case 2:
                this.rules();
                break;
            case 3:
                this.exit();
                break;
            case 4:
                this.options();
                break;
            default:
                System.out.println("No input given");
                break;
        }
    }

    public void rules() throws SetupException {
        System.out.println("Rules, press 1 to get back to the menu");
        updateGameState("rules");
        int input = ConsoleUserInput.getUniqueInstance().nextInt();
        if (input == 1) {
            this.mainMenu();
        }
    }

    /**
     * Displays the rules of the game, and changes the <code>gameState</code> to <em>"options"</em>.
     *
     * @throws SetupException Exception from the <code>setup()</code> method of the {@link GameOptions} class handled by this {@link Exception} class.
     */
    public void options() throws SetupException {
        updateGameState("options");
        GameOptions.setup();
    }

    public void exit() throws SetupException{
        updateGameState("exit");
    }

    /**
     * Set the <code>gameState</code> to the new state put in the console by the user.
     *
     * @param newState New state of the game put by the user.
     */
    public void updateGameState(String newState) {
        gameState = newState;
        setChanged();
        notifyObservers();
    }
}
