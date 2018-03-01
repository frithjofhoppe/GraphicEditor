package forms;

import java.awt.*;

public class Rectanlge extends Shape{
    int length;
    int width;

    public Rectanlge(int posX1, int posY1, int length, int width) {
        super(posX1, posY1);
        this.length = length;
        this.width = width;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawRect(super.posX1, super.posY1, width, length);
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
