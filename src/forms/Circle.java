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
}
