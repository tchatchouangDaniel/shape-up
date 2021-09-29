package view.shape.up;

import model.shape.up.OptionData;
import model.shape.up.RoundsManager;

/**
 * This class sole purpose is to decouple the game view in the console of the methods that are used to perform particular
 * actions throughout the program.
 *
 * @author Daniel
 */
public class ConsoleGameView {

    /**
     * This method contain all the different console log that can be made through out the game. The method is being called
     * through the observer design pattern in ConsoleTextOutput class.
     */
    public static void display(ConsoleOutput co){
        switch (co){
            case MAIN_MENU:
                System.out.println("Welcome to shape up Game !");
                System.out.println("--------------------------");
                System.out.println("\n1 - Start game");
                System.out.println("\n2 - Rules");
                System.out.println("\n3 - Quit");
                System.out.println("\n4 - Options");
                break;
            case SETTING_NB_PLAYER :
                System.out.println("How many players for your game ? (you have the choice between 2 or 3)");
                break;
            case PLAYER_NB:
                System.out.println(OptionData.getNbPlayer() + " Players will play the next Game. \n");
                break;
            case REAL_PLAYER:
                System.out.println("How many players are real for this game ?");
                break;
            case PLAY_VAR:
                System.out.println(OptionData.getNbRealPlayer() + " REAL PLAYER(s)");
                System.out.println(OptionData.getNbVirtualPlayer() + " VIRTUAL PLAYER(s)");
                System.out.println("--------------------------");
                System.out.println("1 - Play");
                System.out.println("2 - Quit");
                break;
            case VARIANT:
                System.out.println("we have two different variants for the game. Make your choice");
                System.out.println("1- Variant 1 : ShapeUp basic");
                System.out.println("2- Variant 2 : ShapeUp advanced");
                break;
            case SELECT_VAR:
                System.out.println("You will play with the Variant : " + OptionData.getVariant() + "\n");
                break;
            case NEW_GAME:
                System.out.println("Starting a new game !");
                break;
            case PLAY_CARD:
                System.out.println("Enter the position where you want to play your card");
                break;
            case SELECT_CARD:
                System.out.println("Enter the id of the card: ");
                break;
            case MOVE_CARD:
                System.out.println("Enter the position where you want to place your card");
                break;
            case POS_X:
                System.out.println("Enter the row number where you want to place your card");
                break;
            case POS_Y:
                System.out.println("Enter the column number where you want to place your card");
                break;
            case STANDARD:
                System.out.println("");
                break;
            case CARPET_SHAPE_CHOICE:
                System.out.println("We have two different carpet shapen. Make your choice");
                System.out.println("1- shape 1 : rectangular");
                System.out.println("2- shape 2 : square");
                break;
            case SELECTED_SHAPE:
                System.out.println("Yo will play with the shape : " + OptionData.getCarpetShape() + "\n");
                break;
            case PLAYER_NAME_CONFIG_O:
                System.out.println("Enter the name of the player 1 : ");
                break;
            case PLAYER_NAME_CONFIG_T:
                System.out.println("Enter the name of the player 2 : ");
                break;
            case PLAYER_NAME_CONFIG_TH:
                System.out.println("Enter the name of the player 3 : ");
                break;
            case GAME_OVER:
                System.out.println("GAME IS OVER");
                break;
            case PLAYING:
                System.out.println("Player : "  + RoundsManager.getUniqueRoundManager().getPlayers().get(RoundsManager.getUniqueRoundManager().getCurrentPlayerInd()).getName() + " turn");
                break;
            case BAD_ENTRY:
                System.out.println("Incorrect Value. Retry : ");
                break;
            case BAD_POS:
                System.out.println("Invalid Position. Retry : ");
                break;
            case TO_DO:
                System.out.println("What do you want to do next? You have twe choice");
                System.out.println("1- Play a card --then-- maybe move one");
                System.out.println("2- move a card --then-- Play a card");
                break;
            case MOVING_DECISION:
                System.out.println("W");
                break;
            case CANT_DO:
                System.out.println("You can't do that");
                break;
            case WANNA:
                System.out.println("Do you want to move a card: ");
                System.out.println("1- yes ");
                System.out.println("2- no ");
                break;
            case SCORE:
                System.out.println("RESULTS");
                for (int i = 0; i < OptionData.getNbPlayer(); i++) {
                    System.out.println(RoundsManager.getUniqueRoundManager().getPlayers().get(i).getName() + " : " + RoundsManager.getUniqueRoundManager().getScore()[i] + " points");
                }
                System.out.println("Winner(s) :");
                for (int i = 0; i < RoundsManager.getUniqueRoundManager().getWinners().size(); i++) {
                    System.out.println(RoundsManager.getUniqueRoundManager().getWinners().get(i));
                }
                break;
            case CONFIG_DONE:
                System.out.println("We are done configuring the the game!");
                break;
            case SHOW_PLAYER:
                for (int i = 0; i < OptionData.getNbPlayer(); i++) {
                    System.out.println(RoundsManager.getUniqueRoundManager().getPlayers().get(i));
                }
                break;
            case SHOW_PLAYER_ADV:
                for (int i = 0; i < OptionData.getNbPlayer(); i++) {
                    System.out.println(RoundsManager.getUniqueRoundManager().getPlayers().get(i) + " ");
                    System.out.print("Victory Card: " + RoundsManager.getUniqueRoundManager().getPlayers().get(i).getVictoryCard() + "\n");
                }
                break;
            case LEVEL_VIRTUAL:
                System.out.println("We have 3 level for virtual players");
                System.out.println("1- Easy");
                System.out.println("2- Crazy");
                System.out.println("3- Advanced");

                System.out.println("now for your virtual player(s) make choice between 1,2 and 3 for the level");
                break;
            case VIRTUAL1_LVL:
                System.out.println("Enter virtual player 1 level");
                break;
            case VIRTUAL2_LVL:
                System.out.println("Enter virtual player 2 level");
                break;
            case VIRTUAL3_LVL:
                System.out.println("Enter virtual player 3 level");
                break;
            default:
                System.out.println("Nothing to display");
        }
    }
}
