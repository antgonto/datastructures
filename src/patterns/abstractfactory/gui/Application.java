package patterns.abstractfactory.gui;

public class Application {
    public Application(GUIFactory factory) {
        Button button = factory.createButton();
        Label label = factory.createLabel();
        button.paint();
        label.paint();





    }
}