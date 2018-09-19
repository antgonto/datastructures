package patterns.abstractfactory.drawings;

public class Red implements Color {
	   @Override
	   public void fill(Shape s) {
	      System.out.println("s - Inside Red::fill() method.");
	   }
}