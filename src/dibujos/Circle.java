package dibujos;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Circle extends Ellipse2D.Double implements Dibujo{


    public void setWidth(double ratio) {
        this.width = ratio;
        this.height = ratio;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void draw(Graphics2D graphics) {

        graphics.setColor(Color.RED);
        graphics.fill(this);
        graphics.setColor(Color.BLUE);
        graphics.draw(this);
    }
}
