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

    int prueba(int intento, int solucion) {
        int resultado = 0;
        if (intento > solucion) {
            resultado = +2;
            resultado *= 5;
        }else {
            if (intento < solucion)
                resultado = -1;
            else
                resultado = 0; // Coincidir
        }
        return resultado;
    }

    public static void main(String[] args){
        Window win = new Window();
        win.prueba(15, 10);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window().setVisible(true);
            }
        });
    }

}
