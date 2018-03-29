import forms.Shape;
import utilities.CSVUtil;
import utilities.CaptureUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Display extends JFrame {
    /** Die Liste der dargestellten Figur-Objekte */
    public Drawing drawing;
    private  KeyListener keyboardListener;
    private  MouseListener mousePositionListener;
    private final int xDifference = 9;
    private final int yDiffernce = 39;
    private Shape currentMove;
    private Shape currentClick;
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
        setVisible(true);
    }

    private void initListener() {
        mousePositionListener = new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("X:" + e.getX() + " Y:" + e.getY());
                    Shape selection = CaptureUtil.getShape(drawing.shapes, e.getX() - xDifference, e.getY() - yDiffernce);

                    if (selection != null) {
                        System.out.println(selection.getClass());
                        currentClick = selection;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getX() +" "+e.getY());
                Shape selection = CaptureUtil.getShape(drawing.shapes, e.getX()-xDifference, e.getY()-yDiffernce);
                if(e.getButton() == MouseEvent.BUTTON1) {
                    if (selection != null) {
                        currentMove = selection;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(e.getX() +" "+e.getY());
                if(currentMove != null){
                    int x = e.getX()-xDifference;
                    int y = e.getY()-yDiffernce;
                    int diffX, diffY;

                    if(x >= currentMove.getPosX1()){
                        diffX = x - currentMove.getPosX1();
                    }else{
                        diffX = currentMove.getPosX1() - x;
                        diffX*=-1;
                    }

                    if(y >= currentMove.getPosY1()){
                        diffY = y - currentMove.getPosY1();
                    } else {
                        diffY = currentMove.getPosY1() - y;
                        diffY*=-1;
                    }
                    System.out.println("DIFF: X"+diffX + ", Y:"+diffY);
                    currentMove.move(diffX, diffY);
                    repaint();
                    currentMove = null;
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
                    currentMove = null;
                    currentClick = null;
                    CSVUtil csv = new CSVUtil();
                    csv.exportToPath(drawing.shapes);
                }else if(e.getKeyChar() == 'o'){
                    currentMove = null;
                    currentClick = null;
                    CSVUtil csv = new CSVUtil();
                    csv.importFromPath().forEach(item -> {
                        drawing.add(item);
                    });
                    repaint();
                }else if(e.getKeyChar() == 'c'){
                    drawing.deleteAll();
                    repaint();
                }else if(e.getKeyChar() == KeyEvent.VK_DELETE){
                    if(currentClick != null){
                        drawing.remove(currentClick);
                        repaint();
                        currentClick = null;
                        currentMove = null;
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