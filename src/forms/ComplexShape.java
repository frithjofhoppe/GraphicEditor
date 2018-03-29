package forms;

import sun.security.provider.SHA;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ComplexShape extends Shape {

    ArrayList<Shape> shapes = new ArrayList<>();

    public ComplexShape(int posX1, int posY1) {
        super(posX1, posY1);
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public int getPosX1() {
        int smallest = 1000000000;
        for(Shape s: shapes){
            if(s.getPosX1() < smallest){
                smallest = s.getPosX1();
            }
        }
        return smallest;
    }

    @Override
    public int getPosY1() {
        int largest = 1000000000;
        for(Shape s: shapes){
            if(s.getPosY1() < largest){
                largest = s.getPosY1();
            }
        }
        return largest;
    }

    @Override
    public void draw(Graphics graphics) {
        shapes.forEach(item -> item.draw(graphics));
    }

    @Override
    public boolean containsPosition(int x, int y) {
        boolean contains = true;
        for(Shape item: shapes){
            if (!item.containsPosition(x, y)) {
                contains = false;
            }
        }
        return contains;
    }

    @Override
    public String getShapeAsCsv() {
        String toReturn =  "";
        for (Shape shape : shapes) {
            toReturn+= "x_" + shape.getShapeAsCsv() + "\r\n";
        }
        return  toReturn.substring(0, toReturn.length()-3);
    }

    @Override
    public void move(int deltaX, int deltaY) {
        shapes.forEach(item -> item.move(deltaX, deltaY));
    }
}
