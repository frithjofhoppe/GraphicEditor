import forms.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Drawing extends JFrame {

    public ArrayList<Shape> shapes;
    private Graphics graphics;

    public Drawing(ArrayList<Shape> shapes){
        this.shapes = shapes;
    }

    public void add(Shape figur) {
        shapes.add(figur);
        redraw();
    }

    public void redraw(){
//        repaint();
        paintAll(graphics);
    }

    public void remove(Shape shape){
        shapes.remove(shape);
        redraw();
    }

    /**
     * Löscht alle Figuren und löst die Auffrischung des Fensterinhaltes aus.
     */
    public void deleteAll() {
        shapes.clear();
        redraw();
        System.out.println("CLEARED");
    }

    public void drawShapes() {
           for(Shape a : shapes){
               a.draw(graphics);
           }
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
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
