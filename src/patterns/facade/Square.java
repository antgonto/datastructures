package patterns.facade;

import patterns.facade.Shape;

public class Square implements Shape {

	   @Override
	   public void draw() {
	      System.out.println("Square::draw()");
	   }
	}