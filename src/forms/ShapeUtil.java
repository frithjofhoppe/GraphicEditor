package forms;

public class ShapeUtil {
    public static double getDistance(int x1, int y1, int x2, int y2){
        System.out.println(Math.sqrt((x1 - x2) *  (x1 - x2) + (y1 - y2) *  (y1 - y2)));
       return Math.sqrt((x1 - x2) *  (x1 - x2) + (y1 - y2) *  (y1 - y2));
    }
}
