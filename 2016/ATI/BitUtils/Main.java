/**
 * 메인 클래스
 * @author 문현수
 */
public class Main {
	public static void main(String[] args) {
		System.out.println(getUnsignedByte((byte) 0b00000101));
	} // void main()

	public static short getUnsignedByte(byte value) {
		return (short) (value & 0xFF);
	}

	public static int getUnsignedShort(short value) {
		return (int) (value & 0xFFFF);
	}

	public static long getUnsignedInt(int value) {
		return (long) (value & 0xFFFF_FFFFL);
	}
} // class Main
