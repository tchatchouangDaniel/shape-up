package model.shape.up;

import java.util.ArrayList;
/**
 * This is where all the data concerning the options of the game is stored.
 * It allows a good communication between the <code>View</code> package and the <code>Model</code> package.
 *
 * @author Daniel
 * @see GameOptions
 */
public class OptionData {
    protected static int nbPlayer;
    protected static int nbRealPlayer;
    protected static int nbVirtualPlayer;
    protected static int variant;
    protected static int carpetShape;
    protected static ArrayList<String> playersName = new ArrayList<>();
    protected static String[] vPlayerName = {"Virtual Player 1", "Virtual Player 2", "Virtual Player 3"};
    protected static int[] levels = new int[3];// {level vplayer1, level vplayer2, level vplayer3} //

    public static int getNbPlayer() {
        return nbPlayer;
    }

    /**
     * Sets the number of players for the next game according to the int value specified in the parameters.
     *
     * @param nbPlayer Number of players for the next game.
     */
    public static void setNbPlayer(int nbPlayer) {
        OptionData.nbPlayer = nbPlayer;
    }

    /**
     * get the number of real player
     * @return nbRealPlayer
     */
    public static int getNbRealPlayer() {
        return nbRealPlayer;
    }

    /**
     * Sets the number of real players for the next game according to the int value specified in the parameters.
     *
     * @param nbRealPlayer Number of real players for the next game.
     */
    public static void setNbRealPlayer(int nbRealPlayer) {
        OptionData.nbRealPlayer = nbRealPlayer;
    }

    /**
     * get the number of virtual players
     * @return nbVirtualPlayer
     */
    public static int getNbVirtualPlayer() {
        nbVirtualPlayer = nbPlayer - nbRealPlayer;
        return nbVirtualPlayer;
    }

    /**
     * Sets the number of real players for the next game according to the int value specified in the parameters.
     *
     * @param nbVirtualPlayer Number of real players for the next game.
     */
    public static void setNbVirtualPlayer(int nbVirtualPlayer) {
        OptionData.nbVirtualPlayer = nbVirtualPlayer;
    }

    /**
     * gets the variants chosen by the player
     * @return variant
     */
    public static int getVariant() {
        return variant;
    }

    /**
     * Sets the number of the variant chosen by the user, specified in the parameters.
     *
     * @param variant Number of the variant
     */
    public static void setVariant(int variant) {
        OptionData.variant = variant;
    }

    /**
     * gets the number of the carpet shape chosen by the user, specified in the parameters.
     *
     * @return carpetShape
     */
    public static int getCarpetShape() {
        return carpetShape;
    }

    /**
     * Sets the number of the carpetShape chosen by the user, specified in the parameters.
     *
     * @param carpetShape Number of the carpetShape
     */
    public static void setCarpetShape(int carpetShape) {
        OptionData.carpetShape = carpetShape;
    }

    /**
     * return an arry containing the levels of virtual players
     * @return levels
     */
    public static int[] getLevels() {
        return levels;
    }

    /**
     * Return an arrayList containign all the players name
     * @return playersName
     */
    public static ArrayList<String> getPlayersName() {
        return playersName;
    }
}
