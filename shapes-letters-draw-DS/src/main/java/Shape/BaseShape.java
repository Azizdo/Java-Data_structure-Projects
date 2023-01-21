package Shape;

import Interface.Transform;
import Point.Point2d;

import java.util.*;
import java.util.stream.Collectors;

public class BaseShape extends Transform implements Cloneable {
    private final Collection<Point2d> coords;

    /** TODO
     * Create a BaseShape with empty coordinades
     */
    public BaseShape() { this.coords = new ArrayList<>(); }

    /** TODO
     * Create a BaseShape from a collection of 2D points
     * @param coords The collection of 2D points
     */
    public BaseShape(Collection<Point2d> coords) {
       this();
       for (Point2d coord : coords){ this.coords.add(new Point2d(coord.X(), coord.Y())); }
    }

    /** TODO
     * Add a deep copy of the 2D point to the Shape
     * @param coord 2D point to add
     * @return Updated BaseShape
     */
    public BaseShape add(Point2d coord) {
        Point2d newCoord = new Point2d(coord.X(), coord.Y());
        coords.add(newCoord);
        return this;
    }

    /** TODO
     * Add a deep copy of the 2D points of the shape to the current shape
     * @param shape Shape to add to the current shape
     * @return Updated BaseShape
     */
    public BaseShape add(BaseShape shape) {
        BaseShape newShape = new BaseShape(shape.coords);
        coords.addAll(newShape.coords);
        return this;
    }

    /** TODO
     * Add a deep copy of the points in the collection to the shape
     * @param coords Collections of point to add
     * @return Updated BaseShape
     */
    public BaseShape addAll(Collection<Point2d> coords) {
        for (Point2d point2d : coords)
        {
            Point2d newPoint2d = new Point2d(point2d.X(), point2d.Y());
            this.coords.add(newPoint2d);
        }
        return this;
    }

    /** TODO
     * Remove the 2D point from the shape
     * @param coord Point to remove
     * @return Updated BaseShape
     */
    public BaseShape remove(Point2d coord) {
        coords.remove(coord);
        return this;
    }

    /** TODO
     * Remove the 2D points in the shape from the current shape
     * @param shape Shape containing the points to remove
     * @return Updated BaseShape
     */
    public BaseShape remove(BaseShape shape) {
        for (Point2d point2dToRemove : shape.coords) { coords.remove(point2dToRemove); }
        return this;
    }

    /** TODO
     * Remove the 2D points in the collection from the current shape
     * @param coords Collection of 2D points to remove
     * @return Updated BaseShape
     */
    public BaseShape removeAll(Collection<Point2d> coords) {
        for (Point2d point2dToRemove : coords) { this.coords.remove(point2dToRemove); }
        return this;
    }

    /** TODO
     *  Replace all the coords in a shape with new ones
     * @param newCoords new coords to replace the old one
     * @return Updated BaseShape
     * */
    public BaseShape replaceAll(Collection<Point2d> newCoords) {
        coords.removeAll(coords);
        coords.addAll(newCoords);
        return this;
    }

    /** TODO
     * Return a shallow copy of the coordinates of the shape
     * @return Shallow copy of all coordinates contained by this BaseShape
     */
    public Collection<Point2d> getCoords() { return new ArrayList<>(coords); }

    /** TODO
     * Create and return a deep copy of the coordinates of the shape
     * @return Deep copy of all coordinates contained by this BaseShape
     */
    public Collection<Point2d> cloneCoords() {
        Collection<Point2d> clone = new ArrayList<>();
        for (Point2d point2d : coords){
            Point2d point2dClone = new Point2d(point2d.X(), point2d.Y());
            clone.add(point2dClone); }
        return clone; }

    /** TODO
     * @return Maximum X coordinate of the shape
     */
    public Double getMaxX() {
        double maxX = -Double.MAX_VALUE;
        for (Point2d point2d : coords){
            if (maxX < point2d.X()) { maxX = point2d.X(); }
        }
        return maxX;
    }

    /** TODO
     * @return Maximum Y coordinate of the shape
     */
    public Double getMaxY() {
        double maxY = -Double.MAX_VALUE;
        for (Point2d point2d : coords){
            if (maxY < point2d.Y()) { maxY = point2d.Y(); }
        }
        return maxY;
    }

    /** TODO
     * @return 2D Point containing the maximum X and Y coordinates of the shape
     */
    public Point2d getMaxCoord() { return new Point2d(getMaxX(), getMaxY()); }

    /** TODO
     * @return Minimum X coordinate of the shape
     */
    public Double getMinX() {
        double minX = Double.MAX_VALUE;
        for (Point2d point2d : coords){
            if (minX > point2d.X()) { minX = point2d.X(); }
        }
        return minX;
    }

    /** TODO
     * @return Minimum Y coordinate of the shape
     */
    public Double getMinY() {
        double minY = Double.MAX_VALUE;
        for (Point2d point2d : coords){
            if (minY > point2d.Y()) { minY = point2d.Y(); }
        }
        return minY;
    }

    /** TODO
     * @return 2D point containing the minimum X and Y coordinate of the shape
     */
    public Point2d getMinCoord() { return new Point2d(getMinX(), getMinY()); }

    /** TODO
     * @return Deep copy of the current shape
     */
    public BaseShape clone() { return new BaseShape(coords); }
}
