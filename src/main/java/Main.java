import forms.*;
import sun.security.provider.SHA;

import javax.script.ScriptContext;
import java.util.ArrayList;

public class Main {

    public static final Display DISPLAY = new Display();

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Rectangle(0,0,100,100));
        shapes.add(new RoundedRectangle(300,300,100,100,20,20));
        shapes.add(new Line(400,400,500,500));
        shapes.add(new Circle(400,400,100));

        Rectangle r = new Rectangle(200,200,200,200);
        Circle c = new Circle(200,200,100);
        ComplexShape cs = new ComplexShape(200,200);
        cs.addShape(r);
        cs.addShape(c);

        shapes.add(cs);



        Drawing drawing = new Drawing(shapes);

        DISPLAY.setDrawing(drawing);
        DISPLAY.createAndAddDrawingPanel();

        Thread.sleep(2000);
//
//        for(Shape s : shapes){
//            s.move(50,50);
//        }

//        cs.move(100,100);
        DISPLAY.repaint();
    }
}
