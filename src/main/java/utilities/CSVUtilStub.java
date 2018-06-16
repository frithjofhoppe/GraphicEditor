package utilities;

import forms.*;

import java.util.ArrayList;
import java.util.List;

public class CSVUtilStub implements ICSVUtil {
    @Override
    public List<Shape> importFromPath() {
        List<String> rawShapes = new ArrayList<>();
        rawShapes.add("Line,165,117,339,244");
        rawShapes.add("Line,165,117,339,244");
        rawShapes.add("Rectangle,176,95,85,419");
        return generateShapes(rawShapes);
    }

    private List<Shape> generateShapes(List<String> rawShapes) {
        ArrayList<Shape> imported = new ArrayList<>();
        ComplexShape complex = null;
        boolean wasPreviousComplex = false;
        for (String line : rawShapes) {
            String[] split = line.split(",");

            if (split[0].substring(0, 1).equals("x")) {
                if (complex == null) {
                    complex = new ComplexShape(0, 0);
                }
                wasPreviousComplex = true;
            } else if (wasPreviousComplex) {
                wasPreviousComplex = false;
                imported.add(complex);
                complex = null;
            }

            System.out.println(line);
            int counter = 1;
            switch (split[0]) {
                case "Circle":
                    imported.add(new Circle(line));
                    break;
                case "Line":
                    imported.add(new Line(line));
                    break;
                case "Rectangle":
                    imported.add(new Rectangle(line));
                    break;
                case "RoundedRectangle":
                    imported.add(new RoundedRectangle(line));
                    break;
                case "x_Rectangle":
                    complex.addShape(new Rectangle(line));
                    break;
                case "x_Circle":
                    complex.addShape(new Circle(line));
                    break;
                case "x_Line":
                    complex.addShape(new Line(line));
                    break;
                case "x_RoundedRectangle":
                    complex.addShape(new RoundedRectangle(line));
                    break;
                default:
                    System.out.println("WARNING: Shape on line:" + counter + " couldn't be found");
                    break;
            }
            counter++;
        }
        return imported;
    }


    @Override
    public void exportToPath(List<Shape> shapes) {

    }
}
