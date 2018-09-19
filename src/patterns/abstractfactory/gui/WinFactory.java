package patterns.abstractfactory.gui;

public class WinFactory implements GUIFactory {
    public Button createButton() {
        return new WinButton();
    }

    public Label createLabel() {
        return new WinLabel();
    }
}
