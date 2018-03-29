package forms;

import sun.plugin.dom.css.Rect;

import java.awt.*;

public class Rectangle extends Shape {
    protected int length;
    protected int width;

    public Rectangle(String csv) {
        super(csv);
        String[] split = csv.split(",");
        length = Integer.parseInt(split[3]);
        width = Integer.parseInt(split[4]);
    }

    public Rectangle(int posX1, int posY1, int length, int width) {
        super(posX1, posY1);
        this.length = length;
        this.width = width;
    }

    @Override
    public void draw(Graphics graphics) {
//        graphics.setColor(Color.red);
        graphics.fillRect(super.posX1, super.posY1, width, length);
//        graphics.drawRect(super.posX1, super.posY1, width, length);
    }

    @Override
    public boolean containsPosition(int x, int y) {
        if (x <= posX1 + length &&
                x >= posX1 &&
                y <= posY1 + width &&
                y >= posY1) {
            return true;
        }
        return false;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String getShapeAsCsv() {
        return "Rectangle," + super.getPosX1() + "," + super.getPosY1() + "," + length + "," + width;
    }
}
