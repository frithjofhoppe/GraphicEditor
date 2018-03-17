import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MousPositionListener implements MouseListener {



    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed" + e.getX() + " " + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse release" + e.getX() + " " + e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
