public class Point {
	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if( !(o instanceof Point) ) {
			return false;
		}

		Point p = (Point) o;

		return (p.x == x) && (p.y == y);
	}

	@Override
	public int hashCode() {
		int f = this.x;
		f = f * this.y;
		f = f + this.y;

		return f;
	}
}
