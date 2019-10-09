package patterns.abstractfactory.gui;

public class ApplicationRunner {
    public static void main(String[] args) {

        GUIFactory factory = GUIFactory.createOsSpecificFactory("osx");
        Button button = factory.createButton();
        Label label = factory.createLabel();
        button.paint();
        label.paint();
        //Application app = new Application(factory);
    }


}