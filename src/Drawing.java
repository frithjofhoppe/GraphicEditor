import forms.Rectanlge;
import forms.Shape;
import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
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
    }

    public void drawShapes(Graphics g) {
           for(Shape a : shapes){
               a.draw(g);
           }
    }
}
