package patterns.observer;

public class BinObserver extends Observer {
    public BinObserver(Sujeto subject) {
        this.sujeto = subject;
        this.sujeto.add(this);
    }

    public void update()
    {
        System.out.print(" " + Integer.toBinaryString(sujeto.getState()));
    }
}