import forms.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Drawing extends JFrame {

    public ArrayList<Shape> shapes;

    public Drawing(ArrayList<Shape> shapes){
        this.shapes = shapes;
    }

    public void add(Shape figur) {
        shapes.add(figur);
        repaint();
    }

    /**
     * Löscht alle Figuren und löst die Auffrischung des Fensterinhaltes aus.
     */
    public void deleteAll() {
        shapes.clear();
        repaint();
        System.out.println("CLEARED");
    }

    public void drawShapes(Graphics g) {
           for(Shape a : shapes){
               a.draw(g);
           }
    }

    public Shape getShapeAtPosition(int x, int y){
        for(Shape s: shapes){
            if(s.containsPosition(x,y)){
                return s;
            }
        }
        return null;
    }
}
