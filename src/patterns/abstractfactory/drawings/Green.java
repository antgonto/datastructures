package patterns.abstractfactory.drawings;

public class Green implements Color {
	@Override
	public void fill(Shape s) {
		System.out.println("s - Inside Green::fill() method.");
		
	}
}