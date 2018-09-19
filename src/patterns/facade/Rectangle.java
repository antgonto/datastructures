package patterns.facade;

import patterns.facade.Shape;

public class Rectangle implements Shape {

	   @Override
	   public void draw() {
	      System.out.println("Rectangle::draw()");
	   }
}