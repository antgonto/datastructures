package dibujos;

import java.awt.*;

public class Figura implements Dibujo{
    private double x = 0;
    private double y = 0;
    private double area = 0;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getArea() {
        return area;
    }

    @Override
    public void draw(Graphics2D graphics2D) {

    }
}
