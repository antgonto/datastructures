package dibujos;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Rectangle2D.Double implements Dibujo{
    // aslkfjalñfjañlkfjañls
    /*
    asfljasldfjaldfjañl
    asdfñlasdjfañlsdfjkñaaf
    asdñlfjasdñlfjañl
     */


    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
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
