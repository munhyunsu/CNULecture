//public class ColorPoint {
//	private final Point point;
//	private final Color color;
//	
//	public ColorPoint(int x, int y, Color color) {
//		if(color == null) {
//			throw new NullPointerException();
//		}
//		point = new Point(x, y);
//		this.color = color;
//	}
//
//	/**
//	* Returns the point-view of this color point.
//	*/
//	public Point asPoint() {
//		return point;
//	}
//
//	@Override
//	public boolean equals(Object o) {
//		// (1)
//	}
//
//	// Remainder omitted
//}

public class ColorPoint {
	public static void main(String[] args) {
		if(args.length > 0) {
			System.out.println("Hello, World!!" + args[0]);
		} else{
			System.out.println("Hello, World!");
		}
	}

}
