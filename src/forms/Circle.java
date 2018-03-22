package forms;

import java.awt.*;

public class Circle extends Shape{
    int radius;

    public Circle(int posX1, int posY1, int radius) {
        super(posX1, posY1);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawOval(super.posX1, super.posY1, radius, radius);
    }

    @Override
    public boolean containsPosition(int x, int y) {
        if (ShapeUtil.getDistance(posX1 +radius, posY1 +radius , x, y) <= radius) return true ;
        return false;
    }
}
