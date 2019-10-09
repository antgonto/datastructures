package patterns.abstractfactory.gui;

public abstract class GUIFactory {

    public abstract Button createButton();
    public abstract Label createLabel();

    public static GUIFactory createOsSpecificFactory(String type) {
        String osname = System.getProperty("os.name").toLowerCase();
        if(osname != null && osname.contains(type))
            return new WinFactory();
        else
            return new OSXFactory();
    }
}

