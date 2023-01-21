package Interface;

import Point.Point2d;

import java.util.Collection;

public interface Translate {
    public Collection<Point2d> translate(Collection<Point2d> coords, Point2d translateVector);
}
