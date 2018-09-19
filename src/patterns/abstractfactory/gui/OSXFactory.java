package patterns.abstractfactory.gui;

public class OSXFactory implements GUIFactory {
    public Button createButton() {
        return new OSXButton();
    }

    public Label createLabel() {
        return new OSXLabel();
    }
}