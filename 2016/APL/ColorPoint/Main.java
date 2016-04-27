import java.util.List;
import java.util.ArrayList;
import java.util.AbstractSet;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {

		/**
		* 테스트용 코드
		*/
//		if(args.length > 0) {
//			System.out.println("Hello, World!!" + args[0]);
//		} else{
//			System.out.println("Hello, World!");
//		}


		/**
		* 포인트, 컬러포인트 이퀄 확인
		*/
//		Point p = new Point(1, 2);
//		ColorPoint cp = new ColorPoint(1, 2, Color.RED);
//
//		System.out.println( p.equals(cp) );
//		System.out.println( cp.equals(p) );

		/**
		* 해쉬셋 구현
		*/
		Set<ColorPoint> colorPointSet = new HashSet<ColorPoint>();
		colorPointSet.add(new ColorPoint(1, 3, Color.RED));
		colorPointSet.add(new ColorPoint(1, 3, Color.RED));

		Iterator it = colorPointSet.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}

		System.out.println( Color.RED.hashCode() );

		Set<ColorPoint> colorPointSet2 = new TreeSet<ColorPoint>();
		colorPointSet2.add(new ColorPoint(1, 3, Color.RED));
		colorPointSet2.add(new ColorPoint(1, 3, Color.RED));

		it = colorPointSet2.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}

		Set<ColorPoint> colorPointSet3 = blueSet(colorPointSet2);

		it = colorPointSet3.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public static Set<ColorPoint> blueSet(Set<ColorPoint> original) {
		ColorPoint[] value_array = original.toArray(new ColorPoint[0]);
		List<ColorPoint> values = new ArrayList<ColorPoint>(Arrays.asList(value_array));

		Set<ColorPoint> aset = new AbstractSet<ColorPoint>() {
			ArrayList<ColorPoint> al = new ArrayList<ColorPoint>();

			@Override
			public boolean add(ColorPoint cp) {
				al.add(cp);
				return true;
			}

			@Override
			public Iterator<ColorPoint> iterator() {
				return al.iterator();
			}

			@Override
			public int size() {
				return al.size();
			}
		};
		
		Iterator it = original.iterator();
		while(it.hasNext()) {
			ColorPoint cp = (ColorPoint)it.next();
			ColorPoint temp = new ColorPoint( cp.asPoint(), Color.BLUE );
			aset.add( temp );
		}

		return aset;
	}

}

