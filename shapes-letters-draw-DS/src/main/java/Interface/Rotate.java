package Interface;

import Point.Point2d;

import java.util.Collection;

public interface Rotate {
    public Collection<Point2d> rotate(Collection<Point2d> coords, Double angle);
}
