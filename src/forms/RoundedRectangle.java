package forms;

import java.awt.*;

public class RoundedRectangle extends Rectangle {
    int arcWidth;
    int arcHeight;

    public RoundedRectangle(int posX1, int posY1, int length, int width, int arcWidth, int arcHeight) {
        super(posX1, posY1, length, width);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    public boolean containsPosition(int x, int y) {
        return super.containsPosition(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawRoundRect(posX1, posY1, width, length, arcWidth, arcHeight);
    }
}
