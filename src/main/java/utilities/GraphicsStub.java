package utilities;

public class GraphicsStub extends AdaptedGraphics {

    public int x;
    public int y;
    public int width;
    public int height;

    public int drawOvalCount;

    @Override
    public void fillOval(int x, int y, int width, int height) {
        drawOvalCount++;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
