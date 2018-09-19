package patterns.abstractfactory.gui;

public class WinButton implements Button {
    public void paint() {
        System.out.println("I'm a WinButton");
    }
}