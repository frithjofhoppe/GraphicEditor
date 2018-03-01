import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import forms.Rectanlge;
import forms.Shape;

public class Display extends JFrame {
    /** Die Liste der dargestellten Figur-Objekte */
    private ArrayList shapes = new ArrayList<Shape>();

    /**
     * Konstruktor. Initialisiert das Fenster in der Mitte des Bildschirms und erzeugt ein
     * JFrame-Objekt, auf welchem die Figuren gezeichnet werden.
     */
    public Display() {
        Dimension windowSize = new Dimension(800, 600);
        setSize(windowSize);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int windowPositionX = (screenSize.width - windowSize.width) / 2;
        int windowPositionY = (screenSize.height - windowSize.height) / 2;
        Point windowPosition = new Point(windowPositionX, windowPositionY);
        setLocation(windowPosition);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createAndAddDrawingPanel();
        setVisible(true);
    }


    private void createAndAddDrawingPanel() {
        // Das JPanel-Objekt ist ein Objekt einer anonymen Unterklasse von JPanel
        // Siehe Java-Grundkurs Abschnitt 3.9
        add(new JPanel() {
            // Die paintComponent()-Methode wird automatisch aufgerufen, wenn irgendwer die repaint()-
            // Methode beim Dsiplay aufruft.
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawShapes(g);
            }
        });
    }


    /**
     * Zeichnet alle Figuren.
     * @param g Referenz auf das Graphics-Objekt zum zeichnen.
     */
    private void zeichneFiguren(Graphics g) {
        for (Shape f : shapes) {
            if (f instanceof Rectanlge) {
                Rectanlge r = (Rectanlge) f;
                g.drawRect(r.getX(), r.getY(), r.getBreite(), r.getHoehe());
            }
      /* TODO: Hier muss für jede weitere Figur-Klasse, welche dargestellt werden können muss,
       * ein analoger Abschnitt, wie für die Rechteck-Klasse folgen.
       */
        }
    }

    /**
     * Fügt eine weitere Figur hinzu und löst die Auffrischung des Fensterinhaltes aus.
     * @param figur Referenz auf das weitere Figur-Objekt.
     */
    public void hinzufuegen(Shape figur) {
        shapes.add(figur);
        repaint();
    }

    /**
     * Löscht alle Figuren und löst die Auffrischung des Fensterinhaltes aus.
     */
    public void allesLoeschen() {
        shapes.clear();
        repaint();
    }
}