package forms;

import java.awt.*;

abstract public class Shape {
    public int posX1;
    public int posY1;

    public Shape(int posX1, int posY1) {
        this.posX1 = posX1;
        this.posY1 = posY1;
    }

    public Shape(String csv){
        String[] split = csv.split(",");
        posX1 = Integer.parseInt(split[1]);
        posY1 = Integer.parseInt(split[2]);
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

    public abstract String getShapeAsCsv();
}
