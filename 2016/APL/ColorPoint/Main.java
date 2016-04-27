public class Main {
	public static void main(String[] args) {
//		if(args.length > 0) {
//			System.out.println("Hello, World!!" + args[0]);
//		} else{
//			System.out.println("Hello, World!");
//		}

		Point p = new Point(1, 2);
		ColorPoint cp = new ColorPoint(1, 2, Color.RED);

		System.out.println( p.equals(cp) );
		System.out.println( cp.equals(p) );



	}
}

