import forms.Circle;
import forms.Rectanlge;
import forms.Shape;

public class Main {

    public static final Display DISPLAY = new Display();

    public static void main(String[] args) throws InterruptedException {
        DISPLAY.add(new Rectanlge(10,10,50,100));
        DISPLAY.add(new Circle(20,20,200));


        Shape f = new Circle(12,12, 20);
        f = new Rectanlge(33,33,12,12);

        int value  = 0;
        int direction = 20;

        while(true){


            if(value == 10){
               if(direction == 20){
                   direction = -20;
               }
               else {
                   direction = 20;
               }
               value = 0;
            }

            Thread.sleep(100);


            for(Shape s: DISPLAY.shapes){
                s.move(direction, direction);
            }


            DISPLAY.repaint();

            value++;
        }
    }
}
