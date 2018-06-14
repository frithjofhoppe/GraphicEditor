package tests;

import forms.Line;
import forms.Rectangle;
import forms.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.plugin.dom.css.Rect;
import utilities.CSVUtilStub;
import utilities.ICSVUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVUtilStubTest {

    static ICSVUtil stubCSV;

    @BeforeEach
    private void initialize() {
        stubCSV = new CSVUtilStub();
    }

    @Test
    void importFromPath_validShapes_returns() {
        List<Shape> shapes = stubCSV.importFromPath();

        assertEquals(shapes.size(), 3);
        assertEquals(shapes.get(0).posX1, 165);
        assertEquals(shapes.get(0).posY1, 117);
        assertEquals(shapes.get(0) instanceof Line, true);
        assertEquals(((Line)shapes.get(0)).getPosX2(), 339);
        assertEquals(((Line)shapes.get(0)).getPosY2(), 244);

        assertEquals(shapes.get(1).posX1, 165);
        assertEquals(shapes.get(1).posY1, 117);
        assertEquals(shapes.get(1) instanceof Line, true);
        assertEquals(((Line)shapes.get(1)).getPosX2(), 339);
        assertEquals(((Line)shapes.get(1)).getPosY2(), 244);

        assertEquals(shapes.get(2).posX1, 176);
        assertEquals(shapes.get(2).posY1, 95);
        assertEquals(shapes.get(2) instanceof Rectangle, true);
        assertEquals(((Rectangle)shapes.get(2)).getLength(), 85);
        assertEquals(((Rectangle)shapes.get(2)).getWidth(), 419);
    }
}