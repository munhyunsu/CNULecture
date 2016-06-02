/**
 * 메인 클래스
 * @author 문현수
 */
public class Main {
	public static void main(String[] args) {
		int tInt01 = Integer.MIN_VALUE;
		int tInt02 = 0xFFFF_FFFF;

		System.out.println(((long) tInt01) & 0xFFFF_FFFFL );
		System.out.println(~0xFFFF_FFFFL);
		System.out.println(0x7FFF_FFFFL);
		System.out.println(tInt01 % 0xFFFF_FFFFL);

		System.out.println(Integer.toUnsignedLong(tInt02));
		System.out.println(0xFFFF_FFFFL);
		System.out.println(Integer.toUnsignedLong(tInt02) % 0xFFFF_FFFFL);

	} // void main()
} // class Main
