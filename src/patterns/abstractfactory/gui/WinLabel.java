package patterns.abstractfactory.gui;

public class WinLabel implements Label {
    public void paint() {
        System.out.println("I'm a WinLabel");
    }
}