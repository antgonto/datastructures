package patterns.abstractfactory.gui;

public class OSXLabel implements Label {
    public void paint() {
        System.out.println("I'm an OSXLabel");
    }
}