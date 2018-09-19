package dibujos;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window(){
        setTitle("Welcome to Data Structures I");
        setSize(600, 400);
        setLocation(200, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Canvas canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(500,300));
        Container container = this.getContentPane();
        container.add(canvas);
        pack();
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window().setVisible(true);
            }
        });
    }

}
