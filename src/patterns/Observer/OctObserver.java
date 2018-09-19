package patterns.observer;

public class OctObserver extends Observer {
    public OctObserver(Sujeto sujeto) {
        this.sujeto = sujeto;
        this.sujeto.add( this );
    }

    public void update() {
        System.out.print(" " + this.sujeto);
    }
}