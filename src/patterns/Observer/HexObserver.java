package patterns.observer;

public class HexObserver extends Observer {
    public HexObserver(Sujeto subject) {
        this.sujeto = subject;
        this.sujeto.add(this);
    }

    public void update() {
        System.out.print(" " + Integer.toHexString(sujeto.getState()));
    }
}
