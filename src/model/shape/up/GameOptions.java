package model.shape.up;

import observer.shape.up.Observable;
import view.shape.up.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
//Allow the player to setup the different var
public abstract class GameOptions extends Observable {


    public static int consolePutCarpetShape(){
        boolean correctNumber = false;
        while(!correctNumber){
            ConsoleGameView.display(ConsoleOutput.CARPET_SHAPE_CHOICE);
            try{
                int carpetShape = ConsoleUserInput.getUniqueInstance().nextInt();
                OptionData.carpetShape = carpetShape;
                ConsoleUserInput.getUniqueInstance().isCorrectInputBetweenMinMax(1,2,carpetShape,ConsoleOutput.SELECTED_SHAPE);
                correctNumber = true;
            }catch (SetupException e){
                e.getMessage();
                System.out.println("choose between 1 and 2");
            } catch (InputMismatchException e){
                System.out.println("You have to put a number.");
                System.exit(0);
            }
        }
        return OptionData.carpetShape;
    }

    public static ArrayList<String> consolePutNames(){
        for (int i = 0; i < OptionData.nbRealPlayer; i++) {
            if(i==0) ConsoleGameView.display(ConsoleOutput.PLAYER_NAME_CONFIG_O);
            if(i==1) ConsoleGameView.display(ConsoleOutput.PLAYER_NAME_CONFIG_T);
            if(i==2) ConsoleGameView.display(ConsoleOutput.PLAYER_NAME_CONFIG_TH);

            String name = ConsoleUserInput.getUniqueInstance().nextString();
            OptionData.playersName.add(name);
        }
        return OptionData.playersName;
    }

    public static int consolePutNbPlayer() {
        boolean correctNumber = false;
        while(!correctNumber){
            ConsoleGameView.display(ConsoleOutput.SETTING_NB_PLAYER);
            try{
                int nbPlayer = ConsoleUserInput.getUniqueInstance().nextInt();
                OptionData.nbPlayer = nbPlayer;
                ConsoleUserInput.getUniqueInstance().isCorrectInputBetweenMinMax(2,3,nbPlayer,ConsoleOutput.PLAYER_NB);
                correctNumber = true;
            }catch (SetupException e){
                e.getMessage();
                System.out.println("choose between 2 and 3");
            } catch (InputMismatchException e){
                System.out.println("You have to put a number.");
                ConsoleUserInput.getUniqueInstance().next();
            }
        }
        return OptionData.nbPlayer;
    }

    public static int consolePutNbRealPlayer(int nbPlayer){
        OptionData.nbPlayer = nbPlayer;
        boolean correctNumber = false;
        while (!correctNumber){
            ConsoleGameView.display(ConsoleOutput.REAL_PLAYER);

            try{
                int nbRealPlayer = ConsoleUserInput.getUniqueInstance().nextInt();
                OptionData.nbRealPlayer = nbRealPlayer;
                ConsoleUserInput.getUniqueInstance().isCorrectInputBetweenMinMax(0,nbPlayer,nbRealPlayer,ConsoleOutput.STANDARD);
                correctNumber = true;
            } catch (SetupException e) {
                e.getMessage();
                System.out.println("number of real player cannot be superior to number of players");
            } catch (InputMismatchException e) {
                System.out.println("You have to put a number.");
                System.out.println(0);
            }
        }

        return OptionData.nbRealPlayer;
    }


    public static int consolePutNbVirtualPlayer(){
        int nbVirtualPlayer = OptionData.nbPlayer - OptionData.nbRealPlayer;
        OptionData.nbVirtualPlayer = nbVirtualPlayer;
        return nbVirtualPlayer;
    }

    public static int[] consolePutVirtualPlayerLevel(){
        if(OptionData.nbVirtualPlayer !=0) {
            ConsoleGameView.display(ConsoleOutput.LEVEL_VIRTUAL);
        }
        for (int i = 0; i < OptionData.nbVirtualPlayer; i++) {
            if(i == 0) ConsoleGameView.display(ConsoleOutput.VIRTUAL1_LVL);
            if(i == 1) ConsoleGameView.display(ConsoleOutput.VIRTUAL2_LVL);
            if(i == 2) ConsoleGameView.display(ConsoleOutput.VIRTUAL3_LVL);
            boolean correctNumber = false;
            while (!correctNumber){
                try{
                    int lvl = ConsoleUserInput.getUniqueInstance().nextInt();
                    OptionData.levels[i] = lvl;
                    ConsoleUserInput.getUniqueInstance().isCorrectInputBetweenMinMax(1,3,lvl,ConsoleOutput.STANDARD);
                    correctNumber = true;
                } catch (SetupException e) {
                    e.getMessage();
                    System.out.println("Enter 1, 2 or 3 please. Retry!");
                } catch (InputMismatchException e) {
                    System.out.println("You have to put a number.");
                    System.out.println(0);
                }
            }
        }
        return OptionData.levels;
    }

    public static int chooseVariant(){
        boolean correctNumber = false;
        while (!correctNumber){
            ConsoleGameView.display(ConsoleOutput.VARIANT);
            try{
                int nbVariant = ConsoleUserInput.getUniqueInstance().nextInt();
                OptionData.variant = nbVariant;
                ConsoleUserInput.getUniqueInstance().isCorrectInputBetweenMinMax(1,2,nbVariant, ConsoleOutput.SELECT_VAR);
                correctNumber = true;
            }catch (SetupException e){
                e.getMessage();
                System.out.println("Please choose wisely between 1 and 3 !");
            }catch (InputMismatchException e){
                System.out.println("You have to put a number.");
            }
        }
        return OptionData.variant;
    }

    public static int selectionOptionMenu(){
        ConsoleGameView.display(ConsoleOutput.PLAY_VAR);
        return ConsoleUserInput.getUniqueInstance().nextInt();
    }

    public static void setup(){

        OptionData.variant = chooseVariant();
        OptionData.carpetShape = consolePutCarpetShape();
        OptionData.nbPlayer = consolePutNbPlayer();
        OptionData.nbRealPlayer = consolePutNbRealPlayer(OptionData.nbPlayer);
        OptionData.nbVirtualPlayer = consolePutNbVirtualPlayer();
        OptionData.levels = consolePutVirtualPlayerLevel();
        OptionData.playersName = consolePutNames();
        ConsoleGameView.display(ConsoleOutput.CONFIG_DONE);

        boolean startGame = false;
        while (!startGame){
            int playerChoice = selectionOptionMenu();
            switch (playerChoice) {
                case 2 -> {
                    ConsoleGameView.display(ConsoleOutput.GAME_OVER);
                    System.exit(0);
                }
                case 1 -> {
                    startGame = true;
                    ConsoleGameView.display(ConsoleOutput.NEW_GAME);
                }
                default -> System.out.println("Your value is not correct.");
            }
        }
    }
    public static void setup(int variant,int carpetShape, int nbPlayer, int nbRealPlayer, int[] levels, ArrayList<String> names){
        OptionData.variant = variant;
        OptionData.carpetShape = carpetShape;
        OptionData.nbPlayer = nbPlayer;
        OptionData.nbRealPlayer = nbRealPlayer;
        OptionData.nbVirtualPlayer = (nbPlayer - nbRealPlayer);
        OptionData.levels = levels;
        OptionData.playersName = names;
        ConsoleGameView.display(ConsoleOutput.CONFIG_DONE);
    }
}
// TODO: 22/12/2020 Penser à reinitialiser les options data pour vider les tableaux array list et autres à la fin d'une partie 