package view.shape.up;

import javax.swing.*;
import java.awt.*;
/**
 * A class which purpose is to draw figures  inside of the gameboard tile
 *
 * @author Daniel
 */
public class Draw extends JPanel {
    private String cardToDraw;
    /**
     * It is the main method of the class since when it is called it also call paintComponent method to paint a figure
     */
    public void drawing(){
        repaint();
    }
    /**
     * Method used to draw our different figure
     * @param g  argument that indicates figures to draw with specific methods
     */
    public void paintComponent(Graphics g){
        int width = BoardView.getUniqueInstance().getWidth(); //width of a panel on board
        int height = BoardView.getUniqueInstance().getHeight(); //Height of panel on a board
        super.paintComponent(g);
        switch (cardToDraw) {
            case "bhc" -> {
                g.setColor(Color.BLUE);
                g.drawOval((width - (23)) / 2, (height - (height - 40)) / 2, 23, 23);
            }
            case "bhs" -> {
                g.setColor(Color.BLUE);
                g.drawRect((width - (23)) / 2, (height - (height - 40)) / 2, 23, 23);
            }
            case "bht" -> {
                g.setColor(Color.BLUE);
                g.drawPolygon(new int[]{width/2, (width/3)*2,width/3}, new int[]{width/3,(width/3)*2,(width/3)*2}, 3);
            }
            case "bsc" -> {
                g.setColor(Color.BLUE);
                g.fillOval((width - (23)) / 2, (height - (height - 40)) / 2, 23, 23);
            }
            case "bss" -> {
                g.setColor(Color.BLUE);
                g.fillRect((width - (23)) / 2, (height - (height - 40)) / 2, 23, 23);
            }
            case "bst" -> {
                g.setColor(Color.BLUE);
                g.fillPolygon(new int[]{width/2, (width/3)*2,width/3}, new int[]{width/3,(width/3)*2,(width/3)*2}, 3);            }
            case "rhc" -> {
                g.setColor(Color.RED);
                g.drawOval((width - (23)) / 2, (height - (height - 40)) / 2, 23, 23);
            }
            case "rhs" -> {
                g.setColor(Color.RED);
                g.drawRect((width - (23)) / 2, (height - (height - 40)) / 2, 23, 23);
            }
            case "rht" -> {
                g.setColor(Color.RED);
                g.drawPolygon(new int[]{width/2, (width/3)*2,width/3}, new int[]{width/3,(width/3)*2,(width/3)*2}, 3);            }
            case "rsc" -> {
                g.setColor(Color.RED);
                g.fillOval((width - (23)) / 2, (height - (height - 40)) / 2, 23, 23);
            }
            case "rss" -> {
                g.setColor(Color.RED);
                g.fillRect((width - (23)) / 2, (height - (height - 40)) / 2, 23, 23);
            }
            case "rst" -> {
                g.setColor(Color.RED);
                g.fillPolygon(new int[]{width/2, (width/3)*2,width/3}, new int[]{width/3,(width/3)*2,(width/3)*2}, 3);            }
            case "ghc" -> {
                g.setColor(Color.GREEN);
                g.drawOval((width - (23)) / 2, (height - (height - 40)) / 2, 23, 23);
            }
            case "ghs" -> {
                g.setColor(Color.GREEN);
                g.drawRect((width - (23)) / 2, (height - (height - 40)) / 2, 23, 23);
            }
            case "ght" -> {
                g.setColor(Color.GREEN);
                g.drawPolygon(new int[]{width/2, (width/3)*2,width/3}, new int[]{width/3,(width/3)*2,(width/3)*2}, 3);
            }
            case "gsc" -> {
                g.setColor(Color.GREEN);
                g.fillOval((width - (23)) / 2, (height - (height - 40)) / 2, 23, 23);
            }
            case "gss" -> {
                g.setColor(Color.GREEN);
                g.fillRect((width - (23)) / 2, (height - (height - 40)) / 2, 23, 23);
            }
            case "gst" -> {
                g.setColor(Color.GREEN);
                g.fillPolygon(new int[]{width/2, (width/3)*2,width/3}, new int[]{width/3,(width/3)*2,(width/3)*2}, 3); //Draws triangle
            }
            case "erase" ->{
                g.setColor(Color.WHITE);
                g.fillRect((width - (23)) / 2, (height - (height - 40)) / 2, 23, 23);
            }


        }
    }

    public void setCardToDraw(String cardToDraw){
        this.cardToDraw = cardToDraw;
    }
}
