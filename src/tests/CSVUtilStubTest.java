package tests;

import forms.Line;
import forms.Shape;
import org.junit.jupiter.api.Test;
import utilities.CSVUtilStub;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVUtilStubTest {
    @Test
    void importFromPath() {
        CSVUtilStub stub = new CSVUtilStub();
         List<Shape> shapes = stub.importFromPath();
         assertEquals(shapes.size(), 3);
         assertEquals(shapes.get(0) instanceof Line, true);
    }
}