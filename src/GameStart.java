import controller.shape.up.GameViewController;
import model.shape.up.GameManager;
import view.shape.up.GameViewApp;
import view.shape.up.SetupException;

/**
 * This is the main class of our program.
 * It is only use to instantiate the differents objects for the game :
 * @author Daniel
 */
public class GameStart {
//    public static void main(String[] args) throws SetupException {
//        GameManager gm = GameManager.getUniqueGm();
//        gm.mainMenu();
//    }

    /**
     * This is the main method of the program that will be executed on the launch of the program.
     * This is where we create the objects of the MVC for the game :
     * the model : <code>GameManager</code>
     * the controller : <code>GameViewController</code>
     * the view :<code>GameViewApp</code>
     *
     * Add the observer GameViewApp to the GameManager
     * Create and start a new Thread for the graphical interface
     * Start the console interface (not yet in a separated thread)
     */
    public static void main(String[] args) throws SetupException {
        GameManager gm =GameManager.getUniqueGm();
        GameViewController gvc = GameViewController.getUniqueInstance();
        GameViewApp ga = GameViewApp.getUniqueInstance();
        gm.addObserver(ga);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                ga.setVisible(true);
            }
        });
        t.start();
        gm.mainMenu();
    }
}
