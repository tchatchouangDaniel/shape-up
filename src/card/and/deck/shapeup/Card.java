package card.and.deck.shapeup;

/**
 * The class for a card in the game
 * @author Daniel
 */
public class Card {
    protected final Color color;
    protected final Fill fill;
    protected final Shape shape;
    protected String id;
    protected String imagePath;

    /**
     * The constructor of a card create a card using it color, it fill and its shape
     * The id is set automatically
     * @param color color of the card we build
     * @param fill fill of the card we build
     * @param shape shape of the card we build
     */
    public Card(final Color color,final Fill fill,final Shape shape) {
        this.color = color;
        this.fill = fill;
        this.shape = shape;
//        id = this.color + "_" + this.fill + "_" + this.shape;
        if(color == Color.RED){
            id = "r";
        }else if(color == Color.BLUE){
            id = "b";
        }else if(color == Color.GREEN){
            id = "g";
        }else if(color == Color.NULL){
            id = "-";
        }

        if(fill == Fill.HOLLOW){
            id += "h";
        }else if(fill == Fill.SOLID){
            id += "s";
        }else if(fill == Fill.NULL){
            id += "-";
        }

        if(shape == Shape.CIRCLE){
            id += "c";
        }else if(shape == Shape.SQUARE){
            id += "s";
        }else if(shape == Shape.TRIANGLE){
            id += "t";
        }else if(shape == Shape.NULL){
            id += "-";
        }
    }

    /**
     * Return card id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Return card color
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Return card fill
     * @return fill
     */
    public Fill getFill() {
        return fill;
    }

    /**
     * Return card form
     * @return form
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * redefinition of toString method to show a card with color fill and name
     * @return String that match a card characteristics
     */
    public String toString() {
        return color + "_" + fill + "_" + shape;
    }
}
