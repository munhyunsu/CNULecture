import java.util.Collection;
import java.util.LinkedList;

/**
 * 메인 클래스
 * @author 문현수
 */
public class Main {
	public static void main(String[] args) {
		A<Object> B = new A<Object>() {
			public void shoutA(Object object) {
				System.out.println("\t" + object);
			}
		};

		Collection<Object> collection = new LinkedList<Object>();
		collection.add(new String("First"));
		collection.add(new String("Second"));
		collection.add(new String("Third"));
		collection.add(new String("Fourth"));
		collection.add(new Integer(5));
		System.out.println(writeAll(collection, B));

		Collection<Integer> collection2 = new LinkedList<Integer>();
		collection2.add(new Integer(1));
		collection2.add(new Integer(2));
		collection2.add(new Integer(3));
		collection2.add(new Integer(4));
//		writeAll2(collection2, B);
	} // void main()

	public interface A<T> {
		public void shoutA(T t);
	} // interface A<T>

	public static <T> T writeAll(Collection<? extends T> coll, A<? super T> a) {
		T last = null;
		for (T t : coll) {
			last = t;
			a.shoutA(last);
		} // for
		return last;
	} // <T> T writeAll()
//	public static <? extends T> T writeAll2(Collection<? extends T> coll, A<? extends T> a) {
//		T last = null;
//		for (T t : coll) {
//			last = t;
//			a.shoutA(last);
//		} // for
//		return last;
//	} // <T> T writeAll()
} // class Main
