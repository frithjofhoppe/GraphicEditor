import forms.Circle;
import forms.Rectangle;
import forms.Shape;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import sun.security.provider.SHA;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DrawingTest {

    static Graphics g = mock(Graphics.class);

    @Test
    void drawShapes() {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(10, 10, 5));
        shapes.add(new Rectangle(20, 20, 5, 5));

        Drawing d = new Drawing(shapes);
        d.setGraphics(g);
        d.drawShapes();
        InOrder order = inOrder(g);
        order.verify(g).fillOval(10,10,10,10);
        order.verify(g).fillRect(20,20,5,5);
    }
}