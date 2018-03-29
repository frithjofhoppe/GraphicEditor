package utilities;

import forms.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVUtil {
//    private List<Shape> shapes;
//
//    public CSVUtil(List<Shape> shapes) {
//        this.shapes = shapes;
//
//    }

    public List<Shape> importFromPath() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            System.out.println("IMPORT " + file.getAbsolutePath());
            return openFile(file.getAbsolutePath());
        }
        return null;
    }

    public void exportToPath(List<Shape> shapes) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            System.out.println("EXPORT: " + file.getAbsolutePath());
            saveFile(shapes, file.getAbsolutePath());
        }
    }

    private List<Shape> openFile(String path) {
        ArrayList<Shape> imported = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            ComplexShape complex = null;
            boolean wasPreviousComplex = false;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");

                if(split[0].substring(0,1).equals("x")){
                    if(complex == null){
                        complex = new ComplexShape(0,0);
                    }
                    wasPreviousComplex = true;
                }else if(wasPreviousComplex){
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
            if(wasPreviousComplex){
                imported.add(complex);
            }
            System.out.println("CSV: SUCCESSFULLY IMPORTED");
            JOptionPane.showMessageDialog(null, "Shapre were successfully imported");
            return imported;
        } catch (IOException ex) {
            System.out.println("CSV: Failure ocurred during IMPORT");
        }
        return null;
    }

    private void saveFile(List<Shape> shapes, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Shape s : shapes) {
                bw.write(s.getShapeAsCsv());
                System.out.println(s.getShapeAsCsv());
                bw.newLine();
            }
            bw.close();
            System.out.println("CSV: SUCCESSFULLY EXPORTED");
            JOptionPane.showMessageDialog(null, "Shapre were successfully exported");
        } catch (IOException ex) {
            System.out.println("CSV: Failure occured during EXPORT");
        }
    }
}
