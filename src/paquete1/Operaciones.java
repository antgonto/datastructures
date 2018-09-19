package paquete1;

public class Operaciones {
    private int a = 0;
    int b = 0;

    public Operaciones(){
        this(5);
        Otra o = new Otra();
    }
    public Operaciones(int param1){
        this(param1, 10);
    }

    public Operaciones(int a, int b){
        this.a = a;
        this.b = b;
    }
    public int sumar(){return a + b;}
    public int restar(){return a - b;}

    private class Otra{

    }


}


