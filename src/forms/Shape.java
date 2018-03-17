package forms;

import java.awt.*;

abstract public class Shape {
    protected int posX1;
    protected int posY1;

    public Shape(int posX1, int posY1) {
        this.posX1 = posX1;
        this.posY1 = posY1;
    }

    public int getPosX1() {
        return posX1;
    }

    public int getPosY1() {
        return posY1;
    }

    public abstract void draw(Graphics graphics);

    public abstract boolean containsPosition(int x, int y);

    public void move(int deltaX, int deltaY){
        posX1 += deltaX;
        posY1 += deltaY;
    }
}
