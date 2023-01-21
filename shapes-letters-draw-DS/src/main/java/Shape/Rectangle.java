package Shape;

import Point.Point2d;

import java.util.Collection;

public class Rectangle extends BaseShape {
    /** TODO
     * Create a filled rectangle centered on (0, 0)
     * @param width Width of the rectangle
     * @param height Height of the rectangle
     */
    public Rectangle(Double width, Double height) {
        for (double y = -height/2; y < height/2; y++) {
            for (double x = -width/2; x < width/2; x++) {
                this.add(new Point2d(x, y));
            }
        }
    }

    /** TODO
     * Create a filled rectangle centered on (0, 0)
     * @param dimensions 2D point containing the width and height of the rectangle
     */
    public Rectangle(Point2d dimensions) {
        for (double y = -dimensions.Y()/2; y < dimensions.Y()/2; y++) {
            for (double x = -dimensions.X()/2; x < dimensions.X()/2; x++) {
                this.add(new Point2d(x, y));
            }
        }
    }

    /**
     * Create a rectangle from a given collection of Points
     * @param coords The collection of 2D points
     */
    private Rectangle(Collection<Point2d> coords) { this.addAll(coords); }

    /** TODO
     * @return Deep copy of the rectangle
     */
    @Override
    public Rectangle clone() { return new Rectangle(this.getCoords()); }
}
