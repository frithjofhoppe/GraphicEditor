import forms.*;
import forms.Rectangle;
import forms.Shape;
import utilities.CSVUtil;
import utilities.CaptureUtil;
import utilities.ICSVUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Display extends JFrame {
    /**
     * Die Liste der dargestellten Figur-Objekte
     */
    public Drawing drawing;
    private KeyListener keyboardListener;
    private MouseListener mousePositionListener;
    private final int xDifference = 8; //9
    private final int yDifference = 79; //39
    private Shape currentMove;
    private Shape currentClick;
    private Shape toCreate;
    private int toCreateX1;
    private int toCreateY1;
    private int toCreateX2;
    private int toCreateY2;
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
        getContentPane().add(toolBar(), BorderLayout.NORTH);
        setVisible(true);
        setFocusable(true);
    }

    private void initListener() {
        mousePositionListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("X:" + e.getX() + " Y:" + e.getY());
                    Shape selection = CaptureUtil.getShape(drawing.shapes, e.getX() - xDifference, e.getY() - yDifference);

                    if (selection != null) {
                        System.out.println(selection.getClass());
                        currentClick = selection;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getX() + " " + e.getY());

                if (toCreate != null) {
                    toCreateX1 = e.getX() - xDifference;
                    toCreateY1 = e.getY() - yDifference;
                } else {

                    Shape selection = CaptureUtil.getShape(drawing.shapes, e.getX() - xDifference, e.getY() - yDifference);
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (selection != null) {
                            currentMove = selection;
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(e.getX() + " " + e.getY());
                if (toCreate != null) {
                    toCreateX2 = e.getX() - xDifference;
                    toCreateY2 = e.getY() - yDifference;
                    System.out.println(toCreate.getClass());
                    if (toCreate instanceof Line) {
                        toCreate = new Line(toCreateX1, toCreateY1, toCreateX2, toCreateY2);
                    }  else if (toCreate instanceof RoundedRectangle) {
                        toCreate = new RoundedRectangle(Math.min(toCreateX1, toCreateX2), Math.min(toCreateY2, toCreateY1), Math.abs(toCreateY1-toCreateY2), Math.abs(toCreateX1 - toCreateX2), 50, 50);
                    }else if (toCreate instanceof Rectangle) {
                        toCreate = new Rectangle(Math.min(toCreateX1, toCreateX2), Math.min(toCreateY2, toCreateY1), Math.abs(toCreateY1-toCreateY2), Math.abs(toCreateX1 - toCreateX2));
                    } else if (toCreate instanceof Circle) {
                        toCreate = new Circle(toCreateX1, toCreateY1, (int)ShapeUtil.getDistance(toCreateX1, toCreateY1, toCreateX2, toCreateY2));
                    }
                    drawing.add(toCreate);
                    toCreate = null;
                    repaint();
                } else {
                    if (currentMove != null) {
                        int x = e.getX() - xDifference;
                        int y = e.getY() - yDifference;
                        int diffX, diffY;

                        if (x >= currentMove.getPosX1()) {
                            diffX = x - currentMove.getPosX1();
                        } else {
                            diffX = currentMove.getPosX1() - x;
                            diffX *= -1;
                        }

                        if (y >= currentMove.getPosY1()) {
                            diffY = y - currentMove.getPosY1();
                        } else {
                            diffY = currentMove.getPosY1() - y;
                            diffY *= -1;
                        }
                        System.out.println("DIFF: X" + diffX + ", Y:" + diffY);
                        currentMove.move(diffX, diffY);
                        repaint();
                        currentMove = null;
                    }
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
                    ICSVUtil csv = new CSVUtil();
                    csv.exportToPath(drawing.shapes);
                } else if (e.getKeyChar() == 'o') {
                    currentMove = null;
                    currentClick = null;
                    ICSVUtil csv = new CSVUtil();
                    csv.importFromPath().forEach(item -> {
                        drawing.add(item);
                    });
                    repaint();
                } else if (e.getKeyChar() == 'c') {
                    drawing.deleteAll();
                    repaint();
                } else if (e.getKeyChar() == KeyEvent.VK_DELETE) {
                    if (currentClick != null) {
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

    public void setDrawing(Drawing drawing) {
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
        }, BorderLayout.CENTER);
    }

    /**
     * Zeichnet alle Figuren.
     *
     * @param g Referenz auf das Graphics-Objekt zum zeichnen.
     */
    private void drawShapes(Graphics g) {
        drawing.drawShapes();
    }

    private JToolBar toolBar() {
        JToolBar bar = new JToolBar("Main");
        bar.addKeyListener(keyboardListener);
        JButton btnLine = toolBarButton("images/line.png");
        JButton btnOval = toolBarButton("images/oval.png");
        JButton btnRectangle = toolBarButton("images/rectangle.png");
        JButton btnRoundedRectangle = toolBarButton("images/rounded_rectangle.png");
        JButton btnClearDisplay = toolBarButton("images/eraser.png");
        JButton btnImportFile = toolBarButton("images/import.png");
        JButton btnExportFile = toolBarButton("images/export.png");
        JButton btnDeleteForm = toolBarButton("images/delete.png");

        btnLine.addActionListener(e -> {
            this.toCreate = new Line(0, 0, 0, 0);
        });
        btnOval.addActionListener(e -> {
            this.toCreate = new Circle(0, 0, 0);
        });
        btnRectangle.addActionListener(e -> {
            this.toCreate = new Rectangle(0, 0, 0, 0);
        });
        btnRoundedRectangle.addActionListener(e -> {
            this.toCreate = new RoundedRectangle(0, 0, 0, 0, 0, 0);
        });

        btnClearDisplay.addActionListener(e -> {
            drawing.deleteAll();
            repaint();
        });

        btnExportFile.addActionListener(e -> {
            currentMove = null;
            currentClick = null;
            ICSVUtil csv = new CSVUtil();
            csv.exportToPath(drawing.shapes);
        });

        btnImportFile.addActionListener(e -> {
            drawing.deleteAll();
            currentMove = null;
            currentClick = null;
            ICSVUtil csv = new CSVUtil();
            csv.importFromPath().forEach(item -> {
                drawing.add(item);
            });
            repaint();
        });

        btnDeleteForm.addActionListener(e -> {
            if (currentClick != null) {
                drawing.remove(currentClick);
                repaint();
                currentClick = null;
                currentMove = null;
            }
        });

        bar.add(btnLine);
        bar.add(btnOval);
        bar.add(btnRectangle);
        bar.add(btnRoundedRectangle);
        bar.add(btnClearDisplay);
        bar.add(btnImportFile);
        bar.add(btnExportFile);
        bar.add(btnDeleteForm);


        return bar;
    }

    private JButton toolBarButton(String realtivePath) {
        JButton button = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource(realtivePath));
            button.setIcon(new ImageIcon(img));
        } catch (IOException exception) {
            System.out.println(exception);
        }
        return button;
    }
}