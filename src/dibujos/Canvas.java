package dibujos;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class Canvas extends JComponent {
    private	Graphics2D    doblebuff =   null;
    private BufferedImage imagedb   =	null;

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        imagedb = (BufferedImage) this.createImage(1024, 768);
        doblebuff = (Graphics2D) imagedb.createGraphics();

        Object oldAAKey = doblebuff.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        Object oldTAKey = doblebuff.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
        doblebuff.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        doblebuff.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        Rectangle r = new Rectangle();
        r.setX(100);
        r.setY(100);
        r.setHeight(20);
        r.setWidth(60);


        Circle c = new Circle();
        c.setX(250);
        c.setY(100);
        c.setWidth(40);

        doblebuff.setColor(Color.BLUE);
        Line2D line = new Line2D.Double(r.getX() + r.getWidth() / 2, r.getY() + r.getHeight() / 2, c.getX()+ c.getWidth() / 2, c.getY() + c.getWidth() / 2);
        doblebuff.draw(line);

        r.draw(doblebuff);
        c.draw(doblebuff);

        doblebuff.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        doblebuff.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, oldTAKey);
        doblebuff.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldAAKey);
        g2.drawImage(imagedb, 0, 0, this);
        super.paintComponent(g2);
    }
}
