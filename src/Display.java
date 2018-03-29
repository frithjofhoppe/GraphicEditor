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
    private final int xDifference = 9;
    private final int yDiffernce = 39;
    private Shape current;
    private Graphics graphics;
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
                Shape selection = CaptureUtil.getShape(drawing.shapes, e.getX()-xDifference, e.getY()-yDiffernce);
                System.out.println(selection.getClass());
                if(selection != null){
                    current = selection;
//                    graphics.setColor(Color.cyan);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getX() +" "+e.getY());
                Shape selection = CaptureUtil.getShape(drawing.shapes, e.getX()-xDifference, e.getY()-yDiffernce);
                if(selection != null){
                    current = selection;

                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(e.getX() +" "+e.getY());
                if(current != null){
                    int x = e.getX()-xDifference;
                    int y = e.getY()-yDiffernce;
                    int diffX, diffY;

                    if(x >= current.getPosX1()){
                        diffX = x - current.getPosX1();
                    }else{
                        diffX = current.getPosX1() - x;
                        diffX*=-1;
                    }

                    if(y >= current.getPosY1()){
                        diffY = y - current.getPosY1();
                    } else {
                        diffY = current.getPosY1() - y;
                        diffY*=-1;
                    }
                    System.out.println("DIFF: X"+diffX + ", Y:"+diffY);
                    current.move(diffX, diffY);
                    drawing.redraw();
                    current = null;
                }
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
                    current = null;
                    CSVUtil csv = new CSVUtil();
                    csv.exportToPath(drawing.shapes);
                }else if(e.getKeyChar() == 'o'){
                    current = null;
                    CSVUtil csv = new CSVUtil();
                    csv.importFromPath().forEach(item -> {
                        drawing.add(item);
                    });
                }else if(e.getKeyChar() == 'c'){
                    drawing.deleteAll();
                }else if(e.getKeyChar() == KeyEvent.VK_DELETE){
                    if(current != null){
                        drawing.remove(current);
                        current = null;
                    }
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
                drawing.setGraphics(g);
                drawShapes(g);
                graphics = g;
            }
        });
    }

    /**
     * Zeichnet alle Figuren.
     * @param g Referenz auf das Graphics-Objekt zum zeichnen.
     */
    private void drawShapes(Graphics g) {
       drawing.drawShapes();
    }
}