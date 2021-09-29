package controller.shape.up;

import model.shape.up.GameManager;
import view.shape.up.GameViewApp;
import view.shape.up.SetupException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class for the controler of the <code>GameViewApp</code> graphical interface.
 * This is where all the ActionListener for the buttons are created and linked to the model.
 *
 * @author Daniel
 */
public class GameViewController {
    private GameManager gm;

    /**
     * The constructor of the gameViewController class
     * @param gm an instance of the game manager of the game
     */
    private GameViewController(GameManager gm) {
        this.gm = gm;
    }

    /**
     * Inner class to make sure that GameViewController instance is unique
     */
    private static class SingletonHelper{
        private static final GameViewController gvc =  new GameViewController(GameManager.getUniqueGm());
    }

    /**
     * Method to get the unique gameViewController instance
     * @return SingletonHelper.gvc
     */
    public static GameViewController getUniqueInstance(){
        return SingletonHelper.gvc;
    }

    /**
     * A method to get the Action listener for the Options
     * @return options
     */
    public ActionListener getOptions() {
        return options;
    }
    /**
     * A method to get the Action listener for the rules
     * @return rules
     */
    public ActionListener getRules() {
        return rules;
    }
    /**
     * A method to get the Action listener for the beginning of the game after the options part
     * @return start
     */
    public ActionListener getStart() {
        return start;
    }
    /**
     * A method to get the Action listener for the Quit
     * @return Quit
     */
    public ActionListener getQuit() {
        return quit;
    }

    /**
     * Create an action listener for the option button
     */
    private ActionListener options = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Thread options = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                       gm.executeUserChoice(4);
                    }catch (SetupException e){
                        e.printStackTrace();
                    }
                    System.out.println("option pressed");
                }
            });

            options.start();

        }
    };


    /**
     * Action listener for the buttons play
     */
    private ActionListener play = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Thread play = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        gm.executeUserChoice(4);
                    }catch(SetupException e){
                        e.printStackTrace();
                    }
                }
            });
            play.start();
        }
    };
    /**
     * Action listener for the buttons back
     */
    private ActionListener previous = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Thread previous = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        gm.mainMenu();
                    }catch (SetupException e){
                        e.printStackTrace();
                    }
                }
            });
            previous.start();
        }
    };
    /**
     * Action listener for the buttons start
     */
    public ActionListener begin = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Thread begin = new Thread(new Runnable() {
                @Override
                public void run() {
                    GameViewApp.getUniqueInstance().shareConfigOnBegin();
                    gm.play();
                }
            });
            begin.start();
        }
    };

    /**
     * A method to get the Action listener for the begin button
     * @return begin
     */
    public ActionListener getBegin(){
        return begin;
    }
    /**
     * A method to get the Action listener for the button play
     * @return play
     */
    public ActionListener getPlay() {
        return play;
    }
    /**
     * A method to get the Action listener for the previous button
     * @return previous
     */
    public ActionListener getPrevious() {
        return previous;
    }


    /**
     * Create an action listener for the rule button; There will be a second button during the game so that we can configure
     * the game during the game
     */

    private ActionListener rules = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        Thread rules = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                   gm.executeUserChoice(2);
                }catch (SetupException e){
                    e.printStackTrace();
                }
                System.out.println("rules presses");
            }
        });

            rules.start();

        }

    };

    /**
     * Create an action listener for the button back
     */

    private ActionListener back = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Thread back = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        gm.mainMenu();
                    }catch (SetupException e){
                        e.printStackTrace();
                    }
                    System.out.println("button back pressed");
                }
            });

            back.start();
        }
    };


    /**
     * Create an action listener for the button begin
     */

    private ActionListener start = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Thread start = new Thread(new Runnable() {
                @Override
                public void run() {
                    gm.play();
                }
            });
            start.start();
        }
    };

    /**
     * Create an action listener for the button exit
     */

    private ActionListener quit = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };


}
