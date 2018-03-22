package utilities;

import forms.Shape;
import sun.security.provider.SHA;

import java.util.List;

public class CaptureUtil {

    public static Shape getShape(List<Shape> shapes, int x, int y){
        for(Shape s: shapes){
            if(s.containsPosition(x,y)){
                return  s;
            }
        }
        return null;
    }
}
