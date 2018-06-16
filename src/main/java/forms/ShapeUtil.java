package forms;

import java.awt.geom.Point2D;

public class ShapeUtil {
    public static double getDistance(double x1, double y1, double x2, double y2){
        return Point2D.distance(x1,y1,x2,y2);
    }
}
