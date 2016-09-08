import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Arrays;

public class ColorPoint implements Comparable<ColorPoint> {
	private final Point point;
	private final Color color;
	
	public ColorPoint(int x, int y, Color color) {
		if(color == null) {
			throw new NullPointerException();
		}
		point = new Point(x, y);
		this.color = color;
	}

	public ColorPoint(Point point, Color color) {
		this.point = point;
		this.color = color;
	}

	/**
	* Returns the point-view of this color point.
	*/
	public Point asPoint() {
		return point;
	}

	/**
	* http://stackoverflow.com/questions/5396939/hashcode-and-equals-for-hashset
	*/ 

	@Override
	public boolean equals(Object o) {
		if( !(o instanceof ColorPoint) ) {
			return false;
		}

		ColorPoint cp = (ColorPoint) o;

		return ((this.point).equals(cp.point)) && ((this.color).equals(cp.color));

	}

	// Remainder omitted

	/**
	* 해쉬코드를 정의하지 않으면 같은 member variable value를 가진
	* 객체가 다른 객체로 인식된다
	* OID가 달라서 그렇다
	* member variable value가 같은 객체를 같은 객체로 인식하려면
	* hashCode를 정의해야 한다
	*/
	@Override
	public int hashCode() {
		int f = point.hashCode();
		f = f * color.hashCode();
		f = f + color.hashCode();

		return f;
	}

	/**
	* compareTo을 정의해야 TreeSet을 사용할 수 있다
	*/
	public int compareTo(ColorPoint cp) {
		int hashdiff = this.hashCode() - cp.hashCode();

		// Compare x point
		if(hashdiff > 0) {
			return 1;
		}
		if(hashdiff < 0) {
			return -1;
		}

		return 0;
	}


}

//public class ColorPoint {
//	public static void main(String[] args) {
//		if(args.length > 0) {
//			System.out.println("Hello, World!!" + args[0]);
//		} else{
//			System.out.println("Hello, World!");
//		}
//	}
//
//}
