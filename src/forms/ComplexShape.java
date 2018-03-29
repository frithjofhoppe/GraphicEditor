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
            toReturn+=shape.getShapeAsCsv();
        }
        return  toReturn;
    }

    @Override
    public void move(int deltaX, int deltaY) {
        shapes.forEach(item -> item.move(deltaX, deltaY));
    }
}
