package view.shape.up;
import javax.swing.*;
import java.awt.*;

/**
 * A class designed to draw the next card available to pick in the deck
 *
 * @author Daniel
 */
public class DrawNext extends JPanel {
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
        int width = BoardView.getUniqueInstance().getHand_1().getWidth(); //width of a panel on board
        int height = BoardView.getUniqueInstance().getHand_1().getHeight(); //Height of panel on a board
        super.paintComponent(g);
        switch (cardToDraw) {
            case "bhc" -> {
                g.setColor(Color.BLUE);
                g.drawOval((width - (width - 40)) / 2, (height - (height - 40)) / 2, width - 40, width - 40);
            }
            case "bhs" -> {
                g.setColor(Color.BLUE);
                g.drawRect((width - (width - 40)) / 2, (height - (height - 40)) / 2, width - 40, width - 40);
            }
            case "bht" -> {
                g.setColor(Color.BLUE);
                g.drawPolygon(new int[]{width/2, (width/3)*2,width/3}, new int[]{width/3,(width/3)*2,(width/3)*2}, 3); //Draws triangle
            }
            case "bsc" -> {
                g.setColor(Color.BLUE);
                g.fillOval((width - (width - 40)) / 2, (height - (height - 40)) / 2, width - 40, width - 40);
            }
            case "bss" -> {
                g.setColor(Color.BLUE);
                g.fillRect((width - (width - 40)) / 2, (height - (height - 40)) / 2, width - 40, width - 40);
            }
            case "bst" -> {
                g.setColor(Color.BLUE);
                g.fillPolygon(new int[]{width/2, (width/3)*2,width/3}, new int[]{width/3,(width/3)*2,(width/3)*2}, 3); //Draws triangle
            }
            case "rhc" -> {
                g.setColor(Color.RED);
                g.drawOval((width - (width - 40)) / 2, (height - (height - 40)) / 2, width - 40, width - 40);
            }
            case "rhs" -> {
                g.setColor(Color.RED);
                g.drawRect((width - (width - 40)) / 2, (height - (height - 40)) / 2, width - 40, width - 40);
            }
            case "rht" -> {
                g.setColor(Color.RED);
                g.drawPolygon(new int[]{width/2, (width/3)*2,width/3}, new int[]{width/3,(width/3)*2,(width/3)*2}, 3); //Draws triangle
            }
            case "rsc" -> {
                g.setColor(Color.RED);
                g.fillOval((width - (width - 40)) / 2, (height - (height - 40)) / 2, width - 40, width - 40);
            }
            case "rss" -> {
                g.setColor(Color.RED);
                g.fillRect((width - (width - 40)) / 2, (height - (height - 40)) / 2, width - 40, width - 40);
            }
            case "rst" -> {
                g.setColor(Color.RED);
                g.fillPolygon(new int[]{width/2, (width/3)*2,width/3}, new int[]{width/3,(width/3)*2,(width/3)*2}, 3); //Draws triangle
            }
            case "ghc" -> {
                g.setColor(Color.GREEN);
                g.drawOval((width - (width - 40)) / 2, (height - (height - 40)) / 2, width - 40, width - 40);
            }
            case "ghs" -> {
                g.setColor(Color.GREEN);
                g.drawRect((width - (width - 40)) / 2, (height - (height - 40)) / 2, width - 40, width - 40);
            }
            case "ght" -> {
                g.setColor(Color.GREEN);
                g.drawPolygon(new int[]{width/2, (width/3)*2,width/3}, new int[]{width/3,(width/3)*2,(width/3)*2}, 3); //Draws triangle
            }
            case "gsc" -> {
                g.setColor(Color.GREEN);
                g.fillOval((width - (width - 40)) / 2, (height - (height - 40)) / 2, width - 40, width - 40);
            }
            case "gss" -> {
                g.setColor(Color.GREEN);
                g.fillRect((width - (width - 40)) / 2, (height - (height - 40)) / 2, width - 40, width - 40);
            }
            case "gst" -> {
                g.setColor(Color.GREEN);
                g.fillPolygon(new int[]{width/2, (width/3)*2,width/3}, new int[]{width/3,(width/3)*2,(width/3)*2}, 3); //Draws triangle
            }
            default -> g.setColor(Color.WHITE);
        }
    }

    public void setCardToDraw(String cardToDraw){
        this.cardToDraw = cardToDraw;
    }
}
