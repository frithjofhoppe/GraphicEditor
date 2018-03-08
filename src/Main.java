import forms.Rectangle;
import forms.RoundedRectangle;
import forms.Shape;

import java.util.ArrayList;

public class Main {

    public static final Display DISPLAY = new Display();

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Rectangle(50,50,100,100));
        shapes.add(new RoundedRectangle(300,300,100,100,20,20));

        Drawing drawing = new Drawing(shapes);

        DISPLAY.setDrawing(drawing);
        DISPLAY.createAndAddDrawingPanel();

        Thread.sleep(2000);

        for(Shape s : shapes){
            s.move(50,50);
        }

        DISPLAY.repaint();
    }
}
