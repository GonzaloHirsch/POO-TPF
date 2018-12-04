package game.backend;

import game.backend.element.*;

import java.awt.Point;

public enum Figure {

	// 5 vertical candies --> Bomb
	F6(new Point[]{ new Point(0,-2), new Point(0,-1), new Point(0,1), new Point(0,2)}, 240, Bomb.class, false),

	// 5 horizontal candies --> Bomb
	F15(new Point[]{ new Point(-2,0), new Point(-1,0), new Point(1,0), new Point(2,0)}, 15, Bomb.class, false),

	// 4 vertical candies --> Vertical Striped Candy
	F4(new Point[]{ new Point(0,-1), new Point(0,1), new Point(0,2)}, 112,  VerticalStripedCandy.class),
	F5(new Point[]{ new Point(0,-2), new Point(0,-1), new Point(0,1)}, 208, VerticalStripedCandy.class),

	// 4 horizontal candies --> Horizontal Striped Candy
	F13(new Point[]{ new Point(-1,0), new Point(1,0), new Point(2,0)}, 13,  HorizontalStripedCandy.class),
	F14(new Point[]{ new Point(-2,0), new Point(-1,0), new Point(1,0)}, 7, HorizontalStripedCandy.class),

	// 5 L shaped pointing right and up --> Wrapped Candy
	F7(new Point[]{ new Point(0,1), new Point(0,2), new Point(1,0), new Point(2,0)}, 60, WrappedCandy.class),

	// 5 T shaped pointing right --> Wrapped Candy
	F8(new Point[]{ new Point(0,-1), new Point(0,1), new Point(1,0), new Point(2,0)}, 92, WrappedCandy.class),

	// 5 L shaped pointing right and down --> Wrapped Candy
	F9(new Point[]{ new Point(0,-2), new Point(0,-1), new Point(1,0), new Point(2,0)}, 204, WrappedCandy.class),

	// 5 T shaped pointing up --> Wrapped Candy
	F16(new Point[]{ new Point(-1,0), new Point(1,0), new Point(0,1), new Point(0,2)}, 53, WrappedCandy.class),

	// 5 L shaped pointing left and up --> Wrapped Candy
	F17(new Point[]{ new Point(-2,0), new Point(-1,0), new Point(0,1), new Point(0,2)}, 51, WrappedCandy.class),

	// 5 L shaped pointing left and down --> Wrapped Candy
	F18(new Point[]{ new Point(-2,0), new Point(-1,0), new Point(0,-1), new Point(0,-2)}, 195, WrappedCandy.class),

	// 5 T shaped pointing left --> Wrapped Candy
	F19(new Point[]{ new Point(-2,0), new Point(-1,0), new Point(0,-1), new Point(0,1)}, 83, WrappedCandy.class),

	// 5 T shaped pointing down --> Wrapped Candy
	F20(new Point[]{ new Point(-1,0), new Point(1,0), new Point(0,-2), new Point(0,-1)}, 197, WrappedCandy.class),

	// 3 vertical candies --> No special
	F1(new Point[]{new Point(0,1), new Point(0,2)}, 48),
	F2(new Point[]{new Point(0,-1), new Point(0,1)}, 80),
	F3(new Point[]{new Point(0,-1), new Point(0,-2)}, 192),

	// 3 horizontal candies --> No special
	F10(new Point[]{ new Point(1,0), new Point(2,0)}, 12),	
	F11(new Point[]{ new Point(-1,0), new Point(1,0)}, 5),	
	F12(new Point[]{ new Point(-2,0), new Point(-1,0)}, 3),

	//	Figure representing a Fruit
	F21(Fruit.getFruitValue());
	
	
	private Point[] points;
	private int value;
	private Class<?> replacementClass;
	private boolean isCandyRepl = true;

	Figure(Point[] points, int value, Class<?> replacementClass) {
		this.points = points;
		this.value = value;
		this.replacementClass = replacementClass;
	}
	
	Figure(Point[] points, int value, Class<?> replacementClass, boolean isCandyRepl) {
		this.points = points;
		this.value = value;
		this.replacementClass = replacementClass;
		this.isCandyRepl = isCandyRepl;
	}
	
	Figure(Point[] points, int value) {
		this.points = points;
		this.value = value;
		this.replacementClass = null;
	}

	Figure(int value){
		this.points = null;
		this.value = value;
		this.replacementClass = null;
	}
	
	public Point[] getPoints() {
		return points;
	}
	
	public int size() {
		return points.length;
	}

	public boolean hasReplacement() {
		return replacementClass != null;
	}
	
	public boolean matches(int acum) {
		return value == (value & acum);
	}
	
	public Element generateReplacement(CandyColor color) {
		try {
			Element e;
			e = (Element) replacementClass.newInstance();
			if (isCandyRepl) {
				((Candy)e).setColor(color);
			} 
			return e;
		} catch(Exception e) {
		}
		return null;
	}	
}
