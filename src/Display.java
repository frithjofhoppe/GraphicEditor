import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import forms.Shape;
import utilities.CSVUtil;
import utilities.CaptureUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.EventHandler;

public class Display extends JFrame {
    /** Die Liste der dargestellten Figur-Objekte */
    public Drawing drawing;
    private  KeyListener keyboardListener;
    private  MouseListener mousePositionListener;
    private final int xDifference = 0;
    private final int yDiffernce = 0;
    /**
     * Konstruktor. Initialisiert das Fenster in der Mitte des Bildschirms und erzeugt ein
     * JFrame-Objekt, auf welchem die Figuren gezeichnet werden.
     */
    public Display() {
        Dimension windowSize = new Dimension(800, 600);
        initListener();
        addMouseListener(mousePositionListener);
        addKeyListener(keyboardListener);
        setSize(windowSize);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int windowPositionX = (screenSize.width - windowSize.width) / 2;
        int windowPositionY = (screenSize.height - windowSize.height) / 2;
        Point windowPosition = new Point(windowPositionX, windowPositionY);
        setLocation(windowPosition);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //createAndAddDrawingPanel();
        setVisible(true);
    }

    private void initListener() {
        mousePositionListener = new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("X:"+e.getX() + " Y:" +e.getY());
                System.out.println(CaptureUtil.getShape(drawing.shapes, e.getX()-xDifference, e.getY()-yDiffernce));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

        keyboardListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 's') {
                    CSVUtil csv = new CSVUtil();
                    csv.exportToPath(drawing.shapes);
                }else if(e.getKeyChar() == 'o'){
                    CSVUtil csv = new CSVUtil();
                    csv.importFromPath().forEach(item -> {
                        drawing.add(item);
                    });
                }else if(e.getKeyChar() == 'c'){
                    drawing.deleteAll();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    public void setDrawing(Drawing drawing){
        this.drawing = drawing;
    }

    public void createAndAddDrawingPanel() {
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
    private void drawShapes(Graphics g) {
       drawing.drawShapes(g);
    }
}