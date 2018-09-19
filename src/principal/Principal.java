package principal;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class Principal {



    public static void main(String args[]){
        LinkedList<String> lista = new LinkedList<>();

        lista.add("Mariano");
        lista.add("Romario");
        lista.add("Álvaro");

        System.out.println(lista.get(1));

/*        Operaciones instancia1 =  new Operaciones(8);
        Operaciones instancia2 =  new Operaciones();
        Operaciones instancia3 =  new Operaciones();

        int resultado1 = instancia1.sumar();
        int resultado2 = instancia2.sumar();
        int resultado3= instancia3.sumar();
        System.out.println("Los resultados de las sumas son: "  );
        System.out.println("--: "  + resultado1);
        System.out.println("--: "  + resultado2);
        System.out.println("--: "  + resultado3);*/


    }
}
