package utilities;

import forms.Shape;

import java.util.List;

public interface ICSVUtil {

    List<Shape> importFromPath();

    void exportToPath(List<Shape> shapes);
}

