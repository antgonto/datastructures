package patterns.facade;

public class Circle implements Shape{
		private static Circle instance;

		 public Circle(){

		 }
	   @Override
	   public void draw() {
	      System.out.println("Circle::draw()");
	   }


	}
