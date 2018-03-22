package utilities;

import forms.Shape;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class CSVUtil {
    private List<Shape> shapes;

    public CSVUtil(List<Shape> shapes) {
        this.shapes = shapes;

    }

    private List<String> createContent(){

    }

    public void importFromPath(){
        JFileChooser fileChooser = new JFileChooser();
        if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            System.out.println(file.getAbsolutePath());
        }
    }

    public void exportToPath() {
        JFileChooser fileChooser = new JFileChooser();
        if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            System.out.println(file.getAbsolutePath());
        }
    }
}
