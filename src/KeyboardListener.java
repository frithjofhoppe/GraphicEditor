import org.omg.CORBA.Environment;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    boolean isCTRLPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(isCTRLPressed) {
            System.out.println("CTRL " + e.getKeyChar());
            switch (e.getKeyChar()) {
                case 's':
                    System.out.println("pressed"); ;break;
                case 'o': break;
                case 'q': System.exit(0); break;
                default:break;
            }
        }else{
            switch (e.getKeyChar()){
                case 'c': break;
                case 'l': break;
                case 'r': break;
                case 'a': break;
                default:break;
            }
        }
        isCTRLPressed = e.isControlDown();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
