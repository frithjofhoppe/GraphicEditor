import com.sun.org.apache.regexp.internal.RE;
import forms.Circle;
import forms.Rectangle;
import forms.Shape;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import sun.plugin.dom.css.Rect;
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

        assertEquals(d.shapes.get(0) instanceof Circle, true);
        assertEquals(((Circle)d.shapes.get(0)).posX1, 10);
        assertEquals(((Circle)d.shapes.get(0)).posY1, 10);
        assertEquals(((Circle)d.shapes.get(0)).getRadius(), 5);

        assertEquals(d.shapes.get(1) instanceof Rectangle, true);
        assertEquals(((Rectangle)d.shapes.get(1)).posX1, 20);
        assertEquals(((Rectangle)d.shapes.get(1)).posY1, 20);
        assertEquals(((Rectangle)d.shapes.get(1)).getLength(), 5);
        assertEquals(((Rectangle)d.shapes.get(1)).getWidth(), 5);

        d.drawShapes();
        InOrder order = inOrder(g);
        order.verify(g).fillOval(10,10,10,10);
        order.verify(g).fillRect(20,20,5,5);
    }
}