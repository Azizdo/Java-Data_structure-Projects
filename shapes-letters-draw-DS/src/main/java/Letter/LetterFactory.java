package Letter;

import Point.Point2d;
import Shape.*;

import javax.swing.*;
import java.util.ArrayList;

public final class LetterFactory {
    final static Double maxHeight = 150.0;
    final static Double maxWidth = maxHeight / 2.5;
    final static Double halfMaxHeight = maxHeight / 2;
    final static Double halfMaxWidth = maxWidth / 2;
    final static Double stripeThickness = maxHeight / 8;
    final static Double halfStripeThickness = stripeThickness / 2;
    final static Double zero = 0.0;                     // Ajout d'une variable de positionnement
    final static Double quarterMaxHeight = maxHeight/4;    // Ajout d'une variable de positionnement
    final static Double quarterMaxwidth = maxWidth/4;    // Ajout d'une variable de positionnement

    /** TODO
     * Create the letter A graphically
     * @return BaseShape containing the letter A
     */
    public static BaseShape create_A()  {
        BaseShape letterA = new BaseShape();
        Rectangle rightBorder = new Rectangle(maxHeight, stripeThickness);
        Rectangle leftBorder  = new Rectangle(maxHeight, stripeThickness);
        Rectangle centerLine = new Rectangle(halfMaxWidth, stripeThickness);

        rightBorder.rotate(rightBorder.getCoords(), Math.toRadians(100));
        rightBorder.translate(rightBorder.getCoords(), new Point2d(-quarterMaxwidth, zero));

        leftBorder.rotate(leftBorder.getCoords(), Math.toRadians(80));
        leftBorder.translate(leftBorder.getCoords(), new Point2d(quarterMaxwidth, zero));

        centerLine.translate(centerLine.getCoords(), new Point2d(zero, stripeThickness));

        letterA.add(rightBorder);
        letterA.add(leftBorder);
        letterA.add(centerLine);

        return letterA;
    }

    /** TODO
     * Create the letter B graphically
     * @return BaseShape containing the letter B
     */
    public static BaseShape create_B() {
        BaseShape letterB = new BaseShape();
        Rectangle line = new Rectangle(stripeThickness, maxHeight);
        Circle topCircle = new Circle(halfMaxHeight);
        Circle topHole = new Circle(halfMaxHeight - stripeThickness);
        Circle bottomCircle = new Circle(halfMaxHeight);
        Circle bottomHole = new Circle(halfMaxHeight - stripeThickness);

        topCircle.removeAll(topHole.getCoords());
        topCircle.translate(topCircle.getCoords(), new Point2d(halfMaxWidth, -quarterMaxHeight));

        bottomCircle.removeAll(bottomHole.getCoords());
        bottomCircle.translate(bottomCircle.getCoords(), new Point2d(halfMaxWidth, quarterMaxHeight));

        letterB.add(line);
        letterB.add(topCircle);
        letterB.add(bottomCircle);

        return letterB;
    }

    /** TODO
     * Create the letter C graphically
     * @return BaseShape containing the letter C
     */
    public static BaseShape create_C() {
        BaseShape letterC = create_O();
        Rectangle borderHole = new Rectangle(halfMaxWidth, halfMaxWidth + stripeThickness);

        borderHole.translate(borderHole.getCoords(), new Point2d(stripeThickness, zero));
        letterC.remove(borderHole);

        return letterC;
    }

    /** TODO
     * Create the letter E graphically
     * @return BaseShape containing the letter E
     */
    public static BaseShape create_E() {
        BaseShape letterE = new BaseShape();
        Rectangle horizontalLine = new Rectangle(stripeThickness, maxHeight);
        Rectangle topLine = new Rectangle(maxWidth, stripeThickness);
        Rectangle middleLine = new Rectangle(maxWidth, stripeThickness);
        Rectangle bottomLine = new Rectangle(maxWidth, stripeThickness);

        horizontalLine.translate(horizontalLine.getCoords(), new Point2d(-halfMaxWidth, zero));
        topLine.translate(topLine.getCoords(), new Point2d(zero, -(halfMaxHeight - halfStripeThickness)));
        bottomLine.translate(bottomLine.getCoords(), new Point2d(zero, halfMaxHeight - halfStripeThickness));

        letterE.add(horizontalLine);
        letterE.add(topLine);
        letterE.add(middleLine);
        letterE.add(bottomLine);

        return letterE;
    }

    /** TODO
     * Create the letter H graphically
     * @return BaseShape containing the letter H
     */
    public static BaseShape create_H() {
        BaseShape letterH =   new BaseShape();
        Rectangle rightLine = new Rectangle(stripeThickness, maxHeight);
        Rectangle leftLine =  new Rectangle(stripeThickness, maxHeight);
        Rectangle middletLine = new Rectangle(maxWidth, stripeThickness);

        rightLine.translate(rightLine.getCoords(), new Point2d(halfMaxWidth, zero));
        leftLine.translate(leftLine.getCoords(), new Point2d(-halfMaxWidth, zero));

        letterH.add(rightLine);
        letterH.add(leftLine);
        letterH.add(middletLine);

        return letterH;
    }

    /** TODO
     * Create the letter N graphically
     * @return BaseShape containing the letter N
     */
    public static BaseShape create_N() {
        BaseShape letterN = new BaseShape();
        Rectangle rightLine = new Rectangle(stripeThickness, maxHeight);
        Rectangle leftLine = new Rectangle(stripeThickness, maxHeight);
        Rectangle crossLine = new Rectangle(maxHeight, stripeThickness);

        rightLine.translate(rightLine.getCoords(), new Point2d(-halfMaxWidth, zero));
        leftLine.translate(leftLine.getCoords(), new Point2d(halfMaxWidth, zero));
        crossLine.rotate(crossLine.getCoords(), Math.toRadians(68));

        letterN.add(rightLine);
        letterN.add(leftLine);
        letterN.add(crossLine);

        return letterN;
    }

    /** TODO
     * Create the letter O graphically
     * @return BaseShape containing the letter O
     */
    public static BaseShape create_O() {
        BaseShape letterO = new BaseShape();
        Ellipse border = new Ellipse(maxWidth,maxHeight);
        Ellipse hole = new Ellipse(halfMaxWidth, halfMaxHeight);

        border.remove(hole);

        return letterO.add(border);
    }

}
