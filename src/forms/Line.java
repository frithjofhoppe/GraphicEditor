package forms;

import java.awt.*;

public class Line extends Shape{
    private int posX2;
    private int posY2;

    public Line(int posX1, int posY1, int posX2, int posY2) {
        super(posX1, posY1);
        this.posX2 = posX2;
        this.posY2 = posY2;
    }

    public int getPosX2() {
        return posX2;
    }

    public int getPosY2() {
        return posY2;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawLine(super.posX1, super.posY1, posX2, posY2);
    }

    @Override
    public boolean containsPosition(int x, int y) {
        return false;
    }

    @Override
    public void move(int deltaX, int deltaY) {
        super.move(deltaX, deltaY);
        posX2 += deltaX;
        posY2 += deltaY;
    }
}
