package calcul.score.shape.up;

/**
 * This class is a part of the visitor design pattern. I hold the definition of the method used by a visited object to accept a visitor;
 * @author Daniel
 */
public interface GamePlateform {
    /**
     * retourne une reference à la methode de visite
     * @param visitor the reference to the class object that implement our visitor methods
     * @return the reference to our visitor
     */
    public int[] accept(CalcScoreVisitor visitor);
}
