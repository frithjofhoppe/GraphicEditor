import forms.Circle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.GraphicsStub;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircleTest {

    static GraphicsStub graphics;

    @BeforeEach
    private void initialize(){
        graphics = new GraphicsStub();
    }

    @Test
    void draw() {
        Circle c = new Circle(10,10,5);
        c.draw(graphics);
        assertEquals(graphics.drawOvalCount, 1);
        assertEquals(graphics.height, 10);
        assertEquals(graphics.width, 10);
        assertEquals(graphics.x, 10);
        assertEquals(graphics.y, 10);
    }
}