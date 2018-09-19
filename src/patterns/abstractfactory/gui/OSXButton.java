package patterns.abstractfactory.gui;

public class OSXButton implements Button {
    public void paint() {
        System.out.println("I'm an OSXButton");
    }
}