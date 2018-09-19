package patterns.abstractfactory.gui;

public class ApplicationRunner {
    public static void main(String[] args) {

        new Application(createOsSpecificFactory());
    }

    public static GUIFactory createOsSpecificFactory() {
        String osname = System.getProperty("os.name").toLowerCase();
        if(osname != null && osname.contains("windows"))
            return new WinFactory();
        else
            return new OSXFactory();
    }
}